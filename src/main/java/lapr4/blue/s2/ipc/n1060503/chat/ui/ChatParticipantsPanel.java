/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1060503.chat.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import lapr4.blue.s2.ipc.n1060503.chat.ChangeUserChatProfileController;
import lapr4.blue.s2.ipc.n1060503.chat.connection.UserChatDTO;
import lapr4.blue.s2.ipc.n1060503.chat.profile.UserChatProfile;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import lapr4.green.s1.ipc.n1150532.comm.ui.ConnectToPeerAction;
import lapr4.green.s1.ipc.n1150657.chat.ControllerConnection;
import lapr4.green.s1.ipc.n1150657.chat.ui.ChatAction;

/**
 * It represents the Panel for the chat.
 *
 * @author Pedro Fernandes
 */
public class ChatParticipantsPanel extends JPanel {

    //Static global variables
    /**
     * The height of the JList.
     */
    private static final int HEIGHT_LIST = 50;

    /**
     * The number of clickings for the mouse event.
     */
    private static final int CLICKING_NUM = 2;

    /**
     * The width for the dimension of the panel.
     */
    private static final int DIMENSION_WIDTH = 130;

    /**
     * The height for the dimension of the panel.
     */
    private static final int DIMENSION_HEIGHT = 336;

    /**
     * The name for the border.
     */
    private static final String BORDER_NAME = "Chat Partipants";
    
    public static Map<String,List> map = new HashMap<>();
    
    /**
     * The button to search peers.
     */
    private JButton btSearch;
    
    /**
     * The button to change profile of user chat.
     */
    private JButton btChangeProfile;

    /**
     * The button to connect to a peer.
     */
    private JButton btMessage;
    
    private UserChatListTable table;
    
    /**
     * The UI Controller.
     */
    private final UIController theController;

    /**
     * The extension.
     */
    private final ChatParticipantsExtension theExtension;
    
    private ChangeUserChatProfileController controllerChangeUser;
    private UserChatProfile ucp;
    /**
     * The ChatPanel constructor.
     *
     * @param uiController The ui controller.
     * @param extension
     */
    public ChatParticipantsPanel(UIController uiController, Extension extension) throws IOException {
        super(new BorderLayout());
        theController = uiController;
        theExtension = (ChatParticipantsExtension) extension;
        ucp = new UserChatProfile();
        controllerChangeUser = new ChangeUserChatProfileController(ucp);
        buildPanel();
        changeComponentName(ChatParticipantsExtension.CHAT_NAME);
        createInteractions();
    }

    /**
     * Private method to change the name of the component. It calls a component
     * method to change it.
     *
     * @param name The name to change it
     */
    private void changeComponentName(String name) {
        setName(name);
    }

    /**
     * Private method to build all the panel.
     */
    private void buildPanel() {
        table = new UserChatListTable();
        JPanel northPanel = buildNorthPanel(table);
        buildChatBorder(table);
        add(northPanel, BorderLayout.CENTER);
        add(createNetworkBottomButtonsPanel(), BorderLayout.SOUTH);
    }

    /**
     * Private method to build the North panel using borderlayout
     *
     * @param chatPanel The panel to be added in the north border layout.
     * @return It returns the JPanel
     */
    private JPanel buildNorthPanel(JPanel chatPanel) {
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(chatPanel, BorderLayout.NORTH);
        return northPanel;
    }

    /**
     * Private method to nuild the border for the chat.
     *
     * @param chatPanel The chat panel to add the border.
     */
    private void buildChatBorder(JPanel chatPanel) {
        TitledBorder border = BorderFactory.createTitledBorder(BORDER_NAME);
        border.setTitleJustification(TitledBorder.CENTER);
        chatPanel.setBorder(border);
    }
    
    /**
     * It builds the southern buttons panel from the network panel.
     *
     * @return It return the panel.
     */
    private JPanel createNetworkBottomButtonsPanel() {
        final String searchBtText = "Turn On / Search";
        final String newMessageBtText = "New Message";
        final String changeProfile = "Change Profile";
        final int allignment = FlowLayout.CENTER;
        final JPanel panel = new JPanel(new GridLayout(1, 2));
        final JPanel p1 = new JPanel(new FlowLayout(allignment));
        final JPanel p2 = new JPanel(new FlowLayout(allignment));
        final JPanel p3 = new JPanel(new BorderLayout());
        btSearch = new JButton(searchBtText);
        btMessage = new JButton(newMessageBtText);
        btChangeProfile = new JButton(changeProfile);
        final JPanel p4 = new JPanel(new FlowLayout(allignment));
        p4.add(btChangeProfile);
        
        p1.add(btSearch);
        p2.add(btMessage);
        
        panel.add(p1);
        panel.add(p2);
        
        p3.add(panel, BorderLayout.CENTER);
        p3.add(p4, BorderLayout.SOUTH);
        
        return p3;
    }
    
    /**
     * It adds the action listeners.
     */
    private void createInteractions() {
        btSearch.addActionListener(new ChatParticipantsPanel.SearchUserAction());
        btMessage.addActionListener(new ChatParticipantsPanel.ConnectAction());
        btChangeProfile.addActionListener(new ChatParticipantsPanel.ChangeProfileAction());
    }
    
    /**
     * The action listener for the search button.
     */
    private class ChangeProfileAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                final ChangeUserChatProfileUI cucp = new ChangeUserChatProfileUI(controllerChangeUser);
            } catch (IOException ex) {
                Logger.getLogger(ChatParticipantsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    /**
     * The action listener for the search button.
     */
    private class SearchUserAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                table.clear();            
                (new ChatParticipantsAction(table, 15310, ucp)).actionPerformed(e);
//                btSearch.setEnabled(false);
            } catch (NullPointerException n){
                JOptionPane.showMessageDialog(null, "Go to Network Tab and click Activate");
            }
        }

    }

    /**
     * The action listener for the connect button.
     */
    private class ConnectAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {   
            if(table.getSelectedRowFile() instanceof UserChatDTO){                
                if(table.getSelectedRowFile().getStatus().equals("OFFLINE")){
                    JOptionPane.showMessageDialog(null, table.getSelectedRowFile().getUserChatProfileNickname()
                            +" is OFFLINE","Error",JOptionPane.INFORMATION_MESSAGE);
                }else{         
                    ConnectionID connection = table.getSelectedRowFile().getConnectionID();
                    if (connection != null) {
                        ControllerConnection.setChatController(connection);
                        (new ConnectToPeerAction(connection)).actionPerformed(e);
                        (new ChatAction(connection, theController)).actionPerformed(e);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "You must select a line!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
