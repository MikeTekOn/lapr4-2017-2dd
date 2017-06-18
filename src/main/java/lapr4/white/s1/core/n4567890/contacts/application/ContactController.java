/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.white.s1.core.n4567890.contacts.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

import java.io.IOException;
import java.util.*;

import lapr4.blue.s3.core.n1151159.contactswithtags.domain.Tag;
import lapr4.green.s2.core.n1150738.contacts.application.ProfessionImporterService;
import lapr4.green.s2.core.n1150738.contacts.domain.CompanyContact;
import lapr4.green.s2.core.n1150738.contacts.domain.Profession;
import lapr4.green.s2.core.n1150738.contacts.persistence.CompanyContactRepository;
import lapr4.white.s1.core.n4567890.contacts.ExtensionSettings;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import lapr4.white.s1.core.n4567890.contacts.domain.Event;
import lapr4.white.s1.core.n4567890.contacts.persistence.ContactRepository;
import lapr4.white.s1.core.n4567890.contacts.persistence.PersistenceContext;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;

import static csheets.CleanSheets.OWN_NAME;


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

    /**
     * Returns the list of professions imported from external xml
     * @see ProfessionImporterService
     *
     * @return list of professions
     * @throws ParserConfigurationException exception parsing professions file
     * @throws SAXException                 exception parsing professions file
     * @throws IOException                  exception parsing professions file
     * @author Henrique Oliveira [1150738@isep.ipp.pt]
     */
    public List<Profession> professions() throws ParserConfigurationException, SAXException, IOException {
        ProfessionImporterService svc = new ProfessionImporterService();
        return svc.professions();
    }

    /**
     * Returns a list with all company contacts existing for a contact be associated with.
     * @return  Returns a list with all company.
     * @author Henrique Oliveira [1150738@isep.ipp.pt]
     */
    public List<CompanyContact> companyContacts(){
        CompanyContactRepository companyContactRepository = persistenceContext.repositories().companyContacts();
        List<CompanyContact> list = new LinkedList<>();
        companyContactRepository.findAll().forEach(list::add);
        return list;
    }

    public Contact addContact(String name, String firstName, String lastName, String photo, String address, String email, String phone, CompanyContact companyContact, Profession profession, Set<Tag> tags) throws DataConcurrencyException, DataIntegrityViolationException {
        return this.contactsRepository.save(new Contact(name, firstName, lastName, photo, address, email, phone, profession, companyContact, tags));
    }

    public boolean removeContact(Contact contact) throws DataConcurrencyException, DataIntegrityViolationException, IllegalAccessException {
        if(contact.hasEvents()){
            throw new DataIntegrityViolationException();
        }
        if(contact.name().equals(OWN_NAME)){
            throw new IllegalAccessException();
        }
        return this.contactsRepository.removeContact(contact);
    }
    
    public Contact updateContact(Contact contact, String fullName, String firstName, String lastName, String photo, String address, String email, String phone,CompanyContact companyContact, Profession profession, Set<Tag> tags) throws DataConcurrencyException, DataIntegrityViolationException, IllegalAccessException {
        if(contact.name().equals(OWN_NAME)){
            throw new IllegalAccessException();
        }
        contact.setName(fullName);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setAddress(address);
        contact.setPhoto(photo);
        contact.setEmail(email);
        contact.setPhone(phone);
        contact.setCompanyContact(companyContact);
        contact.setProfession(profession);
        contact.setTags(tags);
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