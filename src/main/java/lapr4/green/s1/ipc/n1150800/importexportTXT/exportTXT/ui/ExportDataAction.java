/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT.exportTXT.ui;

import csheets.ui.FileChooser;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Properties;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ExportDataAction extends BaseAction {

    /**
     * The user interface controller
     */
    protected UIController uiController;

    public ExportDataAction(UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    protected String getName() {
        return "Export Data";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Properties properties = new Properties();
        FileChooser fileChooser = new FileChooser(null, properties);
        File fileToWrite = fileChooser.getFileToSave();

        if (fileToWrite != null) {
            ExportDataUI ui = new ExportDataUI(uiController, fileToWrite);
        }
    }
}
