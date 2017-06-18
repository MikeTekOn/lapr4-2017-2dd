package lapr4.red.s3.ipc.n1150385.groupchat.dto;

import java.io.Serializable;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class UserChatRoomResponseDTO implements Serializable {

    private String clientName;

    public UserChatRoomResponseDTO(String name){
        this.clientName = name;
    }

    public String getClientName(){
        return clientName;
    }
}
