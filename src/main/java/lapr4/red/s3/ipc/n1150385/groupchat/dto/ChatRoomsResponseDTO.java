package lapr4.red.s3.ipc.n1150385.groupchat.dto;

import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionIDImpl;
import lapr4.red.s3.ipc.n1150385.groupchat.ChatRoom;

import java.io.Serializable;
import java.net.UnknownHostException;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class ChatRoomsResponseDTO implements Serializable {
    public ConnectionID owner;
    public String chatRoomName;

    public ChatRoomsResponseDTO(ChatRoom chatRoom) throws UnknownHostException {
        ConnectionID ownerInfo = chatRoom.getOwner();
        this.owner = new ConnectionIDImpl(ownerInfo.getAddress(), ownerInfo.getPortNumber());
        this.chatRoomName = chatRoom.getName();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(o != null && o instanceof ChatRoomsResponseDTO){
            ChatRoomsResponseDTO obj = (ChatRoomsResponseDTO) o;
            return obj.chatRoomName.equals(this.chatRoomName) &&
                    obj.owner.getAddress().equals(this.owner.getAddress()) &&
                    obj.owner.getAddress().getHostName().equals(this.owner.getAddress().getHostName());
        }
        return false;
    }
}
