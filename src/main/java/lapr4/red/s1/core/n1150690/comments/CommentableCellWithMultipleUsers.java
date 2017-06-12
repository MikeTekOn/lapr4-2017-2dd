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
 * EDIT:
 * @author Miguel Silva (1150901) Sprint 2 - I've edited this class to allow to
 * update the history of comments.
 */
public class CommentableCellWithMultipleUsers extends CommentableCell {

    /**
     * The list with the authors of the comments and the respective comments.
     */
    private final Map<User, List<String>> comments;

    /* THIS IS A CHANGE I MADE */
    /**
     * The list with the changes made on comments by an user.
     */
    private final Map<User, Map<String, List<String>>> history;

    /*-------------------------*/
    /**
     * Creates a comentable cell with the indentification of the user extension
     * for the given cell.
     *
     * @param cell the cell to extend
     */
    public CommentableCellWithMultipleUsers(Cell cell) {
        super(cell);
        comments = new HashMap<>();
        /* THIS IS A CHANGE I MADE */
        history = new HashMap<>();
        /*-------------------------*/
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
        /* THIS IS A CHANGE I MADE */
        List<String> newList2 = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        map.put(super.getUserComment(), newList2);
        history.put(user, map);
        /*-------------------------*/
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
        /* THIS IS A CHANGE I MADE */
        super.setCommentHistory(oldComment);
        /*-------------------------*/
        for (Map.Entry<User, List<String>> entry : comments.entrySet()) {
            List<String> historyComments = new ArrayList<>();
            if (entry.getKey().name().equals(oldAuthor)) {
                /* THIS IS A CHANGE I MADE */
                historyComments = history.get(newAuthor).get(oldComment);
                historyComments.add(oldComment);
                history.get(newAuthor).remove(oldComment);
                /*-------------------------*/
                entry.getValue().remove(oldComment);
            }
            if (entry.getKey().name().equals(newAuthor.name())) {
                entry.getValue().add(newComment);
                history.get(newAuthor).put(newComment, historyComments);
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

    /* THIS IS A CHANGE I MADE */
    /**
     * Returns the history of comments of this cell.
     *
     * @return The history of comments.
     */
    public Map<User, Map<String, List<String>>> history() {
        return this.history;
    }

    /**
     * Verifies if this cell has history of comments.
     *
     * @return Returns true if the cell has an history of comments, otherwise
     * returns false.
     */
    @Override
    public boolean hasHistory() {
        return !this.history.isEmpty();
    }
    /*-------------------------*/

}
