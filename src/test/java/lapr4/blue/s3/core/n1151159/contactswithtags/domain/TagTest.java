package lapr4.blue.s3.core.n1151159.contactswithtags.domain;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test for the class Tag.
 *
 * @author Ivo Ferro [115115]
 */
public class TagTest {

    /**
     * Test to ensure that the tag has a designation.
     */
    @Test(expected = IllegalStateException.class)
    public void ensureTagHasDesignation() {
        Tag tag = new Tag(null);
    }

    /**
     * Test to ensure that the tag is alphanumeric.
     */
    @Test(expected = IllegalStateException.class)
    public void ensureTagDesignationIsAlphanumeric() {
        Tag tag = new Tag("This is not alphanumeric");
    }

    /**
     * Test to ensure that two equals tags have the same hash code.
     */
    @Test
    public void ensureTwoEqualsTagsHaveTheSameHashCode() {
        Tag aTag = new Tag("aTag123");
        Tag sameTag = new Tag("aTag123");

        assertTrue(aTag.hashCode() == sameTag.hashCode());
    }

    /**
     * Test to ensure that two not equals tags have different hash codes.
     */
    @Test
    public void ensureTwoNotEqualsTagsHaveDifferentHashCodes() {
        Tag aTag = new Tag("aTag123");
        Tag anotherTag = new Tag("anotherTag456");

        assertFalse(aTag.hashCode() == anotherTag.hashCode());
    }

    /**
     * Test if two instances of the same tag are equals.
     */
    @Test
    public void ensureIfTwoInstancesOfTheSameTagAreEquals() {
        Tag aTag = new Tag("aTag123");
        Tag sameTag = new Tag("aTag123");

        assertTrue(aTag.equals(sameTag));
    }

    /**
     * Test if two differents tags are not equals.
     */
    @Test
    public void ensureIfTwoInstancesDifferentTagsAreNotEquals() {
        Tag aTag = new Tag("aTag123");
        Tag anotherTag = new Tag("anotherTag456");

        assertFalse(aTag.equals(anotherTag));
    }
}