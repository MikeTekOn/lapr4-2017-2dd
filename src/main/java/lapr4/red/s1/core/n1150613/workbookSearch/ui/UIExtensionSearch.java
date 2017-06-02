/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150613.workbookSearch.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.JComponent;

/**
 *
 * @author Diogo Guedes
 */
/**
 * This class implements the UI interface extension for the search extension.
 * A UI interface extension must extend the UIExtension abstract class.
 * @see UIExtension
 * @author Diogo Guedes
 */
public class UIExtensionSearch extends UIExtension {

	/** The icon to display with the extension's name */
//	private Icon icon;

	/** A side bar that provides search of regular expressions */
	private JComponent sideBar;

	/** The menu of the extension
        * @param extension extension
        * @param uiController ui controller 
        */
	public UIExtensionSearch(Extension extension, UIController uiController) {
		super(extension, uiController);
	}
	
	/**
	 * Returns a side bar that provides search of regular expressions
	 * @return a side bar
	 */
        @Override
	public JComponent getSideBar() {
            return null;
	}	
    
    
}
