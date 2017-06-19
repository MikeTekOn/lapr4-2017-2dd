/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150657.chat.ui;

import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import lapr4.green.s1.ipc.n1150657.chat.ext.ChatExtension;

/**
 * It represents the Panel for the chat.
 *
 * @author Sofia Gonçalves (1150657)
 */
public class ChatPanel extends JPanel {

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
    private static final String BORDER_NAME = "Chat";

    /**
     * The controller.
     */
    public static Vector listMessage;
    
    public static Map<String, List> map = new HashMap<>();
    
    //private JTextArea textField = new JTextArea();
    /**
     * The ChatPanel constructor.
     *
     * @param uiController The ui controller.
     */
    public ChatPanel(UIController uiController) {
        super(new BorderLayout());
        buildPanel();
        changeComponentName(ChatExtension.CHAT_NAME);
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
        JPanel chatPanel = buildChatPanel();
        JPanel northPanel = buildNorthPanel(chatPanel);
        buildChatBorder(chatPanel);
        add(northPanel, BorderLayout.NORTH);
    }

    /**
     * Private method to build the chat panel.
     *
     * @return It returns the JPanel.
     */
    private JPanel buildChatPanel() {
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.PAGE_AXIS));
        chatPanel.setPreferredSize(new Dimension(DIMENSION_WIDTH, DIMENSION_HEIGHT));
        chatPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        listMessage = new Vector();

        JList list = new JList(listMessage);
        
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList) e.getSource();
                if (e.getClickCount() == CLICKING_NUM) {
                    if(list.getSelectedValue() != null){
                        ChatFormatFrame chatFormatFrame = new ChatFormatFrame(getConversation(list.getSelectedValue().toString()));
                    }
                }
            }
        });
        list.setFixedCellHeight(HEIGHT_LIST);
        JScrollPane scrollPane = new JScrollPane(list);
        chatPanel.add(scrollPane);
        return chatPanel;
    }
    
    private Vector getConversation(String component){
        List list = map.get(component);
        return new Vector(list);
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
}
