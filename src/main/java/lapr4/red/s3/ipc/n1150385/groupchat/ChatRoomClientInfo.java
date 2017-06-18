package lapr4.red.s3.ipc.n1150385.groupchat;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class ChatRoomClientInfo {

    public TrafficOutputStream out;
    public String name;

    public ChatRoomClientInfo(TrafficOutputStream out, String name){
        this.out = out;
        this.name = name;
    }
}
