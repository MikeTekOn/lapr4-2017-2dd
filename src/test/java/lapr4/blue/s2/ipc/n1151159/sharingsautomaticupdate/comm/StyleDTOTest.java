package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.comm;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;

/**
 * Tests the class StyleDTO.
 *
 * @author Ivo Ferro [1151159]
 */
public class StyleDTOTest {

    /**
     * The instance to be tested.
     */
    private StyleDTO instance;

    /**
     * A black border to be used in tests.
     */
    private Border border = BorderFactory.createLineBorder(Color.black);

    /**
     * Sets up the instance for the tests.
     */
    @Before
    public void setUp() {
        instance = new StyleDTO(new DecimalFormat(),
                new Font("Garamond", Font.BOLD, 11),
                0, 1, Color.GREEN,
                Color.BLUE, border);
    }

    /**
     * Tests the method getFormat.
     */
    @Test
    public void getFormat() {
        assertEquals(instance.getFormat(), new DecimalFormat());
    }

    /**
     * Tests the method getFont.
     */
    @Test
    public void getFont() {
        assertEquals(instance.getFont(), new Font("Garamond", Font.BOLD, 11));
    }

    /**
     * Tests the method getHorizontalAlignment.
     */
    @Test
    public void getHorizontalAlignment() {
        assertEquals(instance.getHorizontalAlignment(), 0);
    }

    /**
     * Tests the method getVerticalAlignment.
     */
    @Test
    public void getVerticalAlignment() {
        assertEquals(instance.getVerticalAlignment(), 1);
    }

    /**
     * Tests the method getForegroundColor.
     */
    @Test
    public void getForegroundColor() {
        assertEquals(instance.getForegroundColor(), Color.GREEN);
    }

    /**
     * Tests the method getBackgroundColor.
     */
    @Test
    public void getBackgroundColor() {
        assertEquals(instance.getBackgroundColor(), Color.BLUE);
    }

    /**
     * Tests the method getBorder.
     */
    @Test
    public void getBorder() {
        assertEquals(instance.getBorder(), border);
    }

}