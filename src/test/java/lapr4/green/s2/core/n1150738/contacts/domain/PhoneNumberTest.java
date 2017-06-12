package lapr4.green.s2.core.n1150738.contacts.domain;

import org.junit.Test;

/**
 * A Unit Test class to test PhoneNumber.
 * @see PhoneNumber
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class PhoneNumberTest {

    /**
     * A method that tests constructing a Profession with null value
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensurePhoneNumberIsNonNull(){
        PhoneNumber n = new PhoneNumber(null);
    }

    /**
     * A method that tests constructing a Profession with empty value
     */
    @Test (expected = IllegalArgumentException.class)
    public void ensurePhoneNumberIsNotEmpty(){
        PhoneNumber n = new PhoneNumber("");
    }
}