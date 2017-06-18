/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s3.core.n1060503.notes.domain;

import java.util.Set;
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
public class NoteTest {
    
    String validTitle = "My Title";
    String invalidString = "";
    String validContent = "**Hello Word!**";
    NoteContent nc = new NoteContent(validContent,1);
    
    public NoteTest() {
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
     * Test of add method, of class Note.
     */
    @Test
    public void ensureAddNoteContentIsOk() {
        Note n = new Note(validTitle, validContent);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureTitleIsNotNull() {
        Note n = new Note(null, validContent);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureTitleIsNotEmpty() {
       Note n = new Note(invalidString, validContent);
    }
    
    @Test(expected = IllegalStateException.class)
    public void ensureContentIsNotNull() {
        Note n = new Note(validTitle, null);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureContentIsNotEmpty() {
       Note n = new Note(validContent, invalidString);
    }
    
    @Test
    public void ensureRemoveNoteContentIsValid() {
        Note n = new Note(validContent, validContent);
        boolean b = n.remove();
        assertEquals(b,true);
    }
    
    
  
}
