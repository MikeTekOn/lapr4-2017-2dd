package lapr4.red.s3.ipc.n1151094.networkExplorer;

import csheets.core.Workbook;
import csheets.ext.Extension;
import csheets.ext.ExtensionManager;
import csheets.ui.ctrl.UIController;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import lapr4.blue.s2.ipc.n1151031.searchnetwork.SearchWorkbookResponseDTO;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.CommExtension;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPClientWorker;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPClientsManager;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;

/**
 * Created by Eduangelo Ferreira
 */
public class HandlerCleansheetRequestDTO implements CommHandler, Serializable {

    private UIController uiController;

    public HandlerCleansheetRequestDTO(UIController uiController) {

        this.uiController = uiController;

    }

    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        PacketEncapsulatorDTO encapsulator = (PacketEncapsulatorDTO) dto;
        CleansheetRequestDTO request = (CleansheetRequestDTO) encapsulator.getDTO();

        CommExtension ce = (CommExtension) ExtensionManager.getInstance().getExtension(CommExtension.NAME);
        String[][] extensionActivate = new String[ExtensionManager.getInstance().getExtensions().length][2];
        String[][] extensionDesactivate = new String[ExtensionManager.getInstance().getDisabledExtensions().length][2];
        for (int i = 0; i < extensionActivate.length; i++) {
            extensionActivate[i][0] = ExtensionManager.getInstance().getExtensions()[i].getName();
            extensionActivate[i][1] = ExtensionManager.getInstance().getExtensions()[i].getDescription();

        }
        for (int i = 0; i < extensionDesactivate.length; i++) {
            extensionDesactivate[i][0] = ExtensionManager.getInstance().getDisabledExtensions()[i].getName();
            extensionDesactivate[i][1] = ExtensionManager.getInstance().getDisabledExtensions()[i].getDescription();

        }
        NetworkExplorerDTO clean = new NetworkExplorerDTO(encapsulator.getPacket().getAddress(), uiController.getActiveWorkbook(), extensionActivate, extensionDesactivate);
        CleansheetResponseDTO cleanResponse = new CleansheetResponseDTO(clean);

        CommTCPClientWorker worker = new CommTCPClientWorker(CommTCPClientsManager.getManager(), encapsulator.getPacket().getAddress(), ce.getTCPServerPortNumber(), true);

        try {
            TrafficOutputStream out = worker.getObjectOutputStream();
            out.write(cleanResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getLastReceivedDTO() {
        return null;
    }
}
