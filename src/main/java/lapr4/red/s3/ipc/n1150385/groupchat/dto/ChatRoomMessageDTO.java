package lapr4.red.s3.ipc.n1150385.groupchat.dto;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class ChatRoomMessageDTO implements Serializable {

    public enum MessageType {
        CONNECTION_REQUEST, CONNECTION_ACCEPT, CONNECTION_REFUSE, MESSAGE, INVITE, DISCONNECT
    }

    private MessageType type;
    private InetAddress senderAddress;
    private String senderName;
    private String chatRoomName;
    private Object message;

    public ChatRoomMessageDTO(MessageType type, InetAddress senderAddress, String senderName,
                              String chatRoomName,Object message) throws UnknownHostException {
        this.type = type;
        this.senderAddress = senderAddress;
        this.senderName = senderName;
        this.chatRoomName = chatRoomName;
        this.message = message;
    }

    public MessageType getType(){
        return type;
    }

    public String getRoomName(){
        return chatRoomName;
    }

    public Object getMessage(){
        return message;
    }

    public InetAddress getSenderAddress(){
        return senderAddress;
    }

    public String getSenderName(){
        return senderName;
    }
}
