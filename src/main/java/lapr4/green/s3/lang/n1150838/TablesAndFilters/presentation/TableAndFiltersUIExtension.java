/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters.presentation;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.JComponent;

/**
 *
 * @author Nuno Pinto 1150838   
 */
public class TableAndFiltersUIExtension extends UIExtension {

    /**
     * A side bar that provides editing of table and filters extension
     */
    private JComponent sideBar;

    public TableAndFiltersUIExtension(Extension extension, UIController uiController) {
        super(extension, uiController);
    }

    /**
     * Returns an icon to display with the extension's name.
     *
     * @return an icon with style
     */
    public Icon getIcon() {
        return null;
    }

    /**
     * Returns a side bar that provides editing of conditional formatting.
     *
     * @return a side bar
     */
    @Override
    public JComponent getSideBar() {
        return new TableAndFiltersPane(uiController);
    }

    
}
