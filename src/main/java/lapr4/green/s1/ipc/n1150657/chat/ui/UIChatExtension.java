/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150657.chat.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.JComponent;
import javax.swing.JMenu;

/**
 * This class implements the UI interface extension for the chat extension. A UI
 * interface extension must extend the UIExtension abstract class.
 *
 * @see UIExtension
 * @author Sofia Gonçalves (1150657)
 */
public class UIChatExtension extends UIExtension {

    //ICON
    /**
     * The side bar that provides the chat.
     */
    private JComponent sideBar;

    /**
     * The menu.
     */
    private JMenu menu;

    /**
     * The ui extension for chat.
     *
     * @param extension The extension.
     * @param uiController The ui controller.
     */
    public UIChatExtension(Extension extension, UIController uiController) {
        //TODO
        super(extension, uiController);
    }

    /**
     * It returns a side bar that provides the chat.
     *
     * @return It returns a side bar.
     */
    @Override
    public JComponent getSideBar() {
        if (sideBar == null) {
            sideBar = new ChatPanel(uiController);
        }
        return sideBar;
    }

    /**
     * It returns the menu.
     *
     * @return It returns the Chat Menu.
     */
    @Override
    public JMenu getMenu() {
        if (menu == null) {
            menu = new ChatMenu(uiController);
        }
        return menu;
    }
}
