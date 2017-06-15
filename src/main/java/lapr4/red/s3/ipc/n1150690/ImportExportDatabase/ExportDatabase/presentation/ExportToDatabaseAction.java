/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ExportDatabase.presentation;

import csheets.CleanSheets;
import csheets.ui.ctrl.FocusOwnerAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import static javax.swing.Action.SMALL_ICON;
import javax.swing.ImageIcon;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ExportToDatabaseAction extends FocusOwnerAction {

    /**
     * The user interface controller
     */
    protected UIController uiController;

    public ExportToDatabaseAction(UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    protected String getName() {
        return "Export data to Database";
    }
    
    protected void defineProperties(){
        putValue(SMALL_ICON, new ImageIcon(CleanSheets.class.getResource("res/img/exportDatabase.png")));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (focusOwner == null) {
            return;
        }
        new ExportToDatabaseUI(uiController); 
    }

}
