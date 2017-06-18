/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ImportDatabase.presentation;

import csheets.CleanSheets;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.FocusOwnerAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import static javax.swing.Action.SMALL_ICON;
import javax.swing.ImageIcon;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ImportFromDatabaseAction extends FocusOwnerAction {

    /**
     * The user interface controller
     */
    protected UIController uiController;
    /**
     * The CleanSheets application
     */
    protected CleanSheets app;
    
    public ImportFromDatabaseAction(UIController uiController) {
        this.uiController = uiController;
    }
    
    @Override
    protected String getName() {
        return "Import data from Database";
    }
    
    protected void defineProperties(){
        putValue(SMALL_ICON, new ImageIcon(CleanSheets.class.getResource("res/img/importDatabase.png")));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (focusOwner == null) {
            return;
        }
        new ImportFromDatabaseUI(uiController);    
    }

    
}
