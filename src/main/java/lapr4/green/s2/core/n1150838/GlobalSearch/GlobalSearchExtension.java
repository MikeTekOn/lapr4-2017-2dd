/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150838.GlobalSearch;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class GlobalSearchExtension extends Extension {
    	/** The name of the extension */
    public static final String NAME = "Workbook Search";
    
    public static final String DESCRIPTION = "Workbook Search description";
    public static final int VERSION = 1;

	/**
	 * Creates a new Example extension.
	 */
	public GlobalSearchExtension() {
            super(NAME,VERSION,DESCRIPTION);
	}
	
	/**
	 * Returns the user interface extension of this extension 
	 * @param uiController the user interface controller
	 * @return a user interface extension, or null if none is provided
	 */
        @Override
	public UIExtension getUIExtension(UIController uiController) {
            return null;
        }
        
        @Override
        public String metadata(){
            return String.format("This is %s with version %d\n"
                    + "This extension has the follow description: %s.", getName(),getVersion(),getDescription());
        }
}
