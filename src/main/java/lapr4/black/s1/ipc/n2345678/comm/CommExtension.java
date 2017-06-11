/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.black.s1.ipc.n2345678.comm;

import csheets.ext.Extension;
import lapr4.black.s1.ipc.n2345678.comm.ui.UICommExtension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 *
 * @author alexandrebraganca
 */
public class CommExtension extends Extension {

    /**
     * The name of the extension
     */
    public static final String NAME = "Network";
    
    /**
     * The description of the extension
     */
    public static final String DESCRIPTION = "The Communication extension.";
    
    /**
     * The first version of the communication extension.
     */
    public static final int VERSION = 1;

    private CommServer commServer;

    /**
     * Creates a new Example extension.
     */
    public CommExtension() {
        super(NAME, VERSION, DESCRIPTION);

        // Create also the CommServer during the construction of the extension
        commServer = CommServer.getCommServer();
    }

    /**
     * Returns the user interface extension of this extension (an instance of
     * the class {@link  lapr4.black.s1.ipc.n2345678.comm.ui.UICommExtension}).
     * <p>
     * In this extension example we are only extending the user interface.
     * @param uiController the user interface controller
     * @return a user interface extension, or null if none is provided
     */
    public UIExtension getUIExtension(UIController uiController) {
        return new UICommExtension(this, uiController);
    }

    @Override
    public String metadata() {
        return String.format("This is %s with version %d\n"
                + "This extension has the follow description: %s\n"
                + "This extension was made by Alexandre Braganca and it is in the package %s",
                getName(), getVersion(), getDescription(),getClass().getName());
    }
}
