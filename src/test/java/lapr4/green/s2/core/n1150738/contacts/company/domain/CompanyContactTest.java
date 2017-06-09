package lapr4.green.s2.core.n1150738.contacts.company.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A Unit Test class to test CompanyContact.
 * @see CompanyContact
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class CompanyContactTest {

    private static final CompanyName VALID_COMPANY_NAME = new CompanyName("ISEP");

    /**
     * A method that tests constructing a CompanyContact with a null CompanyName
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureNameIsNonNull(){
        CompanyContact n = new CompanyContact(null);
    }

    /**
     * A method that tests constructing a CompanyContact with a null Image
     */
    @Test (expected = IllegalArgumentException.class)
    public void ensureNameIsNotEmpty(){
        CompanyContact n = new CompanyContact(VALID_COMPANY_NAME, null);
    }
}