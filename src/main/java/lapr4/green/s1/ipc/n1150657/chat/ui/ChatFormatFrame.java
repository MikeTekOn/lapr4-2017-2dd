/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150657.chat.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import lapr4.green.s1.ipc.n1150657.chat.ControllerConnection;

/**
 * The concept of the chat format.
 *
 * @author Sofia
 */
public class ChatFormatFrame extends JFrame {

    private JTextArea sendTextArea;
    private Vector<String> list;

    public ChatFormatFrame(Vector list) {
        super("Chat");
        this.list = list;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createComponents();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void createComponents() {
        JScrollPane scrollPane1 = new JScrollPane();
        JTextArea chatTextArea = new JTextArea();
        JScrollPane scrollPane2 = new JScrollPane();
        sendTextArea = new JTextArea();
        JButton sendButton = new JButton();

        chatTextArea.setEditable(false);
        for (String s : list) {
            chatTextArea.append(s + "\n");
        }
        chatTextArea.setColumns(20);
        chatTextArea.setRows(5);
        scrollPane1.setViewportView(chatTextArea);

        sendTextArea.setColumns(20);
        sendTextArea.setRows(5);
        sendTextArea.setEditable(true);
        scrollPane2.setViewportView(sendTextArea);

        sendButton.setText("Send");
        sendButton.addActionListener(new SendMessageAction());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane1)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(scrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(scrollPane2)
                                        .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
        );

    }

    private class SendMessageAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String text = sendTextArea.getText();
            if (text == null) {
                JOptionPane.showMessageDialog(null, "Error");
            } else {
                ChatController chat = ControllerConnection.getChatController();
                if (chat != null) {
                    chat.sendMessage(text);
                    ChatPanel.map.get(chat.getConnection().getAddress().getHostName()).add("Me: " + text);
                    dispose();
                } else {
                    System.out.println("Error");
                }

            }

        }
    }
}
