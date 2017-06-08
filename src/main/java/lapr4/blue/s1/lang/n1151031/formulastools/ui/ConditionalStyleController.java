package lapr4.blue.s1.lang.n1151031.formulastools.ui;

import csheets.core.Cell;
import csheets.ext.style.StylableCell;
import csheets.ui.ctrl.FocusOwnerAction;
import csheets.ui.ctrl.UIController;
import lapr4.blue.s1.lang.n1151031.formulastools.ConditionStylableCell;
import lapr4.blue.s1.lang.n1151031.formulastools.ConditionalStyleExtension;
import lapr4.blue.s1.lang.n1151031.formulastools.UserStyle;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * @author Tiago Correia - 1151031@isep.ipp.pt
 * Edited by João Cardoso - 1150943
 *  - Changed controller to be able to handle multiple cells at the same time
 *  - Also corrected the previous implementation
 *  where the userstyle was associated to this controller instead of being associated to each cell
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
     * Added by João Cardoso
     * Selected cells at the moment
     */
    private ArrayList<ConditionStylableCell> selectedCells = new ArrayList<>();

    /**
     * Creates a new conditional style controller.
     *
     * @param uiController the user interface controller
     * @param uiPanel      the user interface panel
     */
    public ConditionalStyleController(UIController uiController, ConditionalStylePanel uiPanel) {
        this.uiController = uiController;
        this.uiPanel = uiPanel;
    }

    /**
     * Edited by João Cardoso - 1150943
     * Updates the selected cells list
     * @param selectedCells
     */
    public boolean setSelectedCells(ArrayList<Cell> selectedCells) {
        this.selectedCells.clear();
        boolean result=true;
        for(Cell c : selectedCells){
            if(this.selectedCells.add((ConditionStylableCell) c.getExtension(ConditionalStyleExtension.NAME))==false){
                result = false;
            }
        }
        return result;
    }

    /**
     * Edited by João Cardoso - 1150943
     *
     * Attempts to create a new condition from the given string. If successful,
     * adds the condition to the selected cells. If the input string is empty or
     * null, the condition is set to null.
     *
     * @param conditionString the condition, as entered by the user
     * @return true if the cells' condition was changed
     */
    public boolean setCondition(String conditionString) throws RuntimeException {
        // Clears condition, if insufficient input
        if (conditionString == null || conditionString.equals("")) {
            for(ConditionStylableCell cell : selectedCells){
                cell.setUserCondition(null);
                cell.resetStyle();
                return true;
            }
        }
        // Stores the condition
        for(ConditionStylableCell cell : selectedCells) {
            String verifiedCondition = verifyCondition(conditionString,cell.getAddress().toString());
            cell.setUserCondition(verifiedCondition);
            uiController.setWorkbookModified(cell.getSpreadsheet().getWorkbook());
        }
        return true;
    }

    /**
     * Created by João Cardoso - 1150943
     * Verifies if the condition has the variable _cell that refers to the cell itself
     * if it has it, this method replaces it with the Cell coordinates
     * @param condition
     * @param cell
     * @return
     */
    private String verifyCondition(String condition,String cell) {
        return condition.replaceAll("_cell",cell);
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

    /**
     * Created by João Cardoso - 1150943
     * resets the style of the selected cells
     */
    public void resetStyle() {
        for(ConditionStylableCell cell : selectedCells) {
            cell.restoreDefaultStyle();
        }
    }

    /**
     * Created by João Cardoso - 1150943
     */
    public void setStyle(UserStyle userStyle) {
        for(ConditionStylableCell cell : selectedCells) {
            cell.setTrueStyleFont(userStyle.getTrueStyleFont());
            cell.setTrueStyleForegroundColor(userStyle.getTrueStyleForegroundColor());
            cell.setTrueStyleBackgroundColor(userStyle.getTrueStyleBackgroundColor());
            cell.setTrueStyleBorder(userStyle.getTrueStyleBorder());
            cell.setFalseStyleFont(userStyle.getFalseStyleFont());
            cell.setFalseStyleForegroundColor(userStyle.getFalseStyleForegroundColor());
            cell.setFalseStyleBackgroundColor(userStyle.getFalseStyleBackgroundColor());
            cell.setFalseStyleBorder(userStyle.getFalseStyleBorder());

        }
    }
}
