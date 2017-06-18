/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.white.s1.core.n4567890.contacts.domain;

/**
 * @author alexandrebraganca
 */
//import eapli.framework.domain.AggregateRoot;

import eapli.framework.domain.AggregateRoot;
import lapr4.blue.s3.core.n1060503.notes.domain.NotesList;
import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Contactable;
import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Tag;
import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Taggable;
import lapr4.green.s2.core.n1150738.contacts.domain.CompanyContact;
import lapr4.green.s2.core.n1150738.contacts.domain.Profession;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * Contact
 *
 * @author ATB
 */
@Entity
public class Contact implements AggregateRoot<Long>, Serializable, Contactable {

    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk = null;

    // This should be the Business ID
    @Column(unique = true)
    private String name = null;

    private String firstName = null;

    private String lastName = null;

    private String photo = null;

    private String address = null;

    //in the future should be created phone and email classes because they have restrictions that should be verified

    private String phone = null;

    private String email = null;

    @OneToOne(cascade = CascadeType.ALL) //(cascade = CascadeType.MERGE)
    private Agenda agenda = null;

    @OneToOne(cascade = CascadeType.ALL) //(cascade = CascadeType.MERGE)
    private NotesList notesList = null;

    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private CompanyContact companyContact = null;

    private Profession profession = null;

    @ElementCollection
    private Set<Tag> tags;

    protected Contact() {
        // for ORM
    }

    /**
     * Creates a new instance of tags receiving their data.
     *
     * @param name the complete name
     * @param firstName the first name
     * @param lastName the last name
     * @param photo the photo
     * @param address the address
     * @param email the email
     * @param phone the phone
     * @param profession the profession
     * @param companyContact the company contacts
     * @param tags the tags
     */
    public Contact(final String name, final String firstName, final String lastName, String photo, String address,
                   String email, String phone, Profession profession, CompanyContact companyContact, Set<Tag> tags) {
        if (name.isEmpty() | firstName.isEmpty() | lastName.isEmpty() | phone.isEmpty()
                | address.isEmpty() | email.isEmpty() | email == null | phone == null | address == null
                | name == null | firstName == null | lastName == null) {
            throw new IllegalStateException();
        }
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.agenda = new Agenda();
        this.companyContact = companyContact;
        this.profession = profession;
        this.notesList = new NotesList();
        this.tags = new TreeSet<>(tags);
    }

    public Contact(final String name, final String firstName, final String lastName, String photo, String address,
                   String email, String phone, Profession profession, CompanyContact companyContact) {
        this(name, firstName, lastName, photo, address, email, phone, profession, companyContact, new TreeSet<>());
    }

    public Contact(final String name, final String firstName, final String lastName, String photo, String address, String email, String phone) {
        this(name, firstName, lastName, photo, address, email, phone, null, null);
    }

    @Override
    public String toString() {
        if (this.name == null)
            return super.toString();
        else
            return this.name() + " (" + this.lastName + ", " + this.firstName + ")";
    }

    public String name() {
        return this.name;
    }

    public String setName(String name) {
        return this.name = name;
    }

    public String firstName() {
        return this.firstName;
    }

    public String setFirstName(String firstName) {
        return this.firstName = firstName;
    }

    public String lastName() {
        return this.lastName;
    }

    public String setLastName(String lastName) {
        return this.lastName = lastName;
    }

    public Agenda agenda() {
        return this.agenda;
    }

    public String photo() {
        return photo;
    }

    public String setPhoto(String newPhoto) {
        return this.photo = newPhoto;
    }

    public String email() {
        return this.email;
    }

    public String setEmail(String newEmail) {
        return this.email = newEmail;
    }

    public String phone() {
        return this.phone;
    }

    public String setPhone(String newPhone) {
        return this.phone = newPhone;
    }

    public String address() {
        return this.address;
    }

    public String setAddress(String newAddress) {
        return this.address = newAddress;
    }

    public CompanyContact getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(CompanyContact companyContact) {
        this.companyContact = companyContact;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    @Override
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    /**
     * List of notes of a contact
     * Implemented by Pedro Fernandes 1060503
     *
     * @return list of notes of a contact
     */
    public NotesList notesList() {
        return this.notesList;
    }

    /**
     * Returns true if contact has events or false otherwise
     *
     * @return
     */
    public boolean hasEvents() {
        return !agenda.isEmpty();
    }

    /**
     * Implemented by João Cardoso - 1150943
     *
     * @param other
     * @return
     */
    @Override
    public boolean sameAs(Object other) {
        if (other instanceof Contact) {
            Contact otherContact = (Contact) other;
            return this.name.equalsIgnoreCase(otherContact.name);
        }
        return false;
    }

    @Override
    public boolean is(Long id) {
        return (this.pk.compareTo(id) == 0);
    }

    @Override
    public Long id() {
        return this.pk;
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
    public String contactName() {
        return name;
    }

    @Override
    public String contactType() {
        return "Personal Contact";
    }
}