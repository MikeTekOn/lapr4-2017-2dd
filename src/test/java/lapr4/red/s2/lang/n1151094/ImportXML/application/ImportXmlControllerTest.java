/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1151094.ImportXML.application;

import csheets.CleanSheets;
import csheets.ui.ctrl.UIController;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eduangelo Ferreira -1151094
 */
public class ImportXmlControllerTest {

    public ImportXmlControllerTest() {

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
     * Test of importXml method, of class ImportXmlController.
     */
    @Test
    public void testImportXml() {
        CleanSheets app = new CleanSheets();
        UIController controller = new UIController(app);
        ImportXmlController instance = new ImportXmlController(controller);
        instance.selectPath("ImportXml.xml");
        System.out.println("importXml");
        boolean expResult = true;
        boolean result = instance.importXml();
        assertEquals(expResult, result);

    }

    /**
     * Test of validateExtension method, of class ImportXmlController.
     */
    @Test
    public void testValidateExtension() {
        CleanSheets app = new CleanSheets();
        UIController controller = new UIController(app);
        ImportXmlController instance = new ImportXmlController(controller);
        instance.selectPath("ImportXml.xml");
        File file = new File("ImportXml.xml");
        boolean expResult = true ;
        boolean result = instance.validateExtension(file);
        
        assertEquals(expResult, result);

    }

}
