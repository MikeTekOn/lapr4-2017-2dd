package lapr4.red.s1.core.n1150623.labelsForContacts.domain;

import java.lang.IllegalArgumentException;
import eapli.util.Strings;
import lapr4.white.s1.core.n4567890.contacts.domain.Agenda;
import lapr4.white.s1.core.n4567890.contacts.domain.Event;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Guilherme Ferreira 1150623 on 01/06/2017.
 *
 * A Label is a business concept as of Use case 10.7.1 - Labels for contacts.
 * This exists to store a contact's information.
 * A Label is to be exported [by a different class] to a PDF file.
 */
public class Label {

    String contact_Foto;
    String contact_Name;
    String contact_LastName;
    Set<String> contact_addresses;
    Set<String> contact_emails;
    Set<String> contact_phoneNumbers;
    Set<Event>  events;

    Agenda contact_agenda;

    public Label(){
        //For ORM
    }

    public void fillLabel(String name, String photo, Set<String> addresses, Set<String> emails, Set<String> phoneNumbers) throws IllegalArgumentException{

        if (Strings.isNullOrEmpty(name) || Strings.isNullOrWhiteSpace(name)) {
            throw new IllegalArgumentException("The name mas not be null or empty!");
        }
        if (Strings.isNullOrEmpty(photo) || Strings.isNullOrWhiteSpace(photo)) {
            throw new IllegalArgumentException("The photo must contain something!");
        }
        if (addresses == null) {
            throw new IllegalArgumentException("The addresses can't be null!");
        }
        if (emails == null) {
            throw new IllegalArgumentException("The emails can't be null!");
        }
        if (phoneNumbers == null) {
            throw new IllegalArgumentException("The phoneNumbers can't be null!");
        }


        //Se sets can be empty, but if they have at leat an element, it can't be empty or null (the element)
        for(String s : addresses){
            if (Strings.isNullOrEmpty(s) || Strings.isNullOrWhiteSpace(s)) {
                throw new IllegalArgumentException("The address must contain something!");
            }
        }
        for(String s : phoneNumbers){
            if (Strings.isNullOrEmpty(s) || Strings.isNullOrWhiteSpace(s) || phoneNumbers == null) {
                throw new IllegalArgumentException("The phone Number must contain something!");
            }
        }
        for(String s : emails){
            if (Strings.isNullOrEmpty(s) || Strings.isNullOrWhiteSpace(s) || emails == null) {
                throw new IllegalArgumentException("The email must contain something!");
            }
        }

        this.contact_Foto = photo;
        this.contact_Name = name;
        this.contact_addresses = addresses;
        this.contact_emails = emails;
        this.contact_phoneNumbers = phoneNumbers;
    }

    /**
     * Adds Events to label
     * @param events - list of events to add
     */
    public void addEvents(List<Event> events){

        if(events == null || events.contains(null)){
            throw new IllegalArgumentException("Invalid Event");
        }

        this.events = new HashSet();
        for(Event e : events){
            events.add(e);
        }
    }
}
