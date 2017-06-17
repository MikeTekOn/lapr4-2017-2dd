package lapr4.blue.s3.core.n1151452.pdfexport.domain;

import csheets.CleanSheets;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import lapr4.red.s2.lang.n1150451.multipleMacros.domain.MacroWithName;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests a PDF exportable workbook
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 17/06/17.
 */
public class PdfExportableWorkbookTest {

    /**
     * App's instance
     */
    private CleanSheets app;

    /**
     * Test setup
     *
     * @throws Exception catches any exception
     */
    @Before
    public void setUp() throws Exception {

        // Try to create the CS application object
        CleanSheets.setFlag(true);
        app = new CleanSheets();
        // This will create a workbook with 3 sheets
        app.create();
    }

    /**
     * Tests if the PDF exportable workbook has a non null workbook
     */
    @Test (expected = IllegalStateException.class)
    public void ensurePdfExportableWorkbookHasNonNullWorkbook() {

        PdfExportableWorkbook pdfWorkbook = new PdfExportableWorkbook(null);
    }

    /**
     * Tests if the PDF exportable workbook exports the spreadsheets
     */
    @Test
    public void ensurePdfExportableWorkbookExportsSpreadsheets() {

        Workbook workbook = app.getWorkbooks()[0];

        Iterable<PdfExportableSpreadsheet> result = (new PdfExportableWorkbook(workbook)).exportSpreadsheets();

        List<PdfExportableSpreadsheet> expResult = new LinkedList<>();
        workbook.iterator().forEachRemaining((spreadsheet) -> {
            expResult.add(new PdfExportableSpreadsheet(spreadsheet));
        });

        result.forEach((pdfSpreadsheet) -> {
            assert expResult.contains(pdfSpreadsheet);
        });
    }

    /**
     * Tests if the PDF exportable workbook exports the correct spreadsheet
     */
    @Test
    public void ensurePdfExportableWorkbookExportsSpreadsheetWithCorrectIndex() {

        Workbook workbook = app.getWorkbooks()[0];

        PdfExportableSpreadsheet result = (new PdfExportableWorkbook(workbook)).exportSpreadsheet(0);

        PdfExportableSpreadsheet expResult = new PdfExportableSpreadsheet(workbook.getSpreadsheet(0));

        assertEquals(result, expResult);
    }

    /**
     * Tests if the PDF exportable workbook exports the macros
     */
    @Test
    public void ensurePdfExportableWorkbookExportsMacros() {

        MacroWithName macro = new MacroWithName("Macro1", ";Test1", app.getWorkbooks()[0].getSpreadsheet(0), null);

        Workbook workbook = app.getWorkbooks()[0];
        workbook.getMacroList().addMacro(macro);

        Iterable<PdfExportableMacro> result = (new PdfExportableWorkbook(workbook)).exportMacros();

        List<PdfExportableMacro> expResult = new LinkedList<>();
        workbook.getMacroList().getMacroList().forEach((macroWithName -> {

            expResult.add(new PdfExportableMacro(macroWithName));
        }));

        assertEquals(result, expResult);
    }
}