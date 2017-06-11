package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch;

import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.PreviewWorkbookBuilder;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.PreviewWorkbook;
import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class PreviewWorkbookBuilderTest {
    
     private CleanSheets app;
     String contentA1="testA1", contentA2="{(_Var:=1+2); _Var+1}", 
        contentB5="=(B1:=3+2)", contentA3="=2+1", contentA4="=2";
    
    public PreviewWorkbookBuilderTest() {
    }
 
    @Before
    public void setUp() throws FormulaCompilationException {
        //Try to create the CS application object
        app = new CleanSheets();
        
        //This will build workbook to preview
        app.create();
      
        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));        
        Cell cellA2 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 0));
        Cell cellA3 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(2, 0));        
        Cell cellA4 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(3, 0));
        Cell cellB5 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(4, 1));
        
        cellA1.setContent(contentA1);
        cellA2.setContent(contentA2);
        cellA3.setContent(contentA3);
        cellA4.setContent(contentA4);
        cellB5.setContent(contentB5);
    }

    /**
     * Test of buildPreviewArea method, of class PreviewWorkbookBuilder.
     * @throws csheets.core.IllegalValueTypeException
     */
    @Test
    public void testBuildPreviewAreaOneSpreadsheet() throws IllegalValueTypeException {
        System.out.println("buildPreviewArea");
 
        Workbook workbook =  app.getWorkbooks()[0];
        
        PreviewWorkbookBuilder instance=new PreviewWorkbookBuilder(workbook);
        
        //Only the first row of cells
        String[][] contentSheet1={{"testA1","5","3","2"}};
       
        Workbook workbookTest=new Workbook(contentSheet1);
       workbookTest.addSpreadsheet();
       workbookTest.addSpreadsheet();
       workbookTest.addSpreadsheet();
    
        PreviewWorkbook expResult = new PreviewWorkbook(workbookTest);
        PreviewWorkbook result = new PreviewWorkbook(workbookTest);
        assertEquals(expResult, result);

    }
    
      /**
     * Test of buildPreviewArea method, of class PreviewWorkbookBuilder.
     * @throws csheets.core.formula.compiler.FormulaCompilationException
     * @throws csheets.core.IllegalValueTypeException
     */
    @Test
    public void testBuildPreviewAreaManySpreadsheet() throws FormulaCompilationException, IllegalValueTypeException {
        System.out.println("buildPreviewAreaManySpreadsheet");
        
        Cell cellAnotherSheet = app.getWorkbooks()[0].getSpreadsheet(2).getCell(new Address(4, 1));
        String contentAnotherSheet="=1+1";
        cellAnotherSheet.setContent(contentAnotherSheet);
        
        Workbook workbookTest =  app.getWorkbooks()[0];
       workbookTest.addSpreadsheet();
       workbookTest.addSpreadsheet();
       workbookTest.addSpreadsheet();
       
        PreviewWorkbookBuilder instance=new PreviewWorkbookBuilder(workbookTest);
        
        //Only the first row of cells
        String[][] content={{"testA1","5","3","2"}};
  
        workbookTest.getSpreadsheet(1).getCell(4, 1).setContent(contentAnotherSheet);
    
        PreviewWorkbook expResult = new PreviewWorkbook(workbookTest);
        PreviewWorkbook result = new PreviewWorkbook(workbookTest);
        assertEquals(expResult, result);
        
    }
    
}
