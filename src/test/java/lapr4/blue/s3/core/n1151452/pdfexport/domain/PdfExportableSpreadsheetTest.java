package lapr4.blue.s3.core.n1151452.pdfexport.domain;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.formula.compiler.FormulaCompilationException;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;

/**
 * Tests a PDF exportable spreadsheet
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 16/06/17.
 */
public class PdfExportableSpreadsheetTest {

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
     * Tests if the exportable spreadsheet has a non null spreadsheet
     */
    @Test(expected = IllegalStateException.class)
    public void ensurePdfExportableSpreadsheetHasNonNullSpreadsheet() {

        PdfExportableSpreadsheet pdfSpreadsheet = new PdfExportableSpreadsheet(null);
    }

    /**
     * Tests if the PDF exportable spreadsheet exports cells
     *
     * @throws FormulaCompilationException if the cell's formula is invalid
     */
    @Test
    public void ensurePdfExportableSpreadsheetExportsCells() throws FormulaCompilationException {

        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        cellA1.setContent("A1");
        Cell cellB6 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 5));
        cellB6.setContent("B6");
        Spreadsheet spreadsheet = app.getWorkbooks()[0].getSpreadsheet(0);

        PdfExportableSpreadsheet pdfExportableSpreadsheet = new PdfExportableSpreadsheet(spreadsheet);
        Iterable<PdfExportableCell> result = pdfExportableSpreadsheet.exportCells();

        LinkedList<PdfExportableCell> expResult = new LinkedList<>();
        expResult.add(new PdfExportableCell(cellA1));
        expResult.add(new PdfExportableCell(cellB6));

        result.forEach((c) -> {
            assert expResult.contains(c);
        });
    }

    /**
     * Tests if the PDF exportable spreadsheet exports cells
     *
     * @throws FormulaCompilationException if the cell's formula is invalid
     */
    @Test
    public void ensurePdfExportableSpreadsheetExportsCellsRange() throws FormulaCompilationException {

        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        cellA1.setContent("A1");
        Cell cellB6 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(1, 5));
        cellB6.setContent("B6");
        Spreadsheet spreadsheet = app.getWorkbooks()[0].getSpreadsheet(0);

        PdfExportableSpreadsheet pdfExportableSpreadsheet = new PdfExportableSpreadsheet(spreadsheet);
        Iterable<PdfExportableCell> result = pdfExportableSpreadsheet.exportCells(cellA1.getAddress(), cellB6.getAddress());

        SortedSet<Cell> cells = spreadsheet.getCells(cellA1.getAddress(), cellB6.getAddress());

        LinkedList<PdfExportableCell> expResult = new LinkedList<>();

        cells.forEach((cell -> {
            expResult.add(new PdfExportableCell(cell));
        }));

        result.forEach((c) -> {
            assert expResult.contains(c);
        });
    }

    /**
     * Tests if the PDF exportable spreadsheet exports the selected cells
     *
     * @throws FormulaCompilationException if the cell's formula is invalid
     */
    @Test
    public void ensurePdfExportableSpreadsheetExportsSelection() throws FormulaCompilationException {

        List<Cell> selection = new LinkedList<>();

        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        cellA1.setContent("A1");
        selection.add(cellA1);

        Cell cellA2 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 1));
        cellA2.setContent("A2");
        selection.add(cellA2);

        Iterable<PdfExportableCell> result = PdfExportableSpreadsheet.exportSelection(selection);

        LinkedList<PdfExportableCell> expResult = new LinkedList<>();

        selection.forEach((cell -> {
            expResult.add(new PdfExportableCell(cell));
        }));

        result.forEach((c) -> {
            assert expResult.contains(c);
        });
    }
}