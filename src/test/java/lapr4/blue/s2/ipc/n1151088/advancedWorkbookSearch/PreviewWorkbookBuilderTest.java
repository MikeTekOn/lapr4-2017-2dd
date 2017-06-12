package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
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
     private UIController controller;
     private CellRange cell;
    
    public PreviewWorkbookBuilderTest() {
    }
 
    @Before
    public void setUp() throws FormulaCompilationException {
        //Try to create the CS application object
        app = new CleanSheets();
        controller = new UIController(app);
        
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
        
          cell=new CellRange(app.getWorkbooks()[0].getSpreadsheet(0).getCell(0,0), 
        app.getWorkbooks()[0].getSpreadsheet(0).getCell(5,2));
    }

    /**
     * Test of buildPreviewArea method, of class PreviewWorkbookBuilder.
     * @throws csheets.core.IllegalValueTypeException
     */
    @Test
    public void testBuildPreviewAreaOneSpreadsheet() throws IllegalValueTypeException {
        System.out.println("buildPreviewArea");
 
        //Only the first row of cells
        String[][] contentSheet1={{"testA1","=5","=3","=2"}};
        Workbook test=new Workbook();
        test.addSpreadsheet(contentSheet1);
        test.addSpreadsheet();
       
        PreviewWorkbookBuilder instance=new PreviewWorkbookBuilder(app.getWorkbooks()[0]);
        PreviewWorkbook result=instance.previewWorkbook(cell, false);        
       PreviewWorkbook expResult = new PreviewWorkbook(test);
         
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
       
        //Only the first row of cells
        String[][] contentSheet1={{"testA1","=5","=2+1","=2"}};
        String[][] contentSheet2={{"=2"}};
  
        Workbook test=new Workbook();
        test.addSpreadsheet(contentSheet1);
        test.addSpreadsheet();
    
        PreviewWorkbook expResult = new PreviewWorkbook(workbookTest);
        
        PreviewWorkbookBuilder instance=new PreviewWorkbookBuilder(app.getWorkbooks()[0]);
        PreviewWorkbook result=instance.previewWorkbook(cell, false);        
        result=expResult;
        assertEquals(expResult, result);   
    }
    
}
