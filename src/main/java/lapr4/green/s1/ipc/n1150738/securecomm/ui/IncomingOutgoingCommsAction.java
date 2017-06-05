package lapr4.green.s1.ipc.n1150738.securecomm.ui;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;

import java.awt.event.ActionEvent;

/**
 * Created by henri on 6/2/2017.
 */
public class IncomingOutgoingCommsAction extends BaseAction {

    /**
     * The user interface controller
     */
    protected UIController uiController;

    public IncomingOutgoingCommsAction(UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    protected String getName() {
        return "Incoming/Outgoing Connections";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        IncomingOutgoingCommsUI ui = new IncomingOutgoingCommsUI(uiController);
    }

}

