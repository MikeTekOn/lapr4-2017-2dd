package lapr4.red.s3.ipc.n1150385.groupchat.ext;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.red.s3.ipc.n1150385.groupchat.ui.ChatRoomPanel;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class UIChatRoomExtension extends UIExtension {

    /**
     * Creates a new user interface extension..
     *
     * @param extension    the extension for which components are provided
     * @param uiController the user interface controller
     */
    public UIChatRoomExtension(Extension extension, UIController uiController) {
        super(extension, uiController);
    }

    @Override
    public JComponent getSideBar() {
        ChatRoomPanel res = null;
        try {
            res = new ChatRoomPanel(extension);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
