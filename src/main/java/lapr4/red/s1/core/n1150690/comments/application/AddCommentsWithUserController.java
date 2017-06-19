/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150690.comments.application;

import csheets.ui.ctrl.UIController;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.red.s1.core.n1150690.comments.domain.User;

import java.util.List;
import java.util.Map;

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

    /**
     * The cell that will contain the comments
     */
    protected CommentableCellWithMultipleUsers cell;

    /**
     * Creates a new comments controller.
     *
     * @param uiController the user interface controller
     */
    public AddCommentsWithUserController(UIController uiController) {
        this.uiController = uiController;
    }

    /**
     * Changes to the cell that the user is adding comments
     *
     * @param cell the active cell
     */
    public void changeActiveCell(CommentableCellWithMultipleUsers cell) {
        this.cell = cell;
    }

    /**
     * Adds a comment to the selected cell.
     * @param comment the comment to add
     * @return true if the comment was properly added
     */
    public User addComment(String comment) {
        User currentUser = new User();
        cell.addUsersComment(comment, currentUser);
        uiController.setWorkbookModified(cell.getSpreadsheet().getWorkbook());
        return currentUser;
    }
    
    public void addComment(String comment, String user) {
        User currentUser = new User(user);
        cell.addUsersComment(comment, currentUser);
        //uiController.setWorkbookModified(cell.getSpreadsheet().getWorkbook());
    }

    /**
     * Changes a comment of an author.
     *
     * @param oldComment the old comment of the cell
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
     * @return the comments of the cell passed as a parameter
     */
    public Map<User, List<String>> comments() {
        return cell.comments();
    }

}
