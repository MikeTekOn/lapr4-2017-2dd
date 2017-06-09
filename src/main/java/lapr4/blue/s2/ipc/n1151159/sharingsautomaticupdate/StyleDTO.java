package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate;

import eapli.framework.dto.DTO;

import javax.swing.border.Border;
import java.awt.*;
import java.io.Serializable;
import java.text.Format;

/**
 * Represents a data transfer object to store the style related fields.
 *
 * @author Ivo Ferro [1151159]
 */
public class StyleDTO implements DTO, Serializable {

    /**
     * The format applied to the cell's value before rendering
     */
    private Format format;

    /**
     * The font used when rendering the cell's content
     */
    private Font font;

    /**
     * The horizontal alignment of the cell's content
     */
    private int hAlignment;

    /**
     * The vertical alignment of the cell's content
     */
    private int vAlignment;

    /**
     * The color used when rendering the cell's content
     */
    private Color fgColor;

    /**
     * The background color of the cell
     */
    private Color bgColor;

    /**
     * The border of the cell
     */
    private Border border;

    /**
     * Creates a new style DTO
     *
     * @param format     the format applied to the cell's value before rendering
     * @param font       the font used when rendering the cell's content
     * @param hAlignment the horizontal alignment of the cell's content
     * @param vAlignment the vertical alignment of the cell's content
     * @param fgColor    the color used when rendering the cell's content
     * @param bgColor    the background color of the cell
     * @param border     the border of the cell
     */
    public StyleDTO(Format format, Font font, int hAlignment, int vAlignment, Color fgColor, Color bgColor, Border border) {
        this.format = format;
        this.font = font;
        this.hAlignment = hAlignment;
        this.vAlignment = vAlignment;
        this.fgColor = fgColor;
        this.bgColor = bgColor;
        this.border = border;
    }

    /**
     * Gets the format.
     *
     * @return format
     */
    public Format getFormat() {
        return format;
    }

    /**
     * Gets the font.
     *
     * @return font
     */
    public Font getFont() {
        return font;
    }


    /**
     * Gets the horizontal alignment.
     *
     * @return horizontal alignment
     */
    public int getHorizontalAlignment() {
        return hAlignment;
    }

    /**
     * Gets the vertical alignment.
     *
     * @return vertical alignment
     */
    public int getVerticalAlignment() {
        return vAlignment;
    }

    /**
     * Gets the foreground color.
     *
     * @return foreground color
     */
    public Color getForegroundColor() {
        return fgColor;
    }

    /**
     * Gets the background color.
     *
     * @return background color
     */
    public Color getBackgroundColor() {
        return bgColor;
    }

    /**
     * Gets the border.
     *
     * @return border
     */
    public Border getBorder() {
        return border;
    }
}
