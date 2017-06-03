/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT.ui;

import lapr4.green.s1.ipc.n1150800.importexportTXT.exportTXT.ui.ExportDataAction;
import lapr4.green.s1.ipc.n1150800.importexportTXT.importTXT.ui.ImportDataAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ImportExportMenu extends JMenu {
    
    public ImportExportMenu(UIController uiController) {
		super("Import/Export TXT");
		setMnemonic(KeyEvent.VK_I);

		// Adds font actions
                add(new ImportDataAction(uiController));
                add(new ExportDataAction(uiController));
	}
}
