package lapr4.blue.s3.core.n1151452.pdfexport.presentation;

import com.itextpdf.text.Rectangle;
import csheets.core.Cell;
import eapli.framework.dto.DTO;
import lapr4.blue.s3.core.n1151452.pdfexport.domain.GridType;
import lapr4.blue.s3.core.n1151452.pdfexport.domain.PageOrientation;
import lapr4.blue.s3.core.n1151452.pdfexport.domain.PrintArea;

import java.awt.*;
import java.util.List;

/**
 * A DTO to store the selected PDF export options
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 18/06/17.
 */
public class PdfOptionsDTO implements DTO {

    private final List<Cell> theActiveCells;
    private final boolean withMacros;
    private final boolean withFormulas;
    private final boolean withComments;
    private final boolean withHeaders;
    private final GridType aGridType;
    private final Color gridColor;
    private final PrintArea aPrintArea;
    private final Rectangle aPageSize;
    private final PageOrientation anOrientation;
    private final String aPath;

    /**
     * Constructs a Pdf Options DTO
     *
     * @param theActiveCells the active cells
     * @param withMacros     include macros
     * @param withFormulas   include formulas
     * @param withComments   include comments
     * @param aGridType      the selected grid type
     * @param gridColor      the grid's color (can be nullable)
     * @param withHeaders    include headers
     * @param aPrintArea     the selected print type
     * @param aPageSize      the selected page size (ex. A4, A3, letter, etc.)
     * @param anOrientation  the selected orientation (portrait or landscape)
     */
    public PdfOptionsDTO(List<Cell> theActiveCells, boolean withMacros, boolean withFormulas, boolean withComments,
                         boolean withHeaders, GridType aGridType, Color gridColor, PrintArea aPrintArea, Rectangle aPageSize,
                         PageOrientation anOrientation, String aPath) {

        this.theActiveCells = theActiveCells;
        this.withMacros = withMacros;
        this.withFormulas = withFormulas;
        this.withComments = withComments;
        this.withHeaders = withHeaders;
        this.aGridType = aGridType;
        this.gridColor = gridColor;
        this.aPrintArea = aPrintArea;
        this.aPageSize = aPageSize;
        this.anOrientation = anOrientation;
        this.aPath = aPath;
    }

    /**
     * Obtain active cells
     *
     * @return active cells
     */
    public List<Cell> getActiveCells() {
        return theActiveCells;
    }

    /**
     * True to include macros
     *
     * @return true if macros section selected
     */
    public boolean withMacros() {
        return withMacros;
    }

    /**
     * True to include formulas
     *
     * @return true if formulas section selected
     */
    public boolean withFormulas() {
        return withFormulas;
    }

    /**
     * True to include comments
     *
     * @return true if comments section selected
     */
    public boolean withComments() {
        return withComments;
    }

    /**
     * True to include headers
     *
     * @return true if headers selected
     */
    public boolean withHeaders() {
        return withHeaders;
    }

    /**
     * Obtain grid type
     *
     * @return grid type
     */
    public GridType getGridType() {
        return aGridType;
    }

    /**
     * Obtain grid color
     *
     * @return grid color
     */
    public Color getGridColor() {
        return gridColor;
    }

    /**
     * Obtain print area
     *
     * @return print area
     */
    public PrintArea getPrintArea() {
        return aPrintArea;
    }

    /**
     * Obtain page size
     *
     * @return page size
     */
    public Rectangle getPageSize() {
        return aPageSize;
    }

    /**
     * Obtain page orientation
     *
     * @return page orientation
     */
    public PageOrientation getOrientation() {
        return anOrientation;
    }

    /**
     * Obtain get file path
     *
     * @return get file path
     */
    public String getPath() {
        return aPath;
    }
}
