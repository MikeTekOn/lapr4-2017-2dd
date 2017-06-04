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
     * Creates an instance of the export xml menu. Adds the associated export
     * xml actions.
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
