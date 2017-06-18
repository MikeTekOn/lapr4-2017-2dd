package lapr4.green.s2.core.n1150738.contacts.domain;


import eapli.framework.domain.AggregateRoot;
import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Contactable;
import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Tag;
import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Taggable;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a Company Contact.
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
@Entity
public class CompanyContact implements AggregateRoot<CompanyName>, Contactable {

    @Id
    @GeneratedValue
    private Long pk;

    /**
     * The name of the Company
     */
    @Column(unique = true)
    private CompanyName companyName;

    /**
     * A image associated with this contact. ex: logo
     */
    private Image image;

    /**
     * The email of the contact.
     */
    private EmailAddress mail;

    /**
     * The phone number.
     */
    private PhoneNumber phoneNumber;
    @ElementCollection
    private Set<Tag> tags;

    /**
     * Creates a new company contact receiving their name, email, number, image and associated tags.
     *
     * @param name   the name
     * @param email  the email
     * @param number the number
     * @param image  the image
     * @param tags   the associated tags
     */
    public CompanyContact(CompanyName name, EmailAddress email, PhoneNumber number, Image image, Set<Tag> tags) {
        if (name == null || email == null || number == null || tags == null) {
            throw new IllegalArgumentException("Campany contact cannot have null attributes");
        }
        this.companyName = name;
        this.mail = email;
        this.phoneNumber = number;
        this.image = image == null ? Image.defaultImage() : image;
        this.tags = new TreeSet<>(tags);
    }

    /**
     * Creates a new company contact receiving their name, email, number and image.
     *
     * @param name   the name
     * @param email  the email
     * @param number the number
     * @param image  the image
     */
    public CompanyContact(CompanyName name, EmailAddress email, PhoneNumber number, Image image) {
        this(name, email, number, image, new TreeSet<>());
    }

    /**
     * Constructs a CompanyContact with a given name and a default user image.
     *
     * @param name the company name
     */
    public CompanyContact(CompanyName name, EmailAddress email, PhoneNumber number) {
        this(name, email, number, Image.defaultImage());
    }

    /**
     * Constructor for ORM
     */
    protected CompanyContact() {
        //ORM
    }

    @Override
    public String toString() {
        return companyName.toString();
    }

    @Override
    public boolean is(CompanyName id) {
        return false;
    }

    @Override
    public CompanyName id() {
        return companyName;
    }

    @Override
    public boolean sameAs(Object other) {
        return this.equals(other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyContact that = (CompanyContact) o;

        return companyName.equals(that.companyName);
    }

    @Override
    public int hashCode() {
        return companyName.hashCode();
    }

    public CompanyName companyName() {
        return companyName;
    }

    public Image image() {
        return image;
    }

    public EmailAddress mail() {
        return mail;
    }

    public PhoneNumber phoneNumber() {
        return phoneNumber;
    }

    /**
     * Updates the company contact with the given parameters
     *
     * @param name   the company name
     * @param email  the campany's email address
     * @param number the campany's number
     * @param image  the campany's image / logo
     * @param tags   the tags associated with the company contact
     * @return success of the operation
     */
    public boolean update(CompanyName name, EmailAddress email, PhoneNumber number, Image image, Set<Tag> tags) {
        if (name == null || email == null || number == null) {
            throw new IllegalArgumentException("CampanyContact cannot have null attributes");
        }
        this.companyName = name;
        this.mail = email;
        this.phoneNumber = number;
        this.image = image;
        this.tags = new TreeSet<>(tags);
        return true;
    }

    @Override
    public boolean containsTag(String tagRegex) {
        for (Tag tag : tags) {
            if (tag.matches(tagRegex)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<Tag> getTags() {
        return tags;
    }

    @Override
    public String contactName() {
        return companyName.name();
    }

    @Override
    public String contactType() {
        return "Company Contact";
    }
}
