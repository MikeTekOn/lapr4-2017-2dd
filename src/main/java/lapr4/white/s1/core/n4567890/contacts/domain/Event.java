/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.white.s1.core.n4567890.contacts.domain;

import eapli.util.DateTime;
import org.jfree.date.DateUtilities;

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
    
    private java.lang.String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dueDate;

    protected Event() {
        // for ORM
    }

    //Validations made by João Cardoso - 1150943
    public Event(final String description, final Calendar dueDate) {
        Calendar currDate = Calendar.getInstance();
        currDate.add(Calendar.HOUR,-1);
        if(!dueDate.getTime().before(currDate.getTime()) && !description.isEmpty()){
            this.description = description;
            this.dueDate = dueDate;
        }else {
            throw new IllegalStateException();
        }
    }

    public Long id(){ return pk; }

    public String description() {
        return this.description;
    }

    public Calendar dueDate(){return dueDate;}

    /**
     * Created by João Cardoso - 1150943
     * @param newDescription
     * @param newDueDate
     */
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

    /**
     * Created by João Cardoso - 1150943
     * @return
     */
    @Override
    public String toString() {
        return String.format("Description: %s %n Due Date: %s",description,DateTime.format(dueDate));
    }

    /**
     * Created by João Cardoso - 1150943
     * @return
     */
    public boolean hasPassed() {
        return dueDate.getTime().before(Calendar.getInstance().getTime());
    }

    /**
     * Created by João Cardoso - 1150943
     * @return
     */
    public boolean isFuture(){
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_MONTH,1);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        today.add(Calendar.MINUTE,-1);
        return dueDate.after(today);
    }

}
