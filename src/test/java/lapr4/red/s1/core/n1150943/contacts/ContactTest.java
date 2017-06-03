package lapr4.red.s1.core.n1150943.contacts;

import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


/**
 * Unit Test class to test Contact
 *  @see Contact
 *  @author João Cardoso
 *
 */
public class ContactTest {

    String first = "john";
    String last = "doe";
    String name = "john doe";
    String address = "address";
    String email = "email";
    String phoneNumber = "123456789";

    Contact contact1=null;
    Contact contact2=null;


    public ContactTest() {
    }

    @Before
    public void setUp() {
        contact1 = new Contact("João Cardoso", "João","Cardoso","photo", "address", "email", "123456789");
        contact2 = new Contact(name,first,last,"photo", address, email, phoneNumber);
    }

    @Test(expected = IllegalStateException.class)
    public void testContactHasName() {
        Contact c = new Contact("",first,last,"vdf",  address, email, phoneNumber);
    }

    @Test(expected = IllegalStateException.class)
    public void testContactHasFirstName() {
        Contact c = new Contact(name,"",last,"fvdv", address, email, phoneNumber);
    }

    @Test(expected = IllegalStateException.class)
    public void testContactHasLastName() {
        Contact c = new Contact(name,first,"","casc", address, email, phoneNumber);
    }

    @Test
    public void testContactIsSameTrue() {
        Contact c = new Contact(name,first,last,"photo",  address, email, phoneNumber);
        boolean res = contact2.sameAs(c);
        assertTrue(res);
    }

    @Test(expected = IllegalStateException.class)
    public void testContactHasAddress() {
        Contact c = new Contact(name,first,"asca","casc", "", email, phoneNumber);
    }

    @Test(expected = IllegalStateException.class)
    public void testContactHasEmail() {
        Contact c = new Contact(name,first,"casc","casc", "ggui", "", phoneNumber);
    }

    @Test(expected = IllegalStateException.class)
    public void testContactHasPhoneNumber() {
        Contact c = new Contact(name,first,"cacas","casc", "jpol", email, "");
    }


    @Test
    public void testContactIsSameFalse() {
        boolean res = contact2.sameAs(contact1);
        assertFalse(res);
    }


}