/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150690.comments;

import lapr4.red.s1.core.n1150690.comments.domain.CommentableCellWithMultipleUsers;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import lapr4.red.s1.core.n1150690.comments.domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class CommentableCellWithMultipleUsersTest {
    
    public CommentableCellWithMultipleUsers commentable;
    
    public CommentableCellWithMultipleUsersTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Workbook book = new Workbook();
        book.addSpreadsheet();
        Spreadsheet s = book.getSpreadsheet(0);
        Cell c = s.getCell(1,1);
        commentable = new CommentableCellWithMultipleUsers(c);
    }
    
    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCommentAddedCantBeEmpty(){
        commentable.addUsersComment("", new User());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureCommentAddedCantBeWithSpacesOnly(){
        commentable.addUsersComment("   ", new User());
    }
}
