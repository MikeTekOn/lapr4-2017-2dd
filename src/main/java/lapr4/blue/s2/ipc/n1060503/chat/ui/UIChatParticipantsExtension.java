/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1060503.chat.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 * This class implements the UI interface extension for the chat extension. A UI
 * interface extension must extend the UIExtension abstract class.
 *
 * @see UIExtension
 * @author Pedro Fernandes
 */
public class UIChatParticipantsExtension extends UIExtension {
    
    /**
     * The side bar that provides the chat.
     */
    private JComponent sideBar;
    
    public UIChatParticipantsExtension(Extension extension, UIController uiController) {
        super(extension, uiController);
    }
    
    /**
     * It returns a side bar that provides the chat.
     *
     * @return It returns a side bar.
     */
    @Override
    public JComponent getSideBar() {
        if (sideBar == null) {
            try {
                sideBar = new ChatParticipantsPanel(uiController, extension);
            } catch (IOException ex) {
                Logger.getLogger(UIChatParticipantsExtension.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sideBar;
    }
    
}
