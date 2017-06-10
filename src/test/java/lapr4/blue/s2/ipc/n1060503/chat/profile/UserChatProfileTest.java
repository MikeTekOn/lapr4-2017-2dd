/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1060503.chat.profile;

import static com.itextpdf.text.pdf.PdfFileSpecification.url;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import org.bouncycastle.util.encoders.UrlBase64;
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
public class UserChatProfileTest {
    
    UserChatProfile u; 
    String nickname = "nick_teste";    
    //URL url = getClass().getClassLoader().getResource("lapr4/blue/s2/ipc/n1060503/chat/img/default_icon.png");
    String invalid_path = "default_icon.png";
    
    public UserChatProfileTest() throws IOException {
        this.u = new UserChatProfile();
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
    public void ensureUsernameIsEqualSystem(){       
        assertEquals(u.getUsername(), System.getProperty("user.name"));
    }
    
    @Test(expected = IllegalStateException.class)
    public void ensureNicknameIsNotNull() throws MalformedURLException{              
        u.changeInfo(null, invalid_path, StatusChatProfile.ONLINE);
    }
    
    @Test(expected = IllegalStateException.class)
    public void ensureNicknameIsNotEmpty() throws MalformedURLException{              
        u.changeInfo("", invalid_path, StatusChatProfile.ONLINE);
    }
    
    @Test
    public void ensureUserChatHasOnlineStatusByDefault(){  
        assertEquals(u.getStatus(), StatusChatProfile.ONLINE);
    }
    
    @Test
    public void ensureUserChatCanChangeStatus(){              
        u.setStatus(StatusChatProfile.OFFLINE);
    }
    
    @Test(expected = IllegalStateException.class)
    public void ensureUserChatStatusIsValid() throws MalformedURLException{              
        u.changeInfo(u.getNickname(), invalid_path, null);
    }
    
    @Test(expected = IllegalStateException.class)
    public void ensureUserChatIconPathIsValid() throws MalformedURLException{ 
        u.changeInfo(u.getNickname(), invalid_path, null);
    }
    
    @Test
    public void ensureIsOK() throws MalformedURLException{       
        u.changeInfo(nickname, "", StatusChatProfile.OFFLINE);
    }
    
}
