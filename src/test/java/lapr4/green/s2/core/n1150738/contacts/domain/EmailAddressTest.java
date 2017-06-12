package lapr4.green.s2.core.n1150738.contacts.domain;

import org.junit.Test;

/**
 * A Unit Test class to test EmailAddress.
 * @see EmailAddress
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class EmailAddressTest {

    /**
     * A method that tests constructing a EmailAddress with null value
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureEmailAddressIsNonNull(){
        EmailAddress n = new EmailAddress(null);
    }

    /**
     * A method that tests constructing a EmailAddress with empty value
     */
    @Test (expected = IllegalArgumentException.class)
    public void ensureEmailAddressIsNotEmpty(){
        EmailAddress n = new EmailAddress("");
    }
}