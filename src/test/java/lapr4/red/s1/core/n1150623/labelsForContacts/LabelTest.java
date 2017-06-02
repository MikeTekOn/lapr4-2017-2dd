package lapr4.red.s1.core.n1150623.labelsForContacts;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by guima on 02/06/2017.
 */
public class Label {


    public Label label;

    public Label() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    private String name = null;

    @Before
    public void setUp() {
        label = new Label();
    }

    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void fillLabel(){
        label.
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCommentAddedCantBeWithSpacesOnly(){
        commentable.addUsersComment("   ", new User());
    }
}

}
