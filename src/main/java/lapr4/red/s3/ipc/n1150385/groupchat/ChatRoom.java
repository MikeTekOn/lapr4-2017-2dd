package lapr4.red.s3.ipc.n1150385.groupchat;

import csheets.ext.ExtensionManager;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission.OpenTransmission;
import lapr4.green.s1.ipc.n1150532.comm.CommExtension;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;
import lapr4.red.s3.ipc.n1150385.groupchat.dto.ChatRoomMessageDTO;
import lapr4.red.s3.ipc.n1150385.groupchat.dto.ChatRoomsRequestDTO;
import lapr4.red.s3.ipc.n1150385.groupchat.dto.ChatRoomsResponseDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Observable;

/**
 * Created by Ricardo Catalao (1150385)
 */
public abstract class ChatRoom extends Observable implements CommHandler {

    /**
     * The connection's details about the owner of the chat room.
     */
    private final ConnectionID owner;

    /**
     * Whether or not we are the owner's of the chat room.
     */
    private final boolean amOwner;

    /**
     * Whether or not the chat room is private.
     */
    private boolean isPrivate;

    /**
     * The name of the chat room
     */
    private final String roomName;

    protected ChatRoom(ConnectionID owner, boolean amOwner, boolean isPrivate, String roomName){
        this.owner = owner;
        this.amOwner = amOwner;
        this.isPrivate = isPrivate;
        this.roomName = roomName;
    }

    public void setPrivate(boolean isPrivate){
        this.isPrivate = isPrivate;
    }

    public boolean isPrivate(){
        return isPrivate;
    }

    /**
     * @return Returns whether or not we are the owners of the chat room
     */
    public boolean amOwner() {
        return amOwner;
    }

    /**
     * @return Returns the name of the chat room
     */
    public String getName() {
        return this.roomName;
    }

    /**
     * @return Returns this owner's information
     */
    public ConnectionID getOwner() {
        return this.owner;
    }

    /**
     * Only the server calls this function. It sends a message from a connected client to everyone including the client
     * that sent the message, so that he knows his message was properly processed.
     *
     * @param message the message to send
     * @param sender the address of the sender
     * @throws IOException
     */
    public abstract void sendMessage(String message, InetAddress sender) throws IOException;

    /**
     * Removes the handlers for this chat room and unregisters the chat room name (for later usage)
     */
    public abstract void terminate();

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
    protected abstract void handleMessage(SocketEncapsulatorDTO obj) throws IOException;

    /**
     * Handles a client disconnect or, if we are not the server, closes the chat room
     *
     * @param obj the disconnect request
     * @throws IOException
     */
    protected abstract void handleDisconnect(SocketEncapsulatorDTO obj) throws IOException;

    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        if (dto != null) {
            if (dto instanceof PacketEncapsulatorDTO) {
                try {
                    PacketEncapsulatorDTO obj = (PacketEncapsulatorDTO) dto;
                    Object o = obj.getDTO();

                    // There is no need to check if the room is private because the constructor already checked it when adding
                    // the handler.
                    if (o != null && o instanceof ChatRoomsRequestDTO) {
                        ChatRoomsRequestDTO request = (ChatRoomsRequestDTO) o;
                        System.out.println("=== CHAT ROOM ===: Chat rooms request got. Sending chat room response to " + request.sender + "...");

                        CommExtension ce = (CommExtension) ExtensionManager.getInstance().getExtension(CommExtension.NAME);

                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        TrafficOutputStream out = new TrafficOutputStream(bos, request.sender, ce.getTCPServerPortNumber(), new OpenTransmission());
                        out.write(new ChatRoomsResponseDTO(this));
                        byte[] data = bos.toByteArray();
                        DatagramPacket udpPacket = new DatagramPacket(data, data.length, request.sender, ce.getUDPServerPortNumber());

                        DatagramSocket sock = new DatagramSocket();
                        sock.send(udpPacket);

                        System.out.println("=== CHAT ROOM ===: Sending chat response success.");
                    }
                } catch (IOException e) {
                    System.out.println("=== CHAT ROOM ===: [ERROR] Sending chat response failed.");
                    e.printStackTrace();
                }
            } else if (dto instanceof SocketEncapsulatorDTO) {
                SocketEncapsulatorDTO obj = (SocketEncapsulatorDTO) dto;
                Object o = obj.getDTO();
                if (o instanceof ChatRoomMessageDTO) {
                    ChatRoomMessageDTO message = (ChatRoomMessageDTO) o;
                    if (!message.getRoomName().equals(this.getName()))
                        return;

                    try{
                        switch (message.getType()) {
                            case CONNECTION_REQUEST:
                                handleConnectionRequest(obj, outStream);
                                break;
                            case DISCONNECT:
                                handleDisconnect(obj);
                                break;
                            case MESSAGE:
                                handleMessage(obj);
                                break;
                            default:
                                System.err.println("=== CHAT ROOM ===: Unsupported message type received.");
                                break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public Object getLastReceivedDTO() {
        return null;
    }
}
