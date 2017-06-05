/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150657.chat.ui;

import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import lapr4.green.s1.ipc.n1150657.chat.ControllerConnection;

/**
 * It represents the New message frame (the first message)
 *
 * @author Sofia
 */
public class NewMessageFrame extends JFrame {

    private final UIController controller;
    private static ChatController chat;
    private JTextArea textArea;
    private String text;

    public NewMessageFrame(UIController controller, ConnectionID connection) {
        super("Chat");
        this.controller = controller;
        chat = ControllerConnection.getChatController();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createComponents();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static ChatController getChat() {
        return chat;
    }

    private void createComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        textArea = new JTextArea(20, 45);

        textArea.setEditable(true);
        JScrollPane scroll = new JScrollPane(textArea);

        panel.add(scroll);

        add(panel, BorderLayout.NORTH);
        JButton bSend = new JButton("Send");

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(bSend);
        add(panel2);
        bSend.addActionListener(new SendMessageAction());
    }

    private class SendMessageAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            text = textArea.getText();
            chat.sendMessage(text);
            dispose();
        }
    }

}
