/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1151094.columnSort;

import csheets.core.Cell;
import csheets.core.Value;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


import csheets.CleanSheets;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Eduangelo Ferreira
 */
public class ColumnSortTest {

    private final char DEFAULT_ASCENDING = '1';
    private final char DEFAULT_DESCENDING = '0';
    private CleanSheets cleansheet;
    private Workbook workbook;
    private Value[] values;
    private Cell[] column;
    private ColumnSort columnSort;



    @Before
    public void setUp() throws Exception {
        cleansheet = new CleanSheets();

        String[][] spreadsheetContent = new String[1][1];
        spreadsheetContent[0][0] = "Hello";

        workbook = new Workbook(null, spreadsheetContent);
        workbook.getSpreadsheet(0).getCell(0, 0).setContent("Isep");
        workbook.getSpreadsheet(0).getCell(0, 1).setContent("1");
        workbook.getSpreadsheet(0).getCell(0, 2).setContent("2");
        workbook.getSpreadsheet(0).getCell(0, 3).setContent("3");
        column = workbook.getSpreadsheet(0).getColumn(0);
        columnSort = new ColumnSort(column);
        values = new Value[columnSort.size()];
        columnSort.fillInVector(column, values);
    }

    @Test
    public void testAscending() throws FormulaCompilationException, IllegalValueTypeException {
        this.columnSort.selectSort(DEFAULT_ASCENDING);
        this.columnSort.sort(values);
        this.columnSort.alterCell(values);
    }

    @Test
    public void testDescending() throws FormulaCompilationException, IllegalValueTypeException {
        this.columnSort.selectSort(DEFAULT_DESCENDING);
        this.columnSort.sort(values);
        this.columnSort.alterCell(values);
    }

}
