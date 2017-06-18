package lapr4.red.s3.ipc.n1150385.groupchat.ui;

import lapr4.red.s3.ipc.n1150385.groupchat.ChatRoom;
import lapr4.red.s3.ipc.n1150385.groupchat.ChatRoomServer;
import lapr4.red.s3.ipc.n1150385.groupchat.exceptions.ChatRoomNameAlreadyExistsException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class UIChatRoomCreate extends JDialog {

    private JTextField txfName;
    private boolean isPrivate = false;

    public UIChatRoomCreate() throws HeadlessException {
        super();

        initComponents();
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);
    }

    private void initComponents(){

        // ==== ROOM NAME ====

        JPanel pnlName     = new JPanel(new BorderLayout());
        JLabel lblName     = new JLabel("Room Name:");
        txfName            = new JTextField("My Chat Room", 30);

        pnlName.add(lblName, BorderLayout.WEST);
        pnlName.add(txfName, BorderLayout.EAST);



        // ==== PRIVATE AND PUBLIC RADIO BUTTONS ====

        JPanel pnlPrivate       = new JPanel(new GridLayout(1, 2, 5, 5));
        ButtonGroup btnGroup    = new ButtonGroup();
        JRadioButton btnPrivate = new JRadioButton("Private", false);
        JRadioButton btnPublic  = new JRadioButton("Public", true);

        btnGroup.add(btnPrivate);
        btnGroup.add(btnPublic);
        pnlPrivate.add(btnPrivate, BorderLayout.WEST);
        pnlPrivate.add(btnPublic,  BorderLayout.EAST);

        btnPrivate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setPrivate(true);
            }
        });

        btnPublic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setPrivate(false);
            }
        });



        // ==== CONFIRM AND CANCEL BUTTONS

        JPanel pnlActions  = new JPanel(new GridLayout(1, 2, 5, 5));
        JButton btnConfirm = new JButton("Create");
        JButton btnCancel  = new JButton("Cancel");

        pnlActions.add(btnConfirm, BorderLayout.WEST);
        pnlActions.add(btnCancel,  BorderLayout.EAST);

        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                createChatRoom();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                closePanel();
            }
        });



        // ==== JOINING EVERYTHING TOGETHER ====

        JPanel pnlMain = new JPanel(new BorderLayout());

        pnlMain.add(pnlName,    BorderLayout.NORTH);
        pnlMain.add(pnlPrivate, BorderLayout.CENTER);
        pnlMain.add(pnlActions, BorderLayout.SOUTH);
        pnlMain.setBorder(new EmptyBorder(10, 10, 10, 10));

        add(pnlMain);

        pack();
    }

    private void closePanel(){
        dispose();
    }

    private void setPrivate(boolean value){
        this.isPrivate = value;
    }

    private void createChatRoom(){
        String chatRoomName = txfName.getText();

        try {
            System.out.println("=== CHAT ROOM ===: Creating chat room.");
            ChatRoom room = new ChatRoomServer(this.isPrivate, chatRoomName);
            System.out.println("=== CHAT ROOM ===: Chat room created.");
            new UIChatRoom(room);
            this.dispose();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (ChatRoomNameAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
