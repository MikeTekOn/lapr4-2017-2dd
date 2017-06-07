package lapr4.green.s1.ipc.n1150901.search.workbook.ui;

import java.awt.event.ActionEvent;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import lapr4.green.s1.ipc.n1150901.search.workbook.ui.table.InstanceTableUI;

/**
 * The action necessary for the user story IPC03.1 - Search in Another Instance.
 *
 * @author Miguel Silva - 1150901
 */
public class SearchWorkbookAction extends BaseAction {

    /**
     * The user interface controller
     */
    protected UIController uiController;

    /**
     * Creates a new action.
     *
     * @param uiController the user interface controller
     */
    public SearchWorkbookAction(UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    protected String getName() {
        return "Search Workbook";
    }

    /**
     * If the user confirms then a window with the active instances in the
     * connection pops up.
     *
     * @param event the event that was fired
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        InstanceTableUI instanceTable = new InstanceTableUI();
    }
}
