package lapr4.blue.s3.core.n1151452.pdfexport.domain;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import csheets.core.Address;
import csheets.core.Cell;
import lapr4.blue.s3.core.n1151452.pdfexport.util.PdfUtil;
import lapr4.s1.export.ExportStrategy;

import java.awt.*;
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
    private final List<Cell> activeCells;

    /**
     * The selected grid type
     */
    private final GridType gridType;

    /**
     * The selected grid color
     */
    private final BaseColor gridBaseColor;

    /**
     * The selected print area
     */
    private final PrintArea printArea;

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
    private final Rectangle pageSize;

    /**
     * The page orientation
     */
    private final PageOrientation pageOrientation;

    /**
     * The path for the file
     */
    private final String path;

    /**
     * If true include headers
     */
    private final boolean headers;

    /**
     * Constructs a PDF export
     *
     * @param theActiveCells the active cells
     * @param theSections    integer to represent the desired sections {use bitwise OR for more than one section}
     * @param aGridType      the selected grid type
     * @param gridColor      the grid's color (can be nullable)
     * @param withHeaders    include headers
     * @param aPrintArea     the selected print type
     * @param aPageSize      the selected page size (ex. A4, A3, letter, etc.)
     * @param anOrientation  the selected orientation (portrait or landscape)
     */
    public PdfExport(List<Cell> theActiveCells, int theSections, boolean withHeaders,
                     GridType aGridType, Color gridColor, PrintArea aPrintArea, Rectangle aPageSize,
                     PageOrientation anOrientation, String aPath) {

        if (theActiveCells == null || theActiveCells.isEmpty())
            throw new IllegalStateException("The active cells can't be null or empty.");
        if (aGridType == null) throw new IllegalStateException("The grid type can't be null.");
        if (aPrintArea == null) throw new IllegalStateException("The print area can't be null.");
        if (aPath == null) throw new IllegalStateException("The path for the PDF file can't be null");
        path = aPath;
        pageSize = (aPageSize != null) ? aPageSize : PageSize.A4; // DEFAULT VALUE
        pageOrientation = (anOrientation != null) ? anOrientation : PageOrientation.PORTRAIT; // DEFAULT VALUE

        sections = theSections;
        headers = withHeaders;
        gridType = aGridType;
        gridBaseColor = PdfUtil.convertToBaseColor(gridColor);
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
            // Sets the margins
            document.setMargins(10, 10, 20, 10);
            document.open();
            event.setRoot(writer.getRootOutline());

            // Export Spreadsheets
            Map<PdfExportableSpreadsheet, Iterable<PdfExportableCell>> map = getExportableCells();
            // Create table for each spreadsheet
            for (Map.Entry<PdfExportableSpreadsheet, Iterable<PdfExportableCell>> entry :
                    map.entrySet()) {
                PdfExportableSpreadsheet pdfSpreadsheet = entry.getKey();
                Iterable<PdfExportableCell> cells = entry.getValue();

                if (cells.iterator().hasNext()) {

                    // List of sections
                    Map<Anchor, Phrase> formulaAnchors = new HashMap<>();
                    Map<Anchor, Paragraph> commentAnchors = new HashMap<>();

                    // Add spreadsheet Title
                    Chunk title = pdfSpreadsheet.title();
                    title.setFont(new Font(Font.FontFamily.COURIER, 12, Font.BOLD | Font.UNDERLINE, BaseColor.BLUE));
                    title.setGenericTag(title.getContent());
                    // Table settings
                    float[] columnWidths = pdfSpreadsheet.columnWidths(headers);

                    float[] rowHeights = pdfSpreadsheet.rowHeights(headers);
                    PdfPTable table = new PdfPTable(columnWidths.length);
                    table.setTotalWidth(columnWidths);
                    table.setSplitRows(true);

                    int tableWidth = 0;
                    for (int i = 0; i < columnWidths.length; i++) {
                        tableWidth += columnWidths[i];
                    }

                    if (tableWidth > pageOrientation.orientation(pageSize).getWidth()) table.setWidthPercentage(100);
                    // Sets the grid layout
//                setGrid(table);
                    // Create Header
                    int numColumns = columnWidths.length - 1; // Header already included
                    int numRows = rowHeights.length - 1; // Header already included
                    if (headers) createHeader((List<PdfExportableCell>) cells, table, numColumns);
                    // Loops cells
                    Iterator<PdfExportableCell> iterator = cells.iterator();
                    // Next non-empty cell
                    PdfExportableCell next = (iterator.hasNext()) ? iterator.next() : null;
                    for (int i = 0; i < numRows; i++) {

                        // Add row header
                        if (headers) table.addCell(new PdfPCell(new Phrase(String.valueOf(i + 1))));

                        for (int j = 0; j < numColumns; j++) {

                            Address cellAddress = new Address(j, i);
                            Address nextAddress = next.address();
                            if (next != null && cellAddress.equals(nextAddress)) {

                                PdfPCell pdfPCell = next.exportCell();

                                if ((sections & FORMULAS) == FORMULAS) {

                                    if (next.cell().getFormula() != null) {
                                        formulaAnchors.put(next.exportFormula(), next.exportFormulaText());
                                    }
                                }

                                if ((sections & COMMENTS) == COMMENTS) {

                                    if (next.hasComments()) {
                                        commentAnchors.put(next.exportCommentsAnchor(), next.exportComments());
                                    }
                                }

                                if (next.hasDefaultBorder()) setBorder(pdfPCell);

                                table.addCell(pdfPCell);
                                next = (iterator.hasNext()) ? iterator.next() : null;

                            } else {

                                PdfPCell cell = new PdfPCell();
                                setBorder(cell);

                                table.addCell(cell);
                            }
                        }
                    }

                    document.add(new Paragraph(title));
                    document.add(new Paragraph("\n\n\n"));
                    document.add(table);
                    document.newPage();

                    if ((sections & FORMULAS) == FORMULAS) {

                        Chunk formulasTitle = new Chunk("Formulas " + pdfSpreadsheet.title().getContent());
                        formulasTitle.setFont(new Font(Font.FontFamily.COURIER, 12, Font.BOLD | Font.UNDERLINE, BaseColor.BLUE));
                        formulasTitle.setGenericTag(formulasTitle.getContent());
                        document.add(new Paragraph(formulasTitle));
                        document.add(new Paragraph());

                        for (Map.Entry<Anchor, Phrase> formula :
                                formulaAnchors.entrySet()) {
                            document.add(formula.getKey());
                            document.add(formula.getValue());
                        }

                        document.newPage();
                    }
                    if ((sections & COMMENTS) == COMMENTS) {

                        Chunk commentsTitle = new Chunk("Comments " + pdfSpreadsheet.title().getContent());
                        commentsTitle.setFont(new Font(Font.FontFamily.COURIER, 12, Font.BOLD | Font.UNDERLINE, BaseColor.BLUE));
                        commentsTitle.setGenericTag(commentsTitle.getContent());
                        document.add(new Paragraph(commentsTitle));
                        document.add(new Paragraph());

                        for (Map.Entry<Anchor, Paragraph> comment :
                                commentAnchors.entrySet()) {
                            document.add(comment.getKey());
                            document.add(comment.getValue());
                        }

                        document.newPage();
                    }

                }
            }

            if ((sections & MACROS) == MACROS) {

                Chunk macrosTitle = new Chunk("Macros");
                macrosTitle.setFont(new Font(Font.FontFamily.COURIER, 12, Font.BOLD | Font.UNDERLINE, BaseColor.BLUE));
                macrosTitle.setGenericTag(macrosTitle.getContent());
                document.add(new Paragraph(macrosTitle));
                document.add(new Paragraph());

                for (PdfExportableMacro pdfMacro :
                        exportableWorkbook.exportMacros()) {
                    document.add(pdfMacro.exportMacro());
                }

                document.newPage();
            }

            if (sections > 0) {

                Chunk commentsTitle = new Chunk("Table of Contents");
                commentsTitle.setFont(new Font(Font.FontFamily.COURIER, 12, Font.BOLD | Font.UNDERLINE, BaseColor.BLUE));
                document.add(new Paragraph(commentsTitle));

                Chunk dottedLine = new Chunk(new DottedLineSeparator());
                for (PdfIndexCreation.IndexEntry indexEntry : event.getIndex()) {
                    Chunk c = new Chunk(indexEntry.title);
                    c.setAction(indexEntry.action);

                    Paragraph paragraph = new Paragraph(c);
                    paragraph.add(dottedLine);
                    paragraph.add(String.valueOf(indexEntry.pageNumber));
                    document.add(paragraph);
                }

            }


        } catch (DocumentException | IOException e) {

            throw new RuntimeException(e.getMessage());
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

    private void createHeader(List<PdfExportableCell> cells, PdfPTable table, int numColumns) {

        // Add first empty cell
        PdfPCell emptyCell = new PdfPCell();
        setBorder(emptyCell);
        table.addCell(emptyCell);

        Collections.sort(cells); // Sort cells

        char column1 = (char) ('A' + cells.get(0).address().getColumn());
        char column2 = '\0';

        for (int i = 0; i < numColumns; i++) {

            if (column1 < 'Z' && column2 == '\0') {
                column1++;
            } else {
                column1 = 'A';
                if (column2 == '\0') {
                    column2 = 'A';
                } else {
                    column2++;
                }
            }

            String header = String.valueOf(column1).concat(String.valueOf(column2));
            PdfPCell headerCell = new PdfPCell(new Phrase(header));
            table.addCell(headerCell);
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
                table.getDefaultCell().setBorderColor(gridBaseColor);
                break;
            case DOTTED:
                table.getDefaultCell().setCellEvent(new DottedBorderCellEvent(gridBaseColor));
        }
    }

    /**
     * Sets the default grid type
     *
     * @param cell table to set border
     */
    private void setBorder(PdfPCell cell) {

        switch (gridType) {
            case NO_GRID:
                cell.setBorder(PdfPCell.NO_BORDER);
                break;
            case SOLID:
                cell.setUseBorderPadding(true);
                cell.setBorderColor(gridBaseColor);
                break;
            case DOTTED:
                cell.setCellEvent(new DottedBorderCellEvent(gridBaseColor));
        }
    }

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
