package lapr4.blue.s1.lang.n1151031.formulastools;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 * A class that has the user selected styling options to be applied when using
 * the conditional formatting feature
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class UserStyle {

    /**
     * The default font
     */
    public static final Font DEFAULT_FONT = UIManager.getFont("Table.font");

    /**
     * The default foreground
     */
    public static final Color DEFAULT_FOREGROUND = UIManager.getColor("Table.foreground");

    /**
     * The default background color for the true style
     */
    public static final Color DEFAULT_TRUE_BACKGROUND = Color.GREEN;

    /**
     * The default background color for the false style
     */
    public static final Color DEFAULT_FALSE_BACKGROUND = Color.RED;

    /**
     * The default empty border
     */
    public static final Border DEFAULT_BORDER
            = BorderFactory.createEmptyBorder(1, 1, 1, 1);

    /**
     * The user selected font for the formatting style when condition evaluates
     * to true
     */
    private Font trueStyleFont;

    /**
     * The user selected foreground color for the formatting style when
     * condition evaluates to true
     */
    private Color trueStyleForegroundColor;

    /**
     * The user selected background color for the formatting style when
     * condition evaluates to true
     */
    private Color trueStyleBackgroundColor;

    /**
     * The user selected border for the formatting style when condition
     * evaluates to true
     */
    private Border trueStyleBorder;

    /**
     * The user selected font for the formatting style when condition evaluates
     * to false
     */
    private Font falseStyleFont;

    /**
     * The user selected foreground color for the formatting style when
     * condition evaluates to false
     */
    private Color falseStyleForegroundColor;

    /**
     * The user selected background color for the formatting style when
     * condition evaluates to false
     */
    private Color falseStyleBackgroundColor;

    /**
     * The user selected border for the formatting style when condition
     * evaluates to false
     */
    private Border falseStyleBorder;

    /**
     * Creates an instance of UserStyle with the default options.
     */
    public UserStyle() {
        this.trueStyleFont = DEFAULT_FONT;
        this.trueStyleForegroundColor = DEFAULT_FOREGROUND;
        this.trueStyleBackgroundColor = DEFAULT_TRUE_BACKGROUND;
        this.trueStyleBorder = DEFAULT_BORDER;

        this.falseStyleFont = DEFAULT_FONT;
        this.falseStyleForegroundColor = DEFAULT_FOREGROUND;
        this.falseStyleBackgroundColor = DEFAULT_FALSE_BACKGROUND;
        this.falseStyleBorder = DEFAULT_BORDER;
    }

    /**
     * @return the trueStyleFont
     */
    public Font getTrueStyleFont() {
        return trueStyleFont;
    }

    /**
     * @param trueStyleFont the trueStyleFont to set
     */
    public void setTrueStyleFont(Font trueStyleFont) {
        this.trueStyleFont = trueStyleFont;
    }

    /**
     * @return the trueStyleForegroundColor
     */
    public Color getTrueStyleForegroundColor() {
        return trueStyleForegroundColor;
    }

    /**
     * @param trueStyleForegroundColor the trueStyleForegroundColor to set
     */
    public void setTrueStyleForegroundColor(Color trueStyleForegroundColor) {
        this.trueStyleForegroundColor = trueStyleForegroundColor;
    }

    /**
     * @return the trueStyleBackgroundColor
     */
    public Color getTrueStyleBackgroundColor() {
        return trueStyleBackgroundColor;
    }

    /**
     * @param trueStyleBackgroundColor the trueStyleBackgroundColor to set
     */
    public void setTrueStyleBackgroundColor(Color trueStyleBackgroundColor) {
        this.trueStyleBackgroundColor = trueStyleBackgroundColor;
    }

    /**
     * @return the trueStyleBorder
     */
    public Border getTrueStyleBorder() {
        return trueStyleBorder;
    }

    /**
     * @param trueStyleBorder the trueStyleBorder to set
     */
    public void setTrueStyleBorder(Border trueStyleBorder) {
        this.trueStyleBorder = trueStyleBorder;
    }

    /**
     * @return the falseStyleFont
     */
    public Font getFalseStyleFont() {
        return falseStyleFont;
    }

    /**
     * @param falseStyleFont the falseStyleFont to set
     */
    public void setFalseStyleFont(Font falseStyleFont) {
        this.falseStyleFont = falseStyleFont;
    }

    /**
     * @return the falseStyleForegroundColor
     */
    public Color getFalseStyleForegroundColor() {
        return falseStyleForegroundColor;
    }

    /**
     * @param falseStyleForegroundColor the falseStyleForegroundColor to set
     */
    public void setFalseStyleForegroundColor(Color falseStyleForegroundColor) {
        this.falseStyleForegroundColor = falseStyleForegroundColor;
    }

    /**
     * @return the falseStyleBackgroundColor
     */
    public Color getFalseStyleBackgroundColor() {
        return falseStyleBackgroundColor;
    }

    /**
     * @param falseStyleBackgroundColor the falseStyleBackgroundColor to set
     */
    public void setFalseStyleBackgroundColor(Color falseStyleBackgroundColor) {
        this.falseStyleBackgroundColor = falseStyleBackgroundColor;
    }

    /**
     * @return the falseStyleBorder
     */
    public Border getFalseStyleBorder() {
        return falseStyleBorder;
    }

    /**
     * @param falseStyleBorder the falseStyleBorder to set
     */
    public void setFalseStyleBorder(Border falseStyleBorder) {
        this.falseStyleBorder = falseStyleBorder;
    }

}
