package lapr4.white.s1.core.n1234567.comments.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import csheets.core.Cell;
import csheets.ui.ext.CellDecorator;
import csheets.ui.sheet.CellRenderer;
import java.util.List;
import java.util.Map;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.red.s1.core.n1150690.comments.domain.User;

import lapr4.white.s1.core.n1234567.comments.CommentableCell;
import lapr4.white.s1.core.n1234567.comments.CommentsExtension;

/**
 * A decorator for commented cells.
 *
 * @author Alexandre Braganca
 * @author Einar Pehrson
 */
public class CommentedCellDecorator extends CellDecorator {

    /**
     * The font used to render the '+'
     */
    private static final Font font = new Font("Dialog", Font.PLAIN, 10);

    /**
     * Creates a new cell decorator.
     */
    public CommentedCellDecorator() {
    }

    /**
     * Decorates the given graphics context if the cell being rendered has a
     * comment.
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
            CommentableCell commentableCell = (CommentableCell) cell.getExtension(CommentsExtension.NAME);
            if (commentableCell.hasComment()) {
                // Stores current graphics context properties
                Graphics2D g2 = (Graphics2D) g;
                Color oldPaint = g2.getColor();
                Font oldFont = g2.getFont();

                // Prints 'A' using own font, then restores the old font
                g2.setColor(Color.red);
                g2.setFont(font);
                g2.drawString("+", 4, 12);

                // Restores graphics context properties
                g2.setColor(oldPaint);
                g2.setFont(oldFont);
            }
        }
    }

    @Override
    public boolean hasTooltip(Cell cell) {
        CommentableCellWithMultipleUsers c = (CommentableCellWithMultipleUsers) cell.getExtension(CommentsExtension.NAME);
        return c.hasComments();
    }

    @Override
    public void applyTooltip(CellRenderer c, Cell cell) {
        String text = "<html>";
        CommentableCellWithMultipleUsers cellWithComments = (CommentableCellWithMultipleUsers) cell.getExtension(CommentsExtension.NAME);
        for(Map.Entry<User, List<String>> entry : cellWithComments.comments().entrySet()){
            for(String comment: entry.getValue()){
                text += "<b>"+entry.getKey().name()+"</b>: "+comment+"<br>";
            }
        }
        text+="</html>";
        c.setToolTipText(text);
    }
}
