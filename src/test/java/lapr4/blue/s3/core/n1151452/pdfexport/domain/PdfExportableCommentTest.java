package lapr4.blue.s3.core.n1151452.pdfexport.domain;

import com.itextpdf.text.Paragraph;
import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.red.s1.core.n1150690.comments.domain.User;
import lapr4.white.s1.core.n1234567.comments.CommentableCell;
import lapr4.white.s1.core.n1234567.comments.CommentsExtension;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests a PDF exportable comment
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 16/06/17.
 */
public class PdfExportableCommentTest {

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
     * Tests if commentable cell is not null
     */
    @Test (expected = IllegalStateException.class)
    public void ensurePdfExportableCommentHasNonNullCommentableCell() {

        PdfExportableComment pdfComment = new PdfExportableComment(null);
    }

    /**
     * Tests if exports a comment paragraph
     */
    @Test
    public void ensurePdfExportableCommentExportsComment() {

        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));

        User user = new User();

        CommentableCellWithMultipleUsers commentableCell = (CommentableCellWithMultipleUsers) cellA1.getExtension(CommentsExtension.NAME);
        commentableCell.addUsersComment("test1", user);
        commentableCell.addUsersComment("test2", user);

        String result = new PdfExportableComment(commentableCell).exportComment().getContent();
        String expResult1 = user.name();
        String expResult2 = "test1";
        String expResult3 = "test2";

        assert result.contains(expResult1);
        assert result.contains(expResult2);
        assert result.contains(expResult3);
    }

    /**
     * Tests if exports a comment's history paragraph
     */
    @Test
    public void ensurePdfExportableCommentExportsHistory() {

        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));

        CommentableCellWithMultipleUsers commentableCell = (CommentableCellWithMultipleUsers) cellA1.getExtension(CommentsExtension.NAME);
        commentableCell.setCommentHistory("history");

        String result = new PdfExportableComment(commentableCell).exportHistory().getContent();
        String expResult = new Paragraph("history").getContent();

        assertEquals(result, expResult);
    }
}