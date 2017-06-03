package lapr4.green.s1.ipc.n1150532.comm.ui;

import csheets.ui.ctrl.BaseAction;
import java.awt.event.ActionEvent;
import java.util.Properties;

/**
 * The action to configure the UDP and TCP port numbers to be used on the
 * servers.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class ConfigurePortAction extends BaseAction {

    /**
     * The name of the action.
     */
    private static final String NAME = "Configure Port Numbers";

    /**
     * The ser properties to be changed.
     */
    private final Properties userProperties;

    /**
     * The full constructor of the action.
     *
     * @param theUserProperties The user properties to be changed.
     */
    public ConfigurePortAction(Properties theUserProperties) {
        userProperties = theUserProperties;
    }

    /**
     * A getter to the action name.
     *
     * @return It returns the action name.
     */
    @Override
    protected String getName() {
        return NAME;
    }

    /**
     * It builds the configure ports UI on action event.
     *
     * @param e The action event. It is not used.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new ConfigurePortUI(NAME, userProperties);
    }

}
