package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ui;

import csheets.core.Cell;
import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.CellDecorator;
import csheets.ui.ext.UIExtension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JToolBar;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ui.FindWorkbookSideBar;
import lapr4.red.s3.ipc.n1150451.multipleRealtimeWorkbookSearch.MultipleRealtimeWorkbookSearchUI;

/**
 *
 * @author nunopinto
 */
public class UIFindWorkbooksExtension extends UIExtension {
    
    public UIFindWorkbooksExtension(Extension extension, UIController uiController) {
        super(extension, uiController);
    }
    
    	/**
	 * Returns a toolbar that gives access to extension-specific
	 * functionality.
	 * @return a JToolBar component, or null if the extension does not provide one
	 */
    @Override
	public JToolBar getToolBar() {
		return new JToolBar();
	}

	/**
	 * Returns a side bar that gives access to extension-specific
	 * functionality.
	 * @return a component, or null if the extension does not provide one
	 */
    @Override
	public JComponent getSideBar() {
		//return new FindWorkbookSideBar(super.uiController);
		return new MultipleRealtimeWorkbookSearchUI(super.uiController);
	}
        
    @Override
    public CellDecorator getCellDecorator(){
       return super.getCellDecorator();            
    }
    
    
}