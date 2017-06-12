package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.comm;

import csheets.core.Address;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the class CellContentDTO.
 *
 * @author Ivo Ferro [1151159]
 */
public class CellContentDTOTest {

    /**
     * The instance to be tested.
     */
    private CellContentDTO instance;

    /**
     * Sets up the instance for the tests.
     */
    @Before
    public void setUp() {
        instance = new CellContentDTO(new Address(0, 0), "abc");
    }

    /**
     * Tests the method getAddress.
     */
    @Test
    public void getAddress() {
        assertEquals(instance.getAddress(), new Address(0, 0));
    }

    /**
     * Tests the method getContent.
     */
    @Test
    public void getContent() {
        assertEquals(instance.getContent(), "abc");
    }

}