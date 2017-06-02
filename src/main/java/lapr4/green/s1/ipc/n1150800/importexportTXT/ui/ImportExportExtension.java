/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import lapr4.green.s1.ipc.n1150800.importexportTXT.ui.UIExtensionImportExportData;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ImportExportExtension extends Extension {

    /**
     * The name of the extension
     */
    public static final String NAME = "Import/Export";
    
    public ImportExportExtension() {
        super(NAME);
    }

    /**
     * Returns the user interface extension of this extension (an instance of
     * the class {@link  csheets.ext.simple.ui.UIExtensionExample}). In this
     * extension example we are only extending the user interface.
     *
     * @param uiController the user interface controller
     * @return a user interface extension, or null if none is provided
     */
    public UIExtension getUIExtension(UIController uiController) {
        return new UIExtensionImportExportData(this, uiController);
    }
}
