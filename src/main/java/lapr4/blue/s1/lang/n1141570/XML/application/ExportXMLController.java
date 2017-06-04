package lapr4.blue.s1.lang.n1141570.XML.application;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Eric
 */
public class ExportXMLController {

    /**
     * The user interface controller
     */
    private UIController uiController;

    /**
     * Creates a new exportXML controller.
     *
     * @param uiController the user interface controller
     *
     */
    public ExportXMLController(UIController uiController) {
        this.uiController = uiController;

    }

    public boolean exportWorkbook(Workbook workbook, List<String> chosenTagNames, String selectedPath) {
        ExportXML exporterXML = new ExportXML();
        Map<Spreadsheet, List<Cell>> cellMap = new LinkedHashMap<>();
        List<Cell> cellList;

        for (int i = 0; i < workbook.getSpreadsheetCount(); i++) {
            cellList = new LinkedList<Cell>();
            //names.add(book.getSpreadsheet(i).getTitle());

            for (int j = 0; j < workbook.getSpreadsheet(i).getColumnCount() + 1; j++) {
                for (int k = 0; k < workbook.getSpreadsheet(i).getRowCount() + 1; k++) {
                    cellList.add(workbook.getSpreadsheet(i).getCell(j, k));
                }
                cellMap.put(workbook.getSpreadsheet(i), cellList);
            }

        }

        exporterXML.configureTagNames(chosenTagNames);
        exporterXML.selectRange(cellMap);
        exporterXML.selectPath(selectedPath);

        return exporterXML.export();
    }

    public boolean exportSpreadsheet(Spreadsheet spreadsheet, List<String> chosenTagNames, String selectedPath) {
        ExportXML exporterXML = new ExportXML();

        Map<Spreadsheet, List<Cell>> cellMap = new LinkedHashMap<>();
        List<Cell> cellList;

        cellList = new LinkedList<Cell>();

        for (int j = 0; j < spreadsheet.getColumnCount() + 1; j++) {
            for (int k = 0; k < spreadsheet.getRowCount() + 1; k++) {
                cellList.add(spreadsheet.getCell(j, k));
            }
        }
        cellMap.put(spreadsheet, cellList);

        exporterXML.configureTagNames(chosenTagNames);
        exporterXML.selectRange(cellMap);
        exporterXML.selectPath(selectedPath);

        return exporterXML.export();
    }

    public boolean exportSelectedCell(Map<Spreadsheet, List<Cell>> cellMap, List<String> chosenTagNames, String selectedPath) {
        ExportXML exporterXML = new ExportXML();

        exporterXML.configureTagNames(chosenTagNames);
        exporterXML.selectRange(cellMap);
        exporterXML.selectPath(selectedPath);

        return exporterXML.export();
    }

    public boolean exportSelectedColumn(Spreadsheet spreadsheet, List<String> chosenTagNames, String selectedPath, int selectedColumn) {
        ExportXML exporterXML = new ExportXML();

        Map<Spreadsheet, List<Cell>> cellMap = new LinkedHashMap<>();
        List<Cell> cellList;

        cellList = new LinkedList<Cell>();

        for (int k = 0; k < spreadsheet.getRowCount() + 1; k++) {
            cellList.add(spreadsheet.getCell(selectedColumn, k));
        }
        cellMap.put(spreadsheet, cellList);

        exporterXML.configureTagNames(chosenTagNames);
        exporterXML.selectRange(cellMap);
        exporterXML.selectPath(selectedPath);

        return exporterXML.export();
    }

    public boolean exportSelectedRow(Spreadsheet spreadsheet, List<String> chosenTagNames, String selectedPath, int selectedRow) {
        ExportXML exporterXML = new ExportXML();

        Map<Spreadsheet, List<Cell>> cellMap = new LinkedHashMap<>();
        List<Cell> cellList;

        cellList = new LinkedList<Cell>();

        for (int j = 0; j < spreadsheet.getColumnCount() + 1; j++) {
            cellList.add(spreadsheet.getCell(j, selectedRow));
        }
        cellMap.put(spreadsheet, cellList);

        exporterXML.configureTagNames(chosenTagNames);
        exporterXML.selectRange(cellMap);
        exporterXML.selectPath(selectedPath);

        return exporterXML.export();
    }
}
