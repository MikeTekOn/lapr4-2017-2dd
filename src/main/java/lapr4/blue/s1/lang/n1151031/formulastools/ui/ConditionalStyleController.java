package lapr4.blue.s1.lang.n1151031.formulastools.ui;

import csheets.ui.ctrl.UIController;
import lapr4.blue.s1.lang.n1151031.formulastools.ConditionStylableCell;

/**
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
class ConditionalStyleController {

    /**
     * The user interface controller
     */
    private UIController uiController;

    /**
     * User interface panel
     */
    private ConditionalStylePanel uiPanel;

    /**
     * Creates a new conditional style controller.
     *
     * @param uiController the user interface controller
     * @param uiPanel the user interface panel
     */
    public ConditionalStyleController(UIController uiController, ConditionalStylePanel uiPanel) {
        this.uiController = uiController;
        this.uiPanel = uiPanel;
    }

    /**
     * Attempts to create a new condition from the given string. If successful,
     * adds the condition to the given cell. If the input string is empty or null,
     * the condition is set to null.
     *
     * @param cell the cell for which the condition should be set
     * @param conditionString the condition, as entered by the user
     * @return true if the cell's condition was changed
     */
    public boolean setCondition(ConditionStylableCell cell, String conditionString) {
        // Clears condition, if insufficient input
        if (conditionString == null || conditionString.equals("")) {
            cell.setUserCondition(null);
            return true;
        }
        // Stores the condition
        cell.setUserCondition(conditionString);
        uiController.setWorkbookModified(cell.getSpreadsheet().getWorkbook());

        return true;
    }

    /**
     * A cell is selected.
     *
     * @param cell the cell whose condition changed
     */
    public void cellSelected(ConditionStylableCell cell) {
        // Updates the text field and validates the condition, if any
        if (cell.hasCondition()) {
            uiPanel.setConditionText(cell.getUserCondition());
        } else {
            uiPanel.setConditionText("");
        }
    }
}
