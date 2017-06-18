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
import lapr4.green.s3.lang.n1150838.TablesAndFilters.domain.Row;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.domain.Table;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.domain.TableBuilder;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.exception.InvalidTableException;

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

    /**
     * Defines a table with this selectedCells
     *
     * @return true if the table was added
     */
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

    /**
     *
     * @return true if the table is available to define otherwise return null.
     * @throws InvalidTableException if the selectedCells have just 1 row
     */
    public boolean isAvailableToDefine() throws InvalidTableException {
        int flag = 0;
        int headerRow = selectedCells.get(0).getAddress().getRow();
        for (Cell selectedCell : selectedCells) {
            if (selectedCell.getAddress().getRow() != headerRow) {
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            throw new InvalidTableException("A table must have at least 2 rows and 1 column");
        }
        return ((SpreadsheetImpl) uiController.getActiveSpreadsheet()).isAvailableToDefine(selectedCells);
    }

    /**
     *
     * @param cell the given cell
     * @return the cell range of the table were the given cell is defined or
     * null if the given cell hasnt a table.
     */
    public CellRange isDefined(Cell cell) {
        return ((SpreadsheetImpl) uiController.getActiveSpreadsheet()).isDefined(cell);
    }
    /**
     * 
     * @return the active tables on the active spreadsheet
     */
    public List<Table> activeTables() {
        return ((SpreadsheetImpl) uiController.getActiveSpreadsheet()).getTables();
    }
    /**
     * 
     * @param d the given table
     * @return false if the given table wasnt removed  true otherwise
     */
    public boolean removeTable(Table d) {
        return ((SpreadsheetImpl) uiController.getActiveSpreadsheet()).removeTable(d);
    }

    /**
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    /**
     *
     * @param d the given table
     * @param formula the given formula
     * @return the list of rows from the given table that doesnt match the given
     * formula
     * @throws FormulaCompilationException
     * @throws IllegalValueTypeException
     */
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
