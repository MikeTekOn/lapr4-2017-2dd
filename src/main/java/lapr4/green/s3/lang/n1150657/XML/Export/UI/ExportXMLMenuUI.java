/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Export.UI;

import lapr4.green.s3.lang.n1150657.XML.Export.Action.ExportWorkBookActionUI;
import csheets.ui.ctrl.UIController;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import lapr4.green.s3.lang.n1150657.XML.Export.Action.ExportSelectedCellsActionUI;
import lapr4.green.s3.lang.n1150657.XML.Export.Action.ExportSelectedColumnActionUI;
import lapr4.green.s3.lang.n1150657.XML.Export.Action.ExportSelectedRowActionUI;
import lapr4.green.s3.lang.n1150657.XML.Export.Action.ExportSelectedSpreadsheetActionUI;

/**
 *
 * @author Eric with improvements of Sofia Gonçalves (1150657)
 */
public class ExportXMLMenuUI extends JMenu {

    /**
     * Creates an instance of the export xml menu. Adds the associated export
     * xml actions.
     *
     * @param uiController the interface controller
     */
    public ExportXMLMenuUI(UIController uiController) {
        super("Export XML");

        //Key event VK_E of export
        setMnemonic(KeyEvent.VK_E);

        add(new ExportWorkBookActionUI(uiController));
        add(new ExportSelectedSpreadsheetActionUI(uiController));
        add(new ExportSelectedCellsActionUI(uiController));
        add(new ExportSelectedColumnActionUI(uiController));
        add(new ExportSelectedRowActionUI(uiController));
    }
}
