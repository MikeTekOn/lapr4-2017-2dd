package lapr4.red.s1.core.n1150623.labelsForContacts.domain;

import java.lang.IllegalArgumentException;

import eapli.util.DateTime;
import eapli.util.Strings;
import lapr4.white.s1.core.n4567890.contacts.domain.Agenda;
import lapr4.white.s1.core.n4567890.contacts.domain.Event;

import java.util.*;

/**
 * Created by Guilherme Ferreira 1150623 on 01/06/2017.
 *
 * A Label is a business concept as of Use case 10.7.1 - Labels for contacts.
 * This exists to store a contact's information.
 * A Label is to be exported [by a different class] to a PDF file.
 */
public class Label {

    protected String contact_Foto;
    protected String contact_Name;
    protected String contact_address;
    protected String contact_email;
    protected String contact_phoneNumber;
    protected List<Event>  events;

    protected Agenda contact_agenda;

    public Label(){
        //For ORM
    }

    public void fillLabel(final String name, final String photo, final String address, final String email, final String phoneNumber) throws IllegalArgumentException{

        if (Strings.isNullOrEmpty(name) || Strings.isNullOrWhiteSpace(name)) {
            throw new IllegalArgumentException("The name mas not be null or empty!");
        }
        if (Strings.isNullOrEmpty(photo) || Strings.isNullOrWhiteSpace(photo)) {
            throw new IllegalArgumentException("The photo must contain something!");
        }
        if (address == null) {
            throw new IllegalArgumentException("The addresses can't be null!");
        }
        if (Strings.isNullOrEmpty(email) || Strings.isNullOrWhiteSpace(email)) {
            throw new IllegalArgumentException("The email mas not be null or empty!");
        }
        if (Strings.isNullOrEmpty(phoneNumber) || Strings.isNullOrWhiteSpace(phoneNumber)) {
            throw new IllegalArgumentException("The phone Number mas not be null or empty!");
        }
        if (Strings.isNullOrEmpty(address) || Strings.isNullOrWhiteSpace(address)) {
            throw new IllegalArgumentException("The address mas not be null or empty!");
        }

        this.contact_Foto = photo;
        this.contact_Name = name;
        this.contact_address = address;
        this.contact_email = email;
        this.contact_phoneNumber = phoneNumber;
    }

    /**
     * Adds Events to label
     * @param events - list of events to add
     */
    public void addEvents(List<Event> events){

        if(events == null || events.contains(null)){
            throw new IllegalArgumentException("Invalid Event");
        }

        this.events = new ArrayList<>();
        for(Event e : events){
            this.events.add(e);
        }
    }

    public void deleteEventsOutsideBoundaries(Calendar endDate){
        List<Event> toRemove = new ArrayList<>();
        for(Event e : events){
            if(e.dueDate().compareTo(endDate) == 1){ // se data evento > endDate
                toRemove.add(e);
            }
        }

        for(Event e : toRemove){
            events.remove(e);
        }
    }

    public void removeEvents() {
        int size = contact_agenda.events().size();
        for(int i = 0; i< size; i++){
            contact_agenda.events().remove(0);
        }
    }

    public String phoneNumber(){
        return this.contact_phoneNumber;
    }
    public String address(){
        return this.contact_address;
    }
    public String name(){
        return this.contact_Name;
    }
    public String email(){
        return this.contact_email;
    }
    public String photo(){
        return this.contact_Foto;
    }
    public List<Event> events(){
        return this.contact_agenda.events();
    }
}
