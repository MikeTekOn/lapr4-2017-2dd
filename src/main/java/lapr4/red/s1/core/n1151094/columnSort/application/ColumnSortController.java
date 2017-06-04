/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1151094.columnSort.application;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.sheet.SpreadsheetTable;
import eapli.framework.application.Controller;
import java.text.ParseException;
import lapr4.red.s1.core.n1151094.columnSort.ColumnSort;

/**
 *
 * @author Eduangelo Ferreira - 1151094
 *
 */
public class ColumnSortController implements Controller {

    private ColumnSort column;
    private Value[] values;
    private final char DEFAULT_ASCENDING = '1';
    private final char DEFAULT_DESCENDING = '0';

    /**
     *This method allows you to instantiate Column Sort through the cell vector of the selected column
     *
     * @param spreadsheetActive Spreadsheet
     * @param focusOwner SpreadsheetTable
     * @throws IllegalValueTypeException
     * @throws FormulaCompilationException
     */
    public void selectCellsOfColumn(Spreadsheet spreadsheetActive, SpreadsheetTable focusOwner) throws IllegalValueTypeException, FormulaCompilationException {

        this.column = new ColumnSort(spreadsheetActive.getColumn(focusOwner.getSelectedCell().getAddress().getColumn()));
        this.values = new Value[column.size()];
        this.column.fillInVector(spreadsheetActive.getColumn(focusOwner.getSelectedCell().getAddress().getColumn()), values);

    }

    /**
     *This method allows you to sort the contents of cells
     * @throws IllegalValueTypeException
     * @throws FormulaCompilationException
     * @throws ParseException 
     */
    public void sortAscending() throws IllegalValueTypeException, FormulaCompilationException, ParseException {
        this.column.selectSort(DEFAULT_ASCENDING);
        this.column.sort(values);
        this.column.alterCell(values);

    }

    /**
     *This method allows you to sort the contents of cells
     * @throws IllegalValueTypeException
     * @throws FormulaCompilationException
     * @throws ParseException 
     */
    public void sortDescending() throws IllegalValueTypeException, FormulaCompilationException, ParseException {
        this.column.selectSort(DEFAULT_DESCENDING);
        this.column.sort(values);
        this.column.alterCell(values);

    }

}
