package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by k4rd050 on 31-05-2017.
 */
public class ImageUtils {

    public static ImageIcon getResizedPhoto(File imageFile, int height, int width) throws IOException {
        java.awt.Image img = ImageIO.read(imageFile);
        java.awt.Image resizedImage = img.getScaledInstance(height,width, java.awt.Image.SCALE_DEFAULT);
        ImageIcon profilePhoto = new ImageIcon(resizedImage);
        return profilePhoto;
    }

}
