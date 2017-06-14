/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Naná
 */
public class SearchPatternTest {
    
    public SearchPatternTest() {
    }
  
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of acceptExp method, of class SearchPattern.
     */
    @Test
    public void testAcceptExp() {
        System.out.println("acceptExp");
        SearchPattern instance = new SearchPattern("ola");
        boolean expResult = true;
        boolean result = instance.acceptExp("c:/ola.cls");
        assertEquals(expResult, result);     
    }
    
     /**
     * Test of acceptExp method, of class SearchPattern.
     */
    @Test
    public void findPartialExpression() {
        System.out.println("findPartialExpression");
        SearchPattern instance = new SearchPattern("ola");
        boolean expResult = true;
        boolean result = instance.checkIfMatches("OiOlaOi");
        assertEquals(expResult, result);     
    }
}
