package lapr4.red.s3.ipc.n1150385.groupchat.events;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class MessageEvent implements ChatRoomEvent {

    private String message;

    public MessageEvent(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
