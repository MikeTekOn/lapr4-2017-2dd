package lapr4.red.s3.ipc.n1150385.groupchat.ui;

import csheets.ext.Extension;
import csheets.ext.ExtensionManager;
import lapr4.blue.s2.ipc.n1060503.chat.ui.ChatParticipantsExtension;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.*;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionIDImpl;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;
import lapr4.red.s3.ipc.n1150385.groupchat.ChatRoom;
import lapr4.red.s3.ipc.n1150385.groupchat.ChatRoomClient;
import lapr4.red.s3.ipc.n1150385.groupchat.connection.CommUDPClient;
import lapr4.red.s3.ipc.n1150385.groupchat.dto.*;
import lapr4.red.s3.ipc.n1150385.groupchat.exceptions.ChatRoomConnectionRefusedException;
import lapr4.red.s3.ipc.n1150385.groupchat.ext.ChatRoomExtension;
import lapr4.red.s3.ipc.n1150385.groupchat.util.InetUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class ChatRoomPanel extends JPanel implements CommHandler {
    /**
     * The name for the border.
     */
    private static final String BORDER_NAME = "Chat Rooms";

    private CommUDPClient worker;
    private ChatRoomsTableModel chatRoomsTableModel;
    private JTable chatRoomsTable;

    /**
     * The button to create a chat room
     */
    private JButton btnCreateChatRoom;

    /**
     * The button to join a chat room
     */
    private JButton btnJoinChatRoom;

    /**
     * The button to search for chat rooms
     */
    private JButton btnSearchChatRooms;

    /**
     * The button to stop the search of chat rooms
     */
    private JButton btnStopSearchChatRooms;

    /**
     * The extension.
     */
    private final ChatRoomExtension theExtension;

    /**
     * The ChatRoomPanel constructor.
     *
     * @param extension
     */
    public ChatRoomPanel(Extension extension) throws IOException {
        super(new BorderLayout());
        theExtension = (ChatRoomExtension) extension;
        buildPanel();
        setName(ChatRoomExtension.CHAT_NAME);
        CommUDPServer.getServer().addHandler(UserChatRoomRequestDTO.class, this);
        CommTCPServer.getServer().addHandler(ChatRoomMessageDTO.class, this);
    }

    /**
     * Private method to build all the panel.
     */
    private void buildPanel() {
        chatRoomsTableModel = new ChatRoomsTableModel();
        chatRoomsTable = new JTable(chatRoomsTableModel);
        JScrollPane pnlList = new JScrollPane(chatRoomsTable);

        JPanel pnlButtons = new JPanel(new GridLayout(2, 2, 5, 5));
        btnCreateChatRoom = new JButton("Create");
        btnJoinChatRoom = new JButton("Join");
        btnSearchChatRooms = new JButton("Search");
        btnStopSearchChatRooms = new JButton("Stop Search");

        btnCreateChatRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                createChatRoom();
            }
        });

        btnJoinChatRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                joinSelectedChatRoom();
            }
        });

        btnSearchChatRooms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startSearch();
            }
        });

        btnStopSearchChatRooms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                stopSearch();
            }
        });

        btnStopSearchChatRooms.setEnabled(false);

        pnlButtons.add(btnCreateChatRoom);
        pnlButtons.add(btnJoinChatRoom);
        pnlButtons.add(btnSearchChatRooms);
        pnlButtons.add(btnStopSearchChatRooms);

        setLayout(new BorderLayout());
        add(pnlList, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);
    }

    private void createChatRoom(){
        if(!CommUDPServer.getServer().isAlive())
            return;

        new UIChatRoomCreate();
        stopSearch();
    }

    private void joinSelectedChatRoom(){
        if(!CommUDPServer.getServer().isAlive())
            return;

        int index = chatRoomsTable.getSelectedRow();
        if(index != -1){
            ConnectionID id = chatRoomsTableModel.getChatRoomConnectionID(index);
            String name = chatRoomsTableModel.getChatRoomName(index);
            try {
                ChatRoom chatRoom = new ChatRoomClient(id, name);
                new UIChatRoom(chatRoom);
                stopSearch();
            } catch (ChatRoomConnectionRefusedException e) {
                JOptionPane.showMessageDialog(this, "Connection to chat room refused.", "Connection refused", JOptionPane.ERROR_MESSAGE);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void startSearch(){
        if(!CommUDPServer.getServer().isAlive())
            return;

        try{
            this.chatRoomsTableModel.clear();

            CommExtension ce = (CommExtension) ExtensionManager.getInstance().getExtension(CommExtension.NAME);
            InetAddress ownAddress = InetUtils.getOwnAddress();
            if(ownAddress != null){
                ChatRoomsRequestDTO request = new ChatRoomsRequestDTO(ownAddress, ce.getUDPServerPortNumber());
                worker = new CommUDPClient(request, ce.getUDPServerPortNumber(), 99999);
                CommUDPServer.getServer().addHandler(ChatRoomsResponseDTO.class, this);

                this.worker.start();
                btnSearchChatRooms.setEnabled(false);
                btnStopSearchChatRooms.setEnabled(true);
            }
        } catch (IllegalThreadStateException | NullPointerException e){
            e.printStackTrace();
        }
    }

    private void stopSearch(){
        if(!CommUDPServer.getServer().isAlive())
            return;

        if(this.worker != null && this.worker.isAlive()){
            this.worker.stopExecution();
        }
        btnSearchChatRooms.setEnabled(true);
        btnStopSearchChatRooms.setEnabled(false);
    }

    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        if(dto != null){
            CommExtension ce = (CommExtension) ExtensionManager.getInstance().getExtension(CommExtension.NAME);
            if(dto instanceof PacketEncapsulatorDTO) {
                PacketEncapsulatorDTO obj = (PacketEncapsulatorDTO) dto;
                Object o = obj.getDTO();
                if (o != null) {
                    if (o instanceof ChatRoomsResponseDTO) {
                        ChatRoomsResponseDTO original = (ChatRoomsResponseDTO) o;
                        System.err.println("=== CHAT ROOM ===: Received chat room response from " + obj.getPacket().getAddress());
                        chatRoomsTableModel.add(original);
                        repaint();
                    } else if (o instanceof UserChatRoomRequestDTO) {
                        System.err.println("=== CHAT ROOM ===: Received chat room client request from " + obj.getPacket().getAddress());
                        UserChatRoomResponseDTO response = new UserChatRoomResponseDTO(System.getProperty("user.name"));
                        CommUDPClient worker = new CommUDPClient(response, ce.getUDPServerPortNumber(), -1);
                        worker.run();
                    }
                }
            } else if(dto instanceof SocketEncapsulatorDTO){
                SocketEncapsulatorDTO obj = (SocketEncapsulatorDTO) dto;
                Object o = obj.getDTO();
                if(o != null && o instanceof ChatRoomMessageDTO){
                    ChatRoomMessageDTO myDTO = (ChatRoomMessageDTO) o;
                    String chatRoomName = myDTO.getRoomName();
                    if(myDTO.getType() == ChatRoomMessageDTO.MessageType.INVITE){
                        int res = JOptionPane.showConfirmDialog(this,
                                "You were invited to join chat room [" + obj.getSocket().getInetAddress() + "/" +
                                        chatRoomName + "]. Do you accept?", "INVITATION", JOptionPane.OK_OPTION);
                        if(res == JOptionPane.OK_OPTION){
                            ConnectionIDImpl id = new ConnectionIDImpl(obj.getSocket().getInetAddress(),
                                    ce.getTCPServerPortNumber());
                            ChatRoom chatRoom = null;
                            try {
                                chatRoom = new ChatRoomClient(id, chatRoomName);
                                new UIChatRoom(chatRoom);
                                stopSearch();
                            } catch (IOException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Object getLastReceivedDTO() {
        return null;
    }
}
