/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.white.s1.core.n4567890.contacts.domain;

/**
 *
 * @author alexandrebraganca
 */
//import eapli.framework.domain.AggregateRoot;
import eapli.framework.domain.AggregateRoot;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Contact
 *
 * @author ATB
 *
 */
@Entity
public class Contact implements AggregateRoot<Long>, Serializable { 
   
    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk=null;    
    
    // This should be the Business ID
    @Column(unique = true)
    private String name=null;
    
    private String firstName=null;
    
    private String lastName=null;
    
    private String photo=null;

    private String address=null;

    //in the future should be created phone and email classes because they have restrictions that should be verified

    private String phone=null;

    private String email=null;
    
    @OneToOne(cascade = CascadeType.ALL) //(cascade = CascadeType.MERGE)
    private Agenda agenda=null;

    protected Contact() {
        // for ORM
    }

    public Contact(final String name, final String firstName, final String lastName, String photo, String address, String email, String phone) {
        if (name.isEmpty()|firstName.isEmpty()|lastName.isEmpty()|phone.isEmpty()
                |address.isEmpty()|email.isEmpty()|email==null|phone==null|address==null
                |name==null|firstName==null|lastName==null){
            throw new IllegalStateException();
        }
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
        this.address=address;
        this.email=email;
        this.phone=phone;
        this.address=address;
        this.phone=phone;
        this.email=email;
        this.agenda=new Agenda();
    }
    
    @Override
    public String toString() {
        if (this.name==null)
            return super.toString();
        else
            return this.name()+" ("+this.lastName+", "+this.firstName+")";
    }
    
    public String name() {
        return this.name;
    }
    
    public String setName(String name) {
        return this.name=name;
    }

    public String firstName() {
        return this.firstName;
    }

    public String setFirstName(String firstName) {
        return this.firstName=firstName;
    }
    
    public String lastName() {
        return this.lastName;
    }
    
    public String setLastName(String lastName) {
        return this.lastName=lastName;
    }
    
    public Agenda agenda() {
        return this.agenda;
    }

    public String photo() {
        return photo;
    }

    public String setPhoto(String newPhoto) {
        return this.photo=newPhoto;
    }

    public String email() {
        return this.email;
    }

    public String setEmail(String newEmail){ return this.email=newEmail;}

    public String phone() {
        return this.phone;
    }

    public String setPhone(String newPhone) {
        return this.phone=newPhone;
    }

    public String address() {
        return this.address;
    }

    public String setAddress(String newAddress) {
        return this.address=newAddress;
    }

    /**
     * Returns true if contact has events or false otherwise
     * @return
     */
    public boolean hasEvents() {
        return !agenda.isEmpty();
    }

    /**
     * Implemented by João Cardoso - 1150943
     * @param other
     * @return
     */
    @Override
    public boolean sameAs(Object other) {
        if(other instanceof Contact){
            Contact otherContact = (Contact) other;
            return this.name.equalsIgnoreCase(otherContact.name);
        }
        return false;
    }

    @Override
    public boolean is(Long id) {
        return (this.pk.compareTo(id)==0);
    }

    @Override
    public Long id() {
        return this.pk;
    }

}