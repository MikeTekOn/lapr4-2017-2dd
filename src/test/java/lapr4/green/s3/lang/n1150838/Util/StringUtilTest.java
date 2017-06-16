/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.Util;

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
public class StringUtilTest {
    
    public StringUtilTest() {
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
     * Test of removeQuotationMarks method, of class StringUtil.
     */
    @Test
    public void testRemoveQuotationMarks() {
        System.out.println(" removeStartEndSpecialChars");
        String stringToRemove = "\"teste\"";
        String expResult = "teste";
        String result = StringUtil. removeStartEndSpecialChars(stringToRemove);
        assertEquals(expResult, result);
        stringToRemove = "[12]";
         expResult = "12";
        result = StringUtil. removeStartEndSpecialChars(stringToRemove);
        assertEquals(expResult, result);
        
    }
    
}
