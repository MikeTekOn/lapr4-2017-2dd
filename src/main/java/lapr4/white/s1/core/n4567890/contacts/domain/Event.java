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

    //Validations made by João Cardoso - 1150943
    public Event(final String description, final Calendar dueDate) {
        if(dueDate.compareTo(Calendar.getInstance())>0 | !description.isEmpty()){
            this.description = description;
            this.dueDate = dueDate;
        }else {
            throw new IllegalStateException();
        }
    }


    public String description() {
        return this.description;
    }

    public Calendar dueDate(){return dueDate;}

    protected void update(String newDescription, Calendar newDueDate) {
        if(dueDate.compareTo(Calendar.getInstance())>0 | !description.isEmpty()) {
            this.description = newDescription;
            this.dueDate = newDueDate;
        }else {
            throw new IllegalStateException();
        }
    }

    /**
     * Created by João Cardoso - 1150943
     * @return
     */
    public boolean isToday() {
        int year, month, day;
        Calendar today = Calendar.getInstance();
        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH);
        day = today.get(Calendar.DAY_OF_MONTH);
        return (dueDate.get(Calendar.YEAR)==year && dueDate.get(Calendar.MONTH)==month && dueDate.get(Calendar.DAY_OF_MONTH)==day);
    }
}
