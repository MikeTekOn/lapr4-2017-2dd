/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150657.extensions;

import csheets.ext.Extension;
import csheets.ext.deptree.DependencyTreeExtension;
import csheets.ext.simple.ExtensionExample;
import csheets.ext.style.StyleExtension;
import lapr4.blue.s1.lang.n1151159.macros.MacroExtension;
import lapr4.blue.s2.ipc.n1060503.chat.ui.ChatParticipantsExtension;
import lapr4.blue.s2.ipc.n1151031.searchnetwork.SearchWorkbookNetworkExtension;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ext.ExtensionFindWorkbook;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.NetAnalyzerExtension;
import lapr4.green.s1.ipc.n1150532.comm.CommExtension;
import lapr4.green.s1.ipc.n1150657.chat.ext.ChatExtension;
import lapr4.green.s1.ipc.n1150800.importexportTXT.ui.ImportExportExtension;
import lapr4.green.s1.ipc.n1150838.findworkbooks.ext.ExtensionFindWorkbooks;
import lapr4.green.s1.ipc.n1150901.search.workbook.SearchWorkbookExtension;
import lapr4.green.s2.core.n1150838.GlobalSearch.GlobalSearchExtension;
import lapr4.green.s2.core.n1150901.richCommentsAndHistory.domain.CommentsWithHistoryExtension;
import lapr4.red.s1.core.n1150613.workbookSearch.SearchExtension;
import lapr4.white.s1.core.n1234567.comments.CommentsExtension;
import lapr4.white.s1.core.n4567890.contacts.ContactsExtension;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sofia Gonçalves (1150657)
 */
public class ExtensionFactoryTest {
    
    public ExtensionFactoryTest() {
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
     * Test of getExtension method, of class ExtensionFactory.
     */
    @Test
    public void testGetExtension() {
        System.out.println("getExtension");
        String criteria1 = ChatExtension.CHAT_NAME;
        int criteria2 = 1;
        Extension expResult = new ChatExtension();
        Extension result = ExtensionFactory.getExtension(criteria1, criteria2);
        assertEquals(expResult.getName(), result.getName());
        
        criteria1 = StyleExtension.NAME;
        criteria2 = 1;
        expResult = new StyleExtension();
        result = ExtensionFactory.getExtension(criteria1, criteria2);
        assertEquals(expResult.getName(), result.getName());
        
        criteria1 = DependencyTreeExtension.NAME;
        criteria2 = 1;
        expResult = new DependencyTreeExtension();
        result = ExtensionFactory.getExtension(criteria1, criteria2);
        assertEquals(expResult.getName(), result.getName());
        
        criteria1 = ExtensionExample.NAME;
        criteria2 = 1;
        expResult = new ExtensionExample();
        result = ExtensionFactory.getExtension(criteria1, criteria2);
        assertEquals(expResult.getName(), result.getName());
        
        criteria1 = CommentsExtension.NAME;
        criteria2 = 1;
        expResult = new CommentsExtension();
        result = ExtensionFactory.getExtension(criteria1, criteria2);
        assertEquals(expResult.getName(), result.getName());
        criteria2 = 2;
        expResult = new CommentsWithHistoryExtension();
        result = ExtensionFactory.getExtension(criteria1, criteria2);
        assertEquals(expResult.getName(), result.getName());
        
        criteria1 = ContactsExtension.NAME;
        criteria2 = 1;
        expResult = new ContactsExtension();
        result = ExtensionFactory.getExtension(criteria1, criteria2);
        assertEquals(expResult.getName(), result.getName());
        
        criteria1 = ExtensionFindWorkbooks.NAME;
        criteria2 = 1;
        expResult = new ExtensionFindWorkbooks();
        result = ExtensionFactory.getExtension(criteria1, criteria2);
        assertEquals(expResult.getName(), result.getName());
        criteria2 = 2;
        expResult = new ExtensionFindWorkbook();
        result = ExtensionFactory.getExtension(criteria1, criteria2);
        assertEquals(expResult.getName(), result.getName());
        
        criteria1 = SearchWorkbookExtension.NAME;
        criteria2 = 1;
        expResult = new SearchWorkbookExtension();
        result = ExtensionFactory.getExtension(criteria1, criteria2);
        assertEquals(expResult.getName(), result.getName());
        criteria2 = 2;
        expResult = new SearchWorkbookNetworkExtension();
        result = ExtensionFactory.getExtension(criteria1, criteria2);
        assertEquals(expResult.getName(), result.getName());
        
        criteria1 = ImportExportExtension.NAME;
        criteria2 = 1;
        expResult = new ImportExportExtension();
        result = ExtensionFactory.getExtension(criteria1, criteria2);
        assertEquals(expResult.getName(), result.getName());
        
        criteria1 = MacroExtension.NAME;
        criteria2 = 1;
        expResult = new MacroExtension();
        result = ExtensionFactory.getExtension(criteria1, criteria2);
        assertEquals(expResult.getName(), result.getName());
        
        criteria1 = CommExtension.NAME;
        criteria2 = 1;
        expResult = new CommExtension();
        result = ExtensionFactory.getExtension(criteria1, criteria2);
        assertEquals(expResult.getName(), result.getName());
        
        criteria1 = SearchExtension.NAME;
        criteria2 = 1;
        expResult = new SearchExtension();
        result = ExtensionFactory.getExtension(criteria1, criteria2);
        assertEquals(expResult.getName(), result.getName());
        criteria2 = 2;
        expResult = new GlobalSearchExtension();
        result = ExtensionFactory.getExtension(criteria1, criteria2);
        assertEquals(expResult.getName(), result.getName());
        
        criteria1 = NetAnalyzerExtension.NAME;
        criteria2 = 1;
        expResult = new NetAnalyzerExtension();
        result = ExtensionFactory.getExtension(criteria1, criteria2);
        assertEquals(expResult.getName(), result.getName());
    }
    
}
