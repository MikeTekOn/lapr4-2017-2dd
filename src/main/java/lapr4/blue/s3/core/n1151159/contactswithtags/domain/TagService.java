package lapr4.blue.s3.core.n1151159.contactswithtags.domain;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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

    /**
     * Retrieves the tags frequency.
     *
     * @param contacts the contacts to seek the tags
     * @return tags frequency
     */
    public Map<Tag, Integer> filterTagsWithFrequency(Set<Contactable> contacts) {
        Map<Tag, Integer> result = new TreeMap<>();

        for (Contactable contact : contacts) {
            for (Tag tag : contact.getTags()) {
                if (result.containsKey(tag)) {
                    result.put(tag, result.get(tag) + 1);
                } else {
                    result.put(tag, 1);
                }
            }
        }

        return result;
    }
}
