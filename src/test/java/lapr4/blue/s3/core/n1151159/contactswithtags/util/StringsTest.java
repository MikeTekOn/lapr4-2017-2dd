package lapr4.blue.s3.core.n1151159.contactswithtags.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the class String.
 *
 * @author Ivo Ferro [1151159]
 */
public class StringsTest {

    /**
     * Tests if the isAlphanumeric method passes with a alphanumeric string.
     */
    @Test
    public void ensureIsAlphanumericPassesForAlphanumericString() {
        assertTrue(Strings.isAlphanumeric("alpha123"));
    }

    /**
     * Tests if the isAlphanumeric method fails with a non alphanumeric string.
     */
    @Test
    public void ensureIsAlphanumericFailsForNonAlphanumericString() {
        assertFalse(Strings.isAlphanumeric("not alpha"));
    }

}