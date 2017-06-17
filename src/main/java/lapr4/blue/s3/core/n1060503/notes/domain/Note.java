/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s3.core.n1060503.notes.domain;

import eapli.util.Strings;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Represent a Note of a contact
 *
 * @author Pedro Fernandes
 */
@Entity
public final class Note implements Serializable {

    // ORM primary key
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long pk;

    private String title;

    /**
     * status of note (removed or enable to be added more note contents)
     */
    private boolean removed = false;

    /**
     * list of note contens (history of this note)
     */
    @OneToMany(cascade = CascadeType.ALL)
    private final List<NoteContent> noteContentList = new LinkedList<>();

    /**
     * Constructor by default (only use for ORM)
     */
    public Note() {
        // for ORM
    }

    /**
     * constructor of note
     *
     * @param title the title of note
     * @param content the content of note
     */
    public Note(String title, String content) {
        if (Strings.isNullOrEmpty(title)) {
            throw new IllegalStateException("The title is null or empty");
        }
        if (Strings.isNullOrEmpty(content)) {
            throw new IllegalStateException("The content is null or empty");
        }
        this.title = title;
        if (!add(content)) {
            throw new IllegalStateException();
        }
    }

    /**
     * Add a new note content relative this note. If the note is the state
     * removed, it is not possible add anything
     *
     * @param noteContent new note content
     * @return true if add, false if not
     */
    public boolean add(String noteContent) {
        if (!isRemoved()) {
            NoteContent nc = null;
            for (NoteContent notec : getNoteContentList()) {
                if (notec.getContentNote().equals(noteContent)) {
                    return false;
                }
            }
            int vers = getNoteContentList().size() + 1;
            nc = new NoteContent(noteContent, vers);
            return this.getNoteContentList().add(nc);
        }
        return true;
    }

    /**
     * change status to removed
     *
     * @return true if status changed to removed(true), false if not
     */
    public boolean remove() {
        return this.removed = true;
    }

    /**
     * Check if the note is in removed status
     *
     * @return true if status is removed, false if not
     */
    public boolean isRemoved() {
        return this.removed;
    }

    /**
     * gets the title of note
     *
     * @return the title of note
     */
    public String title() {
        return this.title;
    }

    /**
     * compare if other object is equal
     *
     * @param o other object
     * @return true if equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Note)) {
            return false;
        }

        final Note other = (Note) o;
        return this.title.equals(other.title) && (Objects.equals(this.pk, other.pk));
    }

    /**
     * hashcode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.title);
        return hash;
    }

    /**
     * @return the noteContentList
     */
    public List<NoteContent> getNoteContentList() {
        return this.noteContentList;
    }

    /**
     * toString
     *
     * @return toString
     */
    @Override
    public String toString() {
        if (this.isRemoved()) {
            return this.title() + " (Removed)";
        }
        return this.title();
    }
}
