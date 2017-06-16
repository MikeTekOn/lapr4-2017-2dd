/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters.application;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.SpreadsheetImpl;
import csheets.core.formula.Expression;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;
import eapli.framework.application.Controller;
import java.util.ArrayList;
import java.util.List;
import lapr4.blue.s1.lang.n1151031.formulastools.ConditionalStyleCompiler;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.domain.DataRow;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.domain.HeaderRow;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.domain.Row;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.domain.Table;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.domain.TableBuilder;

/**
 *
 * @author nunopinto
 */
public class TableAndFiltersController implements Controller {

    /**
     * The user interface controller
     */
    private UIController uiController;
    /**
     * The table range
     */
    private CellRange range;
    /**
     * The talble
     */
    private Table table;

    /**
     *
     * Selected cells at the moment
     */
    private ArrayList<Cell> selectedCells = new ArrayList<>();

    public TableAndFiltersController(UIController uiController) {
        this.uiController = uiController;
    }

    /**
     * Updates the selected cells list and defines the current range
     *
     * @param selectedCells
     */
    public boolean setSelectedCells(ArrayList<Cell> selectedCells) {
        this.selectedCells.clear();
        Cell start = selectedCells.get(0);
        Cell end = selectedCells.get(selectedCells.size() - 1);
        range = new CellRange(start, end);
        return this.selectedCells.addAll(selectedCells);
    }

    public boolean defineTable() {

        TableBuilder builder = new TableBuilder(selectedCells);
        Table table = builder.build();
        this.table = table;
        return ((SpreadsheetImpl) uiController.getActiveSpreadsheet()).addTable(table);
    }

    /**
     * @return the range
     */
    public CellRange getRange() {
        return range;
    }

    public boolean isAvailableToDefine() {
        return ((SpreadsheetImpl) uiController.getActiveSpreadsheet()).isAvailableToDefine(selectedCells);
    }

    public CellRange isDefined(Cell cell) {
        return ((SpreadsheetImpl) uiController.getActiveSpreadsheet()).isDefined(cell);
    }

    public List<Table> activeTables() {
        return ((SpreadsheetImpl) uiController.getActiveSpreadsheet()).getTables();
    }

    public boolean removeTable(Table d) {
        return ((SpreadsheetImpl) uiController.getActiveSpreadsheet()).removeTable(d);
    }

    /**
     * @return the table
     */
    public Table getTable() {
        return table;
    }


    public List<Row> verifyFormula(Table d, String formula) throws FormulaCompilationException, IllegalValueTypeException {
        List<Row> invalidRows = new ArrayList();
        for (int i = 1; i < d.getCells().size(); i++) {
            Expression expression = null;
            DataRow row = ((DataRow) d.getRow(i));
            expression = ConditionalStyleCompiler.getInstance().compile(row.getCellAt(0), formula, uiController);
          
            if (!expression.evaluate().toBoolean()) {
                
                invalidRows.add(row);
            }
        }
        return invalidRows;
    }


}
