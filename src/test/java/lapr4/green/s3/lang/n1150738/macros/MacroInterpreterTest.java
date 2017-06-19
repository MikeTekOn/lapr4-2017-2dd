package lapr4.green.s3.lang.n1150738.macros;

import csheets.CleanSheets;
import csheets.core.*;
import csheets.core.formula.Expression;
import csheets.core.formula.compiler.FormulaCompilationException;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;
import lapr4.green.s3.lang.n1150738.macros.compiler.Macro2Compiler;
import lapr4.green.s3.lang.n1150738.macros.compiler.MacroInterpreter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

/**
 * Created by henri on 6/18/2017.
 */
public class MacroInterpreterTest {

    private static final String MACRO_SIMPLE_HEADER = "macro test1()\n";
    private static final ParameterList MACRO_SIMPLE_PARAMETER_LIST = new ParameterList();

    private CleanSheets app;

    Cell cellTest;
    Expression expression1, expression2;
    Value value1, value2;

    public MacroInterpreterTest() {
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
    public void ensureMInterpreterEvaluatesSimpleValues() throws MacroCompilationException, ParseException {
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);

        String macro = MACRO_SIMPLE_HEADER+"\"1\"";
        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);
        Value result1 = (Value) m.accept(new MacroInterpreter(m, MACRO_SIMPLE_PARAMETER_LIST));
        assertEquals(Value.parseValue("1", Value.Type.TEXT), result1);

        String macro2 = MACRO_SIMPLE_HEADER+"10";
        MacroWithParameters m2 = Macro2Compiler.getInstance().compile(sheet, null, macro2);
        Value result2 = (Value) m2.accept(new MacroInterpreter(m2, MACRO_SIMPLE_PARAMETER_LIST));
        assertEquals(Value.parseNumericValue("10").toString(), result2.toString());
    }

//    @Test
//    public void ensureMInterpreterEvaluatesBinaryOperations() throws MacroCompilationException, ParseException {
//        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);
//
//        String macro = MACRO_SIMPLE_HEADER+"10-5";
//        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);
//        Value result1 = (Value) m.accept(new MacroInterpreter(m, MACRO_SIMPLE_PARAMETER_LIST));
//        assertEquals(Value.parseNumericValue("5").toString(), result1.toString());
//
//        String macro2 = MACRO_SIMPLE_HEADER+"1+2";
//        MacroWithParameters m2 = Macro2Compiler.getInstance().compile(sheet, null, macro2);
//        Value result2 = (Value) m2.accept(new MacroInterpreter(m2, MACRO_SIMPLE_PARAMETER_LIST));
//        assertEquals(Value.parseNumericValue("3.0").toString(), result2.toString());
//    }

    @Test
    public void ensureMInterpreterEvaluatesFunctionCalls() throws MacroCompilationException, ParseException {
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);

        String macro = MACRO_SIMPLE_HEADER+"(A1:=1)\n(A2:=3)\nSUM(A1:A2)";
        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);
        Value result1 = (Value) m.accept(new MacroInterpreter(m, MACRO_SIMPLE_PARAMETER_LIST));
        assertEquals(Value.parseNumericValue("4").toString(), result1.toString());
        //Values are changed on cells
        assertEquals(Value.parseNumericValue("1").toString(), sheet.getCell(0,0).getValue().toString());
        assertEquals(Value.parseNumericValue("3").toString(), sheet.getCell(0,1).getValue().toString());
    }

    @Test
    public void ensureMInterpreterEvaluatesLocals() throws MacroCompilationException, ParseException {
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);

        String macro = MACRO_SIMPLE_HEADER+"(_A1:=1)\n(_A2:=3)\nSUM(_A1;_A2)";
        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);
        Value result1 = (Value) m.accept(new MacroInterpreter(m, MACRO_SIMPLE_PARAMETER_LIST));
        assertEquals(Value.parseNumericValue("4").toString(), result1.toString());
    }


    @Test
    public void ensureMInterpreterEvaluatesParametersOnFormulas() throws MacroCompilationException, ParseException {
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);

        String macro = macroHeader("param1, param2")+"\nSUM(${param1};${param2})";
        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);
        ParameterList list = new ParameterList();
        list.addParameter(new Parameter("param1", Value.parseNumericValue("2")));
        list.addParameter(new Parameter("param2", Value.parseNumericValue("2")));
        Value result1 = (Value) m.accept(new MacroInterpreter(m, list));
        assertEquals(Value.parseNumericValue("4").toString(), result1.toString());
    }

    @Test
    public void ensureMInterpreterEvaluatesParametersOnBinaryOperations() throws MacroCompilationException, ParseException {
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);

        String macro = macroHeader("param1, param2")+"\n${param1}+${param2}";
        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);
        ParameterList list = new ParameterList();
        list.addParameter(new Parameter("param1", Value.parseNumericValue("2")));
        list.addParameter(new Parameter("param2", Value.parseNumericValue("2")));
        Value result1 = (Value) m.accept(new MacroInterpreter(m, list));
        assertEquals(Value.parseNumericValue("4").toString(), result1.toString());
    }

    @Test
    public void ensureMInterpreterEvaluatesParametersOnUnaryOperations() throws MacroCompilationException, ParseException {
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);

        String macro = macroHeader("param1, param2")+"\n-${param1}";
        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);
        ParameterList list = new ParameterList();
        list.addParameter(new Parameter("param1", Value.parseNumericValue("2")));
        list.addParameter(new Parameter("param2", Value.parseNumericValue("2")));
        Value result1 = (Value) m.accept(new MacroInterpreter(m, list));
        assertEquals(Value.parseNumericValue("-2").toString(), result1.toString());
    }

    @Test
    public void ensureMInterpreterEvaluatesParametersOnComplexScenario() throws MacroCompilationException, ParseException {
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);

        ParameterList list = new ParameterList();
        list.addParameter(new Parameter("param1", Value.parseNumericValue("2")));
        list.addParameter(new Parameter("param2", Value.parseNumericValue("2")));
        list.addParameter(new Parameter("name", new Value("Henrique")));

        String macro = macroHeader("param1, param2, name")
                +"(A1:=${name})\n" //Henrique
                +"(A2:=${param1})\n"
                +"(A3:=${param2})\n"
                +"(A4:=SUM(A2:A3))\n" //4
                +"(B4:=SUM(${param1};${param2}))\n" //4
               +"(B5:=AVERAGE(${param1};${param2}))\n" //2
                +"(B6:=-${param1})\n" //-2
               +"AND(B5=-B6; B4=A4; A1=\"Henrique\")\n"
        ;
        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);
        Value result1 = (Value) m.accept(new MacroInterpreter(m, list));
        assertEquals(Value.parseBooleanValue("true").toString(), result1.toString());
    }

    private static String macroHeader(String params){
        return "macro test1(" + params + ")\n";
    }


}
