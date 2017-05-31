/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150690.comments;

import csheets.core.Cell;
import eapli.util.Strings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lapr4.red.s1.core.n1150690.comments.domain.User;
import lapr4.white.s1.core.n1234567.comments.CommentableCell;

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
            throw new IllegalArgumentException("The comment must contain something!");
        }
        //changes the user comment calling the method in the parent class
        super.setUserComment(comment);

        if (comments.containsKey(user)) {
            comments.get(user).add(comment);
        } else {
            //if the user does not exists, it puts a new field on the list
            List<String> newList = new ArrayList<>();
            newList.add(comment);
            comments.put(user, newList);
        }
    }

    /**
     * Modifies a comment on the list.
     *
     * @param user the author of the comment
     * @param oldComment the old comment to be replaced
     * @param newComment the comment to replace
     */
    public void changeUserComment(User user, String oldComment, String newComment) {
        if (Strings.isNullOrEmpty(newComment) || Strings.isNullOrWhiteSpace(newComment)) {
            throw new IllegalArgumentException("The comment must contain something!");
        }
        int indexComment = comments.get(user).indexOf(oldComment);
        comments.get(user).set(indexComment, newComment);
    }

}
