package lapr4.blue.s2.ipc.n1151452.netanalyzer.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test the custom util class methods
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 09/06/17.
 */
public class CustomUtilTest {

    private int validPort;
    private int invalidPort;

    @Before
    public void setUp() {

        validPort = 8888;
        invalidPort = 0;
    }

    /**
     * Test validate port validates a valid port
     */
    @Test
    public void ensureValidPort() {

        assertTrue(CustomUtil.validatePort(validPort));
    }

    /**
     * Test validate port doesn't validates an invalid port
     */
    @Test
    public void ensureInvalidPort() {

        assertFalse(CustomUtil.validatePort(invalidPort));
    }

    /**
     * Test if long is can't not be negative
     */
    @Test
    public void ensureLongIsNotNegative() {

        assertFalse(CustomUtil.isNullOrNonNegative(-1L));
    }

    /**
     * Test if long is can't not be null
     */
    @Test
    public void ensureLongIsNotNull() {

        assertFalse(CustomUtil.isNullOrNonNegative(null));
    }
}