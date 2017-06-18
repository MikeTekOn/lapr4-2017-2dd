
package lapr4.blue.s3.core.n1151031.navigation.ui;

import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import lapr4.blue.s3.core.n1151031.navigation.NavigationTreeExtension;

/**
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class NavigationTreeUIExtension extends UIExtension {

    /**
     * A panel in which a navigation tree is displayed
     */
    private JComponent sideBar;

    /**
     * Creates a new user interface extension.
     *
     * @param extension the extension for which components are provided
     * @param uiController the user interface controller
     */
    public NavigationTreeUIExtension(NavigationTreeExtension extension,
            UIController uiController) {
        super(extension, uiController);
    }

    /**
     * Returns a panel in which a navigation tree is displayed.
     *
     * @return a panel with the navigation tree
     */
    public JComponent getSideBar() {
        if (sideBar == null) {
            sideBar = new JPanel(new GridLayout(1, 1));
            sideBar.setName(NavigationTreeExtension.NAME);

            // Creates components
            NavigationTree navigationTree = new NavigationTree(uiController);
            JScrollPane navigationPane = new JScrollPane(navigationTree);

            // Adds borders
            TitledBorder border = BorderFactory.createTitledBorder("Navigation");
            border.setTitleJustification(TitledBorder.CENTER);
            navigationPane.setBorder(border);

            // Creates side bar
            sideBar.add(navigationPane);
        }
        return sideBar;
    }

}
