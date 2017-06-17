package lapr4.blue.s3.core.n1151159.contactswithtags.domain;

import java.util.Set;

/**
 * Interface that adds the behavior of being taggable.
 *
 * @author Ivo Ferro [1151159]
 */
public interface Taggable {

    /**
     * Check if the taggable class contains any tag that matches the given tag regular expression.
     *
     * @param tagRegex the tag regular expression
     * @return true if it does contain, false otherwise
     */
    boolean containsTag(String tagRegex);

    /**
     * Gets the tag textual representation.
     *
     * @return tag textual representation
     */
    Set<Tag> getTags();
}
