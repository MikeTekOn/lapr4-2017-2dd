/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.white.s1.core.n4567890.contacts.domain;

import javax.persistence.*;
import java.util.Calendar;

/**
 *
 * @author alexandrebraganca
 */
@Entity
public class Event {
   
    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;    
    
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dueDate;

    protected Event() {
        // for ORM
    }

    public Event(final String description, final Calendar dueDate) {
        if(dueDate.compareTo(Calendar.getInstance())>0){
            this.description = description;
            this.dueDate = dueDate;
        }else {
            throw new IllegalStateException();
        }
    }


    public String description() {
        return this.description;
    }
}
