package lapr4.red.s3.ipc.n1151094.networkExplorer;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * Created by Eduangelo Ferreira on 17/06/2017.
 */
public class NetworkExplorerExtension extends Extension   {

    /**
     * The name of the extension
     */
    public static final String NAME = "Network Explorer";

    /**
     * The description of the extension
     */
    public static final String DESCRIPTION = "An extension to visualize the network explorer.";

    /**
     * The first version of the analyzer extension.
     */
    public static final int VERSION = 1;


    public NetworkExplorerExtension() {
        super(NAME, VERSION, DESCRIPTION);
    }

    public UIExtension getUIExtension(UIController uiController) {
        return new UIExtensionNetworkExplorer(this, uiController);
    }

    @Override
    public String metadata() {
       return String.format("This is %s with version %d\n"
                        + "This extension has the follow description: %s\n"
                        + "This extension was made by Eduangelo Ferreira in Sprint 3 and it is in the package %s",
                getName(), getVersion(), getDescription(), getClass().getName());
    }
}
