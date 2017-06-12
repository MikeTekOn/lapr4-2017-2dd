package lapr4.red.s2.lang.n1150623;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.compiler.FormulaCompilationException;
import lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable;
import lapr4.red.s2.lang.n1150623.globalVariables.VarContentor;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Guilherme Ferreira 1150623 on 09/06/2017.
 */
public class VarContentorTest {

    private VarContentor contentor;
    private String name1, name2;
    private Variable var1, var2;
    private CleanSheets app;
    private Cell cellTest;
    private Expression expression1, expression2;
    private Value value1, value2;

    public VarContentorTest() {
    }

    @Before
    public void setUp() throws FormulaCompilationException {
        contentor = new VarContentor();
        // Try to create the CS application object
        CleanSheets.setFlag(true);
        app = new CleanSheets();
        // This will create a workbook with 3 sheets
        app.create();
        cellTest = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String content= "=2";
        String content2="=3";
        cellTest.setContent(content);
        value1= cellTest.getValue();
        //Test temporary variable
        expression1 = cellTest.getFormula().getExpression();

        cellTest.setContent(content2);
        expression2 =cellTest.getFormula().getExpression();
        value2=cellTest.getValue();

        name1 = "@testName1";
        name2 = "@testName1";

        var1 = new Variable(name1, expression1);
        var2 = new Variable(name2, expression2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureVarContentorCantHaveNullVariables(){
        contentor.update(null);
    }

    @Test
    public void update(){
        contentor.update(var1);
        contentor.update(var2);

        var1 = new Variable(name1, expression2);
        contentor.update(var1);

        assertTrue(contentor.getExpressionOfVariable(name1).equals(expression2));

    }
}
