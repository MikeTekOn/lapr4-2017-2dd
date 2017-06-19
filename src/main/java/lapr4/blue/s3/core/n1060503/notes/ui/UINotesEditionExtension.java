/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s3.core.n1060503.notes.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import java.io.IOException;
import java.util.logging.Level;
import javax.swing.JComponent;

/**
 * This class implements the UI interface extension for the notes edition. A UI
 * interface extension must extend the UIExtension abstract class.
 *
 * @see UIExtension
 * @author Pedro Fernandes
 */
public class UINotesEditionExtension extends UIExtension{
    
     /**
     * The side bar that provides the chat.
     */
    private JComponent sideBar;
    
    public UINotesEditionExtension(Extension extension, UIController uiController){
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
            sideBar = new NotesEditionPanel(uiController, extension);
        }
        return sideBar;
    }
    
}
