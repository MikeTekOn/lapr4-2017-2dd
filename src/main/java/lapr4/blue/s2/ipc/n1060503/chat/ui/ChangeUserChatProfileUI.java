/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1060503.chat.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import lapr4.blue.s2.ipc.n1060503.chat.ChangeUserChatProfileController;
import lapr4.blue.s2.ipc.n1060503.chat.profile.StatusChatProfile;

/**
 *
 * @author Pedro Fernandes
 */
public class ChangeUserChatProfileUI extends JDialog {

    private JButton changeBtn;
    private JButton changeImageBtn;
    private JButton cancelBtn;
    private JTextField txtUsername;
    private JTextField txtNickname;
    private JRadioButton statusON, statusOFF;
    private JLabel image;

    private ChangeUserChatProfileController controller;

    private static final String USERNAME = "USERNAME: ";
    private static final String NICKNAME = "NICKNAME: ";
    private static final String STATUS = "STATUS: ";

    private static final Dimension LABEL_SIZE = new JLabel(USERNAME).
            getPreferredSize();
    private static final int WIDTHW = 300, LENGTH = 200;

    public ChangeUserChatProfileUI(ChangeUserChatProfileController controller) throws IOException {
        setModal(true);
        setTitle("Change User Chat Profile");

        this.controller = controller;

        add(createComponents());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancel();
            }
        });
        pack();
        setResizable(false);
        setMinimumSize(new Dimension(WIDTHW, LENGTH));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * create all ui panels
     *
     * @return all ui panels
     * @throws UnknownElementException to be caught
     */
    private JPanel createComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(createPanelImage(), BorderLayout.NORTH);
        panel.add(createPanelCenter(), BorderLayout.CENTER);
        panel.add(createPanelButons(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createPanelImage() {
        JPanel panel = new JPanel();

        image = new JLabel(controller.getIcon());
        panel.add(image);

        JPanel p = new JPanel();       
        p.add(createButtonChangeImageBtn());
        
        panel.add(createButtonChangeImageBtn());

        return panel;
    }

    private JPanel createPanelCenter() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel p = new JPanel(new GridLayout(4, 1));

        int aux = 20;
        txtUsername = new JTextField(aux);
        txtUsername.requestFocusInWindow();
        txtUsername.setText("" + controller.getUsername());
        txtUsername.setEditable(false);

        txtNickname = new JTextField(aux);
        txtNickname.setText("" + controller.getNickname());

        statusON = new JRadioButton("ONLINE");
        statusON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (statusON.isSelected()) {
                    statusOFF.setSelected(false);
                }
            }
        });

        statusOFF = new JRadioButton("OFFLINE");
        statusOFF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (statusOFF.isSelected()) {
                    statusON.setSelected(false);
                }
            }
        });

        if (controller.getStatus().equals("ONLINE")) {
            statusON.setSelected(true);
        }
        if (controller.getStatus().equals("OFFLINE")) {
            statusOFF.setSelected(true);
        }

        p.add(createPanelLabelText(USERNAME, txtUsername));
        p.add(createPanelLabelText(NICKNAME, txtNickname));
        p.add(createPanelLabelRadioBtn(STATUS, statusON, statusOFF));

        panel.add(p, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createPanelLabelText(String label1, JTextField text) {
        JLabel lb1 = new JLabel(label1, JLabel.RIGHT);
        lb1.setPreferredSize(LABEL_SIZE);

        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

        p.add(lb1);
        p.add(text);

        return p;
    }

    private JPanel createPanelLabelRadioBtn(String label1, JRadioButton radiobtn1, JRadioButton radiobtn2) {
        JLabel lb1 = new JLabel(label1, JLabel.RIGHT);
        lb1.setPreferredSize(LABEL_SIZE);

        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

        p.add(lb1);
        p.add(radiobtn1);
        p.add(radiobtn2);

        return p;
    }

    /**
     * create panel for the buttons
     *
     * @return panel for the buttons
     */
    private JPanel createPanelButons() {

        FlowLayout l = new FlowLayout();
        l.setVgap(5);

        JPanel p = new JPanel(l);

        p.setBorder(BorderFactory.createTitledBorder("Options:"));
        JButton bt1 = createButtonChange();
        JButton bt2 = createButtonCancel();

        getRootPane().setDefaultButton(bt1);

        p.add(bt1);
        p.add(bt2);

        return p;
    }

    /**
     * create Apply Button
     *
     * @return Apply Button
     */
    private JButton createButtonChange() {
        changeBtn = new JButton("Change");
        changeBtn.setToolTipText("Change user chat profile");
        changeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    apply();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid data!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return changeBtn;
    }

    /**
     * create Apply Button
     *
     * @return Apply Button
     */
    private JButton createButtonChangeImageBtn() {
        changeImageBtn = new JButton("Change Image");
        changeImageBtn.setToolTipText("Change image user chat profile");
        changeImageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final ChooseImageUI choose = new ChooseImageUI(controller);
                    image.setIcon(controller.getIcon());
                } catch (IOException ex) {
                    Logger.getLogger(ChangeUserChatProfileUI.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid file!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return changeImageBtn;
    }

    /**
     * create Cancel Button
     *
     * @return Cancel Button
     */
    private JButton createButtonCancel() {
        cancelBtn = new JButton("Cancel");
        cancelBtn.setToolTipText("Back to previous screen");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });

        return cancelBtn;
    }

    /**
     * execute when Apply Button is cliked
     */
    private void apply() throws IOException {

        boolean flag = true;
        if (statusON.isSelected()) {
            flag = controller.changeInfo(txtNickname.getText(), StatusChatProfile.ONLINE);
        }
        if (statusOFF.isSelected()) {
            flag = controller.changeInfo(txtNickname.getText(), StatusChatProfile.OFFLINE);
        }
        if (flag) {
            JOptionPane.showMessageDialog(
                    null,
                    "User Chat Profile Changed with sucess!",
                    "Change User Chat Profile",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid data!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * execute when Cancel Button is cliked
     */
    private void cancel() {
        dispose();
    }

}
