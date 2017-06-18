package lapr4.red.s3.ipc.n1150385.groupchat.actions;

import csheets.ui.ctrl.BaseAction;
import lapr4.red.s3.ipc.n1150385.groupchat.ChatRoom;
import lapr4.red.s3.ipc.n1150385.groupchat.ChatRoomServer;

import java.awt.event.ActionEvent;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class DisconnectAction extends BaseAction {

    public static final String ACTION_NAME = "Disconnect";

    private final ChatRoom room;

    public DisconnectAction(ChatRoom room){
        this.room = room;
    }

    @Override
    protected String getName() {
        return ACTION_NAME;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        room.terminate();
    }
}
