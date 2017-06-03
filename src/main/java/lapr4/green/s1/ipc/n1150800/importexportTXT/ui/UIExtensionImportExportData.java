/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT.ui;

import csheets.ext.Extension;
import csheets.ext.simple.ui.ExampleMenu;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.CellDecorator;
import csheets.ui.ext.TableDecorator;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JToolBar;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class UIExtensionImportExportData extends UIExtension {

    /**
     * The icon to display with the extension's name
     */
    private Icon icon;

    /**
     * The menu of the extension
     */
    private ImportExportMenu menu;

    public UIExtensionImportExportData(Extension extension, UIController uiController) {
        super(extension, uiController);
    }

    /**
     * Returns an icon to display with the extension's name.
     *
     * @return an icon with style
     */
    @Override
    public Icon getIcon() {
        return null;
    }

    /**
     * Returns an instance of a class that implements JMenu. In this simple case
     * this class only supplies one menu option.
     *
     * @see ExampleMenu
     * @return a JMenu component
     */
    @Override
    public JMenu getMenu() {
        if (menu == null) {
            menu = new ImportExportMenu(uiController);
        }
        return menu;
    }

    /**
     * Returns a cell decorator that visualizes the data added by the extension.
     *
     * @return a cell decorator, or null if the extension does not provide one
     */
    @Override
    public CellDecorator getCellDecorator() {
        return null;
    }

    /**
     * Returns a table decorator that visualizes the data added by the
     * extension.
     *
     * @return a table decorator, or null if the extension does not provide one
     */
    @Override
    public TableDecorator getTableDecorator() {
        return null;
    }

    /**
     * Returns a toolbar that gives access to extension-specific functionality.
     *
     * @return a JToolBar component, or null if the extension does not provide
     * one
     */
    @Override
    public JToolBar getToolBar() {
        return null;
    }

    /**
     * Returns a side bar that gives access to extension-specific functionality.
     *
     * @return a component, or null if the extension does not provide one
     */
    @Override
    public JComponent getSideBar() {
        return null;
    }

}
