/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.white.s1.core.n4567890.contacts.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.Optional;
import java.util.Properties;
import lapr4.white.s1.core.n4567890.contacts.ExtensionSettings;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import lapr4.white.s1.core.n4567890.contacts.domain.Event;
import lapr4.white.s1.core.n4567890.contacts.persistence.ContactRepository;
import lapr4.white.s1.core.n4567890.contacts.persistence.PersistenceContext;

import javax.swing.*;


/**
 *
 * @author alexandrebraganca
 */
public class ContactController implements Controller {
    
    private Properties appProps;
    private final ContactRepository contactsRepository;
    private final PersistenceContext persistenceContext;
    private final ExtensionSettings extensionSettings;
    
    public ContactController(Properties props) {
        this.appProps=props;
        this.extensionSettings=new ExtensionSettings(this.appProps);
        this.persistenceContext=new PersistenceContext(this.extensionSettings);
        this.contactsRepository=this.persistenceContext.repositories().contacts();
    }

    public Contact addContact(String name, String firstName, String lastName, String photo) throws DataConcurrencyException, DataIntegrityViolationException {
        return this.contactsRepository.save(new Contact(name, firstName, lastName, photo));
    }

    public boolean removeContact(Contact contact) throws DataConcurrencyException, DataIntegrityViolationException {
        if(contact.hasEvents()){
            throw new DataIntegrityViolationException();
        }
        return this.contactsRepository.removeContact(contact);
    }
    
    public Contact updateContact(Contact contact, String fullName, String firstName, String lastName) throws DataConcurrencyException, DataIntegrityViolationException {
        contact.setName(fullName);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        return this.contactsRepository.save(contact);
    }    

    public Iterable<Contact> allContacts() {
        return this.contactsRepository.findAll();
    }
    
    public Contact getContactById(Long id) {
        Optional<Contact> c=this.contactsRepository.findOne(id);
        if (c.isPresent()) return c.get();
        else return null;
    }
}