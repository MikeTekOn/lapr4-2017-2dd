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
import lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;
import lapr4.green.s3.lang.n1150738.macros.compiler.Macro2Compiler;
import lapr4.green.s3.lang.n1150738.macros.compiler.Macro2EvalVisitor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class LocalVariableTest {

    private CleanSheets app;

    Cell cellTest;
    Expression expression1, expression2;
    Value value1, value2;

    public LocalVariableTest() {
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

    @Test public void ensureLocalVariableIsDetected() throws MacroCompilationException, ParseException, IllegalValueTypeException {
        String macro = "(_local1 := 2)\n" +
                "_local1";
        Spreadsheet sheet = app.getWorkbooks()[0].getSpreadsheet(0);
        Expression m = Macro2Compiler.getInstance().compile(sheet, null, macro);
        Value result = m.evaluate();
        Value expected = Value.parseNumericValue("2");
        assertEquals(expected, result);
    }
}

