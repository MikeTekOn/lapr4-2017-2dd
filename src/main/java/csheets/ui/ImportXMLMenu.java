/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ui;

import csheets.CleanSheets;
import csheets.ui.ctrl.ActionManager;
import csheets.ui.ctrl.UIController;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

/**
 *
 * @author Sofia
 */
public class ImportXMLMenu extends JMenu {

    private CleanSheets app;
    private UIController uiController;
    private ActionManager actionManager;

    /**
     * This is responsible for adding action to import xml file into the system
     *
     * @param app
     * @param uiController
     * @param actionManager
     */
    public ImportXMLMenu(CleanSheets app, UIController uiController, ActionManager actionManager) {
        super("Import to");

        setMnemonic(KeyEvent.VK_I);
        setIcon(new ImageIcon(CleanSheets.class.getResource("res/img/Import.png")));

        add(new ImportToXMLSubMenu(actionManager));
    }
}
