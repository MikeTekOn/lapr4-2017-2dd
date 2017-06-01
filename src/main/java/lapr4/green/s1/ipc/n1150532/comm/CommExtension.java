package lapr4.green.s1.ipc.n1150532.comm;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.green.s1.ipc.n1150532.comm.ui.UICommExtension;

/**
 *
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class CommExtension extends Extension {

    private static final String PROPERTY_KEY_TCP_PORT_NUMBER = "comm.tcp.server.portNumber";
    private static final String PROPERTY_KEY_UDP_PORT_NUMBER = "comm.udp.server.portNumber";
    public static final String NAME = "Network";
    private CommTCPServer tcpServer;
    private CommUDPServer udpServer;
    private CommTCPClientsManager tcpClientsManager;
    private Properties userProperties;

    public CommExtension() {
        super(NAME);
        tcpServer = CommTCPServer.getServer(getTCPServerPortNumber());
        addAllAvailableHandlersToTCPServer();
        udpServer = CommUDPServer.getServer(getUDPServerPortNumber());
        addAllAvailableHandlersToUDPServer();
        tcpClientsManager = new CommTCPClientsManager();
        addAllAvailableHandlersToTCPClientsManager();
    }

    @Override
    public UIExtension getUIExtension(UIController uiController) {
        userProperties = uiController.getUserProperties();
        return new UICommExtension(this, uiController);
    }

    private void addAllAvailableHandlersToTCPServer(){
        //TODO 
    }

    private void addAllAvailableHandlersToUDPServer(){
        //TODO 
    }

    private void addAllAvailableHandlersToTCPClientsManager(){
        //TODO 
    }

    private int getTCPServerPortNumber(){
        return getServerPortNumber(PROPERTY_KEY_TCP_PORT_NUMBER);
    }

    private int getUDPServerPortNumber(){
        return getServerPortNumber(PROPERTY_KEY_UDP_PORT_NUMBER);
    }

    private int getServerPortNumber(String propertyKey){
        try{
            return Integer.parseInt(userProperties.getProperty(propertyKey));
        } catch(NumberFormatException ex) {
            Logger.getLogger(CommUDPServer.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}
