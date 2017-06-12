/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150690.formula;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.formula.compiler.FormulaCompilationException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class MonetaryLanguageTest {

    private CleanSheets app;

    @Before
    public void setUp() throws Exception {

        // Try to create the CS application object
        CleanSheets.setFlag(true);
        app = new CleanSheets();
        // This will create a workbook with 3 sheets
        app.create();
    }

    /**
     * Tests if expression is not malformed.
     *
     * @throws FormulaCompilationException if the formula could not be compiled
     * @throws IllegalValueTypeException if unexpected value
     */
    @Test(expected = FormulaCompilationException.class)
    public void ensureExpressionIsNotMalformed() throws FormulaCompilationException, IllegalValueTypeException {

        // Get A1 cell
        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));

        String expression = "#euro{1.20€+3.00";
        cellA1.setContent(expression);
    }

   /*@Test
    public void ensureCalculationsAreWellDone() throws FormulaCompilationException, IllegalValueTypeException {

        // Get A1 cell
        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));

        String expression = "#euro{3.00£ * 2 + 1.20€}";
        cellA1.setContent(expression);
        Double expectedResult = 8.02764;
        assertEquals(cellA1.getValue().toDouble(), expectedResult, 0.001);
    }*/

}
