/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150690.comments.application;

import csheets.ui.ctrl.UIController;
import java.util.List;
import java.util.Map;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.red.s1.core.n1150690.comments.domain.User;

/**
 * A controller for add and updating the comments of a cell with the
 * identification of the author.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class AddCommentsWithUserController {

    /**
     * The user interface controller
     */
    private UIController uiController;
    
    private CommentableCellWithMultipleUsers cell;

    /**
     * Creates a new comments controller.
     *
     * @param uiController the user interface controller
     */
    public AddCommentsWithUserController(UIController uiController) {
        this.uiController = uiController;
    }
    
    public void changeActiveCell(CommentableCellWithMultipleUsers cell){
        this.cell = cell;
    }

    /**
     * Adds a comment to the selected cell.
     *
     * @param cell the cell whose comments changed
     * @param comment the comment to add
     * @return true if the comment was properly added
     */
    public User addComment(String comment) {
        User currentUser = new User();
        cell.addUsersComment(comment, currentUser);
        uiController.setWorkbookModified(cell.getSpreadsheet().getWorkbook());
        return currentUser;
    }

    /**
     * Changes a comment of an author.
     *
     * @param cell the cell whose comments changed
     * @param oldComment the old comment of the cell
     * @param newComment the new comment of the cell
     * @param oldAuthor the previous author of the comment
     * @return true if the comment was properly changed
     */
    public User changeComment(String oldComment, String newComment, String oldAuthor) {
        User newAuthor = new User();
        cell.changeUserComment(oldAuthor, newAuthor, oldComment, newComment);
        uiController.setWorkbookModified(cell.getSpreadsheet().getWorkbook());
        return newAuthor;
    }

    /**
     * Returns the comments of a cell.
     *
     * @param cell the cell who has the comments.
     * @return the comments of the cell passed as a parameter
     */
    public Map<User, List<String>> comments() {
        return cell.comments();
    }

}
