package lapr4.white.s1.core.n1234567.comments;

import lapr4.white.s1.core.n1234567.comments.CommentableCellListener;
import lapr4.white.s1.core.n1234567.comments.CommentableCell;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;

/**
 * A Unit Test class to test CommentableCell.
 * @see CommentableCell
 * @author Alexandre Braganca
 */
public class CommentableCellTest {
	
	private boolean isNotified=false;

	/**
	 * A method that tests the property hasComment.
	 */
	@Test public void testHasComment() {
		
		// create a workbook with 2 sheets
		Workbook wb=new Workbook(2, null);
		Spreadsheet s=wb.getSpreadsheet(0);
		// get the first cell
		Cell c=s.getCell(0,0);
		
		// activate the comments on the first cell
		CommentableCell cc=new CommentableCell(c);

		boolean hasComment=cc.hasComment();
		
		assertTrue(hasComment==false);		
		
		cc.setUserComment("coment");

		hasComment=cc.hasComment();
		
		assertTrue(hasComment);		
	}

	/**
	 * A method that tests the setter and getter of the user comment.
	 */
	@Test public void testSetGetUserComment() {
		
		// create a workbook with 2 sheets
		Workbook wb=new Workbook(2, null);
		Spreadsheet s=wb.getSpreadsheet(0);
		// get the first cell
		Cell c=s.getCell(0,0);
		
		// activate the comments on the first cell
		CommentableCell cc=new CommentableCell(c);

		cc.setUserComment("Hello");
		
		assertTrue("Hello".compareTo(cc.getUserComment())==0);		
	}
	
	/**
	 * A method that tests the notifications for commented cell listeners.
	 * @see CommentableCellListener
	 */	
	@Test public void testCommentableCellListenner() {
		
		// create a workbook with 2 sheets
		Workbook wb=new Workbook(2, null);
		Spreadsheet s=wb.getSpreadsheet(0);
		// get the first cell
		Cell c=s.getCell(0,0);
		
		// activate the comments on the first cell
		CommentableCell cc=new CommentableCell(c);
		
		CommentableCellListener listener=new CommentableCellListenerImpl();
		
		cc.addCommentableCellListener(listener);

		// modify the cell... this should create an event
		cc.setUserComment("Hello");
		
		assertTrue(isNotified);		
	}

	/**
	 * A inner utility class used by the method testCommentableCellListenner.
	 */	
	class CommentableCellListenerImpl implements CommentableCellListener {

		@Override
		public void commentChanged(CommentableCell cell) {
			isNotified=true;
		}
		
	}
}
