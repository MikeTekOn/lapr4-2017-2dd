package lapr4.green.s2.core.n1150738.contacts.company.domain;


import eapli.framework.domain.AggregateRoot;

import javax.persistence.*;

/**
 * Represents a Company Contact.
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
@Entity
public class CompanyContact implements AggregateRoot<CompanyName> {

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
     * Constructs a CompanyContact with a given name and image.
     *
     * @param image the company's image
     * @param name the company name
     */
    public CompanyContact(CompanyName name, Image image){
        if(name == null || image == null){
            throw new IllegalArgumentException("CampanyContact cannot have null attributes");
        }
    }

    /**
     * Constructs a CompanyContact with a given name and a default user image.
     *
     * @see Image@defaultImage
     * @param name the company name
     */
    public CompanyContact(CompanyName name){
        this(name, Image.defaultImage());
    }

    /**
     * Constructor for ORM
     */
    protected CompanyContact(){
        //ORM
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
}
