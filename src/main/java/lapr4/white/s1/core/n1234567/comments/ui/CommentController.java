package lapr4.white.s1.core.n1234567.comments.ui;

import lapr4.white.s1.core.n1234567.comments.CommentableCell;
import csheets.ui.ctrl.UIController;

/**
 * A controller for updating the user-specified comment of a cell.
 * @author Alexandre Braganca
 * @author Einar Pehrson
 */
public class CommentController {

	/** The user interface controller */
	private UIController uiController;
        
        /** User interface panel **/
        private CommentPanel uiPanel;
        
	/**
	 * Creates a new comment controller.
	 * @param uiController the user interface controller
         * @param uiPanel the user interface panel
	 */
	public CommentController(UIController uiController, CommentPanel uiPanel) {
		this.uiController = uiController;
                this.uiPanel = uiPanel;
	}

	/**
	 * Attempts to create a new comment from the given string.
	 * If successful, adds the comment to the given cell.
	 * If the input string is empty or null, the comment is set to null.
	 * @param cell the cell for which the comment should be set
	 * @param commentString the comment, as entered by the user
	 * @return true if the cell's comment was changed
	 */
	public boolean setComment(CommentableCell cell, String commentString) {
		// Clears comment, if insufficient input
		if (commentString == null || commentString.equals("")) {
			cell.setUserComment(null);
			return true;
		}

		// Stores the comment
		cell.setUserComment(commentString);
		uiController.setWorkbookModified(cell.getSpreadsheet().getWorkbook());
                
		return true;
	}
        
        /**
	 * A cell is selected.
	 * @param cell the cell whose comments changed
	 */
	public void cellSelected(CommentableCell cell) {
		// Updates the text field and validates the comment, if any
		if (cell.hasComment()) {
			uiPanel.setCommentText(cell.getUserComment());
		} else {
			uiPanel.setCommentText("");
		}
	}
        
        public UIController uiController(){
            return this.uiController;
        }
}
