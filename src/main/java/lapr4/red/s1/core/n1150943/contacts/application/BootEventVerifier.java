package lapr4.red.s1.core.n1150943.contacts.application;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import lapr4.red.s1.core.n1150943.contacts.alerts.EventReminder;
import lapr4.red.s1.core.n1150943.contacts.alerts.ShowAlertAction;
import lapr4.white.s1.core.n4567890.contacts.ExtensionSettings;
import lapr4.white.s1.core.n4567890.contacts.domain.Agenda;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import lapr4.white.s1.core.n4567890.contacts.domain.Event;
import lapr4.white.s1.core.n4567890.contacts.persistence.ContactRepository;
import lapr4.white.s1.core.n4567890.contacts.persistence.PersistenceContext;

import java.util.Properties;

import static csheets.CleanSheets.OWN_NAME;

/**
 * Created by João Cardoso - 1150943 on 01-06-2017.
 */
public class BootEventVerifier {

    private Properties appProps=null;
    private ContactRepository contactsRepository;
    private PersistenceContext persistenceContext=null;
    private ExtensionSettings extensionSettings=null;

    /**
     * Verifies the user events, if the app is running for the
     * first time the method creates the OWN CONTACT for him
     * @param props
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    public void verify(Properties props) throws DataConcurrencyException, DataIntegrityViolationException {
        appProps=props;
        extensionSettings=new ExtensionSettings(this.appProps);
        persistenceContext=new PersistenceContext(this.extensionSettings);
        contactsRepository=this.persistenceContext.repositories().contacts();

        for(Contact c : contactsRepository.findAll()){
            if(c.name().equals(OWN_NAME)){
                checkEvents(c);
                return;
            }
        }
        Contact ownContact = new Contact(OWN_NAME,OWN_NAME,OWN_NAME,"","address","email","987515234");
        contactsRepository.save(ownContact);
    }

    /**
     * If the event is today it shows a warning message, if it's not
     * it creates a timer to alert the user when the due date arrives
     * @param c
     */
    private void checkEvents(Contact c){
        Agenda agenda = c.agenda();
        for(Event e : agenda.events()){
            if(e.isToday() && e.notified()==false){
                ShowAlertAction.showAlert(e.description(),e.dueDate());
                e.notifyUser();
                try {
                    contactsRepository.save(c);
                } catch (DataConcurrencyException e1) {
                    e1.printStackTrace();
                } catch (DataIntegrityViolationException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
