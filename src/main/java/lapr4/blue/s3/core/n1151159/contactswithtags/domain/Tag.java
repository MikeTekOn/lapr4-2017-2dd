package lapr4.blue.s3.core.n1151159.contactswithtags.domain;

import eapli.framework.domain.ValueObject;
import lapr4.blue.s3.core.n1151159.contactswithtags.util.Strings;

import javax.persistence.Embeddable;

/**
 * A tag is a alphanumeric word that can be associated to contacts.
 *
 * @author Ivo Ferro [1151159]
 */
@Embeddable
public class Tag implements ValueObject, Comparable<Tag> {

    /**
     * The alphanumeric designation of the tag.
     */
    private String designation;

    /**
     * Protected constructor for JPA purposes.
     */
    protected Tag() {
        // for ORM
    }

    /**
     * Creates a new tag with the given designation.
     *
     * @param designation the alphanumeric designation of the tag
     * @throws IllegalStateException if the designation is not alphanumeric
     */
    public Tag(String designation) throws IllegalStateException {
        if (eapli.util.Strings.isNullOrWhiteSpace(designation) || !Strings.isAlphanumeric(designation)) {
            throw new IllegalStateException("The designation of a tag must be alphanumeric.");
        }

        this.designation = designation;
    }

    @Override
    public int hashCode() {
        return designation.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tag tag = (Tag) o;
        return designation.equals(tag.designation);
    }

    @Override
    public String toString() {
        return String.format("Tag{designation=%s}", designation);
    }

    @Override
    public int compareTo(Tag tag) {
        return this.designation.compareTo(tag.designation);
    }
}
