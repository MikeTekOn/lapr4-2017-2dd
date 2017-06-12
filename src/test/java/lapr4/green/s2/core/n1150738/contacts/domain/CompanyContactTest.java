package lapr4.green.s2.core.n1150738.contacts.domain;

import org.junit.Test;

/**
 * A Unit Test class to test CompanyContact.
 * @see CompanyContact
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class CompanyContactTest {

    private static final CompanyName VALID_COMPANY_NAME = new CompanyName("ISEP");
    public static final EmailAddress VALID_EMAIL_ADDRESS = new EmailAddress("1234@isep.ipp.pt");
    public static final PhoneNumber VALID_PHONE_NUMBER = new PhoneNumber("1234567879");

    /**
     * A method that tests constructing a CompanyContact with a null CompanyName
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureNameIsNonNull(){
        CompanyContact n = new CompanyContact(null, VALID_EMAIL_ADDRESS, VALID_PHONE_NUMBER);
    }

    /**
     * A method that tests constructing a CompanyContact with a null Email
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureEmailIsNonNull(){
        CompanyContact n = new CompanyContact(VALID_COMPANY_NAME, null, VALID_PHONE_NUMBER);
    }


    /**
     * A method that tests constructing a CompanyContact with a null PhoneNumber
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensurePhoneNumberIsNonNull(){
        CompanyContact n = new CompanyContact(VALID_COMPANY_NAME, VALID_EMAIL_ADDRESS, null);
    }

    /**
     * A method that tests constructing a CompanyContact with a null image
     */
    @Test
    public void ensureImageCanBeNull(){
        CompanyContact n = new CompanyContact(VALID_COMPANY_NAME, VALID_EMAIL_ADDRESS, VALID_PHONE_NUMBER, null);
    }

}