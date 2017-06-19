package lapr4.red.s3.ipc.n1151094.networkExplorer;

import csheets.CleanSheets;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ext.Extension;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

import csheets.ext.ExtensionManager;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.CommExtension;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPServer;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;
import lapr4.red.s1.core.n1150385.enabledisableextensions.ExtensionEvent;
import lapr4.red.s1.core.n1150385.enabledisableextensions.ExtensionStateListener;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import lapr4.green.s1.ipc.n1150532.comm.CommUDPClient;

/**
 * Created by Eduangelo Ferreira on 16/06/2017.
 */
public class UI extends JPanel implements CommHandler {

    private DefaultMutableTreeNode root;
    private HashMap<InetAddress, DefaultMutableTreeNode> map = new HashMap<InetAddress, DefaultMutableTreeNode>();
    private CommUDPClientNetwork udpClient = null;
    private JTree tree;

    public UI() {
        root = new DefaultMutableTreeNode("root", true);
        CommTCPServer.getServer().addHandler(CleansheetResponseDTO.class, this);
        creatComponents();
    }

    private void creatComponents() {

        tree = new JTree(root);
        JScrollPane scroll = new JScrollPane(tree);
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);

        JButton btnStartSearch = new JButton("Start Search");
        btnStartSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startUDPClient();
            }
        });

        add(btnStartSearch, BorderLayout.SOUTH);
    }

    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        if (dto != null && dto instanceof SocketEncapsulatorDTO) {
            SocketEncapsulatorDTO o = (SocketEncapsulatorDTO) dto;
            Object myDto = o.getDTO();
            if (myDto != null && myDto instanceof CleansheetResponseDTO) {
                CleansheetResponseDTO cleansheetResponseDTO = (CleansheetResponseDTO) myDto;

                InetAddress address = cleansheetResponseDTO.cleanSheets.getAdrress();
                Workbook work = cleansheetResponseDTO.cleanSheets.getWork();
                String[][] listExtensionAtivacte = cleansheetResponseDTO.cleanSheets.getListExtensionActivate();
                String[][] listExtensionDesactivate = cleansheetResponseDTO.cleanSheets.getListExtensionDesactivate();
                DefaultMutableTreeNode ipTree = new DefaultMutableTreeNode(address.getHostAddress(), true);
                DefaultMutableTreeNode workTree = new DefaultMutableTreeNode("Workbook", true);
                DefaultMutableTreeNode spreadTree;
                DefaultMutableTreeNode loadedExtensions = new DefaultMutableTreeNode("Loaded Extensions", true);
                DefaultMutableTreeNode loadedActive = new DefaultMutableTreeNode("Active", true);
                DefaultMutableTreeNode loadeInactivate = new DefaultMutableTreeNode("Inactivate", true);
                DefaultMutableTreeNode extensions = new DefaultMutableTreeNode("Extensions", true);
                DefaultMutableTreeNode versionTreeActivate, versionTreeInactivate;
                DefaultMutableTreeNode descriptionTreeActivate,descriptionTreeInactivate;
                if (root.getChildCount() != 0) {
                    root.removeFromParent();
                    root.removeAllChildren();
                }
                for (int i = 0; i < work.getSpreadsheetCount(); i++) {
                    Spreadsheet sheet = work.getSpreadsheet(i);
                    spreadTree = new DefaultMutableTreeNode(sheet.getTitle());
                    workTree.add(spreadTree);
                }
                for (int i = 0; i < listExtensionAtivacte.length; i++) {
                    String version = listExtensionAtivacte[i][0];
                    String description = listExtensionAtivacte[i][1];
                    versionTreeActivate = new DefaultMutableTreeNode(version);
                    descriptionTreeActivate = new DefaultMutableTreeNode(description);
                    loadedActive.add(versionTreeActivate);
                    loadedActive.add(descriptionTreeActivate);
                }
                
                  for (int i = 0; i < listExtensionDesactivate.length; i++) {
                    String version = listExtensionDesactivate[i][0];
                    String description = listExtensionDesactivate[i][1];
                    versionTreeInactivate= new DefaultMutableTreeNode(version);
                    descriptionTreeInactivate = new DefaultMutableTreeNode(description);
                    loadeInactivate.add(versionTreeInactivate);
                    loadeInactivate.add(descriptionTreeInactivate);
                }
                extensions.add(loadedActive);
                extensions.add(loadeInactivate);
                loadedExtensions.add(extensions);
                ipTree.add(workTree);
                ipTree.add(loadedExtensions);

//                if (map.containsKey(address)) {
//                    root.remove(map.get(address));
//                }
//                map.put(address, ipTree);
                root.add(ipTree);
                // tree.updateUI();
            }

        }
    }

    @Override
    public Object getLastReceivedDTO() {
        return null;
    }

    public void startUDPClient() {
        if (udpClient != null) {
            udpClient = null;
        }
        if (udpClient == null) {
            CommExtension ce = (CommExtension) ExtensionManager.getInstance().getExtension(CommExtension.NAME);
            if (ce != null) {
                try {
                    CleansheetRequestDTO cleanRequest = new CleansheetRequestDTO();
                    udpClient = new CommUDPClientNetwork(cleanRequest, ce.getUDPServerPortNumber(), 1000);
                    udpClient.start();
                } catch (Exception e) {
                    udpClient = null;
                    // Do nothing
                }
            }
        }
    }
}
