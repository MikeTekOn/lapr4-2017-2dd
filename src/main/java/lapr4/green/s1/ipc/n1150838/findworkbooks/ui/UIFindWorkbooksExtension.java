/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150838.findworkbooks.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.JComponent;
import javax.swing.JToolBar;

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
	public JToolBar getToolBar() {
		return new JToolBar();
	}

	/**
	 * Returns a side bar that gives access to extension-specific
	 * functionality.
	 * @return a component, or null if the extension does not provide one
	 */
	public JComponent getSideBar() {
		return new FindWorkbooksSideBar(super.uiController);
	}
    
}
