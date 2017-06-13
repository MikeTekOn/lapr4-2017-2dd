package lapr4.blue.s1.lang.n1151088.formula;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Diana Silva [1151088@isep.ipp.pt]
 *         on 02/06/17
 * @author Guilherme Ferreira 1150623 on 09/06/17
 */
public class FormulaVariableTest {

//    private CleanSheets app;
//    Cell cellA1;
//
//    @Before
//    public void setUp() throws Exception {
//
//        // Try to create the CS application object
//        CleanSheets.setFlag(true);
//        app = new CleanSheets();
//
//        app.setUIController(new UIController(app));
//        // This will create a workbook with 3 sheets
//        app.create();
//
//    }
//
//    /**
//     * Ensure operation with block containing temporary variable
//     * @throws FormulaCompilationException
//     * @throws IllegalValueTypeException
//     */
////    @Test
////    public void testBlockWithTemporary() throws FormulaCompilationException, IllegalValueTypeException {
////        // Introducing expression
////        Cell cellTest = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
////        String content= "={(_Var1:=1+2); _Var1 + 1}";
////        cellTest.setContent(content);
////
////        //Test temporary variable
////        Double result = cellTest.getValue().toDouble();
////        Double expResult = 4d;
////    }
//
//
//    /**
//     * Ensure operation with block containing global variable
//     * @throws FormulaCompilationException
//     * @throws IllegalValueTypeException
//     */
//
//    public void testBlockWithGlobalVariable() throws FormulaCompilationException, IllegalValueTypeException {
//        // Introducing expression
//        Cell cellTest = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
//        String content= "={(@Var1:=1+2); (@Var1+1)}";
//        cellTest.setContent(content);
//
//        //Test temporary variable
//        Double result = cellTest.getValue().toDouble();
//        Double expResult = 4d;
//    }
//
//    /**
//     * Ensure assignment with temporary variable
//     * @throws FormulaCompilationException
//     * @throws IllegalValueTypeException
//     */
//    @Test
//    public void testAssignmentOperatorWithTemporary() throws FormulaCompilationException, IllegalValueTypeException {
//        // Introducing expression
//        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
//        Cell cellA2 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 1));
//
//        String content= "={(A2:=2) ; (_Var1 := A2) ; _Var1 }";
//        cellA1.setContent(content);
//
//        //Test value
//        assert cellA1.getValue().toDouble() == 2d;
//    }
//
//    /**
//     * Ensure assignment with global variable
//     * @throws FormulaCompilationException
//     * @throws IllegalValueTypeException
//     */
//
//    public void testAssignmentOperatorWithGlobal() throws FormulaCompilationException, IllegalValueTypeException {
//        // Introducing expression
//        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
//        Cell cellA2 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 1));
//
//        String content= "={(A2:=2) ; (@Var1 := A2) ; @Var1 }";
//        cellA1.setContent(content);
//
//        //Test value
//        assert cellA1.getValue().toDouble() == 2d;
//    }
//
//    /**
//     * Ensure function call by temporary variable
//     * @throws FormulaCompilationException
//     * @throws IllegalValueTypeException
//     */
//     @Test
//    public void testFunctionWithTemporary() throws FormulaCompilationException, IllegalValueTypeException {
//        // Introducing expression
//        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
//        Cell cellA2 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 1));
//
//        String content= "={(A2:=2); (A3:=3); (_Var1:= SUM(A2:A3)); _Var1+1}";
//        cellA1.setContent(content);
//
//        //Test value
//        assert cellA1.getValue().toDouble() == 6d;
//    }
//
//
//    /**
//     * Ensure function call by global variable
//     * @throws FormulaCompilationException
//     * @throws IllegalValueTypeException
//     */
//    public void testFunctionWithGlobal() throws FormulaCompilationException, IllegalValueTypeException {
//        // Introducing expression
//        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
//        Cell cellA2 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 1));
//
//        String content= "={(A2:=2); (A3:=3); (@Var1:= SUM(A2:A3)); @Var1+1}";
//        cellA1.setContent(content);
//
//        //Test value
//        assert cellA1.getValue().toDouble() == 6d;
//    }
//
//    /**
//     * Ensure a block with more than one temporary variable
//     * @throws FormulaCompilationException
//     * @throws IllegalValueTypeException
//     */
//     @Test
//    public void testFormulaManyTemporaryVariables() throws FormulaCompilationException, IllegalValueTypeException {
//        Cell cellTest= app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 0));
//
//        String content= "={(_Var1:=2);( _Var2:=3);( _Var3:=_Var1+_Var2); _Var3 }";
//        cellTest.setContent(content);
//
//        //Test temporary variable value
//        assert cellTest.getValue().toDouble() == 5;
//    }
//
//    /**
//     * Ensure a block with more than one global variable
//     * @throws FormulaCompilationException
//     * @throws IllegalValueTypeException
//     */
//    public void testFormulaManyGlobalVariables() throws FormulaCompilationException, IllegalValueTypeException {
//        Cell cellTest= app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 0));
//
//        String content= "={(@Var1:=2);( @Var2:=3);( @Var3:=@Var1+@Var2); @Var3 }";
//        cellTest.setContent(content);
//
//        //Test temporary variable value
//        assert cellTest.getValue().toDouble() == 5;
//    }
//
//    /**
//     * Ensure function call with temporary variable
//     * @throws FormulaCompilationException
//     * @throws IllegalValueTypeException
//     */
//    @Test
//    public void testFormulaWithTemporaryVariable() throws FormulaCompilationException, IllegalValueTypeException {
//        Cell cellTest= app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 0));
//
//        String content= "={(_Var1:=2);( _Var2:=1); MAX(_Var1; _Var2)}";
//        cellTest.setContent(content);
//
//        //Test temporary variable value
//        assert cellTest.getValue().toDouble() == 2;
//    }
//
//    /**
//     * Ensure function call with global variable
//     * @throws FormulaCompilationException
//     * @throws IllegalValueTypeException
//     */
//
//    public void testFormulaWithGlobalVariable() throws FormulaCompilationException, IllegalValueTypeException {
//        Cell cellTest= app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 0));
//
//        String content= "={(@Var1:=2);(@Var2:=1); MAX(@Var1; @Var2)}";
//        cellTest.setContent(content);
//
//        //Test temporary variable value
//        assert cellTest.getValue().toDouble() == 2;
//    }
//
//    /**
//     * Ensure that it is possible to a temporary variable call itself by assignment
//     *
//     * @throws FormulaCompilationException if the formula could not be compiled
//     * @throws IllegalValueTypeException   if unexpected value
//     */
//    @Test
//    public void ensureSelfExecution() throws FormulaCompilationException, IllegalValueTypeException {
//
//        // Get cells & define A1's formula
//        Cell cellA2 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 1));
//        String content = "={(_a:=1);(_a:=_a+1);_a}";
//
//        cellA2.setContent(content);
//
//        //Test temporary variable result
//        assert cellA2.getValue().toDouble()==2d;
//
//    }
//
//    /**
//     * Ensure that it is possible to a global variable call itself by assignment
//     *
//     * @throws FormulaCompilationException if the formula could not be compiled
//     * @throws IllegalValueTypeException   if unexpected value
//     */
//
//    public void ensureSelfExecutionGlobal() throws FormulaCompilationException, IllegalValueTypeException {
//
//        // Get cells & define A1's formula
//        Cell cellA2 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 1));
//        String content = "={(@a:=1);(@a:=@a+1);@a}";
//
//        cellA2.setContent(content);
//
//        //Test temporary variable result
//        assert cellA2.getValue().toDouble()==2d;
//
//    }
//
//     /**
//     * Tests if temporary variable is not malformed.
//     *
//     * @throws FormulaCompilationException if the formula could not be compiled
//     * @throws IllegalValueTypeException   if unexpected value
//     */
//    @Test(expected = FormulaCompilationException.class)
//    public void ensureTemporaryIsNotMalformed() throws FormulaCompilationException, IllegalValueTypeException {
//
//        // Get A1 cell
//        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
//
//        String content = "={_2:=2";
//        cellA1.setContent(content);
//    }
//
//
//    /**
//     * Tests if temporary variable is not malformed.
//     *
//     * @throws FormulaCompilationException if the formula could not be compiled
//     * @throws IllegalValueTypeException   if unexpected value
//     */
//    @Test(expected = FormulaCompilationException.class)
//    public void ensureGlobalIsNotMalformed() throws FormulaCompilationException, IllegalValueTypeException {
//
//        // Get A1 cell
//        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
//
//        String content = "={@2:=2";
//        cellA1.setContent(content);
//    }
}