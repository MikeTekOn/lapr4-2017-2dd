/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s3.core.n1060503.notes.domain;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 * Represent a NoteContent of a contact
 * @author Pedro Fernandes
 */
@Entity 
public class NoteContent implements ValueObject, Serializable{
    
    // ORM primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;
    
    /**
     * timestamp of note content
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar timestamp;
    
    /**
     * the content of the note
     */
    private String contentNote;    
    
    /**
     * Constructor by default (only use for ORM)
     */
    protected  NoteContent(){
        // for ORM
    }
    
    /**
     * constructor of note content
     * @param contentNote the content of note
     */
    public NoteContent(String contentNote){
        if(Strings.isNullOrEmpty(contentNote)){
            throw new IllegalStateException("The content of note is null or empty");  
        }
        this.timestamp = Calendar.getInstance();
        this.contentNote = contentNote;
    }
    
    /**
     * gets the timestamp
     * @return the timestamp
     */
    private Calendar timestamp(){
        return this.timestamp;
    }
    
    /**
     * the content of note
     * @return the content of note
     */
    private String contentNote(){
        return this.contentNote;
    }
    
    /**
     * compare if other object is equal
     * @param o other object
     * @return true if equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NoteContent)) {
            return false;
        }

        final NoteContent other = (NoteContent) o;
        return Objects.equals(this.contentNote, other.contentNote);
    }
    
    /**
     * hashcode
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.contentNote);
        return hash;
    }
    
    /**
     * toString
     * @return toString
     */
    @Override
    public String toString(){
        return "[" + this.timestamp() +"]" + "{" + this.contentNote() + "}";
    }

}
