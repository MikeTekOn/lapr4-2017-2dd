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

    Contact contact1=null;
    Contact contact2=null;

    public ContactTest() {
    }

    @Before
    public void setUp() {
        contact1 = new Contact("João Cardoso", "João","Cardoso","");
        contact2 = new Contact(name,first,last,"");
    }

    @Test(expected = IllegalStateException.class)
    public void testContactHasName() {
        Contact c = new Contact("",first,last,"");
    }

    @Test(expected = IllegalStateException.class)
    public void testContactHasFirstName() {
        Contact c = new Contact(name,"",last,"");
    }

    @Test(expected = IllegalStateException.class)
    public void testContactHasLastName() {
        Contact c = new Contact(name,first,"","");
    }

    @Test
    public void testContactIsSameTrue() {
        Contact c = new Contact(name,first,last,"");
        boolean res = contact2.sameAs(c);
        assertTrue(res);
    }

    @Test
    public void testContactIsSameFalse() {
        boolean res = contact2.sameAs(contact1);
        assertFalse(res);
    }


}