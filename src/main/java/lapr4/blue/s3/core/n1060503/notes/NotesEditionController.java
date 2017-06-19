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
import java.util.Vector;
import lapr4.blue.s3.core.n1060503.notes.domain.Note;
import lapr4.blue.s3.core.n1060503.notes.domain.NoteContent;
import lapr4.blue.s3.core.n1060503.notes.domain.NotesList;
import lapr4.white.s1.core.n4567890.contacts.ExtensionSettings;
import lapr4.white.s1.core.n4567890.contacts.domain.Contact;
import lapr4.white.s1.core.n4567890.contacts.persistence.ContactRepository;
import lapr4.white.s1.core.n4567890.contacts.persistence.PersistenceContext;

/**
 * Represents the controller of notes edition
 *
 * @author Pedro Fernandes
 */
public class NotesEditionController {

    private final Properties appProps;
    private final ContactRepository contactRepository;
    private final PersistenceContext persistenceContext;
    private final ExtensionSettings extensionSettings;

    /**
     * Construtor of notes edition controller
     *
     * @param props
     */
    public NotesEditionController(Properties props) {
        this.appProps = props;
        this.extensionSettings = new ExtensionSettings(this.appProps);
        this.persistenceContext = new PersistenceContext(this.extensionSettings);
        this.contactRepository = this.persistenceContext.repositories().contacts();
    }

    /**
     * List of all contacts
     *
     * @return list of all contacts
     */
    public List<String> getContacts() {
        Iterable<Contact> contacts = contactRepository.findAll();
        List<String> list = new LinkedList<>();
        for (Contact c : contacts) {
            String aux = c.name();
            list.add(aux);
        }
        return list;
    }

    /**
     * list of notes of a contact
     *
     * @param contactName name of the contact
     * @return list of notes of a contact
     */
    public List<Note> notesOfContact(String contactName) {
        Contact contact = findContactByName(contactName);
        return contact.notesList().getNotesList();
    }

    /**
     * the list of notes content of a note
     *
     * @param n note
     * @return list of notes content of a note
     */
    public List<NoteContent> notesContentOfNote(Note n) {
        if (n != null) {
            return n.getNoteContentList();
        }
        return new Vector<>();
    }

    /**
     * adds a note of a contact
     *
     * @param contactName contact name
     * @param title title of note
     * @param content note content
     * @return true if is saved, flase if not
     * @throws DataConcurrencyException DataConcurrencyException
     * @throws DataIntegrityViolationException DataIntegrityViolationException
     */
    public boolean addNote(String contactName, String title, String content) throws DataConcurrencyException, DataIntegrityViolationException {
        title = title.replaceAll("(\\r|\\n|\\t)", "");
        Contact contact = findContactByName(contactName);
        if (contact.notesList().add(title, content)) {
            Contact c = contactRepository.save(contact);
            if (!(c == null)) {
                return true;
            }
        }
        return false;
    }

    /**
     * edit a note of a contact
     *
     * @param contactName contact name
     * @param n note
     * @param content note content
     * @return true if is saved, flase if not
     * @throws DataConcurrencyException DataConcurrencyException
     * @throws DataIntegrityViolationException DataIntegrityViolationException
     */
    public boolean editNote(String contactName, Note n, String content) throws DataConcurrencyException, DataIntegrityViolationException {
        Contact contact = findContactByName(contactName);
        if (contact.notesList().edit(n.title(), content)) {
            Contact c = contactRepository.save(contact);
            if (!(c == null)) {
                return true;
            }
        }
        return false;
    }

    /**
     * change status of a note to removed
     *
     * @param contactName contact name
     * @param n note
     * @return true if is saved, flase if not
     * @throws DataConcurrencyException DataConcurrencyException
     * @throws DataIntegrityViolationException DataIntegrityViolationException
     */
    public boolean removeNote(String contactName, Note n) throws DataConcurrencyException, DataIntegrityViolationException {
        Contact contact = findContactByName(contactName);
        if (contact.notesList().remove(n.title())) {
            Contact c = contactRepository.save(contact);
            if (!(c == null)) {
                return true;
            }
        }
        return false;
    }

    /**
     * private method to find a contact by name
     *
     * @param contactName contact name
     * @return contact
     */
    private Contact findContactByName(String contactName) {
        Iterable<Contact> contacts = contactRepository.findAll();
        Contact contact = null;
        for (Contact c : contacts) {
            if (c.name().equals(contactName)) {
                contact = c;
                break;
            }
        }
        return contact;
    }
}
