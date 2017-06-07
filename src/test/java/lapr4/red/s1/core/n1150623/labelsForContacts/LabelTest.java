package lapr4.red.s1.core.n1150623.labelsForContacts;

import lapr4.red.s1.core.n1150623.labelsForContacts.domain.Label;
import lapr4.white.s1.core.n4567890.contacts.domain.Event;
import org.junit.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Guilherme Ferreira 1150623 on 02/06/2017.
 */
public class LabelTest {

    private Label label;

    private String name;
    private String photo;
    private String phoneNumber;
    private String email;
    private String address;
    private List<Event> events;

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

        photo = "something";
        name = "something";
        phoneNumber = "something";
        address = "something";
        email = "something";

        events = new ArrayList<>();
        events.add(new Event("something", Calendar.getInstance()));
    }

    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameCantBeNull(){
        label.fillLabel(null, photo, address, email, phoneNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameCantBeEmpty(){
        label.fillLabel("", photo, address, email, phoneNumber);
    }
/*
    @Test(expected = IllegalArgumentException.class)
    public void ensurePhotoCantBeNull(){
        label.fillLabel(name, null, address, email, phoneNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurePhotoCantBeEmpty(){
        label.fillLabel(name, "", address, email, phoneNumber);
    }
*/
    @Test(expected = IllegalArgumentException.class)
    public void ensureAddressesCantBeNull(){
        label.fillLabel(name, photo, null, email, phoneNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmailsCantBeNull(){
        label.fillLabel(name, photo, address, null, phoneNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurePhoneNumbersCantBeNull(){
        label.fillLabel(name, photo, address, email, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEventsListCantBeNull(){
        label.addEvents(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEventsListCantHaveNullEvents(){
        events.add(null);
        label.addEvents(events);
    }
}
