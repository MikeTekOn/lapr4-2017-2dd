/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150690.comments.presentation;

import java.util.EventListener;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;

/**
 * A listener interface for receiving notification on events occurring in an
 * commentable cell.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public interface CommentableCellWithMultipleUsersListener extends EventListener {

    /**
     * Invoked when a comment is added, changed or removed from a cell.
     *
     * @param cell the cell that was modified
     */
    public void commentChanged(CommentableCellWithMultipleUsers cell);
}
