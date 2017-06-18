package lapr4.green.s2.core.n1150738.contacts.domain;

import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Contactable;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a domain service to manipulate tags.
 *
 * @author Ivo Ferro [1151159]
 */
public class TagService {

    /**
     * Retrieves a new filtered set of the given contactables that have tags that match the given tag regex.
     *
     * @param contacts the contacts to be filtered.
     * @param tagRegex the tag regex
     * @return filtered set of contactables that have tags that match the given tag regex
     */
    public Set<Contactable> filterContactablesByRegex(Set<Contactable> contacts, String tagRegex) {
        Set<Contactable> filteredContacts = new HashSet<>();

        for (Contactable contact : contacts) {
            if (contact.containsTag(tagRegex)) {
                filteredContacts.add(contact);
            }
        }

        return filteredContacts;
    }
}
