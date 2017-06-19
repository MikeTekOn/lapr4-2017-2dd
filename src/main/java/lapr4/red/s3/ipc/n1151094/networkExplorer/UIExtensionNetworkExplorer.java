package lapr4.red.s3.ipc.n1151094.networkExplorer;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Eduangelo Ferreira on 17/06/2017.
 */
public class UIExtensionNetworkExplorer extends UIExtension {

    /**
     * The icon to display with the extension's name
     */
    private Icon icon;

    /**
     * A panel in which a precedents tree and a dependents tree is displayed
     */
    private JComponent sideBar;

    public UIExtensionNetworkExplorer(Extension extension, UIController uiController) {

        super(extension, uiController);
    }

//    public Icon getIcon() {
//        if (icon == null)
//            icon = new ImageIcon(
//                    UIExtensionNetworkExplorer.class.getResource("graph.png"));
//        return icon;
//    }

    public JComponent getSideBar() {
        if (sideBar == null) {
            sideBar = new UI();
            sideBar.setName(NetworkExplorerExtension.NAME);

        }
        return sideBar;
    }
}
