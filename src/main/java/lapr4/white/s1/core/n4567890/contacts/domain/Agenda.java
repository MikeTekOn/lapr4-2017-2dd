/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.white.s1.core.n4567890.contacts.domain;

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
 * @author alexandrebraganca
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

    public boolean edit(int index, String newDescription, Calendar newDueDate){
        Event[]events = (Event[]) this.events.toArray();
        Event ev = events[index];
        if (ev == null) {
            throw new IllegalStateException();
        }
        ev.update(newDescription, newDueDate);
        return true;
    }

    public boolean remove(int index){
        Event[]events = (Event[]) this.events.toArray();
        Event ev = events[index];
        if (ev == null) {
            throw new IllegalStateException();
        }
        return this.events.remove(ev);
    }

    public List<Event> events(){
        ArrayList<Event>events=new ArrayList<>(this.events);
        return events;
    }


    /**
     * returns true if the agenda has no events
     * @return
     */
    protected boolean isEmpty(){
        return events.isEmpty();
    }

}