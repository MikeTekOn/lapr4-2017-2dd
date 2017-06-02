package lapr4.blue.s1.lang.n1151452.formula;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.formula.compiler.FormulaCompilationException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 01/06/17.
 */
public class FormulaTest {

    private CleanSheets app;
    Cell cellA1;

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
    public void ensureAssignmentsLeftOperandIsACellRef() throws FormulaCompilationException, IllegalValueTypeException {

        // Get A1's formula
        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String assignment = "=(2:=3)";
        cellA1.setContent(assignment);
    }

    /**
     * Tests if cell assign's the right operand to another cell
     *
     * @throws FormulaCompilationException if the formula could not be compiled
     * @throws IllegalValueTypeException   if unexpected value
     */
    @Test
    public void ensureExpressionIsAssignedToCell() throws FormulaCompilationException, IllegalValueTypeException {

        // Get A1's formula
        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String assignment = "=(B1:=3+2)";
        cellA1.setContent(assignment);

        //Test
        Cell cellB1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 0));
        Double result = cellB1.getValue().toDouble();
        Double expResult = 5d;

        assertEquals(result, expResult);
    }

    /**
     * Tests if sequence block is not malformed.
     *
     * @throws FormulaCompilationException if the formula could not be compiled
     * @throws IllegalValueTypeException   if unexpected value
     */
    @Test(expected = FormulaCompilationException.class)
    public void ensureBlockIsNotMalformed() throws FormulaCompilationException, IllegalValueTypeException {

        // Get A1 cell
        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));

        String block = "={1+2";
        cellA1.setContent(block);
    }

    /**
     * Tests if sequence block return his last statement (ex. {2+2;3-1;2-2} should return 0).
     *
     * @throws FormulaCompilationException if the formula could not be compiled
     * @throws IllegalValueTypeException   if unexpected value
     */
    @Test
    public void ensureBlockReturnsLastStatementResult() throws FormulaCompilationException, IllegalValueTypeException {

        // Get A1 cell & define it's formula
        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String block = "={1+2;2+2;1-1}";
        cellA1.setContent(block);

        //Test
        Double result = cellA1.getValue().toDouble();
        Double expResult = 0d;
        assertEquals(result, expResult);
    }

    /**
     * Tests if all expresions of the block are executed.
     *
     * @throws FormulaCompilationException if the formula could not be compiled
     * @throws IllegalValueTypeException   if unexpected value
     */
    @Test
    public void ensureBlockExecutesAllStatements() throws FormulaCompilationException, IllegalValueTypeException {

        // Get cells & define A1's formula
        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        Cell cellA2 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 1));
        Cell cellA3 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 2));

        String block = "={(A2:=2);(A3:=3);2-1}";
        cellA1.setContent(block);

        //Test Each cell's value
        assert cellA1.getValue().toDouble() == 1d;
        assert cellA2.getValue().toDouble() == 2d;
        assert cellA3.getValue().toDouble() == 3d;
    }
}