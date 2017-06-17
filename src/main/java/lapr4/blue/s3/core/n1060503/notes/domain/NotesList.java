/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s3.core.n1060503.notes.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Reprents a list of Notes
 * @author Pedro Fernandes
 */
@Entity
public class NotesList implements Serializable{
    
    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;
    
    /**
     * list of Notes
     */
    @OneToMany(cascade = CascadeType.ALL)
    private final List<Note> notesList ;
    
    /**
     * Constructor by default
     */
    public NotesList() {
        notesList = new LinkedList<>();
    }
    
    /**
     * database id
     * @return database id
     */
    public Long id() {
        return this.pk;
    }
    
    /**
     * create a new note if don't exist into list
     * @param title the title of note
     * @param content the content of note
     * @return true if added, false if not
     */
    public boolean add(String title, String content) {
        Note note = null;
        for(Note n : notesList){
            if(n.title().equals(title)){
                if(n.isRemoved()){
                    return false;
                }else{
                    note = n;
                note.add(content);
                return true;                 
                }                              
            }
        }
        if(note == null){
            note = new Note(title, content);
        }
        
        return this.notesList.add(note);
    }
    
    /**
     * edit a new note content
     * @param title the note title
     * @param content the new content of note
     * @return true if edit the note, false if not
     */
    public boolean edit(String title, String content) {
        for (Note n : notesList){
            if(n.title().equals(title)){
                if(n.isRemoved()){
                    return false;
                }else{
                    return n.add(content);                
                }                
            }
        }
        return false;
    }
    
    /**
     * change status of note if exist into list
     * @param title the note to be changed the status
     * @return true if change status, false if not
     */
    public boolean remove(String title) {
        for (Note n : notesList){
            if(n.title().equals(title)){
                if(n.isRemoved()){
                    return false;
                }else{
                    return n.remove();                
                }
            }
        }
        return false;
    }
    
    /**
     * gets this notes list
     * @return this notes list
     */
    public List<Note> getNotesList(){
        return this.notesList;
    }
}
