/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s3.core.n1060503.notes.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro Fernandes
 */
public class NotesListTest {
    
    String validTitle = "My Title";
    String validContent = "**Hello Word!**";
    Note note = new Note(validTitle, validContent);
    String invalidString = "";
    
    public NotesListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class NotesList.
     */
    @Test
    public void testAddNoteIsOk() {
        NotesList notesList = new NotesList();
        boolean b1 = notesList.add(validTitle, validContent);
        assertEquals(b1, true);
    }
    
    /**
     * Test of add method, of class NotesList.
     */
    @Test(expected = IllegalStateException.class)
    public void testAddNoteIsNotOk() {
        NotesList notesList = new NotesList();
        notesList.add(invalidString, validContent);
    }

    /**
     * Test of edit method, of class NotesList.
     */
    @Test
    public void testEditNoteIsOk() {
        NotesList notesList = new NotesList();
        notesList.add(validTitle, validContent);
        notesList.edit(validTitle, "new content");
    }
    
    /**
     * Test of edit method, of class NotesList.
     */
    @Test
    public void testEditNoteIsNotOk() {
        NotesList notesList = new NotesList();
        notesList.add(validTitle, validContent);
        // ensure is not the same note
        boolean b = notesList.edit(validTitle, validContent);
        assertEquals(b, false);
    }

    /**
     * Test of remove method, of class NotesList.
     */
    @Test
    public void testRemoveNoteIsOk() {
        NotesList notesList = new NotesList();
        notesList.add(validTitle, validContent);
        
        boolean b1 = notesList.remove(validTitle);
        assertEquals(b1, true);
        
        // ensure is not the same note
        boolean b2 = notesList.remove(invalidString);
        assertEquals(b2, false);
    }
    
}
