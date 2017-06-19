package lapr4.blue.s3.core.n1151088.searchReplace;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.blue.s3.core.n1151088.searchReplace.presentation.UISearchReplaceExtension;
import lapr4.green.s2.core.n1150838.GlobalSearch.GlobalSearchExtension;

/**
 * Represents an extension to support search and replace feature
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class SearchReplaceExtension extends GlobalSearchExtension{

    /**
     * Creates a new Notes Edition Extension
     */
    public SearchReplaceExtension(){
        super();
        setVersion(super.getVersion()+1);
        
    }
    
    /**
     * It returns the user interface extension of this extension.
     *
     * @param uiController The user interface controller.
     * @return It returns a user interface extension or NullPointerException if
     * none is provided
     */
    @Override
    public UIExtension getUIExtension(UIController uiController) {
        UISearchReplaceExtension ui = new UISearchReplaceExtension(this, uiController);
        if (ui == null) {
            throw new NullPointerException("Extension from search and replace can't be build.");
        }
        return ui;
    }

    @Override
    public String metadata() {
        return NAME;
    }
}
