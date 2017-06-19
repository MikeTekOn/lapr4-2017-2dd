package lapr4.green.s3.lang.n1150738.macros;

import csheets.CleanSheets;
import csheets.core.*;
import csheets.core.formula.Expression;
import csheets.core.formula.compiler.FormulaCompilationException;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;
import lapr4.green.s3.lang.n1150738.macros.compiler.Macro2Compiler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/**
 * A class to test semantic checks of the compiler
 * Created by henri on 6/18/2017.
 */
public class Macro2EvalVisitorTest {

    private static final String MACRO_SIMPLE_HEADER = "macro test1()\n";

    private CleanSheets app;

    Cell cellTest;
    Expression expression1, expression2;
    Value value1, value2;

    public Macro2EvalVisitorTest() {
    }

    @Before
    @SuppressWarnings("Duplicates")
    public void setUp() throws FormulaCompilationException {
        CleanSheets.setFlag(true);
        app = new CleanSheets();
        app.create();
        cellTest = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String content = "=2";
        String content2 = "=3";
        cellTest.setContent(content);
        value1 = cellTest.getValue();
        //Test temporary variable
        expression1 = cellTest.getFormula().getExpression();

        cellTest.setContent(content2);
        expression2 = cellTest.getFormula().getExpression();
        value2 = cellTest.getValue();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void ensureCompilerDetectsCellReference() throws MacroCompilationException, ParseException, IllegalValueTypeException {
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);
        //MacroInterpreter interpreter = new MacroInterpreter();

        String macro = MACRO_SIMPLE_HEADER+"(A$1:=2)";
        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);
        m.evaluate();

        String macro2 = MACRO_SIMPLE_HEADER+"(AA124:=2)";
        MacroWithParameters m2 = Macro2Compiler.getInstance().compile(sheet, null, macro2);

        assertEquals(Value.parseNumericValue("2"), sheet.getCell(0,0).getValue());
    }

    @Test(expected = MacroCompilationException.class)
    public void ensureCompilerRejectsInvalidCellReference() throws MacroCompilationException{
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);
       // MacroInterpreter interpreter = new MacroInterpreter();

        String macro = MACRO_SIMPLE_HEADER+"(AA:=2)";
        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);
    }

    @Test(expected = MacroCompilationException.class)
    public void ensureCompilerRejectsInvalidCellReference2() throws MacroCompilationException{
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);
       // MacroInterpreter interpreter = new MacroInterpreter();

        String macro = MACRO_SIMPLE_HEADER+"(AAAAAA12:=2)";
        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);
    }


    @Test(expected = MacroCompilationException.class)
    public void ensureCompilerRejectsInvalidParameterReference() throws MacroCompilationException{
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);
       // MacroInterpreter interpreter = new MacroInterpreter();

        String macro = macroHeader("param1, param2")+"(_local1:=param3)";
        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);
    }

    @Test(expected = MacroCompilationException.class)
    public void ensureCompilerRecognizesCorrectParameterReference() throws MacroCompilationException{
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);
        //MacroInterpreter interpreter = new MacroInterpreter();

        String macro = macroHeader("param1, param2")+"(_local1:=param1)";
        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);
    }


    private static String macroHeader(String params){
        return "macro test1(" + params + ")\n";
    }



}
