/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150657.chat.ext;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.green.s1.ipc.n1150657.chat.ui.UIChatExtension;

/**
 * It represents an extension to support a chat.
 *
 * @author Sofia Gonçalves (1150657)
 */
public class ChatExtension extends Extension {

    /**
     * The name of the extension
     */
    public static final String CHAT_NAME = "Chat";
    
    /**
     * The description of the extension
     */
    public static final String DESCRIPTION = "It represents an extension to support a chat.";
    
    /**
     * The first version of the chat extension.
     */
    public static final int VERSION = 1;

    /**
     * Creates a new Chat Extension
     */
    public ChatExtension() {
        super(CHAT_NAME, VERSION, DESCRIPTION);
    }

    /**
     * It returns the user interface extension of this extension.
     *
     * @param uiController The user interface controller.
     * @return It returns a user interface extension or NullPointerException if
     * none is provided
     */
    @Override
    public UIExtension getUIExtension(UIController uiController) {
        UIChatExtension ui = new UIChatExtension(this, uiController);
        if (ui == null) {
            throw new NullPointerException("Extension from chat can't be build.");
        }
        return ui;
    }

    @Override
    public String metadata() {
        return String.format("This is %s with version %d\n"
                + "This extension has the follow description: %s\n"
                + "This extension was made by Sofia Gonçalves in Sprint 1 and it is in the package %s",
                getName(), getVersion(), getDescription(),getClass().getName());
    }

}
