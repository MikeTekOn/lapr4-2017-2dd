package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch;

import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.PreviewWorkbook;
import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class PreviewWorkbookTest {
    
    private CleanSheets app;
   
    public PreviewWorkbookTest() {
    }

    @Before
    public void setUp() throws FormulaCompilationException {
         // Try to create the CS application object
        app = new CleanSheets();
        // This will create a workbook with 3 sheets
        app.create();        
    }

    /**
     * Test of nrSpreadSheetsFilled method, of class PreviewWorkbook.
     * @throws csheets.core.formula.compiler.FormulaCompilationException
     */
    @Test
    public void testNrSpreadSheetsFilledThree() throws FormulaCompilationException {
        System.out.println("nrSpreadSheetsFilled");
       
        Cell cellA1,cellA2, cellA3;
        String contentA1="testA1";

        cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));        
        cellA2 = app.getWorkbooks()[0].getSpreadsheet(1).getCell(new Address(1, 0));
        cellA3 = app.getWorkbooks()[0].getSpreadsheet(2).getCell(new Address(2, 3));
        
        cellA1.setContent(contentA1);
        cellA2.setContent(contentA1);
        cellA3.setContent(contentA1);
        
        Workbook workbook =  app.getWorkbooks()[0];
        PreviewWorkbook instance = new PreviewWorkbook(workbook);
        
        int expResult = 2;
        int result = instance.nrSpreadSheetsFilled();
        assertEquals(expResult, result);
    }

    /**
     * Test of isFilled method, of class PreviewWorkbook.
     * @throws csheets.core.formula.compiler.FormulaCompilationException
     */
    @Test
    public void isFilled() throws FormulaCompilationException {
        System.out.println("isFilled");
        
         Cell cellA1;
        String contentA1="testA1";

        cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        cellA1.setContent(contentA1);
        
        Workbook workbook =  app.getWorkbooks()[0];
        PreviewWorkbook instance = new PreviewWorkbook(workbook);
        boolean expResult = true;
        boolean result = instance.isFilled(workbook);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of isFilled method, of class PreviewWorkbook.
     * @throws csheets.core.formula.compiler.FormulaCompilationException
     * @throws IllegalStateException if workbook to preview only have cell with space
     */
    @Test(expected= IllegalStateException.class) 
    public void isFilledWithSpace() throws FormulaCompilationException {
        System.out.println("isFilledWithSpace");
        
        String contentSpace=" ";
        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        cellA1.setContent(contentSpace);
        
        Workbook workbook =  app.getWorkbooks()[0];
        PreviewWorkbook instance = new PreviewWorkbook(workbook);
    }
    
    /**
     * Test of isFilled method, of class PreviewWorkbook.
     * @throws csheets.core.formula.compiler.FormulaCompilationException
     * @throws IllegalStateException if workbook to preview only have cell without content
     */
    @Test(expected= IllegalStateException.class) 
    public void isNotFilled() throws FormulaCompilationException {
        System.out.println("isNotFilled");
        
        String  contentWithout="";
        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        cellA1.setContent(contentWithout);
        
        Workbook workbook =  app.getWorkbooks()[0];
        PreviewWorkbook instance = new PreviewWorkbook(workbook);
        boolean expResult = false;
        boolean result = instance.isFilled(workbook);
        assertEquals(expResult, result);
    }
    
}
