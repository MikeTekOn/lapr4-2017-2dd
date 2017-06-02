/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150657.chat.ui;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;

/**
 * It represents the action for the chat.
 *
 * @author Sofia Gonçalves (1150657)
 */
public class ChatAction extends BaseAction {

    /**
     * The controller.
     */
    private UIController uiController;

    /**
     * The constructor for the ChatAction
     * @param uiController The controller.
     */
    public ChatAction(UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    protected String getName() {
        return "Chat";
    }

    @Override
    protected void defineProperties() {
        //TODO
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO
    }

}
