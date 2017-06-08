package lapr4.blue.s1.lang.n1151159.macros;

import csheets.CleanSheets;
import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.ui.ctrl.UIController;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the macro class.
 *
 * @author Ivo Ferro
 */
public class MacroTest {

    private CleanSheets app;

    @Before
    public void setUp() throws Exception {
        // Try to create the CS application object
        app = new CleanSheets();
        // This will create a workbook with 3 sheets
        app.create();
    }

    @Test
    public void ensureMacroResultReturnsLastExpression() throws IllegalValueTypeException, MacroCompilationException {
        final String macroText = String.format("2*2-2%n1+3%n29%n5+5");

        Spreadsheet spreadsheet = app.getWorkbooks()[0].getSpreadsheet(0);

        final MacroController macroController = new MacroController();
        final double result = macroController.executeMacro(spreadsheet, null, macroText).toDouble();

        final double expectResult = new Value(10).toDouble();

        final double EPSILON = 0.01d;
        assertEquals(result, expectResult, EPSILON);
    }

    @Test
    public void ensureAssignmentChangesCell() throws IllegalValueTypeException, MacroCompilationException {
        final String macroText = "(B2:=4+4*2)";

        Spreadsheet spreadsheet = app.getWorkbooks()[0].getSpreadsheet(0);

        final MacroController macroController = new MacroController();
        macroController.executeMacro(spreadsheet, null, macroText).toDouble();

        final double result = spreadsheet.getCell(1, 1).getValue().toDouble();
        final double expectResult = new Value(12).toDouble();

        final double EPSILON = 0.01d;
        assertEquals(result, expectResult, EPSILON);
    }

    @Test
    public void ensureMacroExecutesAllStatements() throws IllegalValueTypeException, MacroCompilationException {
        final String macroText = String.format("(A1:=4+4)%n(A2:=5*5)%n(A3:=A2-A1)");

        Spreadsheet spreadsheet = app.getWorkbooks()[0].getSpreadsheet(0);

        final MacroController macroController = new MacroController();
        macroController.executeMacro(spreadsheet, null, macroText).toDouble();

        final double resultA1 = spreadsheet.getCell(0, 0).getValue().toDouble();
        final double resultA2 = spreadsheet.getCell(0, 1).getValue().toDouble();
        final double resultA3 = spreadsheet.getCell(0, 2).getValue().toDouble();

        final double expectResultA1 = new Value(8).toDouble();
        final double expectResultA2 = new Value(25).toDouble();
        final double expectResultA3 = new Value(17).toDouble();

        final double EPSILON = 0.01d;
        assertEquals(resultA1, expectResultA1, EPSILON);
        assertEquals(resultA2, expectResultA2, EPSILON);
        assertEquals(resultA3, expectResultA3, EPSILON);
    }
}