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
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
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
    private DefaultTableModel tableModel;
    private JTable table;

    public ShareFrame(UIController controller) {
        super("File share");
        this.setPreferredSize(new Dimension(400, 400));
        this.setSize(400, 400);
        this.controller = controller;
     

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createComponents();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void createComponents() {
        table = new JTable(3, 3);
        table.setSize(100, 100);

        listFiles = new JList();
        listFiles.setSize(100, 100);
        listFiles.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {

            }
        });
        tableModel = new DefaultTableModel(3,3);
        model = new DefaultListModel();
        add(table, BorderLayout.CENTER);
        JButton downloadButton = createDownloadButton();
        add(downloadButton, BorderLayout.SOUTH);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof HandlerFileNameListDTO) {

            for (String fileName : ((FileNameListDTO) arg).filesMap().keySet()) {
//                if (!tableModel.(fileName + "-> " + ((FileNameListDTO) arg).getConnectionOwner())) {
                Object[] rowData = new Object[3];
                rowData[0] = fileName;
               rowData[1] = ((FileNameListDTO) arg).connID();
                rowData[2] = (((FileNameListDTO) arg).filesMap().get(fileName).intValue()) +" bytes";
                tableModel.addRow(rowData);
                //model.addElement(fileName + "-> " + ((FileNameListDTO) arg).getConnectionOwner());
                //  }
            }
            table.setModel(tableModel);
        }
    }

    public JButton createDownloadButton() {
        JButton button = new JButton("Download");
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                shareController = new FileSharingController((ConnectionID)tableModel.getValueAt(table.getSelectedRow(),1));
                shareController.requestFile((String)tableModel.getValueAt(table.getSelectedRow(),0));
            }

        });
        return button;
    }
}
