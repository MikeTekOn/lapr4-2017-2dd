package lapr4.green.s2.core.n1150738.contacts.application;

import lapr4.green.s2.core.n1150738.contacts.domain.CompanyContact;
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
        Iterable<Contact> contacts = contactRepository.allRelatedToCompany(c);
        LinkedList<Contact> list = new LinkedList();
        contacts.forEach(list::add);
        return list;
    }

    private List<Event> companyAgenda(CompanyContact c) {
        Iterable<Contact> contacts = contactRepository.allRelatedToCompany(c);
        LinkedList<Event> list = new LinkedList();
        contacts.forEach((Contact cnt)->{cnt.agenda().events().forEach(list::add);});
        return list;
        //TODO
    }

}
