package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.util;

import csheets.CleanSheets;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.comm.StyleDTO;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertTrue;

/**
 * Tests the class Styles.
 *
 * @author Ivo Ferro [1151159]
 */
public class StylesTest {

    /**
     * The cleanSheets instance used for tests.
     */
    private CleanSheets app;

    /**
     * Sets up the instance for the tests.
     */
    @Before
    public void setUp() {
        // Try to create the CS application object
        app = new CleanSheets();
        // This will create a workbook with 3 sheets
        app.create();
    }

    /**
     * Tests the method setStyleFromDTO.
     */
    @Test
    public void setStyleFromDTO() {
        Spreadsheet spreadsheet = app.getWorkbooks()[0].getSpreadsheet(0);

        Cell cellA1 = spreadsheet.getCell(0, 0);
        Cell cellA2 = spreadsheet.getCell(0, 1);

        StylableCell styleCellA1 = (StylableCell) cellA1.getExtension(StyleExtension.NAME);
        StylableCell styleCellA2 = (StylableCell) cellA2.getExtension(StyleExtension.NAME);

        styleCellA1.setBorder(BorderFactory.createLineBorder(Color.black));
        styleCellA1.setBackgroundColor(Color.BLUE);
        styleCellA1.setForegroundColor(Color.GREEN);

        StyleDTO styleDTO = Styles.createStyleDtoFromCell(styleCellA1);
        Styles.setStyleFromDTO(styleCellA2, styleDTO);

        assertTrue(styleCellA1.getBorder().equals(styleCellA2.getBorder()));
        assertTrue(styleCellA1.getBackgroundColor().equals(styleCellA2.getBackgroundColor()));
        assertTrue(styleCellA1.getForegroundColor().equals(styleCellA2.getForegroundColor()));
    }

    /**
     * Tests the method createStyleDtoFromCell.
     */
    @Test
    public void createStyleDtoFromCell() {
        Spreadsheet spreadsheet = app.getWorkbooks()[0].getSpreadsheet(0);

        Cell cellA1 = spreadsheet.getCell(0, 0);
        Cell cellA2 = spreadsheet.getCell(0, 1);

        StylableCell styleCellA1 = (StylableCell) cellA1.getExtension(StyleExtension.NAME);

        styleCellA1.setBorder(BorderFactory.createLineBorder(Color.black));
        styleCellA1.setBackgroundColor(Color.BLUE);
        styleCellA1.setForegroundColor(Color.GREEN);

        StyleDTO styleDTO = Styles.createStyleDtoFromCell(styleCellA1);

        assertTrue(styleDTO.getBorder().equals(styleCellA1.getBorder()));
        assertTrue(styleDTO.getBackgroundColor().equals(styleCellA1.getBackgroundColor()));
        assertTrue(styleDTO.getForegroundColor().equals(styleCellA1.getForegroundColor()));
    }

}