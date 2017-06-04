/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150451.exportPDF.domain;

import csheets.core.Cell;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diogo Santos
 */
public class ExportPDFTest {
    
    public ExportPDFTest() {
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
     * Test of selectPath method, of class ExportPDF.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSelectPath() {
        System.out.println("selectPath");
        String path = "test.pdt";
        ExportPDF instance = new ExportPDF();
        instance.selectPath(path);
    }
    
}
