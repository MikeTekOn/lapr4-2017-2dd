package lapr4.green.s1.ipc.n1150901.search.workbook.ui;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import csheets.ui.ctrl.UIController;

/**
 * Representes the UI extension menu of the user story IPC03.1 - Search in
 * Another Instance.
 *
 * @author Miguel Silva - 1150901
 */
public class SearchWorkbookMenu extends JMenu {

    /**
     * Creates a new menu for the user story IPC03.1 - Search in Another
     * Instance. This constructor creates and adds the menu options.
     *
     * @param uiController the user interface controller
     */
    public SearchWorkbookMenu(UIController uiController) {
        super("Workbook");
        setMnemonic(KeyEvent.VK_S);

        // Adds font actions
        add(new SearchWorkbookAction(uiController));
    }
}
