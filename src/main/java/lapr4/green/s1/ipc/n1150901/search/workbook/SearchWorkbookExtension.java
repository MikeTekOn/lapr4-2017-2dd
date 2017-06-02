/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150901.search.workbook;

import lapr4.green.s1.ipc.n1150901.search.workbook.ui.UIExtensionSearchWorkbook;
import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * An extension to support the user story IPC03.1 - Search in Another Instance.
 * An extension must expand the Extension abstract class.
 * The class that implements the Extension is the "bootstrap" of the extension.
 * @see Extension
 * @author Miguel Silva - 1150901
 */
public class SearchWorkbookExtension extends Extension {

	/** The name of the extension */
	public static final String NAME = "Search Workbook";

	/**
	 * Creates a new Example extension.
	 */
	public SearchWorkbookExtension() {
		super(NAME);
	}
	
	/**
	 * Returns the user interface extension of this extension 
	 * @param uiController the user interface controller
	 * @return a user interface extension, or null if none is provided
	 */
        @Override
	public UIExtension getUIExtension(UIController uiController) {
		return new UIExtensionSearchWorkbook(this, uiController);
	}
}

