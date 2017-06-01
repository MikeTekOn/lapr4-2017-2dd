/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150657.chat.ui;

import csheets.ui.ctrl.UIController;
import javax.swing.JMenu;

/**
 * It represents the menu for the chat.
 *
 * @author Sofia Gonçalves (1150657)
 */
public class ChatMenu extends JMenu {

    /**
     * The constructor for the ChatMenu
     *
     * @param uiController The ui controller.
     */
    public ChatMenu(UIController uiController) {
        super("Chat");
        add(new ChatAction(uiController));
    }
}
