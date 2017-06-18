package lapr4.red.s3.ipc.n1150385.groupchat.ui;

import csheets.ext.ExtensionManager;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.CommExtension;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPServer;
import lapr4.green.s1.ipc.n1150532.comm.CommUDPServer;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;
import lapr4.red.s3.ipc.n1150385.groupchat.ChatRoomServer;
import lapr4.red.s3.ipc.n1150385.groupchat.connection.CommUDPClient;
import lapr4.red.s3.ipc.n1150385.groupchat.dto.UserChatRoomRequestDTO;
import lapr4.red.s3.ipc.n1150385.groupchat.dto.UserChatRoomResponseDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class UIChatRoomInvite extends JDialog implements CommHandler {

    private static final int TIMEOUT = 5000;

    private final ChatRoomServer server;

    private CommUDPClient worker;

    private ChatRoomClientsTableModel tableModel;

    private JTable table;

    public UIChatRoomInvite(ChatRoomServer server){
        this.server = server;
        UserChatRoomRequestDTO request = new UserChatRoomRequestDTO();
        CommExtension ce = (CommExtension) ExtensionManager.getInstance().getExtension(CommExtension.NAME);
        worker = new CommUDPClient( request, ce.getUDPServerPortNumber(), TIMEOUT);
        CommUDPServer.getServer().addHandler(UserChatRoomResponseDTO.class, this);
        worker.start();

        initComponents();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                closeWindow();
            }
        });
        setVisible(true);
    }

    private void initComponents(){
        tableModel = new ChatRoomClientsTableModel();
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(300, 300));
        JScrollPane tablePanel = new JScrollPane(table);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JButton btnInvite = new JButton("Invite");
        JButton btnClose = new JButton("Close");

        btnInvite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                inviteSelectedClient();
            }
        });

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                closeWindow();
            }
        });

        buttonPanel.add(btnInvite);
        buttonPanel.add(btnClose);

        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    private void inviteSelectedClient(){
        int index = table.getSelectedRow();
        if(index != -1){
            server.inviteClient(tableModel.getAddress(index));
            JOptionPane.showMessageDialog(this, "Invitation to chat room [" + server.getName() +
                    "] to client [" + tableModel.getClientName(index) + "] sent.", "Invitation sent",
                    JOptionPane.INFORMATION_MESSAGE);
            tableModel.remove(index);
        }
    }

    private void closeWindow(){
        CommTCPServer.getServer().removeHandler(UserChatRoomResponseDTO.class, this);
        worker.stopExecution();
        this.dispose();
    }

    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        if(dto != null && dto instanceof PacketEncapsulatorDTO){
            PacketEncapsulatorDTO o = (PacketEncapsulatorDTO) dto;
            Object obj = o.getDTO();
            if(obj != null && obj instanceof UserChatRoomResponseDTO){
                UserChatRoomResponseDTO myDto = (UserChatRoomResponseDTO) obj;
                if(!server.isConnected(o.getPacket().getAddress())){
                    tableModel.add(o.getPacket().getAddress(), myDto.getClientName());
                    table.repaint();
                }
            }
        }
    }

    @Override
    public Object getLastReceivedDTO() {
        return null;
    }
}
