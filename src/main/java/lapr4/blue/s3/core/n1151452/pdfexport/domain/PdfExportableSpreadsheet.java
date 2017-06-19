package lapr4.blue.s3.core.n1151452.pdfexport.domain;

import com.itextpdf.text.Chunk;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.ext.style.StylableSpreadsheet;
import csheets.ext.style.StyleExtension;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A PDF exportable spreadsheet
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 16/06/17.
 */
public class PdfExportableSpreadsheet {

    /**
     * The related spreadsheet
     */
    private Spreadsheet spreadsheet;

    /**
     * Default column width
     */
    private static final int DEFAULT_COLUMN_WIDTH = 75;

    /**
     * Default column width
     */
    private static final int DEFAULT_ROW_HEADER_WIDTH = 35;

    /**
     * Default row height
     */
    private static final int DEFAULT_ROW_HEIGHT = 16;

    /**
     * Constructs a PDF exportable spreadsheet
     *
     * @param aSpreadsheet the realted spreadsheet
     */
    public PdfExportableSpreadsheet(Spreadsheet aSpreadsheet) {

        if (aSpreadsheet == null) throw new IllegalStateException("The spreadsheet can't be null.");

        spreadsheet = aSpreadsheet;
    }

    /**
     * Obtains the spreadsheet title as a PDF chunk
     *
     * @return the spreadsheet title as a PDF chunk
     */
    public Chunk title() {

        return new Chunk(spreadsheet.getTitle());
    }

    /**
     * Exports all active cells from spreadsheet to a list of pdf exportable cells
     *
     * @return a list of pdf exportable cells (by order)
     */
    public Iterable<PdfExportableCell> exportCells() {

        List<PdfExportableCell> pdfCells = new LinkedList<>();

//        spreadsheet.getCells(new Address(0, 0), new Address(52, 127)).forEach((cell -> {

        spreadsheet.iterator().forEachRemaining((cell -> {

            boolean columnCheck = cell.getAddress().getColumn() >= 0 && cell.getAddress().getColumn() < 53;
            boolean rowCheck = cell.getAddress().getRow() >= 0 && cell.getAddress().getRow() < 127;

            if (columnCheck && rowCheck) pdfCells.add(new PdfExportableCell(cell));
        }));

        Collections.sort(pdfCells); // Order cells

        return pdfCells;
    }

    /**
     * Exports all cells from range to a list of pdf exportable cells
     *
     * @param from the initial address
     * @param to   the final address
     * @return a list of pdf exportable cells (by order)
     */
    public Iterable<PdfExportableCell> exportCells(Address from, Address to) {

        List<PdfExportableCell> pdfCells = new LinkedList<>();

        spreadsheet.getCells(from, to).forEach((cell -> {

            pdfCells.add(new PdfExportableCell(cell));
        }));

        Collections.sort(pdfCells); // Order cells

        return pdfCells;
    }

    /**
     * Exports a selection of cells [STATIC METHOD]
     *
     * @param cells the selected cells to export
     * @return a list of pdf exportable cells (by order)
     */
    public static Iterable<PdfExportableCell> exportSelection(List<Cell> cells) {

        List<PdfExportableCell> pdfCells = new LinkedList<>();

        cells.forEach((cell -> pdfCells.add(new PdfExportableCell(cell))));

        Collections.sort(pdfCells); // Order cells

        return pdfCells;
    }

    /**
     * Obtains the widths of all columns of the spreadsheet
     *
     * @param withHeaders include headers
     * @return the widths of all columns of the spreadsheet
     */
    public float[] columnWidths(boolean withHeaders) {

        StylableSpreadsheet stylableSpreadsheet = ((StylableSpreadsheet) spreadsheet.getExtension(StyleExtension.NAME));

        float[] widths = new float[stylableSpreadsheet.getColumnCount() + ((withHeaders) ? 2 : 1)]; // Plus Header (ex. A, B, C...)
        if (withHeaders) widths[0] = DEFAULT_ROW_HEADER_WIDTH;
        for (int i = (withHeaders) ? 1 : 0; i < widths.length; i++) {

            int index = i - ((withHeaders) ? 1 : 0);
            float width = stylableSpreadsheet.getColumnWidth(index);

            widths[i] = (width < 0) ? DEFAULT_COLUMN_WIDTH : width;
        }

        return widths;
    }

    /**
     * Obtains the heights of all rows of the spreadsheet
     *
     * @param withHeaders include headers
     * @return the heights of all rows of the spreadsheet
     */
    public float[] rowHeights(boolean withHeaders) {

        StylableSpreadsheet stylableSpreadsheet = ((StylableSpreadsheet) spreadsheet.getExtension(StyleExtension.NAME));

        float[] heights = new float[stylableSpreadsheet.getRowCount() + ((withHeaders) ? 2 : 1)]; // Plus Header (ex. 1, 2, 3...)
        if (withHeaders) heights[0] = DEFAULT_ROW_HEIGHT;
        for (int i = (withHeaders) ? 1 : 0; i < heights.length; i++) {

            int index = i - ((withHeaders) ? 1 : 0);
            int height = stylableSpreadsheet.getRowHeight(index);

            heights[i] = (height < 0) ? DEFAULT_ROW_HEIGHT : height;
        }

        return heights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PdfExportableSpreadsheet that = (PdfExportableSpreadsheet) o;

        return spreadsheet != null ? spreadsheet.equals(that.spreadsheet) : that.spreadsheet == null;
    }

    @Override
    public int hashCode() {
        return spreadsheet != null ? spreadsheet.hashCode() : 0;
    }
}
