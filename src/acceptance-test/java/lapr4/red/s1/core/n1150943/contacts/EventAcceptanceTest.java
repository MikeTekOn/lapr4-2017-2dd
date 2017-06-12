package lapr4.red.s1.core.n1150943.contacts;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import lapr4.red.s1.core.n1150943.contacts.application.EventController;
import lapr4.white.s1.core.n4567890.contacts.application.ContactController;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import lapr4.white.s1.core.n4567890.contacts.domain.Event;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

/**
 * Created by João Cardoso on 31-05-2017.
 */
public class EventAcceptanceTest {

    private static Properties appProps=null;
    private static EventController controller1=null;
    private static ContactController controller2=null;
    private static Contact aContact=null;
    private static Event aEvent=null;

    private static final String REPOSITORY_FACTORY_KEY = "persistence.repositoryFactory";
    private static final String REPOSITORY_FACTORY_VALUE = "lapr4.white.s1.core.n4567890.contacts.persistence.jpa.JpaRepositoryFactory";

    private static final String PERSISTENCE_UNIT_KEY = "persistence.persistenceUnit";
    private static final String PERSISTENCE_UNIT_VALUE = "lapr4.csheets-crm-extension-PU";


    private static void setupProperties() {

        // Properties for persitence unit using h2 "memory"
        appProps=new Properties();

        appProps.put(REPOSITORY_FACTORY_KEY, REPOSITORY_FACTORY_VALUE);
        appProps.put(PERSISTENCE_UNIT_KEY, PERSISTENCE_UNIT_VALUE);

        appProps.put("javax.persistence.jdbc.url", "jdbc:h2:mem:csheets-crm-extension;MV_STORE=FALSE");
        appProps.put("javax.persistence.jdbc.password", "");
        appProps.put("javax.persistence.jdbc.driver", "org.h2.Driver");
        appProps.put("javax.persistence.jdbc.user", "");
        appProps.put("javax.persistence.schema-generation.database.action", "create");
        appProps.put("eclipselink.logging.level", "FINE");
    }

    @BeforeClass
    public static void setUp() throws DataIntegrityViolationException, DataConcurrencyException {

        // Setup for testing (JPA in Memory)
        setupProperties();
        controller1=new EventController(appProps);
        controller2=new ContactController(appProps);

        // Populate the repository
        aContact = controller2.addContact("Jane Doe", "Jane", "Doe", "","asd","asd","asd", null, null);

        Calendar tomorrow=Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_WEEK, 1);
        aEvent=controller1.addEvent(aContact, "Pay Taxes", tomorrow);
    }

    @Test
    public void normalBehaviorAddEventToContact() throws DataIntegrityViolationException, DataConcurrencyException {

        // First: Add or Select existing Contact
        Contact contact=controller2.addContact("Jane Doe4", "Jane", "Doe4","","asd","asd","asd", null, null);
        EventController c = new EventController(appProps);
        // Second: Add Event to Contact's Agenda
        Event ev=null;
        Calendar tomorrow=Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_WEEK, 1);
        ev=c.addEvent(contact, "Pay Taxes", tomorrow);

        assertNotNull(ev);
    }

}
