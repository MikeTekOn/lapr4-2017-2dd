//package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch;
//
//import csheets.CleanSheets;
//import csheets.core.Address;
//import csheets.core.Cell;
//import csheets.core.Workbook;
//import csheets.core.formula.compiler.FormulaCompilationException;
//import static org.junit.Assert.assertEquals;
//import org.junit.Before;
//import org.junit.Test;
//
///**
// *
// * @author Diana Silva [1151088@isep.ipp.pt]
// */
//public class PreviewWorkbookBuilderTest {
//    
//     private CleanSheets app;
//     String contentA1="testA1", contentA2="{(_Var:=1+2); _Var1+1}", 
//        contentB5="=(B1:=3+2)";
//    
//    public PreviewWorkbookBuilderTest() {
//    }
// 
//    @Before
//    public void setUp() throws FormulaCompilationException {
//         // Try to create the CS application object
//        app = new CleanSheets();
//        
//        //This will build workbook to preview
//        app.create(); 
//
//        Cell cellA1, cellA2, cellB5;
//      
//        cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));        
//        cellA2 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 0));
//        cellB5 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(4, 1));
//        
//        cellA1.setContent(contentA1);
//        cellA2.setContent(contentA2);
//        cellB5.setContent(contentB5);
//    }
//
////    /**
////     * Test of buildPreviewArea method, of class PreviewWorkbookBuilder.
////     */
////    @Test
////    public void testBuildPreviewAreaOneSpreadsheet() {
////        System.out.println("buildPreviewArea");
//// 
////        Workbook workbook =  app.getWorkbooks()[0];
////        
////        PreviewWorkbookBuilder instance=new PreviewWorkbookBuilder(workbook);
////        
////        //Only the first row of cells
////        String[][] content={{"testA1","{(_Var:=1+2); _Var1+1}"}};
////        
////        Workbook test=new Workbook();    
////        test.addSpreadsheet(content);
////    
////        PreviewWorkbook expResult = new PreviewWorkbook(test);
////        PreviewWorkbook result = instance.buildPreviewArea();
////        assertEquals(expResult, result);
////
////    }
////    
////      /**
////     * Test of buildPreviewArea method, of class PreviewWorkbookBuilder.
////     */
////    @Test
////    public void testBuildPreviewAreaManySpreadsheet() throws FormulaCompilationException {
////        System.out.println("buildPreviewAreaManySpreadsheet");
////        
////        Cell cellAnotherSheet = app.getWorkbooks()[0].getSpreadsheet(2).getCell(new Address(4, 1));
////        String contentAnotherSheet="=1+1";
////        cellAnotherSheet.setContent(contentAnotherSheet);
////        
////        Workbook workbook =  app.getWorkbooks()[0];
////        
////        PreviewWorkbookBuilder instance=new PreviewWorkbookBuilder(workbook);
////        
////      
////        PreviewWorkbook expResult = null;
////        PreviewWorkbook result = instance.buildPreviewArea();
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
//    
//}
