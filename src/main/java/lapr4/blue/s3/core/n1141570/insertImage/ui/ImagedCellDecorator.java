package lapr4.blue.s3.core.n1141570.insertImage.ui;

import csheets.core.Cell;
import csheets.ui.ext.CellDecorator;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import lapr4.blue.s3.core.n1141570.insertImage.ImageableCell;
import lapr4.blue.s3.core.n1141570.insertImage.ImagesExtension;

/**
 *
 * @author Eric
 */
public class ImagedCellDecorator extends CellDecorator {

    /**
     * The font used to render the '+'
     */
    private static final Font font = new Font("Dialog", Font.PLAIN, 16);

    /**
     * Creates a new cell decorator.
     */
    public ImagedCellDecorator() {
    }

    /**
     * Decorates the given graphics context if the cell being rendered has an
     * image.
     *
     * @param component the cell renderer component
     * @param g the graphics context on which drawing should be done
     * @param cell the cell being rendered
     * @param selected whether the cell is selected
     * @param hasFocus whether the cell has focus
     */
    public void decorate(JComponent component, Graphics g, Cell cell,
            boolean selected, boolean hasFocus) {
        if (enabled) {
            ImageableCell imageableCell = (ImageableCell) cell.getExtension(ImagesExtension.NAME);
            if (imageableCell.hasAnyImage()) {
                // Stores current graphics context properties
                Graphics2D g2 = (Graphics2D) g;
                Color oldPaint = g2.getColor();
                Font oldFont = g2.getFont();

                // Prints 'A' using own font, then restores the old font
                g2.setColor(Color.BLUE);
                g2.setFont(font);
                g2.drawString("*", 4, 18);

                // Restores graphics context properties
                g2.setColor(oldPaint);
                g2.setFont(oldFont);
            }
        }
    }
}
