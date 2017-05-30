package lapr4.red.s1.core.n1150943.contacts;

import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import org.junit.Before;
import org.junit.Test;

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

    public ContactTest() {
    }

    @Before
    public void setUp() {

    }

    @Test(expected = IllegalStateException.class)
    public void testContactHasName() {
        Contact c = new Contact("",first,last);
    }

    @Test(expected = IllegalStateException.class)
    public void testContactHasFirstName() {
        Contact c = new Contact(name,"",last);
    }

    @Test(expected = IllegalStateException.class)
    public void testContactHasLastName() {
        Contact c = new Contact(name,first,"");
    }

}