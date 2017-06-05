package lapr4.green.s1.ipc.n1150532.comm.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.green.s1.ipc.n1150738.securecomm.ui.IncomingOutgoingCommsMenu;

import javax.swing.*;

/**
 * The UI for the CommExtension. It provides a side bar.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class UICommExtension extends UIExtension {

    /**
     * The side bar.
     */
    private UICommExtensionSideBar sideBar;

    /**
     * The full constructor of the UI.
     * 
     * @param extension The extension for which the UI is built.
     * @param uiController The UI controller.
     */
    public UICommExtension(Extension extension, UIController uiController) {
        super(extension, uiController);
        sideBar = null;
    }

    /**
     * It provides the existing side bar. It will build it if not yet done.
     * 
     * @return It returns the extension's side bar.
     */
    @Override
    public JComponent getSideBar() {
        if (sideBar == null) {
            sideBar = new UICommExtensionSideBar(uiController, extension);
        }
        return sideBar;
    }

    @Override
    public JMenu getMenu() {
        return new IncomingOutgoingCommsMenu(uiController);
    }
}
