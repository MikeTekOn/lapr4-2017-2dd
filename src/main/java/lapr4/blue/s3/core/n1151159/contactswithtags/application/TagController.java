package lapr4.blue.s3.core.n1151159.contactswithtags.application;

import eapli.framework.application.Controller;
import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Contactable;
import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Tag;
import lapr4.blue.s3.core.n1151159.contactswithtags.domain.TagService;
import lapr4.green.s2.core.n1150738.contacts.domain.CompanyContact;
import lapr4.green.s2.core.n1150738.contacts.persistence.CompanyContactRepository;
import lapr4.white.s1.core.n4567890.contacts.ExtensionSettings;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import lapr4.white.s1.core.n4567890.contacts.persistence.ContactRepository;
import lapr4.white.s1.core.n4567890.contacts.persistence.PersistenceContext;

import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * An application controller to search contacts by tags.
 *
 * @author Ivo Ferro [1151159]
 */
public class TagController implements Controller {

    /**
     * The constant of no filters.
     */
    public static final int NO_FILTER = 0;
    /**
     * The constant of personal contacts filter.
     */
    public static final int PERSONAL_CONTACTS_FILTER = 1;
    /**
     * The constant of company contacts filter.
     */
    public static final int COMPANY_CONTACTS_FILTER = 2;
    /**
     * The persistence context.
     */
    private final PersistenceContext persistenceContext;
    /**
     * The contacts repository.
     */
    private ContactRepository contactsRepository;
    /**
     * The company contacts repository.
     */
    private CompanyContactRepository companyContactRepository;

    /**
     * Creates a new instance of search contact by tag controller.
     *
     * @param props the application properties
     */
    public TagController(Properties props) {
        Properties appProps = props;
        ExtensionSettings extensionSettings = new ExtensionSettings(appProps);
        persistenceContext = new PersistenceContext(extensionSettings);
    }

    /**
     * Finds the contacts that has a tag that matches the given tag regular expression.
     *
     * @param tagRegex the tag regular expression
     * @param filter   the search filters
     * @return contacts that has a tag that matches the given tag regular expresion
     */
    public Set<Contactable> findContactsByTag(String tagRegex, int filter) {
        TagService tagService = new TagService();
        return tagService.filterContactablesByRegex(getContacts(filter), tagRegex);
    }


    /**
     * Gets the tags frequency.
     *
     * @return a map with the tags frequency, in which the key represents the tag and value represents the occurrences.
     */
    public Map<Tag, Integer> getTagFrequency() {
        TagService tagService = new TagService();
        return tagService.filterTagsWithFrequency(getContacts(NO_FILTER));
    }

    /**
     * Retrieves contacts from repository.
     *
     * @return set with all contacts
     */
    private Set<Contactable> getContacts(int filter) {
        Set<Contactable> allContacts = new HashSet<>();

        switch (filter) {
            case NO_FILTER:
                allContacts.addAll(personalContacts());
                allContacts.addAll(companyContacts());
                break;
            case PERSONAL_CONTACTS_FILTER:
                allContacts.addAll(personalContacts());
                break;
            case COMPANY_CONTACTS_FILTER:
                allContacts.addAll(companyContacts());
                break;
        }

        return allContacts;
    }

    /**
     * Get the personal contacts from repository.
     *
     * @return personal contacts
     */
    private Set<Contact> personalContacts() {
        contactsRepository = persistenceContext.repositories().contacts();
        Set<Contact> personalContacts = new HashSet<>();

        Iterable<Contact> contacts = contactsRepository.findAll();
        for (Contact contact : contacts) {
            personalContacts.add(contact);
        }

        return personalContacts;
    }

    /**
     * Get the company contacts from repository.
     *
     * @return company contacts
     */
    private Set<CompanyContact> companyContacts() {
        companyContactRepository = persistenceContext.repositories().companyContacts();
        Set<CompanyContact> companyContacts = new HashSet<>();

        Iterable<CompanyContact> contacts = companyContactRepository.findAll();
        for (CompanyContact contact : contacts) {
            companyContacts.add(contact);
        }

        return companyContacts;
    }


}
