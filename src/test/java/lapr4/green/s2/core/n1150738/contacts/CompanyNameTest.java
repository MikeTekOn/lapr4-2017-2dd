package lapr4.green.s2.core.n1150738.contacts;

import lapr4.green.s2.core.n1150738.contacts.domain.CompanyName;
import org.junit.Test;

/**
 * A Unit Test class to test CompanyName.
 * @see CompanyName
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class CompanyNameTest {

    /**
     * A method that tests constructing a CompanyName with null value
     */
    @Test (expected = IllegalArgumentException.class)
    public void ensureNameIsNonNull(){
        CompanyName n = new CompanyName(null);
    }

    /**
     * A method that tests constructing a CompanyName with empty value
     */
    @Test (expected = IllegalArgumentException.class)
    public void ensureNameIsNotEmpty(){
        CompanyName n = new CompanyName("");
    }
}