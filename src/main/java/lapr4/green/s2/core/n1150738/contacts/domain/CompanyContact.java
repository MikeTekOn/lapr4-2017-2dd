package lapr4.green.s2.core.n1150738.contacts.domain;


import eapli.framework.domain.AggregateRoot;
import org.eclipse.persistence.annotations.PrimaryKey;

import javax.persistence.*;

/**
 * Represents a Company Contact.
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
@Entity
public class CompanyContact implements AggregateRoot<CompanyName> {

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

    /**
     * Constructs a CompanyContact with a given name and image.
     *
     * @param image the company's image
     * @param name the company name
     */
    public CompanyContact(CompanyName name, EmailAddress email, PhoneNumber number, Image image){
        if(name == null || email == null || number == null){
            throw new IllegalArgumentException("CampanyContact cannot have null attributes");
        }
        this.companyName = name;
        this.mail = email;
        this.phoneNumber = number;
        this.image = image == null ? Image.defaultImage() : image;
    }

    /**
     * Constructs a CompanyContact with a given name and a default user image.
     *
     * @see Image@defaultImage
     * @param name the company name
     */
    public CompanyContact(CompanyName name, EmailAddress email, PhoneNumber number){
        this(name, email, number, Image.defaultImage());
    }

    /**
     * Constructor for ORM
     */
    protected CompanyContact(){
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

    public boolean update(CompanyName name, EmailAddress email, PhoneNumber number, Image image){
        if(name == null || email == null || number == null || image == null){
            throw new IllegalArgumentException("CampanyContact cannot have null attributes");
        }
        this.companyName = name;
        this.mail = email;
        this.phoneNumber = number;
        this.image = image;
        return true;
    }
}
