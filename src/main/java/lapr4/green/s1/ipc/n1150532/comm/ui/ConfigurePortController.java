package lapr4.green.s1.ipc.n1150532.comm.ui;

import eapli.framework.application.Controller;
import java.util.Properties;

/**
 * The controller of the ConfigurePortUI.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class ConfigurePortController implements Controller {

    /**
     * The minimum inclusive valid port number.
     */
    private static final int MINIMUM_PORT_NUMBER = 15300;

    /**
     * The maximum inclusive valid port number.
     */
    private static final int MAXIMUM_PORT_NUMBER = 15399;

    /**
     * The user properties to be changed.
     */
    private final Properties userProperties;

    /**
     * The full constructor of the controller.
     *
     * @param theUserProperties The user properties to be changed.
     */
    public ConfigurePortController(Properties theUserProperties) {
        userProperties = theUserProperties;
    }

    /**
     * It validates the port numbers and, if valid, saves them into the user
     * properties.
     *
     * @param udp The UDP port number.
     * @param tcp The TCP port number.
     */
    public void save(int udp, int tcp) {
        validateNumbers(udp, tcp);
        userProperties.setProperty("comm.udp.server.portNumber", Integer.toString(udp));
        userProperties.setProperty("comm.tcp.server.portNumber", Integer.toString(tcp));
    }

    /**
     * It checks if both numbers are within the minimum and maximum values
     * allowed and if both are different.
     *
     * @param udp The UDP port number.
     * @param tcp The TCP port number.
     */
    private void validateNumbers(int udp, int tcp) {
        if (udp < MINIMUM_PORT_NUMBER || udp > MAXIMUM_PORT_NUMBER) {
            throw new IllegalArgumentException("The UDP port number must be between " + MINIMUM_PORT_NUMBER + " and " + MAXIMUM_PORT_NUMBER + ".");
        }
        if (tcp < MINIMUM_PORT_NUMBER || tcp > MAXIMUM_PORT_NUMBER) {
            throw new IllegalArgumentException("The TCP port number must be between " + MINIMUM_PORT_NUMBER + " and " + MAXIMUM_PORT_NUMBER + ".");
        }
        if (udp == tcp) {
            throw new IllegalArgumentException("The UDP port number must be different from the TCP port number.");
        }
    }
}
