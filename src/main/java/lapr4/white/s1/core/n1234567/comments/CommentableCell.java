/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.white.s1.core.n1234567.comments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import csheets.core.Cell;
import csheets.ext.CellExtension;
import lapr4.green.s2.core.n1150901.richCommentsAndHistory.domain.CommentsWithHistoryListener;

/**
 * An extension of a cell in a spreadsheet, with support for comments.
 * @author Alexandre Braganca
 * @author Einar Pehrson
 */
public class CommentableCell extends CellExtension {

	/** The unique version identifier used for serialization */
	private static final long serialVersionUID = 1L;

	/** The cell's user-specified comment */
        private String userComment;
        
        /** The history of a comment in a cell */
        private String commentHistory;

	/** The listeners registered to receive events from the comentable cell */
	protected transient List<CommentableCellListener> listeners
		= new ArrayList<CommentableCellListener>();
        
        /** The listeners registered to receive events from the comentable cell */
	private transient List<CommentsWithHistoryListener> historyListeners
		= new ArrayList<CommentsWithHistoryListener>();

	/**
	 * Creates a comentable cell extension for the given cell.
	 * @param cell the cell to extend
	 */
	public CommentableCell(Cell cell) {
		super(cell, CommentsExtension.NAME);
	}


/*
 * DATA UPDATES
 */


//	public void contentChanged(Cell cell) {
//	}


/*
 * COMMENT ACCESSORS
 */

	/**
	 * Get the cell's user comment.
	 * @return The user supplied comment for the cell or <code>null</code> if no user
	 supplied comment exists.
	*/
	public String getUserComment() {
		return userComment;
	}

	/**
	 * Returns whether the cell has a comment.
	 * @return true if the cell has a comment
	 */
	public boolean hasComment() {
		return userComment != null;
	}
        
        	/**
	 * Get the comment's history.
	 * @return The user supplied comment for the cell or <code>null</code> if no user
	 supplied comment exists.
	*/
	public String getCommentHistory() {
		return commentHistory;
	}

	/**
	 * Returns whether the comment has a history.
	 * @return true if the comment has a history.
	 */
	public boolean hasHistory() {
		return commentHistory != null;
	}

/*
 * COMMENT MODIFIERS
 */

	/**
	 * Sets the user-specified comment for the cell.
	 * @param comment the user-specified comment
	 */
	public void setUserComment(String comment) {
		this.userComment = comment;
		// Notifies listeners
		fireCommentsChanged();
	}
        
        /**
	 * Sets the user-specified comment's history for the cell.
	 * @param previousComment The user-specified history.
	 */
	public void setCommentHistory(String previousComment) {
		this.commentHistory = previousComment;
		// Notifies listeners
		fireHistoryChanged();
	}


/*
 * EVENT LISTENING SUPPORT
 */

	/**
	 * Registers the given listener on the cell.
	 * @param listener the listener to be added
	 */
	public void addCommentableCellListener(CommentableCellListener listener) {
		listeners.add(listener);
	}

	/**
	 * Removes the given listener from the cell.
	 * @param listener the listener to be removed
	 */
	public void removeCommentableCellListener(CommentableCellListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Notifies all registered listeners that the cell's comments changed.
	 */
	public void fireCommentsChanged() {
		for (CommentableCellListener listener : listeners)
			listener.commentChanged(this);
	}
        
        /**
	 * Registers the given listener on the cell.
	 * @param listener the listener to be added
	 */
	public void addCommentsWithHistoryListener(CommentsWithHistoryListener listener) {
		historyListeners.add(listener);
	}

	/**
	 * Removes the given listener from the cell.
	 * @param listener the listener to be removed
	 */
	public void removeCommentsWithHistoryListener(CommentsWithHistoryListener listener) {
		historyListeners.remove(listener);
	}

	/**
	 * Notifies all registered listeners that the cell's comment's history changed.
	 */
	public void fireHistoryChanged() {
		for (CommentsWithHistoryListener listener : historyListeners)
			listener.historyChanged(this);
	}

	/**
	 * Customizes serialization, by recreating the listener list.
	 * @param stream the object input stream from which the object is to be read
	 * @throws IOException If any of the usual Input/Output related exceptions occur
	 * @throws ClassNotFoundException If the class of a serialized object cannot be found.
	 */
	private void readObject(java.io.ObjectInputStream stream)
			throws java.io.IOException, ClassNotFoundException {
	    stream.defaultReadObject();
		listeners = new ArrayList<CommentableCellListener>();
		historyListeners = new ArrayList<CommentsWithHistoryListener>();
	}
}

