/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1140822.fileShare;

import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class ShareFrame extends JFrame implements Observer {

    private UIController controller;
    private JList listFiles;
    private FileSharingController shareController;
    private DefaultListModel model;

    public ShareFrame(UIController controller, ConnectionID connection) {
        super("File share");
        this.setPreferredSize(new Dimension(400, 400));
        this.setSize(400, 400);
        this.controller = controller;
        shareController = new FileSharingController(connection);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createComponents();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void createComponents() {
        listFiles = new JList();
        listFiles.setSize(100, 100);
        listFiles.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {

            }
        });
        model = new DefaultListModel();
        add(listFiles, BorderLayout.CENTER);
        JButton downloadButton = createDownloadButton();
        add(downloadButton,BorderLayout.SOUTH);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof HandlerFileNameListDTO) {
            for (String fileName : ((FileNameListDTO) arg).filesMap().keySet()) {
                if (!model.contains(fileName)) {
                    model.addElement(fileName + "-> "+ ((FileNameListDTO) arg).getConnectionOwner());
                }
            }
            listFiles.setModel(model);
        }
    }

    public JButton createDownloadButton() {
        JButton button = new JButton("Download");
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                shareController.requestFile((String)listFiles.getSelectedValue());
            }

        });
        return button;
    }
}
