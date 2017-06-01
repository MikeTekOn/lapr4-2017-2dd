package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by João Cardoso - 1150943 on 31-05-2017.
 */
public class ImageUtils {

    /**
     * This method converts an image file into an ImageIcon with the desired size
     * @param imageFile
     * @param height
     * @param width
     * @return
     * @throws IOException
     */
    public static ImageIcon getResizedPhoto(File imageFile, int height, int width) throws IOException {
        java.awt.Image img = ImageIO.read(imageFile);
        java.awt.Image resizedImage = img.getScaledInstance(height,width, java.awt.Image.SCALE_DEFAULT);
        ImageIcon profilePhoto = new ImageIcon(resizedImage);
        return profilePhoto;
    }

}
