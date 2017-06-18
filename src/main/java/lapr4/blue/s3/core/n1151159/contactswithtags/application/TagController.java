package lapr4.blue.s3.core.n1151159.contactswithtags.application;

import eapli.framework.application.Controller;
import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Contactable;
import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Tag;
import lapr4.green.s2.core.n1150738.contacts.domain.CompanyContact;
import lapr4.blue.s3.core.n1151159.contactswithtags.domain.TagService;
import lapr4.green.s2.core.n1150738.contacts.persistence.CompanyContactRepository;
import lapr4.white.s1.core.n4567890.contacts.ExtensionSettings;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import lapr4.white.s1.core.n4567890.contacts.persistence.ContactRepository;
import lapr4.white.s1.core.n4567890.contacts.persistence.PersistenceContext;

import java.util.*;

/**
 * An application controller to search contacts by tags.
 *
 * @author Ivo Ferro [1151159]
 */
public class TagController implements Controller {

    /**
     * The contacts repository.
     */
    private final ContactRepository contactsRepository;

    /**
     * The company contacts repository.
     */
    private final CompanyContactRepository companyContactRepository;

    /**
     * Creates a new instance of search contact by tag controller.
     *
     * @param props the application properties
     */
    public TagController(Properties props) {
        Properties appProps = props;
        ExtensionSettings extensionSettings = new ExtensionSettings(appProps);
        PersistenceContext persistenceContext = new PersistenceContext(extensionSettings);

        this.contactsRepository = persistenceContext.repositories().contacts();
        this.companyContactRepository = persistenceContext.repositories().companyContacts();
    }

    /**
     * Finds the contacts that has a tag that matches the given tag regular expression.
     *
     * @param tagRegex the tag regular expression
     * @return contacts that has a tag that matches the given tag regular expresion
     */
    public Set<Contactable> findContactsByTag(String tagRegex) {
        TagService tagService = new TagService();
        return tagService.filterContactablesByRegex(allContacts(), tagRegex);
    }


    /**
     * Gets the tags frequency.
     *
     * @return a map with the tags frequency, in which the key represents the tag and value represents the occurrences.
     */
    public Map<Tag, Integer> getTagFrequency() {
        TagService tagService = new TagService();
        return tagService.filterTagsWithFrequency(allContacts());
    }

    /**
     * Retrieves all contacts from repository.
     *
     * @return set with all contacts
     */
    private Set<Contactable> allContacts() {
        Set<Contactable> allContacts = new HashSet<>();

        Iterable<Contact> contacts = contactsRepository.findAll();
        for (Contact contact : contacts) {
            allContacts.add(contact);
        }
        Iterable<CompanyContact> companyContacts = companyContactRepository.findAll();
        for (CompanyContact contact : companyContacts) {
            allContacts.add(contact);
        }

        return allContacts;
    }
}
