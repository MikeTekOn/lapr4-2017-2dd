package lapr4.green.s2.core.n1150738.contacts.company;

import lapr4.green.s2.core.n1150738.contacts.company.domain.Image;
import org.junit.Test;

/**
 * A Unit Test class to test Image.
 * @see Image
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class ImageTest {

    /**
     * A method that tests constructing a Image with null value
     */
    @Test (expected=IllegalArgumentException.class)
    public void ensureImageIsNonNull(){
        Image i = new Image(null);
    }

    /**
     * A method that tests constructing a Image with null value
     *
     * Throws IllegalArgumentException if the process fails
     */
    @Test public void ensureDefaultImageCanBeConstructed(){
        Image i = Image.defaultImage();
    }

}