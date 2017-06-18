package lapr4.red.s3.ipc.n1150385.groupchat.actions;

import csheets.ui.ctrl.BaseAction;
import lapr4.red.s3.ipc.n1150385.groupchat.ChatRoom;
import lapr4.red.s3.ipc.n1150385.groupchat.ChatRoomServer;
import lapr4.red.s3.ipc.n1150385.groupchat.ui.UIChatRoomInvite;

import java.awt.event.ActionEvent;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class InviteAction extends BaseAction {

    public static final String ACTION_NAME = "Invite";

    private final ChatRoomServer roomServer;

    public InviteAction(ChatRoomServer roomServer){
        this.roomServer = roomServer;
    }

    @Override
    protected String getName() {
        return ACTION_NAME;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        new UIChatRoomInvite(roomServer);
    }
}
