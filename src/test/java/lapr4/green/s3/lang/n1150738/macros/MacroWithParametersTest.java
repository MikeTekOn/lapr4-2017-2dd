/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class MacroWithParametersTest {

    private static final String MACRO_SIMPLE_HEADER = "macro test1()\n";

    private CleanSheets app;

    Cell cellTest;
    Expression expression1, expression2;
    Value value1, value2;

    public MacroWithParametersTest() {
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

    @Test(expected = MacroCompilationException.class)
    public void ensureMacroWithoutHeaderCantCompile() throws MacroCompilationException{
        String macro = "1";
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);
        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);
    }

    @Test
    public void ensureMacroCanHaveZeroParameters() throws MacroCompilationException{
        String macro = "macro test1()\n"+"1";
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);
        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);
    }

    @Test
    public void ensureMacroHasParameters() throws MacroCompilationException{
        String macro = "macro test1(param1, param2)\n"+"1";
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);
        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);

        assertEquals(2, m.getParameterDefinition().count());
        assertEquals("param1",m.getParameterDefinition().getParameter(0));
        assertEquals("param2",m.getParameterDefinition().getParameter(1));
    }

    @Test
    public void ensureMacroParametersCanBeReferenced() throws MacroCompilationException{
        String macro = "macro test1(param1, param2)\n"+"${param1}+1";
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);
        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);
    }


    @Test
    public void ensureMacroNameIsCaptured() throws MacroCompilationException{
        String macro = "macro test1()\n"+"1";
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);
        MacroWithParameters m = Macro2Compiler.getInstance().compile(sheet, null, macro);
        String expectedName = "test1";
        assertEquals(expectedName,m.getName());
    }


}

