/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150690.comments;

import csheets.core.Cell;
import eapli.util.Strings;
import lapr4.white.s1.core.n1234567.comments.CommentableCell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lapr4.red.s1.core.n1150690.comments.domain.User;

/**
 * An extension of a cell in a spreadsheet, with support for comments and their
 * authors.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class CommentableCellWithMultipleUsers extends CommentableCell {

    /**
     * The list with the authors of the comments and the respective comments.
     */
    private Map<User, List<String>> comments;

    /**
     * Creates a comentable cell with the indentification of the user extension
     * for the given cell.
     *
     * @param cell the cell to extend
     */
    public CommentableCellWithMultipleUsers(Cell cell) {
        super(cell);
        comments = new HashMap<>();
    }

    /**
     * Adds a new comment to the list.
     *
     * @param comment the comment to add
     * @param user the author of the comment
     */
    public void addUsersComment(String comment, User user) {
        if (Strings.isNullOrEmpty(comment) || Strings.isNullOrWhiteSpace(comment)) {
            throw new IllegalArgumentException("The comment should not be empty!");
        }
        //changes the user comment calling the method in the parent class
        super.setUserComment(comment);

        for (Map.Entry<User, List<String>> entry : comments.entrySet()) {
            if (entry.getKey().name().equals(user.name())) {
                entry.getValue().add(super.getUserComment());
                return;
            }
        }

        //if the user does not exists, it puts a new field on the list
        List<String> newList = new ArrayList<>();
        newList.add(super.getUserComment());
        comments.put(user, newList);
    }

    /**
     * Modifies a comment on the list.
     *
     * @param oldAuthor the author of the comment
     * @param newAuthor the author who made the changes
     * @param oldComment the old comment to be replaced
     * @param newComment the comment to replace
     */
    public void changeUserComment(String oldAuthor, User newAuthor, String oldComment, String newComment) {
        if (Strings.isNullOrEmpty(newComment) || Strings.isNullOrWhiteSpace(newComment)) {
            throw new IllegalArgumentException("The comment should not be empty!");
        }
        for (Map.Entry<User, List<String>> entry : comments.entrySet()) {
            if (entry.getKey().name().equals(oldAuthor)) {
                entry.getValue().remove(oldComment);
            }
            if (entry.getKey().name().equals(newAuthor.name())) {
                entry.getValue().add(newComment);
            }
        }
    }

    /**
     * Returns the comments of this cell.
     *
     * @return the comments
     */
    public Map<User, List<String>> comments() {
        return this.comments;
    }

    /**
     * Verifies if this cell has comments.
     *
     * @return true if the cell has comments. Otherwise returns false
     */
    public boolean hasComments() {
        return !this.comments.isEmpty();
    }

}
