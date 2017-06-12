/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150657.extensions;

import csheets.ext.Extension;
import csheets.ext.ExtensionManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import lapr4.green.s1.ipc.n1150532.comm.CommExtension;
import lapr4.green.s1.ipc.n1150800.importexportTXT.ui.ImportExportExtension;
import lapr4.green.s1.ipc.n1150901.search.workbook.SearchWorkbookExtension;
import lapr4.green.s1.ipc.n1150901.search.workbook.ui.SearchWorkbookAction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sofia
 */
public class ExtensionLoadTest {
    
    private ExtensionLoad extensionLoad;
    private Properties props = ExtensionManager.getInstance().getProperties();
    private ExtensionManager extensionInstance = ExtensionManager.getInstance();;
    
    public ExtensionLoadTest() {
        extensionLoad = new ExtensionLoad(props, extensionInstance);
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
     * Test of getExtensionsMap method, of class ExtensionLoad.
     */
    @Test
    public void testGetExtensionsMap() {
        System.out.println("getExtensionsMap");
        ExtensionLoad instance = extensionLoad;
        
        
        Map<String, Set<Extension>> result = instance.getExtensionsMap();

        assertEquals(result.size(), extensionLoad.numberOfExtensions());
    }

    /**
     * Test of numberOfExtensions method, of class ExtensionLoad.
     */
    @Test
    public void testNumberOfExtensions() {
        System.out.println("numberOfExtensions");
        ExtensionLoad instance = extensionLoad;
        int expResult = instance.getExtensionsMap().size();
        int result = instance.numberOfExtensions();
        assertEquals(expResult, result);
    }

    /**
     * Test of numberVersionsByExtension method, of class ExtensionLoad.
     */
    @Test
    public void testNumberVersionsByExtension() {
        System.out.println("numberVersionsByExtension");
        String extensionName = CommExtension.NAME;
        ExtensionLoad instance = extensionLoad;
        int expResult = 1;
        int result = instance.numberVersionsByExtension(extensionName);
        assertEquals(expResult, result);
        
        extensionName = SearchWorkbookExtension.NAME;
        expResult = 2;
        result = instance.numberVersionsByExtension(extensionName);
        assertEquals(expResult, result);
        
        extensionName = "nothing";
        expResult = 0;
        result = instance.numberVersionsByExtension(extensionName);
        assertEquals(expResult, result);
    }
    
}
