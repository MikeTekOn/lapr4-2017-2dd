/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1151088.temporaryVariables;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.compiler.FormulaCompilationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guilherme Ferreira 1150623
 */
public class VariableTest {
    
    private CleanSheets app;

    Cell cellTest;
    Expression expression1, expression2;
    Value value1, value2;
    
    public VariableTest() {
    }

    @Before
    public void setUp() throws FormulaCompilationException {
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class Variable.
     * @throws java.lang.Exception
     */
    @Test
    public void testEvaluate() throws Exception {
        System.out.println("evaluate");
        Variable instance = new Variable("@test", expression1);
        Value expResult = value1;
        Value result = instance.evaluate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Variable.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String test="@test";
        Variable instance = new Variable(test, expression1);
        String result = instance.getName();
        String expResult=test;
        assertEquals(expResult, result);
    }


    @Test
    public void testGetExpression() throws IllegalValueTypeException {
        System.out.println("getExpression");
        Variable instance = new Variable("@test", expression1);
        
        Expression result = instance.getExpression();
        Expression expResult= expression1;
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Variable.
     * @throws IllegalValueTypeException
     */
    @Test
    public void testEqualsValid() throws IllegalValueTypeException {
        System.out.println("equals");
        String test="@test";

        Variable instance = new Variable(test, expression1);
        Variable instance2= new Variable(test, expression2);

        assertTrue(instance.equals(instance2));
    }
    
     /**
     * Test of equals method, of class Variable.
     * @throws csheets.core.IllegalValueTypeException
     */
    @Test
    public void testEqualsInvalid() throws IllegalValueTypeException {
        System.out.println("equalsInvalid");
    
        String test="@test";
        Variable instance = new Variable(test, expression1);
        
        String test2="@test2";
        Variable instance2= new Variable(test2, expression2);
        assertFalse(instance.equals(instance2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameCantBeEmptyOrNull(){
        Variable v = new Variable("", expression1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureExpressionCantBeNull(){
        Variable v = new Variable("@Name", null);
    }
}