/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Export;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.FocusOwnerAction;
import csheets.ui.ctrl.UIController;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lapr4.red.s2.lang.n1150451.multipleMacros.domain.MacroList;
import lapr4.red.s2.lang.n1150623.globalVariables.VarContentor;

/**
 *
 * @author Sofia
 */
public class ExportComponent {

    private UIController uiController;

    public ExportComponent(UIController uiController) {
        this.uiController = uiController;
    }

    public Map<Spreadsheet, List<Cell>> mapWithActiveCellsByWorkbook() {
        Map<Spreadsheet, List<Cell>> cellMap = new LinkedHashMap<>();
        List<Cell> cellList;
        Workbook workbook = uiController.getActiveWorkbook();

        for (int i = 0; i < workbook.getSpreadsheetCount(); i++) {
            cellList = new LinkedList<Cell>();

            for (int j = 0; j < workbook.getSpreadsheet(i).getColumnCount() + 1; j++) {
                for (int k = 0; k < workbook.getSpreadsheet(i).getRowCount() + 1; k++) {
                    cellList.add(workbook.getSpreadsheet(i).getCell(j, k));
                }
                cellMap.put(workbook.getSpreadsheet(i), cellList);
            }
        }
        return cellMap;
    }

    public Map<Spreadsheet, List<Cell>> mapWithActiveCellsBySpreadcheet() {
        Map<Spreadsheet, List<Cell>> cellMap = new LinkedHashMap<>();
        List<Cell> cellList;
        cellList = new LinkedList<>();
        Spreadsheet spreadsheet = uiController.getActiveSpreadsheet();

        for (int j = 0; j < spreadsheet.getColumnCount() + 1; j++) {
            for (int k = 0; k < spreadsheet.getRowCount() + 1; k++) {
                cellList.add(spreadsheet.getCell(j, k));
            }
        }
        cellMap.put(spreadsheet, cellList);

        return cellMap;
    }

    public Map<Spreadsheet, List<Cell>> mapWithActiveCell(Cell selectedCells[][]) {
        Map<Spreadsheet, List<Cell>> cellMap = new LinkedHashMap<>();
        List<Cell> cellsList;
        cellsList = new LinkedList<Cell>();
        Spreadsheet spreadsheet = uiController.getActiveSpreadsheet();
        //rows
        for (int j = 0; j < selectedCells.length; j++) {
            //columns
            for (int k = 0; k < selectedCells[j].length; k++) {
                cellsList.add(selectedCells[j][k]);
            }
        }
        cellMap.put(spreadsheet, cellsList);
        return cellMap;
    }
    
    public Map<Spreadsheet, List<Cell>> mapWithActiveCellByColumn(int selectedColumn){
        Map<Spreadsheet, List<Cell>> cellMap = new LinkedHashMap<>();
        List<Cell> cellList;
        cellList = new LinkedList<Cell>();
        Spreadsheet spreadsheet = uiController.getActiveSpreadsheet();

        for (int k = 0; k < spreadsheet.getRowCount() + 1; k++) {
            cellList.add(spreadsheet.getCell(selectedColumn, k));
        }
        cellMap.put(spreadsheet, cellList);
        return cellMap;
    }
    
    public Map<Spreadsheet, List<Cell>> mapWithActiveCellByRow(int selectedRow){
        Map<Spreadsheet, List<Cell>> cellMap = new LinkedHashMap<>();
        List<Cell> cellList;
        cellList = new LinkedList<Cell>();
        Spreadsheet spreadsheet = uiController.getActiveSpreadsheet();

        for (int j = 0; j < spreadsheet.getColumnCount() + 1; j++) {
            cellList.add(spreadsheet.getCell(j, selectedRow));
        }
        cellMap.put(spreadsheet, cellList);
        return cellMap;
    }

    public MacroList getMacroListByActiveworkbook() {
        return uiController.getActiveWorkbook().getMacroList();
    }

    public VarContentor getGlobalVariablesByActiveWorkbook() {
        return uiController.getActiveWorkbook().globalVariables();
    }

    public Spreadsheet getActiveSpreadsheetByActiveWorbkbook() {
        return uiController.getActiveSpreadsheet();
    }
}
