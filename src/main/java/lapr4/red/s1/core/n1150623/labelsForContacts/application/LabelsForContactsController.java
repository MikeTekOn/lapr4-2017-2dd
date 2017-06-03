package lapr4.red.s1.core.n1150623.labelsForContacts.application;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import lapr4.red.s1.core.n1150623.labelsForContacts.LabelList;
import lapr4.red.s1.core.n1150623.labelsForContacts.domain.Label;
import lapr4.red.s1.core.n1150623.labelsForContacts.domain.LabelFactory;
import lapr4.white.s1.core.n4567890.contacts.ExtensionSettings;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import lapr4.white.s1.core.n4567890.contacts.persistence.ContactRepository;
import lapr4.white.s1.core.n4567890.contacts.persistence.PersistenceContext;

import java.util.Calendar;
import java.util.List;
import java.util.Properties;

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
     * @param address
     * @param phoneNumber
     * @return
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    private boolean addLabel(String name, String photo, String email, String address,  String phoneNumber) throws DataConcurrencyException, DataIntegrityViolationException {
        Label createdLabel = labelFactory.construct(name,photo,email, address, phoneNumber);
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

    public boolean toExportContacts(List<Contact> toExport){
        for(Contact c: toExport){
            try{
                addLabel(c.name(), c.photo(), c.email(), c.address(), c.phoneNumber());
            }catch(Exception e){
                return false;
            }
        }
        return true;
    }

    public void limitEvents(Calendar endDate){
        labelList.limitEvents(endDate);
    }

    public boolean doExport(){
        return labelList.exportPDF();
    }

    public void removeEvents() {
        labelList.removeEvents();
    }
}
