package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ext;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ui.UIFindWorkbooksExtension;

/**
 * @author Diana Silva [1151088@isep.ipp.pt]
 * update on 10/06/2017 due to preview funcionality
 * @author nunopinto
 */
public class ExtensionFindWorkbook extends Extension {
    
    /** The name of the extension */
	public static final String NAME = "Find Workbook";

    public ExtensionFindWorkbook() {
        super(NAME);
       
    }
    
    /**
     * Returns the user interface extension of this extension (an instance of the class {@link  csheets.ext.simple.ui.UIExtensionExample}).
     * In this extension example we are only extending the user interface.
     * @param uiController the user interface controller
     * @return a user interface extension, or null if none is provided
     */
    @Override
    public UIExtension getUIExtension(UIController uiController) {
            return new UIFindWorkbooksExtension(this, uiController);
    }    
}
