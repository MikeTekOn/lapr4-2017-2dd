package lapr4.blue.s2.ipc.n1151452.netanalyzer;

import csheets.core.Cell;
import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.ui.UIExtensionNetAnalyzer;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.white.s1.core.n1234567.comments.ui.UIExtensionComments;

/**
 * An extension to visualize the network analyzer.
 *
 * @see Extension
 * @author Einar Pehrson
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 10/06/17.
 */
public class NetAnalyzerExtension extends Extension {

    /**
     * The name of the extension
     */
    public static final String NAME = "Network Analyzer";

    /**
     * Creates a new extension.
     */
    public NetAnalyzerExtension() {
        super(NAME);
    }

    /**
     * Returns the user interface extension of this extension
     * @param uiController the user interface controller
     * @return a user interface extension, or null if none is provided
     */
    public UIExtension getUIExtension(UIController uiController) {
        return new UIExtensionNetAnalyzer(this, uiController);
    }
}
