package lapr4.green.s1.ipc.n1150901.search.workbook.ui;

import javax.swing.JMenu;
import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * This class implements the UI interface extension for the user story IPC03.1 -
 * Search in Another Instance. A UI interface extension must extend the
 * UIExtension abstract class.
 *
 * @see UIExtension
 * @author Miguel Silva - 1150901
 */
public class UIExtensionSearchWorkbook extends UIExtension {

    /**
     * The menu of the extension.
     */
    private SearchWorkbookMenu menu;

    public UIExtensionSearchWorkbook(Extension extension, UIController uiController) {
        super(extension, uiController);
    }

    /**
     * Returns an instance of a class that implements JMenu. 
     * @see SearchWorkbookMenu
     * @return a JMenu component
     */
    @Override
    public JMenu getMenu() {
        if (menu == null) {
            menu = new SearchWorkbookMenu(uiController);
        }
        return menu;
    }

}
