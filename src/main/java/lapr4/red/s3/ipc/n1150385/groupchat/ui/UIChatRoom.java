package lapr4.red.s3.ipc.n1150385.groupchat.ui;

import lapr4.red.s3.ipc.n1150385.groupchat.ChatRoom;
import lapr4.red.s3.ipc.n1150385.groupchat.events.ChatRoomEvent;
import lapr4.red.s3.ipc.n1150385.groupchat.events.DisconnectEvent;
import lapr4.red.s3.ipc.n1150385.groupchat.events.MessageEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by pyska on 6/14/17.
 */
public class UIChatRoom extends JFrame implements Observer {

    private ChatRoom room;
    private JTextArea txtArea;
    private JTextField txtField;

    public UIChatRoom(ChatRoom room) throws HeadlessException {
        super();
        this.room = room;
        this.room.addObserver(this);

        setTitle(room.getName());
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                closeWindow();
            }
        });
    }

    private void initComponents() {
        txtArea = new JTextArea(16, 40);
        txtArea.setEditable(false);
        JScrollPane chatPanel = new JScrollPane(txtArea);
        chatPanel.setPreferredSize(txtArea.getPreferredSize());
        chatPanel.setBorder(new TitledBorder(new LineBorder(new Color(75, 0, 130)), "Chat"));

        JPanel pnlBottom = new JPanel(new BorderLayout());
        txtField = new JTextField(40);
        txtField.setBorder(new TitledBorder(new LineBorder(new Color(75, 0, 130)), "Message"));
        JButton btnSend = new JButton("Send");

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    room.sendMessage(txtField.getText(), room.getOwner().getAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        pnlBottom.add(txtField, BorderLayout.CENTER);
        pnlBottom.add(btnSend, BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(chatPanel, BorderLayout.CENTER);
        add(pnlBottom, BorderLayout.SOUTH);

        setJMenuBar(new UIChatMenuBar(room));
        pack();
    }

    private void closeWindow() {
        this.room.terminate();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o != null && o instanceof ChatRoomEvent) {
            if (o instanceof MessageEvent) {
                MessageEvent evt = (MessageEvent) o;
                txtArea.setText(txtArea.getText() + "\n" + evt.getMessage());
                repaint();
            } else if (o instanceof DisconnectEvent) {
                if (!this.room.amOwner()) {
                    JOptionPane.showMessageDialog(this, "You have been disconnected from the chat room.",
                            "Disconnect", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Everyone was disconnected from the server.",
                            "Disconnect", JOptionPane.INFORMATION_MESSAGE);
                }
                this.dispose();
            }
        }
    }
}
