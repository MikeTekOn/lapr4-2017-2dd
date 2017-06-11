/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150838.GlobalSearch.application;

import csheets.CleanSheets;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;
import lapr4.green.s2.core.n1150838.GlobalSearch.domain.Filter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class GlobalSearchControllerTest {
    
    private GlobalSearchController ctrl;
    
    public GlobalSearchControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FormulaCompilationException {
        CleanSheets app = new CleanSheets();

        UIController extensions = new UIController(app);
       ctrl = new GlobalSearchController(extensions);

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validateFormula method, of class GlobalSearchController.
     */
    @Test
    public void testValidateFormula() throws Exception {
        System.out.println("validateFormula");
        String formula = "=2";
        boolean expResult = true;
        boolean result = ctrl.validateFormula(formula);
        assertEquals(expResult, result);
        
    }


    
}
