package lapr4.blue.s1.lang.n1151088.formula;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.formula.compiler.FormulaCompilationException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Diana Silva [1151088@isep.ipp.pt]
 *         on 02/06/17
 */
public class TemporaryVariableTest {

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
     * Tests if sequence block is not malformed.
     *
     * @throws FormulaCompilationException if the formula could not be compiled
     * @throws IllegalValueTypeException   if unexpected value
     */
    @Test
    public void ensureTemporaryVarStartWithUnderscoreValid() throws FormulaCompilationException, IllegalValueTypeException {

        // Introducing expression
        Cell cellTest = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        //    String content= "=_Var:=2";
        String content = "=1+2";
        cellTest.setContent(content);

        //Test temporary variable
        Double result = cellTest.getValue().toDouble();
        Double expResult = 3d;
        assertEquals(result, expResult);
    }

    /**
     *  <li>startWithUnderscore()</li>
     *   <li>haveLetterAfterUnderscore()</li>
     *   <li>rejectNumberAfterUnderscore()</li>
     *   <li>acceptDigitsAndLettersName()</li>
     *   <li>addTemporaryVariableToContentorFirstTime()</li>
     *   <li>addTemporaryVariableToContentorAlreadyExists</li>
     *   <li>testBasicExpressionWithTemporary() -&gt; example "_Var:=1+2"</li>
     *   <li>testAssignmentOperatorWithTemporary() -&gt; example "_Var:=A1"</li>
     *   <li>testFunctionExpressionWithTemporary() -&gt; example "_Var:= sum(A2:A4)"</li>
     *    <li>testFormulaBlocksWithTemporary() -&gt; ex: "= {A=1+2; _Var:= 1+A ;"
     *   <li>testFormulaManyTemporaryVariables() -&gt; ex: "={_Var1:=2; _Var2:=3; _Var3:=_Var1+_Var2; A= _Var+3]"
     *   <li>formulaWithTemporaryVariable()</li>
     */
}