package lapr4.green.s1.ipc.n1150738.securecomm.ui;

import csheets.ui.ctrl.UIController;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by henri on 6/2/2017.
 */
public class IncomingOutgoingCommsMenu extends JMenu {

    public IncomingOutgoingCommsMenu(UIController uiController) {
        super("Incoming/Outgoing Connections");
        setMnemonic(KeyEvent.VK_K);

        // Adds font actions
        add(new IncomingOutgoingCommsAction(uiController));
    }
}