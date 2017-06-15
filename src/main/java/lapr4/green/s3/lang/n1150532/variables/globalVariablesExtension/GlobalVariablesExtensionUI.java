package lapr4.green.s3.lang.n1150532.variables.globalVariablesExtension;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.JComponent;

/**
 * The UI for the GlobalVariablesExtension. It provides a side bar.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class GlobalVariablesExtensionUI extends UIExtension {

    /**
     * The extension's side bar.
     */
    private GlobalVariablesExtensionSideBarUI sideBar;

    /**
     * The full constructor of the UI.
     *
     * @param extension The extension for which the UI is built.
     * @param uiController The UI controller.
     */
    public GlobalVariablesExtensionUI(Extension extension, UIController uiController) {
        super(extension, uiController);
    }

    /**
     * It provides the existing side bar. It will build it if not yet done.
     *
     * @return It returns the extension's side bar.
     */
    @Override
    public JComponent getSideBar() {
        if (sideBar == null) {
            sideBar = new GlobalVariablesExtensionSideBarUI(uiController);
        }
        return sideBar;
    }

}
