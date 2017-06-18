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
import java.util.LinkedList;
import java.util.Observable;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class ChatRoomClient extends ChatRoom {

    /**
     * Information about the owner of the chat room
     */
    private final ChatRoomClientInfo ownerInfo;

    /**
     * The client TCP worker.
     */
    private final CommTCPClientWorker worker;

    /**
     * Constructor to be used to connect to an existing chat room.
     *
     * @param server
     */
    public ChatRoomClient(ConnectionID server, String roomName) throws IOException, ClassNotFoundException {
        super(server, false, true, roomName);

        worker = new CommTCPClientWorker(CommTCPClientsManager.getManager(), getOwner().getAddress(), getOwner().getPortNumber(), true);
        System.err.println("=== CHAT ROOM ===: Connecting to server at " + getOwner().getAddress() + ":" + getOwner().getPortNumber());
        TrafficOutputStream out = worker.getObjectOutputStream();
        out.writeObject(new ChatRoomMessageDTO(ChatRoomMessageDTO.MessageType.CONNECTION_REQUEST, null,
                System.getProperty("user.name"), roomName, null));

        TrafficInputStream in = worker.getObjectInputStream();
        ChatRoomMessageDTO response = (ChatRoomMessageDTO) in.readObjectOvveride();
        if (response.getType() == ChatRoomMessageDTO.MessageType.CONNECTION_ACCEPT) {
            ownerInfo = new ChatRoomClientInfo(out, null);
            setPrivate((Boolean) response.getMessage());
            CommTCPServer.getServer().addHandler(ChatRoomMessageDTO.class, this);
            System.err.println("=== CHAT ROOM ===: Connected to server. Requesting chat room details.");
        } else {
            throw new ChatRoomConnectionRefusedException();
        }
    }

    /**
     * @return Returns whether or not we are the owners of the chat room
     */
    public boolean amOwner() {
        return false;
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
        TrafficOutputStream out = this.ownerInfo.out;
        ChatRoomMessageDTO request = new ChatRoomMessageDTO(ChatRoomMessageDTO.MessageType.MESSAGE,
                null, System.getProperty("user.name"), getName(), message);
        out.write(request);
    }

    /**
     * Removes the handlers for this chat room and unregisters the chat room name (for later usage)
     */
    public void terminate() {
        ChatRoomMessageDTO message = null;
        try {
            message = new ChatRoomMessageDTO(ChatRoomMessageDTO.MessageType.DISCONNECT, null,
                    null, getName(), null);
            this.ownerInfo.out.write(message);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CommTCPServer.getServer().removeHandler(ChatRoomMessageDTO.class, this);

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
        return;
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
        if (getOwner().getAddress().equals(obj.getSocket().getInetAddress())) {
            setChanged();
            notifyObservers(new MessageEvent((String) message.getMessage()));
        }
    }

    /**
     * Handles a client disconnect or, if we are not the server, closes the chat room
     *
     * @param obj the disconnect request
     * @throws IOException
     */
    protected void handleDisconnect(SocketEncapsulatorDTO obj) throws IOException {
        this.terminate();
    }
}
