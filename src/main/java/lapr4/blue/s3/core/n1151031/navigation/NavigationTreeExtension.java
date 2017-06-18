package lapr4.blue.s3.core.n1151031.navigation;

import lapr4.blue.s3.core.n1151031.navigation.ui.NavigationTreeUIExtension;
import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * A extension that implements a navigation window.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class NavigationTreeExtension extends Extension {

    /**
     * The name of the extension
     */
    public static final String NAME = "Navigation Window";

    /**
     * The description of the extension
     */
    public static final String DESCRIPTION = "The extension for the navigation tree.";

    /**
     * The version of the navigation tree extension.
     */
    public static final int VERSION = 1;

    /**
     * Creates a new assertion extension.
     */
    public NavigationTreeExtension() {
        super(NAME, VERSION, DESCRIPTION);
    }

    /**
     * Returns a user interface extension for navigation tree.
     *
     * @param uiController the user interface controller
     * @return a user interface extension for navigation tree
     */
    public UIExtension getUIExtension(UIController uiController) {
        return new NavigationTreeUIExtension(this, uiController);
    }

    @Override
    public String metadata() {
        return String.format("This is %s with version %d\n"
                + "This extension has the follow description: %s\n"
                + "This extension was made by Tiago Correia and it is in the package %s",
                getName(), getVersion(), getDescription(), getClass().getName());
    }
}
