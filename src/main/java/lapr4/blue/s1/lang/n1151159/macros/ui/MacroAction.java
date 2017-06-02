package lapr4.blue.s1.lang.n1151159.macros.ui;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;

import java.awt.event.ActionEvent;

/**
 * Represents an action to execute a macro.
 */
public class MacroAction extends BaseAction {

    /**
     * The user interface controller
     */
    protected UIController uiController;

    /**
     * Creates a macro action.
     *
     * @param uiController the user interface controller
     */
    public MacroAction(UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    protected String getName() {
        return "Create Macro";
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MacroDialog macroDialog = new MacroDialog(uiController);
        macroDialog.setVisible(true);
    }
}
