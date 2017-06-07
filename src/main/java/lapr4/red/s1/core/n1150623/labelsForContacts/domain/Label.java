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

    protected List<Event> contact_agenda;

    public Label(){
        contact_agenda = new ArrayList<>();
        //For ORM
    }

    public void fillLabel(final String name, final String photo, final String address, final String email, final String phoneNumber) throws IllegalArgumentException{

        if (Strings.isNullOrEmpty(name) || Strings.isNullOrWhiteSpace(name)) {
            throw new IllegalArgumentException("The name mas not be null or empty!");
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
        for(Event e : events){
            contact_agenda.add(e);
        }
    }

    public void deleteEventsOutsideBoundaries(Calendar endDate){
        List<Event> toRemove = new ArrayList<>();
        boolean remove = false;
        for(Event e : contact_agenda){

            int year = e.dueDate().get(Calendar.YEAR);
            int month = e.dueDate().get(Calendar.MONTH);
            int day = e.dueDate().get(Calendar.DAY_OF_MONTH);

            int my_year = endDate.get(Calendar.YEAR);
            int my_month = endDate.get(Calendar.MONTH);
            int my_day = endDate.get(Calendar.DAY_OF_MONTH);


            if(year <= my_year){
                if(year == my_year){
                    if(month <= my_month){
                        //keep
                        if(month == my_month){
                            if(day <= my_day){
                                //keep
                            }else{
                                remove = true;
                            }
                        }else{
                            //keep
                        }
                    }else{
                        remove = true;
                    }
                }else{
                    //keep
                }
            }else{
                remove = true;
            }

            if(remove){
                toRemove.add(e);
            }
            remove = false;
        }

        for(Event e : toRemove){
            contact_agenda.remove(e);
        }

    }

    public void removeEvents() {
        int size = contact_agenda.size();
        for(int i = 0; i < size; i++){
            contact_agenda.remove(0);
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
        return this.contact_agenda;
    }

    @Override
    public String toString(){
        return "Name -> " + name()
                + "\nAddress -> " + address()
                + "\nPhoto -> " + photo()
                + "\nPhoneNumber -> " + phoneNumber();

    }
}
