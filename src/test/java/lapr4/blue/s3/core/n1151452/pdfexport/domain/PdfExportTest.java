package lapr4.blue.s3.core.n1151452.pdfexport.domain;

import com.itextpdf.text.PageSize;
import csheets.CleanSheets;
import csheets.core.Cell;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests the PDF export
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 17/06/17.
 */
public class PdfExportTest {

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
     * Tests if PdfExport has non null list of cells
     */
    @Test(expected = IllegalStateException.class)
    public void ensurePdfExportHasNonNullListOfActiveCells() {

        PdfExport pdfExport = new PdfExport(null, PdfExport.MACROS|PdfExport.COMMENTS| PdfExport.FORMULAS,
                GridType.NO_GRID, PrintArea.WORKBOOK, PageSize.A4, PageOrientation.PORTRAIT, "dest");
    }

    /**
     * Tests if PdfExport has non empty list of cells
     */
    @Test(expected = IllegalStateException.class)
    public void ensurePdfExportHasNonEmptyListOfActiveCells() {

        PdfExport pdfExport = new PdfExport(new ArrayList<>(), PdfExport.MACROS,
                GridType.NO_GRID, PrintArea.WORKBOOK, PageSize.A4, PageOrientation.PORTRAIT, "dest");
    }

    /**
     * Tests if PdfExport has non null grid type
     */
    @Test(expected = IllegalStateException.class)
    public void ensurePdfExportHasNonNullGridType() {

        List<Cell> activeCells = new ArrayList<>();
        activeCells.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(0, 0));

        PdfExport pdfExport = new PdfExport(activeCells, PdfExport.MACROS | PdfExport.COMMENTS,
                null, PrintArea.WORKBOOK, PageSize.A4, PageOrientation.PORTRAIT, "dest");
    }

    /**
     * Tests if PdfExport has non null print area
     */
    @Test(expected = IllegalStateException.class)
    public void ensurePdfExportHasNonNullPrintArea() {

        List<Cell> activeCells = new ArrayList<>();
        activeCells.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(0, 0));

        PdfExport pdfExport = new PdfExport(activeCells, PdfExport.NO_SECTIONS, GridType.NO_GRID,
                null, PageSize.A4, PageOrientation.PORTRAIT, "dest");
    }

    /**
     * Tests if PdfExport has non null path
     */
    @Test(expected = IllegalStateException.class)
    public void ensurePdfExportHasNonNullPath() {

        List<Cell> activeCells = new ArrayList<>();
        activeCells.add(app.getWorkbooks()[0].getSpreadsheet(0).getCell(0, 0));

        PdfExport pdfExport = new PdfExport(activeCells, PdfExport.NO_SECTIONS, GridType.NO_GRID,
                PrintArea.ACTIVE_SPREADSHEET, PageSize.A3, PageOrientation.LANDSCAPE, null);
    }
}