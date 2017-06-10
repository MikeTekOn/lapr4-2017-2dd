/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150623;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.compiler.FormulaCompilationException;
import lapr4.blue.s1.lang.n1151088.temporaryVariables.Variable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class VariablesTest {

    private CleanSheets app;
    Cell cellA1;
    Cell cellTest;
    Expression exp1, exp2;
    Value value1, value2;

    public VariablesTest() {
    }

    @Before
    public void setUp() throws FormulaCompilationException {
         // Try to create the CS application object
        app = new CleanSheets();
        // This will create a workbook with 3 sheets
        app.create();
        cellTest = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        String content= "=2";
        String content2="=3";
        cellTest.setContent(content);
        value1= cellTest.getValue();
        //Test temporary variable
        exp1 = cellTest.getFormula().getExpression();

        cellTest.setContent(content2);
        exp2=cellTest.getFormula().getExpression();
        value2=cellTest.getValue();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class Variable.
     * @throws Exception
     */
    @Test
    public void testEvaluate() throws Exception {
        System.out.println("evaluate");
        Variable instance = new Variable("_test", exp1);
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
        String test="test";
        Variable instance = new Variable(test, exp1);
        String result = instance.getName();
        String expResult=test;
        assertEquals(expResult, result);
    }

    /**
     * Test of getExpression method, of class Variable.
     * @throws IllegalValueTypeException
     */
    @Test
    public void testGetExpression() throws IllegalValueTypeException {
        System.out.println("getExpression");
        String test="test";
        Variable instance = new Variable("_test", exp1);

        Expression result = instance.getExpression();
        Expression expResult=exp1;
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Variable.
     * @throws IllegalValueTypeException
     */
    @Test
    public void testEqualsValid() throws IllegalValueTypeException {
        System.out.println("equals");

        String test="test";

        Variable instance = new Variable(test, exp1);

        Variable instance2= new Variable(test,  exp2);

        boolean expResult = true;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }

     /**
     * Test of equals method, of class Variable.
     * @throws IllegalValueTypeException
     */
    @Test
    public void testEqualsInvalid() throws IllegalValueTypeException {
        System.out.println("equalsInvalid");
    
        String test="test";
        Variable instance = new Variable(test, exp1);
        
        String test2="test2";
        Variable instance2= new Variable(test2, exp2);
        
        boolean expResult = false;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    } 
}