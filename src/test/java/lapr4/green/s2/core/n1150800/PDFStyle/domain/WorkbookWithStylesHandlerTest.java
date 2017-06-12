/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150800.PDFStyle.domain;

import csheets.CleanSheets;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ui.ctrl.UIController;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.SwingConstants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class WorkbookWithStylesHandlerTest {
    
    private CleanSheets cleansheet;
    private UIController uiC;
    private Workbook workbook;
    private File workbookFile;
    private Spreadsheet spreadsheet1;
    private Spreadsheet spreadsheet2;
    
    private StylableCell stylableCell1;
    private StylableCell stylableCell2;
    private StylableCell stylableCell3;
    private StylableCell stylableCell4;
    
    @Before
    public void setUp() throws IOException, FormulaCompilationException {
        CleanSheets.setFlag(true);
        cleansheet = new CleanSheets();
        uiC = new UIController(cleansheet);
        workbook = new Workbook();
        workbook.addSpreadsheet();
        workbook.addSpreadsheet();
        workbookFile = new File("workbookfile.cls");
        cleansheet.saveAs(workbook, workbookFile);
        
        spreadsheet1 = workbook.getSpreadsheet(0);
        spreadsheet2 = workbook.getSpreadsheet(1);
        
        stylableCell1 = (StylableCell) spreadsheet2.getCell(0, 0).getExtension(StyleExtension.NAME);
        stylableCell1.setContent("Hello");
        stylableCell1.setBackgroundColor(Color.BLACK);
        stylableCell1.setForegroundColor(Color.WHITE);
        
        stylableCell2 = (StylableCell) spreadsheet2.getCell(1, 0).getExtension(StyleExtension.NAME);
        stylableCell2.setContent("World");
        stylableCell2.setFont(new Font(Font.DIALOG, 6, Font.BOLD));
        stylableCell2.setHorizontalAlignment(SwingConstants.CENTER);
        
        stylableCell3 = (StylableCell) spreadsheet1.getCell(0, 0).getExtension(StyleExtension.NAME);
        stylableCell3.setContent("Hello");
        
        stylableCell4 = (StylableCell) spreadsheet1.getCell(1, 0).getExtension(StyleExtension.NAME);
        stylableCell4.setContent("World");
    }

    /**
     * Test of getStylableCellsFromWorkbook method, of class WorkbookWithStylesHandler.
     */
    @Test
    public void testGetStylableCellsFromWorkbook() {
        System.out.println("getStylableCellsFromWorkbook");
        WorkbookWithStylesHandler instance = new WorkbookWithStylesHandler(workbook);
        List<StylableCell> cells = instance.getStylableCellsFromWorkbook();
        
        StylableCell c1 = null;
        for (StylableCell cell : cells) {
            if(cell.getAddress().equals(stylableCell1.getAddress()) && cell.getSpreadsheet().equals(stylableCell1.getSpreadsheet())) {
                c1 = cell;
            }
        }
        
        StylableCell c2 = null;
        for (StylableCell cell : cells) {
            if(cell.getAddress().equals(stylableCell2.getAddress()) && cell.getSpreadsheet().equals(stylableCell2.getSpreadsheet())) {
                c2 = cell;
            }
        }
        
        StylableCell c3 = null;
        for (StylableCell cell : cells) {
            if(cell.getAddress().equals(stylableCell3.getAddress()) && cell.getSpreadsheet().equals(stylableCell3.getSpreadsheet())) {
                c3 = cell;
            }
        }
        
        StylableCell c4 = null;
        for (StylableCell cell : cells) {
            if(cell.getAddress().equals(stylableCell4.getAddress()) && cell.getSpreadsheet().equals(stylableCell4.getSpreadsheet())) {
                c4 = cell;
            }
        }
        
        assertEquals(stylableCell1.getBackgroundColor(), c1.getBackgroundColor());
        assertEquals(stylableCell1.getForegroundColor(), c1.getForegroundColor());
        assertEquals(stylableCell1.getFont(), c1.getFont());
        assertEquals(stylableCell1.getHorizontalAlignment(), c1.getHorizontalAlignment());
        
        assertEquals(stylableCell2.getBackgroundColor(), c2.getBackgroundColor());
        assertEquals(stylableCell2.getForegroundColor(), c2.getForegroundColor());
        assertEquals(stylableCell2.getFont(), c2.getFont());
        assertEquals(stylableCell2.getHorizontalAlignment(), c2.getHorizontalAlignment());
        
        assertEquals(stylableCell3.getBackgroundColor(), c3.getBackgroundColor());
        assertEquals(stylableCell3.getForegroundColor(), c3.getForegroundColor());
        assertEquals(stylableCell3.getFont(), c3.getFont());
        assertEquals(stylableCell3.getHorizontalAlignment(), c3.getHorizontalAlignment());
        
        assertEquals(stylableCell4.getBackgroundColor(), c4.getBackgroundColor());
        assertEquals(stylableCell4.getForegroundColor(), c4.getForegroundColor());
        assertEquals(stylableCell4.getFont(), c4.getFont());
        assertEquals(stylableCell4.getHorizontalAlignment(), c4.getHorizontalAlignment());
    }

    /**
     * Test of getStylableCellsFrom method, of class WorkbookWithStylesHandler.
     */
    @Test
    public void testGetStylableCellsFrom() {
        System.out.println("getStylableCellsFrom");
        WorkbookWithStylesHandler instance = new WorkbookWithStylesHandler(workbook);
        List<StylableCell> cells = instance.getStylableCellsFrom(spreadsheet2);
        
        StylableCell c1 = null;
        for (StylableCell cell : cells) {
            if(cell.getAddress().equals(stylableCell1.getAddress()) && cell.getSpreadsheet().equals(stylableCell1.getSpreadsheet())) {
                c1 = cell;
            }
        }
        
        assertEquals(stylableCell1.getBackgroundColor(), c1.getBackgroundColor());
        assertEquals(stylableCell1.getForegroundColor(), c1.getForegroundColor());
    }

    /**
     * Test of getStylableCellsFromSpreadsheetWithinRange method, of class WorkbookWithStylesHandler.
     */
    @Test
    public void testGetStylableCellsFromSpreadsheetWithinRange() {
        System.out.println("getStylableCellsFromSpreadsheetWithinRange");
        String strRange = "A1:B1";
        uiC.setActiveSpreadsheet(spreadsheet2);
        ExportStylePDF ePDF = new ExportStylePDF();
        WorkbookWithStylesHandler instance = new WorkbookWithStylesHandler(workbook);
        List<StylableCell> cells = instance.getStylableCellsFromSpreadsheetWithinRange(spreadsheet2, strRange, uiC, ePDF);
        
        StylableCell c1 = null;
        for (StylableCell cell : cells) {
            if(cell.getAddress().equals(stylableCell1.getAddress())) {
                c1 = cell;
            }
        }
        
        assertEquals(stylableCell1.getBackgroundColor(), c1.getBackgroundColor());
        assertEquals(stylableCell1.getForegroundColor(), c1.getForegroundColor());
    }
    
    @After
    public void cleanUp() {
        workbookFile.delete();
    }
    
}
