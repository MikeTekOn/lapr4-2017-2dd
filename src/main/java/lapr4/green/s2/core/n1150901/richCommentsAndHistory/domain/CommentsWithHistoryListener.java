/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150901.richCommentsAndHistory.domain;

import lapr4.white.s1.core.n1234567.comments.CommentableCell;

/**
 *
 * @author Miguel Silva - 1150901
 */
public interface CommentsWithHistoryListener {

    /**
     * Invoked when a comment's history is changed.
     *
     * @param cell The comment's history that was modified.
     */
    public void historyChanged(CommentableCell cell);
}
