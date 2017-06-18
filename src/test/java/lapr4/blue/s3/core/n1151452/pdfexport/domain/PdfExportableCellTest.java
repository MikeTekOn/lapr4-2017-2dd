package lapr4.blue.s3.core.n1151452.pdfexport.domain;

import csheets.CleanSheets;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.formula.compiler.FormulaCompilationException;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.red.s1.core.n1150690.comments.domain.User;
import lapr4.white.s1.core.n1234567.comments.CommentsExtension;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests a PDF exportable cell
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 16/06/17.
 */
public class PdfExportableCellTest {

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
     * Test if cell parameter is not null
     */
    @Test (expected = IllegalStateException.class)
    public void ensurePdfExportableCellHasNonNullCell() {

        PdfExportableCell pdfCell = new PdfExportableCell(null);
    }

    /**
     * Tests if a cell is exported to a PdfPCell
     */
    @Test
    public void ensurePdfExportableCellExportsCell() throws FormulaCompilationException {

        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        cellA1.setContent("test");

        PdfExportableCell pdfCell = new PdfExportableCell(cellA1);

        String result = pdfCell.exportCell().getPhrase().getContent();
        String expResult = "test";

        assertEquals(result, expResult);
    }

    /**
     * Tests if cell exports formula
     *
     * @throws FormulaCompilationException throws if formula is not valid
     */
    @Test
    public void ensurePdfExportableCellExportsFormula() throws FormulaCompilationException {

        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));
        cellA1.setContent("=2+2");

        PdfExportableCell pdfCell = new PdfExportableCell(cellA1);

        String result = pdfCell.exportFormulaText().getContent();
        String expResult1 = "2+2";

        assert result.contains(expResult1);
    }

    @Test
    public void ensurePdfExportableCellExportsComments() {

        Cell cellA1 = app.getWorkbooks()[0].getSpreadsheet(0).getCell(new Address(0, 0));

        User user = new User();

        CommentableCellWithMultipleUsers commentableCell = (CommentableCellWithMultipleUsers) cellA1.getExtension(CommentsExtension.NAME);
        commentableCell.addUsersComment("test1", user);
        commentableCell.addUsersComment("test2", user);

        String result = new PdfExportableCell(cellA1).exportComments().getContent();
        String expResult1 = user.name();
        String expResult2 = "test1";
        String expResult3 = "test2";

        assert result.contains(expResult1);
        assert result.contains(expResult2);
        assert result.contains(expResult3);
    }
}