package lapr4.red.s1.core.n1150623.labelsForContacts;

import lapr4.red.s1.core.n1150623.labelsForContacts.domain.Label;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by guima on 02/06/2017.
 */
public class LabelTest {


    public Label label;

    private String name;
    private String photo;
    private Set<String> phoneNumbers;
    private Set<String> emails;
    private Set<String> addresses;


    public LabelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        label = new Label();

        phoneNumbers = new HashSet();
        addresses = new HashSet();
        emails = new HashSet();

        photo = "something";
        name = "something";
        phoneNumbers.add("something");
        addresses.add("something");
        emails.add("something");
    }

    @After
    public void tearDown() {
    }



    @Test(expected = IllegalArgumentException.class)
    public void ensureNameCantBeNull(){
        name = null;
        label.fillLabel(name, photo, addresses, emails, phoneNumbers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameCantBeEmpty(){
        name = "";
        label.fillLabel(name, photo, addresses, emails, phoneNumbers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurePhotoCantBeNull(){
        photo = null;
        label.fillLabel(name, photo, addresses, emails, phoneNumbers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurePhotoCantBeEmpty(){
        photo = "";
        label.fillLabel(name, photo, addresses, emails, phoneNumbers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAddressesCantBeNull(){
        addresses = null;
        label.fillLabel(name, photo, addresses, emails, phoneNumbers);
    }


    @Test(expected = IllegalArgumentException.class)
    public void ensureEmailsCantBeNull(){
        emails = null;
        label.fillLabel(name, photo, addresses, emails, phoneNumbers);
    }


    @Test(expected = IllegalArgumentException.class)
    public void ensurePhoneNumbersCantBeNull(){
        phoneNumbers = null;
        label.fillLabel(name, photo, addresses, emails, phoneNumbers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAddressesHaveEmptyElements(){
        addresses.add("");
        label.fillLabel(name, photo, addresses, emails, phoneNumbers);
    }


    @Test(expected = IllegalArgumentException.class)
    public void ensureEmailsCantHaveEmptyElements(){
        emails.add("");
        label.fillLabel(name, photo, addresses, emails, phoneNumbers);
    }


    @Test(expected = IllegalArgumentException.class)
    public void ensurePhoneNumbersCantHaveEmptyElements(){
        phoneNumbers .add("");
        label.fillLabel(name, photo, addresses, emails, phoneNumbers);
    }


    @Test(expected = IllegalArgumentException.class)
    public void ensureAddressesHaveNullElements(){
        addresses.add(null);
        label.fillLabel(name, photo, addresses, emails, phoneNumbers);
    }


    @Test(expected = IllegalArgumentException.class)
    public void ensureEmailsCantHaveNullElements(){
        emails.add(null);
        label.fillLabel(name, photo, addresses, emails, phoneNumbers);
    }


    @Test(expected = IllegalArgumentException.class)
    public void ensurePhoneNumbersCantHaveNullElements(){
        phoneNumbers .add(null);
        label.fillLabel(name, photo, addresses, emails, phoneNumbers);
    }
}
