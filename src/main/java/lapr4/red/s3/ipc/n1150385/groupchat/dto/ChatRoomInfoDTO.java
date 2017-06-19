package lapr4.red.s3.ipc.n1150385.groupchat.dto;

import java.net.InetAddress;
import java.util.LinkedList;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class ChatRoomInfoDTO {

    public LinkedList<InetAddress> clients;
    public boolean isPrivate;

    public ChatRoomInfoDTO(LinkedList<InetAddress> clients, boolean isPrivate){
        this.clients = clients;
        this.isPrivate = isPrivate;
    }
}
