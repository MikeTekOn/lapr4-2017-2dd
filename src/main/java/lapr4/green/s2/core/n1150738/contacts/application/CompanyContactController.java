package lapr4.green.s2.core.n1150738.contacts.application;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import lapr4.green.s2.core.n1150738.contacts.domain.*;
import lapr4.green.s2.core.n1150738.contacts.persistence.CompanyContactRepository;
import lapr4.white.s1.core.n4567890.contacts.ExtensionSettings;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import lapr4.white.s1.core.n4567890.contacts.domain.Event;
import lapr4.white.s1.core.n4567890.contacts.persistence.ContactRepository;
import lapr4.white.s1.core.n4567890.contacts.persistence.PersistenceContext;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Represents a controller for the user story of company contacts Core10.1.2
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class CompanyContactController {

    private Properties appProps;
    private final CompanyContactRepository companyContactRepository;
    private final ContactRepository contactRepository;
    private final PersistenceContext persistenceContext;
    private final ExtensionSettings extensionSettings;

    public CompanyContactController(Properties props) {
        this.appProps = props;
        this.extensionSettings = new ExtensionSettings(this.appProps);
        this.persistenceContext = new PersistenceContext(this.extensionSettings);
        this.companyContactRepository = this.persistenceContext.repositories().companyContacts();
        this.contactRepository = this.persistenceContext.repositories().contacts();
    }

    public List<CompanyContact> allCompanyContacts() {
        Iterable<CompanyContact> contacts = companyContactRepository.findAll();
        LinkedList<CompanyContact> list = new LinkedList();
        contacts.forEach(list::add);
        return list;
    }

    public List<Contact> allContactsRelatedTo(CompanyContact c) {
        CompanyContact ct = getContactById(c.companyName());
        Iterable<Contact> contacts = contactRepository.allRelatedToCompany(ct);
        LinkedList<Contact> list = new LinkedList();
        contacts.forEach(list::add);
        return list;
    }

    public List<Event> companyAgenda(CompanyContact c) {
        CompanyContact ct = getContactById(c.companyName());
        Iterable<Contact> contacts = contactRepository.allRelatedToCompany(ct);
        LinkedList<Event> list = new LinkedList();
        contacts.forEach((Contact cnt)->{cnt.agenda().events().forEach(list::add);});
        return list;
        //TODO
    }

    public CompanyContact getContactById(CompanyName id) {
        return companyContactRepository.findByCompanyName(id);
    }

    public CompanyContact addContact(String name, String email, String phone, byte[] image) throws DataConcurrencyException, DataIntegrityViolationException {
        CompanyContact c = new CompanyContact(new CompanyName(name), new EmailAddress(email), new PhoneNumber(phone), image == null ? null : new Image(image));
        c = companyContactRepository.save(c);
        return c;
    }

    public boolean removeContact(CompanyContact contact) throws DataConcurrencyException, DataIntegrityViolationException {
        List<Contact> related = allContactsRelatedTo(contact);
        for(Contact c : related){
            c.setCompanyContact(null);
            contactRepository.save(c);
        }
        return companyContactRepository.remove(contact);
    }

    public CompanyContact updateContact(CompanyContact c, String name, String email, String phone, byte[] image) throws DataConcurrencyException, DataIntegrityViolationException {
        c.update(new CompanyName(name), new EmailAddress(email), new PhoneNumber(phone), image == null ? null : new Image(image));
        companyContactRepository.save(c);
        return c;
    }
}
