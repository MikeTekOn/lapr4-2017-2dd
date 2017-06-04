/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1141570.XML.ui;

import csheets.ui.ctrl.UIController;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;

/**
 *
 * @author Eric
 */
public class ExportXMLMenuUI extends JMenu {
     /**
     * Creates an instance of the macro menu.
     * Adds the associated macro action.
     */
    public ExportXMLMenuUI(UIController uiController) {
        super("Export XML");

        //Key event VK_E of export
        setMnemonic(KeyEvent.VK_E);

        add(new ExportWorkBookActionUI(uiController));
        add(new ExportSelectedSpreadsheetActionUI(uiController));
        add(new ExportSelectedCellActionUI(uiController));
        add(new ExportSelectedColumnActionUI(uiController));
        add(new ExportSelectedRowActionUI(uiController));
    }
}
