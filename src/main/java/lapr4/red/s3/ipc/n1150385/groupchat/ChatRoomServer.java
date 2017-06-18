package lapr4.red.s3.ipc.n1150385.groupchat;

import csheets.ext.ExtensionManager;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficInputStream;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission.OpenTransmission;
import lapr4.green.s1.ipc.n1150532.comm.*;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionIDImpl;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;
import lapr4.red.s3.ipc.n1150385.groupchat.dto.ChatRoomMessageDTO;
import lapr4.red.s3.ipc.n1150385.groupchat.dto.ChatRoomsRequestDTO;
import lapr4.red.s3.ipc.n1150385.groupchat.dto.ChatRoomsResponseDTO;
import lapr4.red.s3.ipc.n1150385.groupchat.events.DisconnectEvent;
import lapr4.red.s3.ipc.n1150385.groupchat.events.MessageEvent;
import lapr4.red.s3.ipc.n1150385.groupchat.exceptions.ChatRoomConnectionRefusedException;
import lapr4.red.s3.ipc.n1150385.groupchat.exceptions.ChatRoomNameAlreadyExistsException;
import lapr4.red.s3.ipc.n1150385.groupchat.util.InetUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Observable;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class ChatRoomServer extends ChatRoom {

    /**
     * List of room names that are in use for the current host.
     */
    private static final LinkedList<String> roomNamesInUse = new LinkedList<>();

    /**
     * List of clients connected to the server.
     */
    private final HashMap<InetAddress, ChatRoomClientInfo> clients;

    /**
     * Set of client invitations.
     */
    private final HashSet<InetAddress> invitations;

    /**
     * Constructor to be used to create a new chat room.
     *
     * @param isPrivate whether or not this is a private chat room
     * @param roomName  the name to be given to this chat room
     */
    public ChatRoomServer(boolean isPrivate, String roomName) throws UnknownHostException, ChatRoomNameAlreadyExistsException {
        super(new ConnectionIDImpl(InetUtils.getOwnAddress(), CommTCPServer.getServer().provideConnectionPort()),
                true, isPrivate, roomName);
        for (String name : roomNamesInUse) {
            if (roomName.equals(name)) {
                throw new ChatRoomNameAlreadyExistsException();
            }
        }
        this.clients = new HashMap<>();
        this.clients.put(InetUtils.getOwnAddress(), new ChatRoomClientInfo(null, System.getProperty("user.name")));
        this.invitations = new HashSet<>();
        if (!isPrivate) {
            CommUDPServer.getServer().addHandler(ChatRoomsRequestDTO.class, this);
        }
        CommTCPServer.getServer().addHandler(ChatRoomMessageDTO.class, this);
    }

    @Override
    public boolean amOwner() {
        return true;
    }

    /**
     * Returns whether or not a client is connected
     *
     * @param inetAddress the address of the client
     * @return TRUE if he is connected, FALSE otherwise
     */
    public boolean isConnected(InetAddress inetAddress) {
        return clients.keySet().contains(inetAddress);
    }

    /**
     * Only the server calls this function. It sends a message from a connected client to everyone including the client
     * that sent the message, so that he knows his message was properly processed.
     *
     * @param message the message to send
     * @param sender the address of the sender
     * @throws IOException
     */
    public void sendMessage(String message, InetAddress sender) throws IOException {
        String newMessage = message;
        if(sender != null){
            if (!this.clients.containsKey(sender)) {
                return;
            }
            String clientName = clients.get(sender).name;
            newMessage = clientName + ": " + message;
        }
        ChatRoomMessageDTO response = new ChatRoomMessageDTO(ChatRoomMessageDTO.MessageType.MESSAGE,
                sender, null, getName(), newMessage);

        setChanged();
        notifyObservers(new MessageEvent(newMessage));

        for (InetAddress address : this.clients.keySet()) {
            TrafficOutputStream out = this.clients.get(address).out;
            if (out != null) {
                out.write(response);
            }
        }
    }

    /**
     * Removes the handlers for this chat room and unregisters the chat room name (for later usage)
     */
    public void terminate() {
        roomNamesInUse.remove(this.getName());
        if (!isPrivate()) {
            CommUDPServer.getServer().removeHandler(ChatRoomsRequestDTO.class, this);
        }
        CommTCPServer.getServer().removeHandler(ChatRoomMessageDTO.class, this);

        for(InetAddress address : clients.keySet()){
            try {
                ChatRoomMessageDTO message = new ChatRoomMessageDTO(ChatRoomMessageDTO.MessageType.DISCONNECT, address,
                        null, getName(), null);
                TrafficOutputStream outputStream = clients.get(address).out;
                if(outputStream != null){
                    clients.get(address).out.write(message);
                }
            } catch (IOException e) {
                // Do nothing
            }
        }

        setChanged();
        notifyObservers(new DisconnectEvent());
    }

    /**
     * Handles a connection request from a client
     *
     * @param obj the connection request the client sent
     * @param outStream the TrafficOutputStream to be used to respond to the client, either to tell him the connection7
     *                  was accepted or that it was not accepted
     * @throws IOException
     */
    protected void handleConnectionRequest(SocketEncapsulatorDTO obj, TrafficOutputStream outStream) throws IOException {
        ChatRoomMessageDTO message = (ChatRoomMessageDTO) obj.getDTO();
        InetAddress clientAddress = obj.getSocket().getInetAddress();
        if (this.clients.containsKey(clientAddress)) {
            System.err.println("=== CHAT ROOM ===: Got request from already connected client... denying new connection.");
            outStream.write(new ChatRoomMessageDTO(ChatRoomMessageDTO.MessageType.CONNECTION_REFUSE,
                    getOwner().getAddress(), this.clients.get(getOwner().getAddress()).name,
                    getName(), null));
        }else{
            System.err.println("=== CHAT ROOM ===: Connection request received.");
            if (!isPrivate() || (isPrivate() && invitations.contains(clientAddress))) {
                CommExtension ce = (CommExtension) ExtensionManager.getInstance().getExtension(CommExtension.NAME);
                CommTCPClientWorker worker = new CommTCPClientWorker(CommTCPClientsManager.getManager(),
                        clientAddress, ce.getTCPServerPortNumber(), true);
                this.clients.put(clientAddress, new ChatRoomClientInfo(worker.getObjectOutputStream(),
                        message.getSenderName()));
                outStream.write(new ChatRoomMessageDTO(ChatRoomMessageDTO.MessageType.CONNECTION_ACCEPT,
                        getOwner().getAddress(), this.clients.get(getOwner().getAddress()).name,
                        getName(), isPrivate()));
                if(isPrivate())
                    invitations.remove(clientAddress);
                sendMessage("[SERVER] " + clients.get(clientAddress).name + " connected to the server", null);
            } else {
                System.err.println("=== CHAT ROOM ===: Got request from a non-invited client... denying new connection.");
                outStream.write(new ChatRoomMessageDTO(ChatRoomMessageDTO.MessageType.CONNECTION_REFUSE,
                        getOwner().getAddress(), this.clients.get(getOwner().getAddress()).name,
                        getName(), null));
            }
        }
    }

    /**
     * Sends an invitation to a client for this chat room (private or not)
     *
     * @param client the client to send the invitation to
     * @return TRUE if the invitation was successful, FALSE otherwise.
     */
    public boolean inviteClient(InetAddress client){
        try {
            this.invitations.add(client);
            CommExtension ce = (CommExtension) ExtensionManager.getInstance().getExtension(CommExtension.NAME);
            CommTCPClientWorker worker = new CommTCPClientWorker(CommTCPClientsManager.getManager(), client,
                ce.getTCPServerPortNumber(), true);
            TrafficOutputStream out = worker.getObjectOutputStream();
            ChatRoomMessageDTO message = new ChatRoomMessageDTO(ChatRoomMessageDTO.MessageType.INVITE,
                    getOwner().getAddress(), System.getProperty("user.name"), getName(), null);
            out.write(message);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * If we are the server, the message is forwarded to everyone that is connected, otherwise, it is just handled
     * internally
     *
     * @param obj the message to handle
     * @throws IOException
     */
    protected void handleMessage(SocketEncapsulatorDTO obj) throws IOException {
        ChatRoomMessageDTO message = (ChatRoomMessageDTO) obj.getDTO();
        if (this.clients.containsKey(obj.getSocket().getInetAddress())) {
            System.err.println("=== CHAT ROOM ===: Received message from [" +
                    obj.getSocket().getInetAddress() +
                    "] " + message.getMessage());
            sendMessage((String) message.getMessage(), obj.getSocket().getInetAddress());
        }
    }

    /**
     * Handles a client disconnect or, if we are not the server, closes the chat room
     *
     * @param obj the disconnect request
     * @throws IOException
     */
    protected void handleDisconnect(SocketEncapsulatorDTO obj) throws IOException {
        InetAddress clientAdress = obj.getSocket().getInetAddress();
        if(this.clients.containsKey(clientAdress)){
            ChatRoomClientInfo clientInfo = this.clients.remove(clientAdress);
            sendMessage("[SERVER]" + clientInfo.name + " disconnected from the server", null);
        }
    }
}
