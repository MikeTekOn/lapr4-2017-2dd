package lapr4.blue.s3.core.n1151452.pdfexport.domain;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import lapr4.blue.s3.core.n1151452.pdfexport.util.PdfUtil;
import lapr4.green.s2.core.n1150901.richCommentsAndHistory.domain.CommentsWithHistoryExtension;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;

import javax.swing.border.MatteBorder;

/**
 * A PDF exportable Cell
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 16/06/17.
 */
public class PdfExportableCell implements Comparable<PdfExportableCell> {

    /**
     * The related cell
     */
    private Cell cell;

    /**
     * A reference starter for a formula
     */
    private static String FORMULA_REF_HEADER = "formula-";
    /**
     * A reference starter for a formula
     */
    private static String COMMENT_REF_HEADER = "comment-";


    /**
     * Constructs a PDF exportable cell
     *
     * @param aCell the related cell
     */
    public PdfExportableCell(Cell aCell) {

        if (aCell == null) throw new IllegalStateException("The cell can't be null.");

        cell = aCell;
    }

    /**
     * Obtains the cell's address
     *
     * @return the cell's address
     */
    public Address address() {
        return cell.getAddress();
    }

    /**
     * Obtains the cell
     *
     * @return the cell
     */
    public Cell cell() {
        return cell;
    }

    /**
     * Exports to PDF the related cell
     *
     * @return a PdfExportableCell
     */
    public PdfPCell exportCell() {

        // Cell's styles
        StylableCell styleCell = (StylableCell) cell.getExtension(StyleExtension.NAME);
        // Cell's Font
        Font cellFont = FontFactory.getFont(styleCell.getFont().getFontName());
        cellFont.setStyle(styleCell.getFont().getStyle());
        cellFont.setSize(styleCell.getFont().getSize());
        cellFont.setColor(PdfUtil.convertToBaseColor(styleCell.getForegroundColor()));
        // Create Anchor
        Anchor cellAnchor = new Anchor(cell.getValue().toString(), cellFont);
        cellAnchor.setName(cell.getSpreadsheet().getWorkbook().hashCode() + cell.getSpreadsheet().getTitle() + cell.getAddress().toString());
        // Create PdfPCell
        PdfPCell pdfCell = new PdfPCell(cellAnchor);
        // Set Background
        pdfCell.setBackgroundColor(PdfUtil.convertToBaseColor(styleCell.getBackgroundColor()));
        // Set H & V Alignment
        pdfCell.setHorizontalAlignment(PdfUtil.convertSwingAlignment(styleCell.getHorizontalAlignment()));
        pdfCell.setVerticalAlignment(PdfUtil.convertSwingAlignment(styleCell.getVerticalAlignment()));

        int border = PdfUtil.convertSwingBorder(styleCell.getBorder());
        if (border > 0) {
            pdfCell.setBorder(border);
            pdfCell.setBorderColor(PdfUtil.convertToBaseColor(((MatteBorder) styleCell.getBorder()).getMatteColor()));
        }

        return pdfCell;
    }

    /**
     * Has default border
     *
     * @return has default border
     */
    public boolean hasDefaultBorder() {

        // Cell's styles
        StylableCell styleCell = (StylableCell) cell.getExtension(StyleExtension.NAME);

        return !(styleCell.getBorder() instanceof MatteBorder);
    }

    /**
     * Creates a reference in the cell to the corresponding formula
     *
     * @return a anchor to the corresponding formula
     */
    public Anchor createFormulaReference() {

        Anchor formulaRef = new Anchor(" [f]", new Font(Font.FontFamily.COURIER, 8, Font.UNDERLINE));
        formulaRef.setReference("#" + FORMULA_REF_HEADER + cell.getSpreadsheet().getWorkbook().hashCode() + cell.getSpreadsheet().getTitle() + cell.getAddress().toString());

        return formulaRef;
    }

    /**
     * Creates a reference in the cell to the corresponding comment
     *
     * @return a anchor to the corresponding comment
     */
    public Anchor createCommentReference() {

        Anchor formulaRef = new Anchor(" [c]", new Font(Font.FontFamily.COURIER, 8, Font.UNDERLINE));
        formulaRef.setReference("#" + COMMENT_REF_HEADER + cell.getSpreadsheet().getWorkbook().hashCode() + cell.getSpreadsheet().getTitle() + cell.getAddress().toString());

        return formulaRef;
    }

    /**
     * Exports a formula in a PDF Phrase
     *
     * @return a formula in a PDF Phrase
     */
    public Anchor exportFormula() {

        Anchor cellRef = new Anchor(cell.getAddress().toString() + " ");
        Font refFont = cellRef.getFont();
        refFont.setColor(BaseColor.BLUE);
        cellRef.setFont(refFont);
        cellRef.setReference("#" + cell.getSpreadsheet().getWorkbook().hashCode() + cell.getSpreadsheet().getTitle() + cell.getAddress().toString());
//        cellRef.setName(FORMULA_REF_HEADER + cell.getSpreadsheet().getWorkbook().hashCode() + cell.getSpreadsheet().getTitle() + cell.getAddress().toString());

        return cellRef;
    }

    /**
     * Exports the formula text
     *
     * @return the formula text
     */
    public Phrase exportFormulaText() {
        return new Phrase("  " + cell.getFormula().toString() + "\n");
    }

    /**
     * Exports the comments in a PDF Paragraph
     *
     * @return the comments in a PDF Paragraph
     */
    public Anchor exportCommentsAnchor() {

        Anchor cellRef = new Anchor("Comments on " + cell.getAddress().toString());
        Font refFont = cellRef.getFont();
        refFont.setColor(BaseColor.GRAY);
        cellRef.setFont(refFont);
        cellRef.setReference("#" + cell.getSpreadsheet().getWorkbook().hashCode() + cell.getSpreadsheet().getTitle() + cell.getAddress().toString());

//        cellRef.setName(COMMENT_REF_HEADER + cell.getSpreadsheet().getWorkbook().hashCode() + cell.getSpreadsheet().getTitle() + cell.getAddress().toString());
        return cellRef;
    }

    /**
     * Exports the comments text
     *
     * @return the comments text
     */
    public Paragraph exportComments() {

        Paragraph pdfComments = new Paragraph();

        CommentableCellWithMultipleUsers commentableCell = (CommentableCellWithMultipleUsers) cell.getExtension(CommentsWithHistoryExtension.NAME);
        pdfComments.add(new PdfExportableComment((commentableCell)).exportComment());

        return pdfComments;
    }

    /**
     * Has comments
     *
     * @return true if comments exist
     */
    public boolean hasComments() {

        CommentableCellWithMultipleUsers commentableCell =
                (CommentableCellWithMultipleUsers) cell.getExtension(CommentsWithHistoryExtension.NAME);

        return commentableCell.hasComments();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PdfExportableCell that = (PdfExportableCell) o;

        return cell != null ? cell.equals(that.cell) : that.cell == null;
    }

    @Override
    public int hashCode() {
        return cell != null ? cell.hashCode() : 0;
    }

    @Override
    public int compareTo(PdfExportableCell o) {

        int column = o.address().getColumn();
        int row = o.address().getRow();

        int rowDiff = this.address().getRow() - row;

        if (rowDiff != 0) {
            return rowDiff;
        } else {
            return this.address().getColumn() - column;
        }
    }
}
