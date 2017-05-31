package lapr4.red.s1.core.n1150943.contacts.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import lapr4.white.s1.core.n4567890.contacts.ExtensionSettings;
import lapr4.white.s1.core.n4567890.contacts.domain.Agenda;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import lapr4.white.s1.core.n4567890.contacts.domain.Event;
import lapr4.white.s1.core.n4567890.contacts.persistence.ContactRepository;
import lapr4.white.s1.core.n4567890.contacts.persistence.PersistenceContext;

import javax.swing.*;
import java.util.Calendar;
import java.util.Properties;

/**
 * Created by João Cardoso - 1150943 on 30-05-2017.
 */
public class AddEventController implements Controller {

    private Properties appProps;
    private final ContactRepository contactsRepository;
    private final PersistenceContext persistenceContext;
    private final ExtensionSettings extensionSettings;

    public AddEventController(Properties props) {
        this.appProps=props;
        this.extensionSettings=new ExtensionSettings(this.appProps);
        this.persistenceContext=new PersistenceContext(this.extensionSettings);
        this.contactsRepository=this.persistenceContext.repositories().contacts();
    }

    /**
     * Creates a new event associated with an existing contacts
     * @param contact
     * @param eventDescription
     * @param dueDate
     * @return
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    public Event addEvent(Contact contact, String eventDescription, Calendar dueDate) throws DataConcurrencyException, DataIntegrityViolationException {
        Agenda a = contact.agenda();
        Event ev=null;
        try{
            ev=new Event(eventDescription, dueDate);
            a.add(ev);
            this.contactsRepository.save(contact);
        }catch (IllegalStateException e){
            JOptionPane.showMessageDialog(null,"The date you picked is in the past");
        }
        return ev;
    }
}
