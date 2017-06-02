/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150657.chat.ui;

import csheets.ui.ctrl.UIController;

/**
 * It represents the controller for the chat.
 *
 * @author Sofia Gonçalves (1150657)
 */
public class ChatController {

    /**
     * The controller.
     */
    private UIController uiController;

    /**
     * The chat panel.
     */
    private ChatPanel uiPanel;

    /**
     * The constructor for the ChatController.
     *
     * @param uiController The controller.
     * @param uiPanel The ui panel.
     */
    public ChatController(UIController uiController, ChatPanel uiPanel) {
        this.uiController = uiController;
        this.uiPanel = uiPanel;
    }

}
