/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT.ui;

import csheets.ui.ctrl.UIController;
import lapr4.green.s1.ipc.n1150800.importexportTXT.exportTXT.ui.ExportDataAction;
import lapr4.green.s1.ipc.n1150800.importexportTXT.importTXT.ui.ImportDataAction;

import javax.swing.*;
import java.awt.event.KeyEvent;
import lapr4.blue.s2.ipc.n1141570.importExportTxtLink.ui.UnlinkExportDataAction;
import lapr4.blue.s2.ipc.n1141570.importExportTxtLink.ui.UnlinkImportDataAction;
import lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ExportDatabase.presentation.ExportToDatabaseAction;
import lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ImportDatabase.presentation.ImportFromDatabaseAction;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ImportExportMenu extends JMenu {

    public ImportExportMenu(UIController uiController) {
        super("Import/Export");
        setMnemonic(KeyEvent.VK_I);

        // Adds font actions
        add(new ImportDataAction(uiController));
        add(new ExportDataAction(uiController));
        add(new UnlinkImportDataAction(uiController));
        add(new UnlinkExportDataAction(uiController));
        add(new ImportFromDatabaseAction(uiController));
        add(new ExportToDatabaseAction(uiController));
    }
}
