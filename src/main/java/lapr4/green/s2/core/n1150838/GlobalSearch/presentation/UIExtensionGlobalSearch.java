/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150838.GlobalSearch.presentation;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.JComponent;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class UIExtensionGlobalSearch extends UIExtension {

    /**
     * The icon to display with the extension's name
     */
//	private Icon icon;

    /**
     * The menu of the extension
     *
     * @param extension extension
     * @param uiController ui controller
     */
    public UIExtensionGlobalSearch(Extension extension, UIController uiController) {
        super(extension, uiController);
    }

    /**
     * Returns a side bar that provides search of regular expressions
     *
     * @return a side bar
     */
    @Override
    public JComponent getSideBar() {
        return new GlobalSearchSideBar(uiController);
    }

}
