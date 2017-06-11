package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ext;


import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ui.UIFindWorkbooksExtension;
import lapr4.green.s1.ipc.n1150838.findworkbooks.ext.ExtensionFindWorkbooks;

/**
 * @author Diana Silva [1151088@isep.ipp.pt] update on 10/06/2017 due to preview
 * funcionality
 * @author nunopinto
 */
public class ExtensionFindWorkbook extends ExtensionFindWorkbooks {

    public ExtensionFindWorkbook() {
        super();
        setVersion(super.getVersion()+1);

    }

    /**
     * Returns the user interface extension of this extension (an instance of
     * the class {@link  csheets.ext.simple.ui.UIExtensionExample}). In this
     * extension example we are only extending the user interface.
     *
     * @param uiController the user interface controller
     * @return a user interface extension, or null if none is provided
     */
    @Override
    public UIExtension getUIExtension(UIController uiController) {
        return new UIFindWorkbooksExtension(this, uiController);
    }

    @Override
    public String metadata() {
        return String.format("This is %s with version %d\n"
                + "This extension has the follow description: %s\n"
                + "This extension was made by Diana Silva in Sprint 2 and it is in the package %s\n"
                + "This extension has a more advanced searching.",
                getName(), getVersion(), getDescription(), getClass().getName());
    }
}
