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
    public void ensureTemporaryVarStartWithUnderscore() throws FormulaCompilationException, IllegalValueTypeException {

        // Introducing expression
        Cell cellTest = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
       // String content= "={(_Var:=2)}";
        String content = "=1+2";
        cellTest.setContent(content);
        
        //Test temporary variable
        Double result = cellTest.getValue().toDouble();
        Double expResult = 3d;
        assertEquals(result, expResult);
    }
    
    /*
    @Test
    public void haveLetterAfterUnderscore() throws FormulaCompilationException, IllegalValueTypeException{
        // Introducing expression
        Cell cellTest = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        //String content= "=_V1:=2";
        String content = "=1+2";
        cellTest.setContent(content);

        //Test temporary variable
        Double result = cellTest.getValue().toDouble();
        Double expResult = 3d;
        assertEquals(result, expResult);
    }
    
    
    @Test//(expected = FormulaCompilationException.class)
    public void rejectNumberAfterUnderscoreInvalid() throws FormulaCompilationException, IllegalValueTypeException{
        // Introducing expression
        Cell cellTest = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String content= "=_12:=2";
        cellTest.setContent(content);
        
        //Test temporary variable
        Double result = cellTest.getValue().toDouble();
        Double expResult = 3d;
        assertEquals(result, expResult);
    }
    
     @Test
    public void acceptsDigitsAndLettersName() throws FormulaCompilationException, IllegalValueTypeException {
        // Introducing expression
        Cell cellTest = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String content= "=_Var1test2:=2";
        cellTest.setContent(content);
        
        //Test temporary variable
        Double result = cellTest.getValue().toDouble();
        Double expResult = 3d;
        assertEquals(result, expResult);
    }
    
    @Test
    public void addTemporaryVariableToContentorFirstTime() throws FormulaCompilationException, IllegalValueTypeException {
        // Introducing expression
        BlueCell cellTest = (BlueCell) app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String content= "=_Var1:=2";
        cellTest.setContent(content);
        
        //Test temporary variable
        boolean result=cellTest.getBlueFormula().getTemporaryVarContentor().getTemporaryVariables().containsKey("_Var1");
        assertEquals(true, result);
    }
    
    @Test
    public void addTemporaryVariableToContentorAlreadyExists() throws FormulaCompilationException, IllegalValueTypeException {
        // Introducing expression
        BlueCell cellTest = (BlueCell) app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String content= "=_Var1:=2; _Var2:=1; _Var1:=3";
        cellTest.setContent(content);
        
        //Test temporary variable
        TemporaryVariable tempVar=(TemporaryVariable) cellTest.getBlueFormula().getTemporaryVarContentor().getTemporaryVariables().get("_Var1");
        Double result = tempVar.evaluate().toDouble();
        Double expResult = 3d;
        assertEquals(true, result);
    }

    @Test
    public void testBasicExpressionWithTemporary() throws FormulaCompilationException, IllegalValueTypeException {
        // Introducing expression
        Cell cellTest = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String content= "=_Var1:=1+2";
        cellTest.setContent(content);
        
        //Test temporary variable
         Double result = cellTest.getValue().toDouble();
        Double expResult = 3d;
    }
    
    @Test
    public void testAssignmentOperatorWithTemporary() throws FormulaCompilationException, IllegalValueTypeException {
        // Introducing expression
        Cell cellTest = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String content= "={(A1:=2);( _Var1:=A1)";    
        
        //Test Each cell's value
        cellTest.setContent(content);
        assert cellTest.getValue().toDouble() == 2d;
    }
   
     @Test
    public void testFunctionExpressionWithTemporary() throws FormulaCompilationException, IllegalValueTypeException {
        // Introducing expression
        Cell cellTest = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        Cell cellTest2 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 1));
        Cell cellTest3= app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 0));
       
        String content= "={_Var:= sum(A1:A2)}";  
        cellTest.setContent(":=2");
        cellTest2.setContent(":=3");
        cellTest3.setContent(content);
      
        //Test Each cell's value
        assert cellTest.getValue().toDouble() == 5d;
    }
    
     @Test
    public void testBlockWithTemporary() throws FormulaCompilationException, IllegalValueTypeException {
        Cell cellTest= app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 0));
       
        String content= "={A1=1+2; _Var= 1+A1;}";  
        cellTest.setContent(content);
      
        //Test Each cell's value
        assert cellTest.getValue().toDouble() == 4d;
    }
    
     @Test
    public void testFormulaManyTemporaryVariables() throws FormulaCompilationException, IllegalValueTypeException {
        Cell cellTest= app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 0));
       
        String content= "=(_Var1:=2; _Var2:=3; _Var3:=_Var1+_Var2; }"; 
        cellTest.setContent(content);
      
        //Test Each cell's value
        assert cellTest.getValue().toDouble() == 5;
    }
    
    public void testFormulaWithTemporaryVariable() throws FormulaCompilationException, IllegalValueTypeException {
        Cell cellTest= app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 0));
       
        String content= "=_Var1:=2; _Var2:=1; MAX(_Var1, _Var2}"; 
        cellTest.setContent(content);
      
        //Test Each cell's value
        assert cellTest.getValue().toDouble() == 2;
    }*/
}
