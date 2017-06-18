package lapr4.blue.s3.core.n1151452.pdfexport;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Represents a utility class to help with conversion JAVA classes to ITEXTPDF classes
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 16/06/17.
 */
public class PdfUtil {

    /**
     * Helper method to convert {@link Color} to a {@link BaseColor}
     *
     * @param color the {@link Color}
     * @return the {@link BaseColor}
     */
    public static BaseColor convertToBaseColor(Color color) {

        return new BaseColor(color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Converts swing alignment constants to itext alignment constants
     *
     * @param swingAlignment swing alignment constant to convert
     * @return converted itext alignment constant
     */
    public static int convertSwingAlignment(int swingAlignment) {

        switch (swingAlignment) {
            case SwingConstants.RIGHT:
                return Element.ALIGN_RIGHT;
            case SwingConstants.CENTER:
                return Element.ALIGN_CENTER;
            case SwingConstants.TOP:
                return Element.ALIGN_TOP;
            case SwingConstants.BOTTOM:
                return Element.ALIGN_BOTTOM;
            default:
                return Element.ALIGN_LEFT;
        }
    }

    /**
     * Converts a swing border to int value to obtain itext PDF border
     *
     * @param border the swing border
     * @return the int value to obtain the itext PDF border
     */
    public static int convertSwingBorder(Border border) {

        int pdfBorder = 0;

        if (border instanceof MatteBorder) {

            MatteBorder matteBorder = (MatteBorder) border;
            Insets insets = matteBorder.getBorderInsets();

            if (insets.top > 0) {
                pdfBorder |= com.itextpdf.text.Rectangle.TOP;
            }
            if (insets.bottom > 0) {
                pdfBorder |= com.itextpdf.text.Rectangle.BOTTOM;
            }
            if (insets.left > 0) {
                pdfBorder |= com.itextpdf.text.Rectangle.LEFT;
            }
            if (insets.right > 0) {
                pdfBorder |= com.itextpdf.text.Rectangle.RIGHT;
            }
        }

        return pdfBorder;
    }
}
