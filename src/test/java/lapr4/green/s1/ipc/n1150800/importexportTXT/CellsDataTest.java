/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Workbook;
import java.io.File;
import java.io.IOException;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Pedro Chilro (1150800)
 */
public class CellsDataTest {
    
    private CleanSheets cleansheet;
    private Workbook workbook;
    private File workbookFile;
    
    @Before
    public void setUp() throws IOException {
        CleanSheets.setFlag(true);
        cleansheet = new CleanSheets();
        
        String[][] spreadsheetContent = new String[1][2];
        spreadsheetContent[0][0] = "Hello";
        spreadsheetContent[0][1] = "World";
        workbook = new Workbook(null, spreadsheetContent);
        
        workbookFile = new File("workbookfile.cls");
        cleansheet.saveAs(workbook, workbookFile);
    }
    
    /**
     * Should be ignored until implementation? 
     * Add @Ignore tag in case so
     */
    @Test
    public void cellDataIsNotEmptyTest() {
        Address a1 = new Address(0, 0);
        Address b1 = new Address(1, 0);
        
        assertFalse(workbook.getSpreadsheet(0).getCell(a1) == null);
        assertFalse(workbook.getSpreadsheet(0).getCell(b1) == null);
    }
    
    @After
    public void cleanUp() {
        workbookFile.delete();
    }
}
