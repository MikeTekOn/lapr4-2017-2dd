/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150613.workbookSearch;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.white.s1.core.n4567890.contacts.ui.UIExtensionContacts;

/**
 * An extension to support search.
 * 
 * @author Diogo Guedes
 */
public class SearchExtension extends Extension {

	/** The name of the extension */
	public static final String NAME = "Workbook Search";

	/**
	 * Creates a new Example extension.
	 */
	public SearchExtension() {
            super(NAME);
	}
	
	/**
	 * Returns the user interface extension of this extension 
	 * @param uiController the user interface controller
	 * @return a user interface extension, or null if none is provided
	 */
        @Override
	public UIExtension getUIExtension(UIController uiController) {
		return new UIExtensionContacts(this, uiController);
	}
}