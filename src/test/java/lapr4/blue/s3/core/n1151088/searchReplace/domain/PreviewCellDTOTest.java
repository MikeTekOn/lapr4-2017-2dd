package lapr4.blue.s3.core.n1151088.searchReplace.domain;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.formula.compiler.FormulaCompilationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Preview cell
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class PreviewCellDTOTest {
    
    private CleanSheets app;
    private Cell beforeCell;
    
    @Before
    public void setUp() throws FormulaCompilationException {
          // Try to create the CS application object
        CleanSheets.setFlag(true);
        app = new CleanSheets();
        // This will create a workbook with 3 sheets
        app.create();
       
        
        beforeCell= app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0,0));
       
    }
    
    @After
    public void tearDown() {
    }
    
     /**
     * Test to ensure that content entered is valid.
     * @throws java.lang.Exception in case of formula compilation error
     */
    @Test
    public void ensureReplaceContentOK() throws Exception {
        System.out.println("ensureReplaceContentOK");
        
        String contentA1="=1+2";
        beforeCell.setContent(contentA1);
       
        String replace="2";
        PreviewCellDTO instance = new PreviewCellDTO(beforeCell, "1", replace);
        instance.previewReplace();
        
        String result=instance.getAfterCell().getValue().toString();
        String expResult="4";
        assertEquals(expResult, result);
    }
    
      /**
     * Test to ensure that content entered is valid.
     * @throws java.lang.Exception in case of formula compilation error
     */
    @Test
    public void rejectReplaceContentInvalid() throws Exception {
        System.out.println("ensureBeforeCellIsValid");
        
        String contentA1="{_Var=1}";
        beforeCell.setContent(contentA1);
       
    }
    
     
    /**
     * Test to ensure that content entered is valid.
     * @throws java.lang.Exception in case of formula compilation error
     */
    @Test (expected = IllegalArgumentException.class)
    public void ensureReplaceValueIsOk() throws Exception {
        System.out.println("ensureBeforeCellIsValid");
        
        String contentA1="doesn´t contain number one";
        beforeCell.setContent(contentA1);
       
        String replace="2";
        PreviewCellDTO instance = new PreviewCellDTO(beforeCell, "1", replace);
        instance.previewReplace();
        
        String result=instance.getAfterCell().getValue().toString();
        String expResult="4";
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of previewReplace method, of class PreviewCellDTO.
     * @throws java.lang.Exception in case of formula compilation error
     */
    @Test (expected = IllegalArgumentException.class)
    public void ensureBeforeCellOk() throws Exception {
        System.out.println("testReplaceCellText");
        
        String contentA1="replaceTestA1";
        beforeCell.setContent(contentA1);
       
        String replace="preview";
        PreviewCellDTO instance = new PreviewCellDTO(null, "Test", replace);
        instance.previewReplace();
        
        String result=instance.getAfterCell().getValue().toString();
        String expResult="replacepreviewA1";
        assertEquals(expResult, result);
    }
     
    /**
     * Test to content entered is valid.
     * @throws java.lang.Exception in case of formula compilation error
     */
    @Test (expected = IllegalArgumentException.class)
    public void ensureReplacedCalculatedsValueIsOk() throws Exception{
        System.out.println("ensureReplaceValueIsOk");
        
        String contentA1="doesn´t contain number one";
        beforeCell.setContent(contentA1);
       
        String replace="2";
        PreviewCellDTO instance = new PreviewCellDTO(beforeCell, "1", replace);
        instance.previewReplace();
        
        String result=instance.getAfterCell().getValue().toString();
        String expResult="4";
        assertEquals(expResult, result);
    }
    
 
    public void rejectReplaceContentWithInvalidResultExpression() throws IllegalValueTypeException, FormulaCompilationException{
    
        System.out.println("ensureReplaceContentOK");

        String contentA1="=1+2";
        beforeCell.setContent(contentA1);
       
        String replace="/*";
        PreviewCellDTO instance = new PreviewCellDTO(beforeCell, "+", replace);
        instance.previewReplace();
        
        String result=instance.getAfterCell().getValue().toString();
        String expResult="4";
        assertEquals(expResult, result);
    }
    /*
    
   
    
 * <p>rejectReplaceContentWithInvalidExpression() - tests if the replace text inserted 
 * is valid</p>
 * <p>rejectReplaceIsNotEmpty() - tests if the replace text inserted is fullfilled</p>
 * <p>ensureReplaceCellText() - tests if replace a cell of type Text</p>
 * <p>ensureReplaceReplaceCellOperation() - tests if replace value caused by replace of formula</p>
 * <p>rejectReplaceWithInvalidFormula - test if the replace text is an invalid
 * formula</p>
 * <p>ensureReplaceWithValidFormula - tests if content of cell is updated with replace</p>
 * <p>replaceComment - tests if comment of cell is updated with replace</p>
 * <p>showForeignerContentOfFoundString() - tests if displays only the adjacent
 * text</p>
    */
   
    /**
     * Test of previewReplace method, of class PreviewCellDTO.
     * @throws java.lang.Exception in case of formula compilation error
     */
    @Test
    public void testReplaceCellText() throws Exception {
        System.out.println("testReplaceCellText");
        
        String contentA1="replaceTestA1";
        beforeCell.setContent(contentA1);
       
        String replace="preview";
        PreviewCellDTO instance = new PreviewCellDTO(beforeCell, "Test", replace);
        instance.previewReplace();
        
        String result=instance.getAfterCell().getValue().toString();
        String expResult="replacepreviewA1";
        assertEquals(expResult, result);
    }
    
     
}
