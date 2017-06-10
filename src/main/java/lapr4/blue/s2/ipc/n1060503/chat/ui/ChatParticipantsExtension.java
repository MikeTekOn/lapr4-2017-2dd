/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1060503.chat.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * Represents an extension to support chat participants
 * 
 * @author Pedro Fernandes
 */
public class ChatParticipantsExtension extends Extension{
    
    /**
     * The name of the extension
     */
    public static final String CHAT_NAME = "Chat Participants";

    /**
     * Creates a new Chat Extension
     */
    public ChatParticipantsExtension() {
        super(CHAT_NAME);
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
        UIChatParticipantsExtension ui = new UIChatParticipantsExtension(this, uiController);
        if (ui == null) {
            throw new NullPointerException("Extension from chat can't be build.");
        }
        return ui;
    }
    
}
