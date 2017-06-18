/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Export.Action;

import csheets.core.Cell;
import csheets.ui.ctrl.FocusOwnerAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import lapr4.green.s3.lang.n1150657.XML.Export.UI.ExportSelectedCellsUI;

/**
 *
 * @author Sofia
 */
public class ExportSelectedCellsActionUI extends FocusOwnerAction {

    /**
     * The user interface controller
     */
    protected UIController uiController;

    /**
     * Creates a new action.
     *
     * @param uiController the user interface controller
     */
    public ExportSelectedCellsActionUI(UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    protected String getName() {
        return "Selected cells";
    }

    @Override
    protected void defineProperties() {
    }

    /**
     * A simple action that presents a confirmation dialog. If the user confirms
     * then the selected cells are exported to xml
     *
     * @param event the event that was fired
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (focusOwner == null) {
            return;
        }
        Cell selectedCells[][] = focusOwner.getSelectedCells();
        ExportSelectedCellsUI exportSelectedCellsUI = new ExportSelectedCellsUI(uiController,selectedCells);

    }
}
