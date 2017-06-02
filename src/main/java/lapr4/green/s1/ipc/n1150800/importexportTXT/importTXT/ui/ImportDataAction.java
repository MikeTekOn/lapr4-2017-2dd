/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT.importTXT.ui;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ImportDataAction extends BaseAction {

    /**
     * The user interface controller
     */
    protected UIController uiController;

    public ImportDataAction(UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    protected String getName() {
        return "Import Data";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ImportDataUI ui = new ImportDataUI(uiController);
    }

}
