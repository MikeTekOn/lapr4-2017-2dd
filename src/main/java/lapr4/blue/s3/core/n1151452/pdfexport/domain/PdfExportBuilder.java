package lapr4.blue.s3.core.n1151452.pdfexport.domain;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import csheets.core.Cell;

import java.awt.*;
import java.util.List;

/**
 * Class to build a PDF Export
 * <p>
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 18/06/17.
 */
public class PdfExportBuilder {

    /**
     * The selected cells
     */
    private List<Cell> cells;

    /**
     * Selected sections (bitwise operations)
     */
    private int sections;

    /**
     * Selected grid type
     */
    private GridType gridType;

    /**
     * Selected grid color
     */
    private Color gridColor;

    /**
     * Selected with headers
     */
    private boolean headers;

    /**
     * Selected print area
     */
    private PrintArea printArea;

    /**
     * Selected page size
     */
    private Rectangle pageSize;

    /**
     * Selected page orientation
     */
    private PageOrientation orientation;

    /**
     * Selected path
     */
    private String path;

    /**
     * Default path
     */
    private static final String DEFAULT_PATH = "untitled.pdf";

    /**
     * constructs PdfExport Builder
     *
     * @param selectedCells the selected cells
     */
    public PdfExportBuilder(List<Cell> selectedCells) {

        cells = selectedCells;

        // DEFAULT VALUES
        sections = 0;
        gridType = GridType.SOLID;
        gridColor = Color.BLACK;
        headers = false;
        printArea = PrintArea.ACTIVE_SPREADSHEET;
        pageSize = PageSize.A4;
        orientation = PageOrientation.PORTRAIT;
        path = DEFAULT_PATH;
    }

    /**
     * Build with introduced section
     *
     * @param section the selected section
     * @return this instance [fluent builder]
     */
    public PdfExportBuilder withSection(int section) {

        sections |= section;

        return this;
    }

    /**
     * Build with selected grid type
     *
     * @param aGridType the selected grid type
     * @return this instance [fluent builder]
     */
    public PdfExportBuilder withGridType(GridType aGridType) {

        gridType = aGridType;

        return this;
    }

    /**
     * Build with selected grid color
     *
     * @param color the selected grid color
     * @return this instance [fluent builder]
     */
    public PdfExportBuilder withGridColor(Color color) {

        gridColor = color;

        return this;
    }

    /**
     * Build with headers
     *
     * @return this instance [fluent builder]
     */
    public PdfExportBuilder withHeaders() {

        headers = true;

        return this;
    }

    /**
     * Build with selected print area
     *
     * @param aPrintArea the selected print area
     * @return this instance [fluent builder]
     */
    public PdfExportBuilder withPrintArea(PrintArea aPrintArea) {

        printArea = aPrintArea;

        return this;
    }

    /**
     * Build with selected page size
     *
     * @param aPageSize the selected page size
     * @return this instance [fluent builder]
     */
    public PdfExportBuilder withPageSize(Rectangle aPageSize) {

        pageSize = aPageSize;

        return this;
    }

    /**
     * Build with selected page orientation
     *
     * @param pageOrientation the selected page orientation
     * @return this instance [fluent builder]
     */
    public PdfExportBuilder withPageOrientation(PageOrientation pageOrientation) {

        orientation = pageOrientation;

        return this;
    }

    /**
     * Build with selected path
     *
     * @param aPath the selected path
     * @return this instance [fluent builder]
     */
    public PdfExportBuilder withPath(String aPath) {

        path = aPath;

        return this;
    }

    /**
     * Builds a PDF Export object
     *
     * @return a PDF Export object
     */
    public PdfExport build() {
        return new PdfExport(cells, sections, headers, gridType, gridColor, printArea, pageSize, orientation, path);
    }
}
