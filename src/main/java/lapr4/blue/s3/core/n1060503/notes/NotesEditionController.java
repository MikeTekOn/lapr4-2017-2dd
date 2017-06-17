/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s3.core.n1060503.notes;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import lapr4.blue.s3.core.n1060503.notes.domain.Note;
import lapr4.blue.s3.core.n1060503.notes.domain.NotesList;
import lapr4.white.s1.core.n4567890.contacts.ExtensionSettings;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import lapr4.white.s1.core.n4567890.contacts.persistence.ContactRepository;
import lapr4.white.s1.core.n4567890.contacts.persistence.PersistenceContext;

/**
 * Represents the controller of notes edition
 * @author Pedro Fernandes
 */
public class NotesEditionController {
    
    private Properties appProps;
    private final ContactRepository contactRepository;
    private final PersistenceContext persistenceContext;
    private final ExtensionSettings extensionSettings;
    private Contact contact;
    private NotesList notesList;
    
    /**
     * Construtor of notes edition controller
     * @param props
     */
    public NotesEditionController(Properties props){
        this.appProps = props;
        this.extensionSettings = new ExtensionSettings(this.appProps);
        this.persistenceContext = new PersistenceContext(this.extensionSettings);
        this.contactRepository = this.persistenceContext.repositories().contacts();
    }
    
    /**
     * List of all contacts
     * @return list of all contacts
     */
    public List<Contact> getContacts(){
        Iterable<Contact> contacts = contactRepository.findAll();
        List<Contact> list = new LinkedList<>();
        contacts.forEach(list::add);
        return list;
    }
    
    /**
     * list of notes of a contact
     * @param c contact
     * @return list of notes of a contact
     */
    public List<Note> notesOfContact(Contact c){
        contact = c;
        notesList = c.notesList();
        return new LinkedList<>(notesList.getNotesList());
    }
    
    public boolean addNote(String title, String content) throws DataConcurrencyException, DataIntegrityViolationException{
        if(notesList.add(title, content)){
            Contact c = contactRepository.save(contact);
            if(!(c == null)){
                return true;
            }    
        }
        return false;
    }
    
    public boolean editNote(Note n, String content) throws DataConcurrencyException, DataIntegrityViolationException{
        if(notesList.edit(n.title(), content)){
            Contact c = contactRepository.save(contact);
            if(!(c == null)){
                return true;
            }    
        }
        return false;        
    }
    
    public boolean removeNote(Note n) throws DataConcurrencyException, DataIntegrityViolationException{
        if(notesList.remove(n.title())){
            Contact c = contactRepository.save(contact);
            if(!(c == null)){
                return true;
            }    
        }
        return false; 
    }
    
}
