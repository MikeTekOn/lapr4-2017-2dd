package lapr4.red.s1.core.n1150943.contacts;

import lapr4.white.s1.core.n4567890.contacts.domain.Event;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

/**
 * Unit Test class to test Contact
 *  @see Event
 *  @author João Cardoso
 *
 */
public class EventTest {

    public EventTest() {
    }

    @Test(expected = IllegalStateException.class)
    public void testEventHasDescription() {
        Event e = new Event("", Calendar.getInstance());
    }

    @Test(expected = IllegalStateException.class)
    public void testEventHasValidDate() {
        Event e = new Event("", Calendar.getInstance());
    }

}