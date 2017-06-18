package lapr4.blue.s3.core.n1151088.searchReplace.presentation;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.JComponent;

/**
 * This class implements the UI interface extension for the search and replace
 * featura.
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class UISearchReplaceExtension extends UIExtension{
    
     /**
     * The side bar that provides the chat.
     */
    private JComponent sideBar;
    
    public UISearchReplaceExtension(Extension extension, UIController uiController){
        super(extension, uiController);
    }
    
    /**
     * It returns a side bar that provides the chat.
     *
     * @return It returns a side bar.
     */
    @Override
    public JComponent getSideBar() {
        if (sideBar == null) {
            sideBar = new SearchReplaceSideBar(uiController);
        }
        return sideBar;
    }
    
    
}
