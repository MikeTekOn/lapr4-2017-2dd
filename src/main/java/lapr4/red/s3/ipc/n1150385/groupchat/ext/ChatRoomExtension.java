package lapr4.red.s3.ipc.n1150385.groupchat.ext;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class ChatRoomExtension extends Extension {

    /**
     * The name of the extension
     */
    public static final String CHAT_NAME = "Chat Rooms";

    /**
     * The description of the extension
     */
    public static final String DESCRIPTION = "Chat Rooms were two or more users can talk to each other as a group";

    /**
     * The version of the extension
     */
    public static final int VERSION = 1;

    /**
     * Creates a new Chat Room extension
     */
    public ChatRoomExtension() {
        super(CHAT_NAME, VERSION, DESCRIPTION);
    }

    /**
     * Returns the user interface extension of this extension
     *
     * @param uiController the user interface controller
     * @return
     */
    @Override
    public UIExtension getUIExtension(UIController uiController) {
        return new UIChatRoomExtension(this, uiController);
    }

    @Override
    public String metadata() {
        return null;
    }
}
