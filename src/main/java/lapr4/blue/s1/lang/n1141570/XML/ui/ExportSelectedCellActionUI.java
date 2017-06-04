package lapr4.blue.s1.lang.n1141570.XML.ui;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.ui.FileChooser;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import lapr4.blue.s1.lang.n1141570.XML.application.ExportXMLController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

/**
 * @author Eric
 */
public class ExportSelectedCellActionUI extends BaseAction {

    /**
     * The user interface controller
     */
    protected UIController uiController;

    /**
     * Creates a new action.
     *
     * @param uiController the user interface controller
     */
    public ExportSelectedCellActionUI(UIController uiController) {
        this.uiController = uiController;
    }

    protected String getName() {
        return "Selected cell";
    }

    protected void defineProperties() {
    }

    /**
     * A simple action that presents a confirmation dialog. If the user confirms
     * then the selected cell is exported to xml
     *
     * @param event the event that was fired
     */
    public void actionPerformed(ActionEvent event) {

        // Lets user select a font
        int result = JOptionPane.showConfirmDialog(null, "You have selected the cell export option. Do you want to export?");

        if (result == JOptionPane.YES_OPTION) {
            List<String> chosenTagNames = new LinkedList<>();
            chosenTagNames.add("projectowner");
            chosenTagNames.add("scrumofscrums");
            chosenTagNames.add("scrum");

            Properties properties = new Properties();
            FileChooser fileChooser = new FileChooser(null, properties);
            String selectedPath = fileChooser.getFileToSave().getAbsolutePath();

            ExportXMLController exportXMLController = new ExportXMLController(uiController);
            Spreadsheet spreadsheet = uiController.getActiveSpreadsheet();

            //Export selected cell
            Map<Spreadsheet, List<Cell>> cellMap = new LinkedHashMap<>();
            List<Cell> cellList;
            cellList = new LinkedList<Cell>();

            for (int j = 0; j < spreadsheet.getColumnCount() + 1; j++) {
                for (int k = 0; k < spreadsheet.getRowCount() + 1; k++) {
                    if (uiController.getActiveCell().equals(spreadsheet.getCell(j, k))) {
                        cellList.add(spreadsheet.getCell(j, k));
                    }
                }
            }
            cellMap.put(spreadsheet, cellList);
            exportXMLController.exportSelectedCell(cellMap, chosenTagNames, selectedPath);
        }
    }
}
