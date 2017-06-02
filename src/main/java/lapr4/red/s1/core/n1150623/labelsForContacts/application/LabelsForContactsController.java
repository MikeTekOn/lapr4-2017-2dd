package lapr4.red.s1.core.n1150623.labelsForContacts.application;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.util.DateTime;
import lapr4.red.s1.core.n1150623.labelsForContacts.LabelList;
import lapr4.red.s1.core.n1150623.labelsForContacts.domain.Label;
import lapr4.white.s1.core.n4567890.contacts.ExtensionSettings;
import lapr4.white.s1.core.n4567890.contacts.application.LabelFactory;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import lapr4.white.s1.core.n4567890.contacts.persistence.ContactRepository;
import lapr4.white.s1.core.n4567890.contacts.persistence.PersistenceContext;

import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Guilherme Ferreira 1150623 on 01/06/2017.
 */
public class LabelsForContactsController {


    private Properties appProps;
    private final ContactRepository contactsRepository;
    private final PersistenceContext persistenceContext;
    private final ExtensionSettings extensionSettings;
    private final LabelFactory labelFactory;
    private final LabelList labelList;

    public LabelsForContactsController(Properties props) {
        this.appProps=props;
        this.extensionSettings=new ExtensionSettings(this.appProps);
        this.persistenceContext=new PersistenceContext(this.extensionSettings);
        this.contactsRepository=this.persistenceContext.repositories().contacts();
        this.labelFactory = new LabelFactory();
        this.labelList = new LabelList();
    }

    /**
     * Adds a label to the label list
     * @param name
     * @param photo
     * @param email
     * @param addresses
     * @param phoneNumbers
     * @return
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    public boolean addLabel(String name, String photo, Set<String> email, Set<String> addresses,  Set<String> phoneNumbers) throws DataConcurrencyException, DataIntegrityViolationException {
        Label createdLabel = labelFactory.construct(name,photo,email, addresses, phoneNumbers);
        return labelList.addLabel(createdLabel);
    }

    /**
     *
     * @return all contacts of the user
     */
    public Iterable<Contact> allContacts() {
        return this.contactsRepository.findAll();
    }

    /**
     * All contacts of the user that matches the given regex
     * @param regexPattern - the regex patter used to find users
     * @return list of users found
     */
    public List<Contact> getContactByRegex(String regexPattern) {
        List<Contact> list =this.contactsRepository.findByRegex(regexPattern);
        return list;
    }


    public void limitEvents(DateTime iniDate, DateTime endDate){
        //TODO: Event limitator - from the list of events, remove those outside the boundaries
    }

    public boolean doExport(){
        return labelList.exportPDF();
    }
}
