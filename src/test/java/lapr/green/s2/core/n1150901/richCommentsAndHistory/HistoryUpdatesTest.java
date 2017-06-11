/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.green.s2.core.n1150901.richCommentsAndHistory;

import csheets.core.Address;
import csheets.core.Workbook;
import java.util.List;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.red.s1.core.n1150690.comments.domain.User;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class HistoryUpdatesTest {
    
    private CommentableCellWithMultipleUsers comm;
    
    private Workbook w;
    
    private Address a;
    
    private User user;

    public HistoryUpdatesTest() {
    }
    
    @Before
    public void setUp() {
        w = new Workbook(1, null);
        a = new Address(0, 0);
        comm = new CommentableCellWithMultipleUsers(w.getSpreadsheet(0).getCell(a));
        user = new User();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void historyUpdatesWhenUserEditsCommentTest() {
        
        comm.addUsersComment("comment1", user);
        comm.changeUserComment(user.name(), user, "comment1", "comment1Changed");
        
        boolean result = false;
        
        List<String> h = comm.history().get(user).get("comment1Changed");
        for (String s : h){
            if (s.equals("comment1")){
                result = true;
            }
        }
        assertTrue(result);
    }
}
