package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.comm;

import csheets.core.Address;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;

/**
 * Tests the class CellStyleDTO.
 *
 * @author Guilherme Ferreira 1150623 added spreadsheet to cellDTO
 * @author Ivo Ferro [1151159]
 */
public class CellStyleDTOTest {

    /**
     * The instance to be tested.
     */
    private CellStyleDTO instance;

    /**
     * Sets up the instance for the tests.
     */
    @Before
    public void setUp() {
        instance = new CellStyleDTO(new Address(0, 0),
                new StyleDTO(new DecimalFormat(),
                        new Font("Garamond", Font.BOLD, 11),
                        0, 1, Color.GREEN, Color.BLUE,
                        BorderFactory.createLineBorder(Color.black)),  null);
    }

    /**
     * Test the method get address.
     */
    @Test
    public void getAddress() {
        assertEquals(instance.getAddress(), new Address(0, 0));
    }

}