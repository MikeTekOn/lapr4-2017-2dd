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

/**
 * Domain test of NoteContent
 * @author Pedro Fernandes
 */
public class NoteContentTest {
    
    String validContent = "**Hello Word!**";
    String invalidContent = "";
    
    public NoteContentTest() {
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

    @Test
    public void ensureNoteContentOk() {
        NoteContent nc = new NoteContent(validContent,0);
    }
    
    @Test(expected = IllegalStateException.class)
    public void ensureContentIsNotNull() {
        NoteContent nc = new NoteContent(null,0);
    }
    
    @Test(expected = IllegalStateException.class)
    public void ensureContentIsNotEmpty() {
        NoteContent nc = new NoteContent(invalidContent,0);
    }
    
}
