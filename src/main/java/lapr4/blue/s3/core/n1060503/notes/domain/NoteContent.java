/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s3.core.n1060503.notes.domain;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 * Represent a NoteContent of a contact
 *
 * @author Pedro Fernandes
 */
@Entity
public class NoteContent implements ValueObject, Serializable {

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
     * the content of the note
     */
    private int versionNote;

    /**
     * Constructor by default (only use for ORM)
     */
    protected NoteContent() {
        // for ORM
    }

    /**
     * constructor of note content
     *
     * @param contentNote the content of note
     * @param versionNote the version of note
     */
    public NoteContent(String contentNote, int versionNote) {
        if (Strings.isNullOrEmpty(contentNote)) {
            throw new IllegalStateException("The content of note is null or empty");
        }
        this.timestamp = Calendar.getInstance();
        this.contentNote = contentNote;
        this.versionNote = versionNote;
    }

    /**
     * gets the timestamp
     *
     * @return the timestamp
     */
    private Calendar timestamp() {
        return this.timestamp;
    }

    /**
     * the content of note
     *
     * @return the content of note
     */
    private String contentNote() {
        return this.getContentNote();
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
        if (!(o instanceof NoteContent)) {
            return false;
        }

        final NoteContent other = (NoteContent) o;
        boolean b = this.getVersionNote() == other.getVersionNote();
        return b && Objects.equals(this.getContentNote(), other.getContentNote());
    }

    /**
     * hashcode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.getContentNote());
        return hash;
    }

    /**
     * toString
     *
     * @return toString
     */
    @Override
    public String toString() {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(timestamp.getTime());
        return "V" + this.versionNote + "; " + s;
    }

    /**
     * @return the contentNote
     */
    public String getContentNote() {
        return contentNote;
    }

    /**
     * @return the versionNote
     */
    public int getVersionNote() {
        return versionNote;
    }

}
