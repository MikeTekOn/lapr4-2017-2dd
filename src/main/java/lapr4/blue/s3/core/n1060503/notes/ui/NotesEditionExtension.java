/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s3.core.n1060503.notes.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * Represents an extension to support notes edition
 * 
 * @author Pedro Fernandes
 */
public class NotesEditionExtension extends Extension{
    
    /**
     * The name of the extension
     */
    public static final String NAME = "Notes Edition";
    
     /**
     * The description of the extension
     */
    public static final String DESCRIPTION = "An extension to support notes edition.";
    
    /**
     * The first version of the conditional style extension.
     */
    public static final int VERSION = 1;
    
    /**
     * Creates a new Notes Edition Extension
     */
    public NotesEditionExtension(){
        super(NAME, VERSION, DESCRIPTION);
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
        UINotesEditionExtension ui = new UINotesEditionExtension(this, uiController);
        if (ui == null) {
            throw new NullPointerException("Extension from chat can't be build.");
        }
        return ui;
    }

    @Override
    public String metadata() {
        return NAME;
    }
    
}
