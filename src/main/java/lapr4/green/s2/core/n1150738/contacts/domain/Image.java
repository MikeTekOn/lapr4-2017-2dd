package lapr4.green.s2.core.n1150738.contacts.domain;

import eapli.framework.domain.ValueObject;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Value Object that represents a Image
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
@Embeddable
public class Image implements ValueObject {

    /**
     * The data of the Image
     */
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] image;

    /**
     * Constructor.
     * <p>
     * Constructs the value object given the image bytes
     *
     * @param image the image's image
     */
    public Image(byte[] image) {
        if (image == null) {
            throw new IllegalArgumentException("Image cannot be null");
        }
        this.image = image;
    }

    /**
     * Constructor for ORM
     */
    protected Image() {
        //ORM
    }

    @Override
    public String toString() {
        return "image";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        return Arrays.equals(this.image, image.image);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(image);
    }

    /**
     * Returns a BufferedImage constructed with the bytes of data this image is holding
     * to be used on the user interface.
     *
     * @return a BufferedImage object from the image's data.
     */
    public BufferedImage image() {
        BufferedImage bufferedImage = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(image);
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return bufferedImage;
    }

    /**
     * Factory Method to construct a default image for the contacts.
     *
     * @return the default user image.
     */
    public static Image defaultImage(){
        InputStream defaultImg = Image.class.getClassLoader().getResourceAsStream("lapr4/green/s2/core/n1150738/contacts/res/default_img.png");
        try {
            byte[] imageByte =  IOUtils.toByteArray(defaultImg);
            return new Image(imageByte);
        } catch(IOException e){
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, "Cannot open default image", e);
        }
        throw new IllegalArgumentException("Illegal default image location");
    }
}
