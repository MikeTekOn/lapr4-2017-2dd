/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150838.GlobalSearch.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PC
 */
public class FilterTest {
    
    public FilterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test to verify if filter is well built
     */
    @Test
    public void ensureFilterIsValid() {
        //case valid!
        Filter instance = new Filter(new ArrayList(),new ArrayList(),false);
    }
    
   /**
     * Test to verify if filter is well built
     */
    @Test(expected=IllegalStateException.class)
    public void ensureFilterIsBuildWithValidTypeList() {
        //case valid!
        Filter instance = new Filter(null,new ArrayList(),false);
    }
     /**
     * Test to verify if filter is well built
     */
    @Test(expected=IllegalStateException.class)
    public void ensureFilterIsBuildWithValidFormulaList() {
        //case valid!
        Filter instance = new Filter(new ArrayList(),null,false);
    }
    
    

}
