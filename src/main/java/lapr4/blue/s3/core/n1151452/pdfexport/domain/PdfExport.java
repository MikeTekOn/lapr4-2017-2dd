package lapr4.blue.s3.core.n1151452.pdfexport.domain;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import csheets.core.Address;
import csheets.core.Cell;
import lapr4.s1.export.ExportStrategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * A PDF exporter
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 17/06/17.
 */
public class PdfExport implements ExportStrategy {

    /**
     * Contains the active workbook
     */
    private PdfExportableWorkbook exportableWorkbook;

    /**
     * Contains the active spreadsheet
     */
    private PdfExportableSpreadsheet exportableSpreadsheet;

    /**
     * Contains the active cells
     */
    private List<Cell> activeCells;

    /**
     * The selected grid type
     */
    private GridType gridType;

    /**
     * The selected print area
     */
    private PrintArea printArea;

    /**
     * No sections (bitwise operations)
     */
    public static final int NO_SECTIONS = 0;

    /**
     * Macros section (bitwise operations)
     */
    public static final int MACROS = 1;

    /**
     * Comments section (bitwise operations)
     */
    public static final int COMMENTS = 2;

    /**
     * Formulas section (bitwise operations)
     */
    public static final int FORMULAS = 4;

    /**
     * Selected sections (bitwise operations)
     */
    private final int sections;

    /**
     * The page size
     */
    private Rectangle pageSize = PageSize.A4; // DEFAULT VALUE

    /**
     * The page orientation
     */
    private PageOrientation pageOrientation = PageOrientation.PORTRAIT;

    /**
     * The path for the file
     */
    private String path;

    /**
     * Constructs a PDF export
     *
     * @param theActiveCells the active cells
     * @param theSections    integer to represent the desired sections {use bitwise OR for more than one section}
     * @param aGridType      the selected grid type
     * @param aPrintArea     the selected print type
     * @param aPageSize      the selected page size (ex. A4, A3, letter, etc.)
     * @param anOrientation  the selected orientation (portrait or landscape)
     */
    public PdfExport(List<Cell> theActiveCells, int theSections,
                     GridType aGridType, PrintArea aPrintArea, Rectangle aPageSize, PageOrientation anOrientation, String path) {

        if (theActiveCells == null || theActiveCells.isEmpty())
            throw new IllegalStateException("The active cells can't be null or empty.");
        if (aGridType == null) throw new IllegalStateException("The grid type can't be null.");
        if (aPrintArea == null) throw new IllegalStateException("The print area can't be null.");
        if (path == null) throw new IllegalStateException("The path for the FDP file can't be null");

        pageSize = (aPageSize != null) ? aPageSize : PageSize.A4;
        pageOrientation = (anOrientation != null) ? anOrientation : PageOrientation.PORTRAIT;

        sections = theSections;

        gridType = aGridType;
        printArea = aPrintArea;
        exportableWorkbook = new PdfExportableWorkbook(theActiveCells.get(0).getSpreadsheet().getWorkbook());
        exportableSpreadsheet = new PdfExportableSpreadsheet(theActiveCells.get(0).getSpreadsheet());
        activeCells = theActiveCells;
    }

    @Override
    public boolean export() {

        // Open Document
        Document document = null;
        PdfWriter writer = null;
        try {
            // Creates the PDF document
            document = new Document(pageOrientation.orientation(pageSize));
            writer = PdfWriter.getInstance(document, new FileOutputStream(path));
            // Creates the index root structure
            PdfIndexCreation event = new PdfIndexCreation();
            writer.setPageEvent(event);
            document.open();
            event.setRoot(writer.getRootOutline());
            // Sets the margins
            document.setMargins(5, 5, 10, 10);

            // Export Spreadsheets
            Map<PdfExportableSpreadsheet, Iterable<PdfExportableCell>> map = getExportableCells();
            // Create table for each spreadsheet
            for (Map.Entry<PdfExportableSpreadsheet, Iterable<PdfExportableCell>> entry :
                    map.entrySet()) {
                PdfExportableSpreadsheet pdfSpreadsheet = entry.getKey();
                Iterable<PdfExportableCell> cells = entry.getValue();

                // Add spreadsheet Title
                Chunk title = pdfSpreadsheet.title();
                title.setFont(new Font(Font.FontFamily.COURIER, 6, Font.BOLD | Font.UNDERLINE));
                title.setGenericTag(title.getContent());
                // Table settings
                float[] columnWidths = pdfSpreadsheet.columnWidths();
                float[] rowHeights = pdfSpreadsheet.rowHeights();
                PdfPTable table = new PdfPTable(columnWidths);
                table.setWidthPercentage(100);
                // Sets the grid layout
                setGrid(table);
                // Create Header
                // TODO : make optional
                createHeader((List<PdfExportableCell>) cells, table);
                // Loops cells
                Iterator<PdfExportableCell> iterator = cells.iterator();
                int numColumns = columnWidths.length - 1; // Header already included
                int numRows = rowHeights.length - 1; // Header already included
                for (int i = 0; i < numRows; i++) {

                    // Add row header
                    table.addCell(new PdfPCell(new Phrase(String.valueOf(i + 1))));

                    // Next non-empty cell
                    PdfExportableCell next = (iterator.hasNext()) ? iterator.next() : null;

                    for (int j = 0; j < numColumns; j++) {

                        Address cellAddress = new Address(j, i);
                        if (next != null && cellAddress.equals(next.address())) {

                            table.addCell(next.exportCell());
                            next = (iterator.hasNext()) ? iterator.next() : null;

                            // TODO : Add sections
                        }
                    }
                }

                document.add(table);
            }


        } catch (DocumentException | IOException e) {
            throw new RuntimeException("File could not be created.");
        } finally {
            if (document != null) document.close();
            if (writer != null) writer.close();
        }
        return (new File(path)).exists();
    }

    /**
     * Obtains all PDF exportable cells for the selected print area
     *
     * @return all PDF exportable cells for the selected print area
     */
    private Map<PdfExportableSpreadsheet, Iterable<PdfExportableCell>> getExportableCells() {

        switch (printArea) {

            case WORKBOOK:
                return exportFromWorkbook();
            case ACTIVE_SPREADSHEET:
                return exportFromActiveSpreadsheet();
            default:
                return exportFromSelection();
        }
    }

    /**
     * Exports to PDF exportable from all cells of the workbook
     *
     * @return PDF exportable cells from all cells for the workbook
     */
    private Map<PdfExportableSpreadsheet, Iterable<PdfExportableCell>> exportFromWorkbook() {

        Map<PdfExportableSpreadsheet, Iterable<PdfExportableCell>> cellMap = new HashMap<>();

        exportableWorkbook.exportSpreadsheets().forEach((pdfExportableSpreadsheet -> {

            cellMap.put(pdfExportableSpreadsheet, pdfExportableSpreadsheet.exportCells());
        }));

        return cellMap;
    }

    /**
     * Exports to PDF exportable from all cells of the active spreadsheet
     *
     * @return PDF exportable cells from all cells of the active spreadsheet
     */
    private Map<PdfExportableSpreadsheet, Iterable<PdfExportableCell>> exportFromActiveSpreadsheet() {

        Map<PdfExportableSpreadsheet, Iterable<PdfExportableCell>> cellMap = new HashMap<>();

        cellMap.put(exportableSpreadsheet, exportableSpreadsheet.exportCells());

        return cellMap;
    }

    /**
     * Exports to PDF exportable cells from the selected cells
     *
     * @return PDF exportable cells from cells from the selected cells
     */
    private Map<PdfExportableSpreadsheet, Iterable<PdfExportableCell>> exportFromSelection() {

        Map<PdfExportableSpreadsheet, Iterable<PdfExportableCell>> cellMap = new HashMap<>();

        cellMap.put(exportableSpreadsheet, PdfExportableSpreadsheet.exportSelection(activeCells));

        return cellMap;
    }

    private void createHeader(List<PdfExportableCell> cells, PdfPTable table) {

        // Add first empty cell
        table.addCell(new PdfPCell());

        Collections.sort(cells); // Sort cells

        char column = (char) ('A' + cells.get(0).address().getColumn());
        for (int i = 0; i < cells.size(); i++) {

            table.addCell(new PdfPCell(new Phrase(String.valueOf(column++))));
        }
    }

    /**
     * Sets the default grid type
     *
     * @param table table to set border
     */
    private void setGrid(PdfPTable table) {

        switch (gridType) {
            case NO_GRID:
                table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                break;
            case SOLID:
                table.getDefaultCell().setUseBorderPadding(true);
                table.getDefaultCell().setBorderColor(BaseColor.BLACK); // TODO
                break;
            case DOTTED:
                table.getDefaultCell().setCellEvent(new DottedBorderCellEvent(BaseColor.BLUE)); // TODO
        }
    }

    // TODO : Color in constructor

    /**
     * Nested class event necessary for making the dotted lines of a border
     */
    private class DottedBorderCellEvent implements PdfPCellEvent {

        /**
         * The border color
         */
        private BaseColor color;

        /**
         * Builds an instance of DottedBorderCellEvent with the border color
         *
         * @param color - the border color
         */
        private DottedBorderCellEvent(BaseColor color) {
            this.color = color;
        }

        @Override
        public void cellLayout(PdfPCell cell, Rectangle rec, PdfContentByte[] cont) {
            PdfContentByte canvas = cont[PdfPTable.LINECANVAS];
            canvas.setLineDash(3f, 3f);
            canvas.setColorStroke(color);
            canvas.stroke();
        }

    }
}
