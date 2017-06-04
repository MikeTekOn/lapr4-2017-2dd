package lapr4.red.s1.core.n1150943.contacts;

import lapr4.white.s1.core.n4567890.contacts.domain.Agenda;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by k4rd050 on 31-05-2017.
 */
public class AgendaTest {

    Contact contact=null;
    Agenda agenda=null;

    @Before
    public void setUp() {
        contact=new Contact("João Cardoso","João","Cardoso","");
        agenda=contact.agenda();
    }

    @Test(expected = IllegalStateException.class)
    public void testAgendaCantAddNullEvent() {
        agenda.add(null);
    }

}
