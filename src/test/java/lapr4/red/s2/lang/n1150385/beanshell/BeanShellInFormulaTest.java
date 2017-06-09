package lapr4.red.s2.lang.n1150385.beanshell;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.formula.compiler.FormulaCompilationException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests new expressions (Sequence Block, Assigner & For Loop)
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 01/06/17.
 */
public class BeanShellInFormulaTest {

    private CleanSheets app;

    @Before
    public void setUp() throws Exception {

        // Try to create the CS application object
        app = new CleanSheets();
        // This will create a workbook with 3 sheets
        app.create();
    }

    /**
     * Tests if assignment's left operand is a cell ref (ex. A2)
     *
     * @throws FormulaCompilationException if the formula could not be compiled
     * @throws IllegalValueTypeException   if unexpected value
     */
    @Test(expected = FormulaCompilationException.class)
    public void verifyAsynchronousDoesNotReturn() throws FormulaCompilationException, IllegalValueTypeException {
        // Get A1's formula
        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String assignment = "={B1:=3 ; <[SHELL[ return 5*2; ]]>}";
        cellA1.setContent(assignment);

        assertEquals(10d, cellA1.getValue().toDouble(), 0.001d);

        // Get B1's formula
        Cell cellB1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        assertEquals(3d, cellB1.getValue().toDouble(), 0.001d);

        assignment = "=<![SHELL[ return 5*2; ]]>";
        cellB1.setContent(assignment);
        assertNotEquals(10d, cellB1.getValue().toDouble(), 0.001d);
    }
}