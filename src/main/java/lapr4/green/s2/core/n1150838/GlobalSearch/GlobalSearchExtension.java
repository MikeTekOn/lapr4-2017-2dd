/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150838.GlobalSearch;

import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.green.s2.core.n1150838.GlobalSearch.presentation.UIExtensionGlobalSearch;
import lapr4.red.s1.core.n1150613.workbookSearch.SearchExtension;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class GlobalSearchExtension extends SearchExtension {

	/**
	 * Creates a new Gloabel search extension.
	 */
	public GlobalSearchExtension() {
            super();
            setVersion(super.getVersion()+1);
	}
	
	/**
	 * Returns the user interface extension of this extension 
	 * @param uiController the user interface controller
	 * @return a user interface extension, or null if none is provided
	 */
        @Override
	public UIExtension getUIExtension(UIController uiController) {
            return new UIExtensionGlobalSearch(this, uiController);
        }
        
        @Override
        public String metadata() {
            return String.format("This is %s with version %d\n"
                    + "This extension has the follow description: %s\n"
                    + "This extension was made by Nuno Pinto in Sprint 2 and it is in the package %s\n"
                    + "This extensions was updated to a global search extension.",
                    getName(), getVersion(), getDescription(),getClass().getName());
        }
}
