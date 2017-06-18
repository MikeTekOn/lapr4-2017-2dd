package lapr4.red.s3.ipc.n1150385.groupchat.dto;

import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

import java.io.Serializable;
import java.net.InetAddress;
import java.security.InvalidParameterException;

/**
 * Simple class to request information about existing chat rooms in the network.
 *
 * Created by Ricardo Catalao (1150385)
 */
public class ChatRoomsRequestDTO implements Serializable {

    public InetAddress sender;
    public int port;

    public ChatRoomsRequestDTO(InetAddress sender, int port){
        if(sender == null){
            throw new InvalidParameterException("Sender cannot be null.");
        }
        this.sender = sender;
        this.port = port;
    }
}
