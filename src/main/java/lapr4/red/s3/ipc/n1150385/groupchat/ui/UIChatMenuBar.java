package lapr4.red.s3.ipc.n1150385.groupchat.ui;

import lapr4.red.s3.ipc.n1150385.groupchat.ChatRoom;
import lapr4.red.s3.ipc.n1150385.groupchat.ChatRoomServer;
import lapr4.red.s3.ipc.n1150385.groupchat.actions.DisconnectAction;
import lapr4.red.s3.ipc.n1150385.groupchat.actions.InviteAction;

import javax.swing.*;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class UIChatMenuBar extends JMenuBar {

    public UIChatMenuBar(ChatRoom room) {

        JMenu connection = add(new JMenu("Connection"));
        if(room.isPrivate() && room.amOwner()){
            connection.add(new InviteAction((ChatRoomServer) room));
            connection.add(new JPopupMenu.Separator());
        }
        connection.add(new DisconnectAction(room));
    }
}
