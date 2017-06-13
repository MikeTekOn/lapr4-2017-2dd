/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1060503.chat.profile;

import eapli.framework.domain.AggregateRoot;
import eapli.util.Strings;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Version;
import javax.swing.ImageIcon;

/**
 * represent local the user chat
 *
 * @author Pedro Fernandes
 */
@Entity
public class UserChatProfile implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    /**
     * name of the system
     */
    private final String username = System.getProperty("user.name");
    /**
     * nickname of user chat
     */
    private String nickname;

    /**
     * byte of the icon
     */
    @Lob
    @Basic(fetch = FetchType.EAGER)
    private byte[] iconBytes;

    private static final int ICON_WIDTH = 20;
    private static final int ICON_HEIGHT = 20;

    /**
     * the status (online or offline) of user chat
     */
    private StatusChatProfile status;

    /**
     * constructor by default
     *
     * @throws java.net.MalformedURLException
     * @throws java.io.IOException
     */
    public UserChatProfile() throws MalformedURLException, IOException {
        nickname = username;
        changeIcon("");
        status = StatusChatProfile.ONLINE;
    }

    /**
     * change all info of user chat profile
     *
     * @param nickname
     * @param status
     * @return changed user chat profile
     * @throws java.net.MalformedURLException
     */
    public UserChatProfile changeInfo(String nickname,
            String status) throws MalformedURLException, IOException {
        if (status == null) {
            throw new IllegalStateException("Invalid status");
        }
        if (Strings.isNullOrEmpty(nickname)) {
            throw new IllegalStateException("Invalid nickname");
        }
        if (!this.setStatus(status)) {
            throw new IllegalStateException("Invalid status");
        }

        this.setNickname(nickname);

        return this;
    }

    /**
     * change the icon or imagem of user chat
     *
     * @param image_path icon or image path
     * @throws java.net.MalformedURLException
     */
    public void changeIcon(String image_path) throws MalformedURLException, IOException {
        // Image must have less then 400Bytes, to be sent by UDP
        URL url = UserChatProfile.class.getResource("res/img/chat1.png");
        if (!image_path.isEmpty()) {
            url = new URL(image_path);
        }
        ImageIcon i = new ImageIcon(url);
        setIcon(i);

    }

    /**
     * set the icon
     *
     * @param icon
     * @throws IOException
     */
    public void setIcon(ImageIcon icon) throws IOException {
        iconBytes = extractBytes(resizeIcon(icon));
    }

    /**
     * resize the icon
     *
     * @param icon
     * @return icon resized
     */
    private ImageIcon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(ICON_WIDTH, ICON_HEIGHT, java.awt.Image.SCALE_SMOOTH);

        return new ImageIcon(newimg);
    }

    /**
     * convert icon to byte[]
     *
     * @param icon
     * @return byte[] of an icon
     * @throws IOException
     */
    private static byte[] extractBytes(ImageIcon icon) throws IOException {
        byte[] imageInByte;
        ImageIcon i = icon;
        
        BufferedImage bi = new BufferedImage(ICON_WIDTH,
                ICON_HEIGHT, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY));
        boolean b = g2d.drawImage(i.getImage(), 0, 0, ICON_WIDTH, ICON_HEIGHT, null);
        System.out.println(b);

        try ( ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(bi, "png", baos );
            baos.flush();
            imageInByte = baos.toByteArray();
        }
        
        
        return imageInByte;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof UserChatProfile)) {
            return false;
        }

        final UserChatProfile that = (UserChatProfile) other;
        if (this == that) {
            return true;
        }

        return id().equals(that.id()) && username.equals(that.username);
    }

    @Override
    public boolean is(Long id) {
        return id().equals(id);
    }

    @Override
    public Long id() {
        return this.pk;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @return the status
     */
    public StatusChatProfile getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     * @return true if status is valid, false if not
     */
    public boolean setStatus(String status) {

        if (status.equals(StatusChatProfile.ONLINE.toString())) {
            this.status = StatusChatProfile.ONLINE;
            return true;
        }

        if (status.equals(StatusChatProfile.OFFLINE.toString())) {
            this.status = StatusChatProfile.OFFLINE;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.nickname + this.status;
    }

    /**
     *
     * @return image icon of user chat
     */
    public ImageIcon getImage() {
        return new ImageIcon(iconBytes);
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
