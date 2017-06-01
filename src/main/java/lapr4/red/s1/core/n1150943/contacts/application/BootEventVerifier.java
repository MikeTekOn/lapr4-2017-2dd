package lapr4.red.s1.core.n1150943.contacts.application;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import lapr4.red.s1.core.n1150943.contacts.alerts.ShowAlertAction;
import lapr4.white.s1.core.n4567890.contacts.ExtensionSettings;
import lapr4.white.s1.core.n4567890.contacts.domain.Agenda;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import lapr4.white.s1.core.n4567890.contacts.domain.Event;
import lapr4.white.s1.core.n4567890.contacts.persistence.ContactRepository;
import lapr4.white.s1.core.n4567890.contacts.persistence.PersistenceContext;

import java.util.Properties;

/**
 * Created by João Cardoso - 1150943 on 01-06-2017.
 */
public class BootEventVerifier {

    private static final String OWN_NAME = "ME";

    private Properties appProps=null;
    private ContactRepository contactsRepository;
    private PersistenceContext persistenceContext=null;
    private ExtensionSettings extensionSettings=null;

    public void verify(Properties props) throws DataConcurrencyException, DataIntegrityViolationException {
        appProps=props;
        extensionSettings=new ExtensionSettings(this.appProps);
        persistenceContext=new PersistenceContext(this.extensionSettings);
        contactsRepository=this.persistenceContext.repositories().contacts();

        for(Contact c : contactsRepository.findAll()){
            if(c.name().equals("ME")){
                checkEvents(c);
                return;
            }
        }
        Contact ownContact = new Contact(OWN_NAME,OWN_NAME,OWN_NAME,"");
        contactsRepository.save(ownContact);
    }

    private void checkEvents(Contact c){
        Agenda agenda = c.agenda();
        for(Event e : agenda.events()){
            if(e.isToday()){
                ShowAlertAction.showAlert(e.description(),e.dueDate());
            }
        }
    }

}
