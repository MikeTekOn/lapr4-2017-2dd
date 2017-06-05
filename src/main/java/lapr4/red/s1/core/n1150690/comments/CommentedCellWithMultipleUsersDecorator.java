/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150690.comments;

import csheets.core.Cell;
import csheets.ui.sheet.CellRenderer;
import java.util.List;
import java.util.Map;
import lapr4.red.s1.core.n1150690.comments.domain.User;
import lapr4.white.s1.core.n1234567.comments.CommentsExtension;
import lapr4.white.s1.core.n1234567.comments.ui.CommentedCellDecorator;

/**
 * A decorator for commented cells.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class CommentedCellWithMultipleUsersDecorator extends CommentedCellDecorator {

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean hasTooltip(Cell cell) {
        CommentableCellWithMultipleUsers c = (CommentableCellWithMultipleUsers) cell.getExtension(CommentsExtension.NAME);
        return c.hasComments();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void applyTooltip(CellRenderer c, Cell cell) {
        String text = "<html>";
        CommentableCellWithMultipleUsers cellWithComments = (CommentableCellWithMultipleUsers) cell.getExtension(CommentsExtension.NAME);
        for (Map.Entry<User, List<String>> entry : cellWithComments.comments().entrySet()) {
            for (String comment : entry.getValue()) {
                text += "<b>" + entry.getKey().name() + "</b>: " + comment + "<br>";
            }
        }
        text += "</html>";
        c.setToolTipText(text);
    }

}
