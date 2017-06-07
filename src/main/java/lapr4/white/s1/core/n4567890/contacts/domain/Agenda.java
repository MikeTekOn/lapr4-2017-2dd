/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.white.s1.core.n4567890.contacts.domain;

import lapr4.red.s1.core.n1150943.contacts.alerts.EventReminder;

import java.io.Serializable;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author alexandrebraganca - edited by João Cardoso - 1150943
 */
@Entity
public class Agenda implements Serializable {

    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;

    // Deve conter uma lista de eventos
    @OneToMany(cascade = CascadeType.ALL)
    private final Set<Event> events = new HashSet<>();



    protected Agenda() {
        // for ORM
    }

    public Long id() {
        return this.pk;
    }

    public boolean add(Event ev) {
        if (ev == null) {
            throw new IllegalStateException();
        }
        return this.events.add(ev);
    }

    /**
     * Created by João Cardoso - 1150943
     * @param index
     * @param newDescription
     * @param newDueDate
     * @return
     */
    public boolean edit(int index, String newDescription, Calendar newDueDate){
        int i=0;
        Event ev=null;
        for(Event e : events){
            if(i==index){
                ev=e;
            }
            i++;
        }
        ev.update(newDescription,newDueDate);
        return true;
    }

    /**
     * Created by João Cardoso - 1150943
     * @param index
     * @return
     */
    public boolean remove(int index){
        int i=0;
        Event ev=null;
        for(Event e : events){
            if(i==index){
                ev=e;
            }
            i++;
        }
        if (ev == null) {
            throw new IllegalStateException();
        }
        return this.events.remove(ev);
    }

    /**
     * Created by João Cardoso - 1150943
     * @return
     */
    public List<Event> events(){
        List<Event>events=new ArrayList<>(this.events);
        return events;
    }


    /**
     * Created by João Cardoso - 1150943
     * returns true if the agenda has no events
     * @return
     */
    protected boolean isEmpty(){
        return events.isEmpty();
    }

    /**
     * Created by João Cardoso - 1150943
     * @return
     */
    public ArrayList<Event> pastEvents() {
        ArrayList<Event>events=new ArrayList<>();
        for (Event event : this.events){
            if(event.hasPassed())
                events.add(event);
        }
        return events;
    }

    /**
     * Created by João Cardoso - 1150943
     * @return
     */
    public ArrayList<Event> todayEvents(){
        ArrayList<Event>events=new ArrayList<>();
        for (Event event : this.events){
            if(event.isToday())
                events.add(event);
        }
        return events;
    }

    /**
     * Created by João Cardoso - 1150943
     * @return
     */
    public ArrayList<Event> futureEvents(){
        ArrayList<Event>events=new ArrayList<>();
        for (Event event : this.events){
            if(event.isFuture())
                events.add(event);
        }
        return events;
    }

    /**
     * Created by Guilherme Ferreira- 1150623
     * @param e - event to remove
     * @return true if romovale was successful
     */
    public boolean remove(Event e){
        return this.events.remove(e);
    }

}