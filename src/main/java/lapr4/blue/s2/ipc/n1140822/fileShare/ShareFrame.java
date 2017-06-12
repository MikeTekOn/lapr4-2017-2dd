package lapr4.blue.s2.ipc.n1140822.fileShare;

import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class ShareFrame extends JFrame implements Observer {

    /**
     * The UI controller.
     */
    private UIController controller;

    /**
     * The list files.
     */
    private JList listFiles;

    /**
     * The share controller.
     */
    private FileSharingController shareController;

    /**
     * The default list model.
     */
    private DefaultListModel model;

    /**
     * The default table model.
     */
    private DefaultTableModel tableModel;

    /**
     * The default table model for downloads.
     */
    private DefaultTableModel dlTableModel;

    /**
     * The table of shares.
     */
    private JTable table;

    /**
     * The downloads table.
     */
    private JTable dlTable;

    /**
     * The download button.
     */
    private JButton button;

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

    /**
     * Creates the components.
     */
    private void createComponents() {
        JMenu menu = new JMenu("Configuration");
        JMenuItem itemChangeDownload = new JMenuItem("Change download folder");
        JMenuItem itemChangeShared = new JMenuItem("Change shared folder");
        JTabbedPane tabs = new JTabbedPane();
        itemChangeDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.showDialog(ShareFrame.this, "Select file");
                try {

                    ShareConfiguration.changeDownloadFolder(fileChooser.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(ShareFrame.this, "Folder changed with success.");

                } catch (IOException | NullPointerException ex) {
                    JOptionPane.showMessageDialog(ShareFrame.this, "Error selecting download folder.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        itemChangeShared.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.showDialog(ShareFrame.this, "Select file");
                try {

                    ShareConfiguration.changeSharedFolder(fileChooser.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(ShareFrame.this, "Folder changed with success.");

                } catch (IOException | NullPointerException ex) {
                    JOptionPane.showMessageDialog(ShareFrame.this, "Error selecting download folder.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        menu.add(itemChangeDownload);
        menu.add(itemChangeShared);
        String[] columnNames = {"File name", "Host", "File size"};
        String[] columnNamesDL = {"File name", "Host", "File size", "State"};
        table = new JTable();
        dlTable = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setSize(100, 100);
        dlTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        dlTable.setSize(100, 100);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button.setEnabled(true);
            }

        });
        dlTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button.setEnabled(false);
            }

        });
        dlTableModel = new DefaultTableModel(columnNamesDL, 0);
        tableModel = new DefaultTableModel(columnNames, 0);
        table.setModel(tableModel);
        dlTable.setModel(dlTableModel);

        try {
            fillDownloadTable();
        } catch (IOException ex) {
            Logger.getLogger(ShareFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JScrollPane scrollPane = new JScrollPane(table);

        JScrollPane scrollPane2 = new JScrollPane(dlTable);

        tabs.add(scrollPane);
        tabs.add(scrollPane2);
        tabs.setTitleAt(0, "Files shared with you");
        tabs.setTitleAt(1, "Downloaded files");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        add(menuBar, BorderLayout.NORTH);
        add(tabs);
        button = createDownloadButton();
        button.setEnabled(false);
        add(button, BorderLayout.SOUTH);

    }

    /**
     * Each time a new shared file update is received.
     *
     * @param o the observed object (dtohandler)
     * @param arg the argument sent(dto)
     */
    @Override
    public void update(Observable o, Object arg) {

        boolean update = true;
        if (o instanceof HandlerFileNameListDTO) {

            for (String fileName : ((FileNameListDTO) arg).filesMap().keySet()) {
                for (int i = 0; i < tableModel.getDataVector().size(); i++) {
                    if (tableModel.getValueAt(i, 0).equals(fileName) && tableModel.getValueAt(i, 1).equals(((FileNameListDTO) arg).connID())) {
                        if (tableModel.getValueAt(i, 2).equals(((FileNameListDTO) arg).filesMap().get(fileName) + " bytes")) {
                            update = false;
                        } else {
                            tableModel.removeRow(i);
                            break;
                        }
                    }
                }
                if (update) {
                    Object[] rowData = new Object[3];
                    rowData[0] = fileName;
                    rowData[1] = ((FileNameListDTO) arg).connID();
                    rowData[2] = (((FileNameListDTO) arg).filesMap().get(fileName)) + " bytes";
                    tableModel.addRow(rowData);

                    for (int row = 0; row < dlTable.getRowCount(); row++) {
                        if (dlTableModel.getValueAt(row, 0).equals(fileName) && dlTableModel.getValueAt(row, 1).equals("/" + ((FileNameListDTO) arg).connID().toString())) {
                            if (!dlTableModel.getValueAt(row, 2).equals(rowData[2].toString())) {
                                dlTableModel.setValueAt("OUTDATED", row, 3);
                            }
                        }
                    }
                }
                update = true;
            }
            table.setModel(tableModel);

        }
        if (o instanceof HandlerFileDTO) {
            JOptionPane.showMessageDialog(ShareFrame.this, "File " + (String) tableModel.getValueAt(table.getSelectedRow(), 0) + " downloaded with success.");
        }

    }

    /**
     * Obtains a download JButton.
     *
     * @return the download JButton
     */
    public JButton createDownloadButton() {
        JButton button = new JButton("Download");

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() != -1) {
                    try {
                        shareController = new FileSharingController((ConnectionID) tableModel.getValueAt(table.getSelectedRow(), 1));
                        shareController.requestFile((String) tableModel.getValueAt(table.getSelectedRow(), 0));
                        fillDownloadTable();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ShareFrame.this, "Error occured when downloading the file", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(ShareFrame.this, "Please select a file to download", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        return button;
    }

    /**
     * Fills the download table the filenames, size, hosts and states.
     *
     * @throws IOException throws an IO exception of exception occurs on input
     * or output
     */
    public void fillDownloadTable() throws IOException {
        dlTableModel.setRowCount(0);
        Map<String, Integer> tempMap = new LinkedHashMap<>();
        File folder = new File(ShareConfiguration.getDownloadFolder());
        folder.mkdirs();
        File[] files = folder.listFiles();
        for (File file : files) {
            UserDefinedFileAttributeView view = Files.getFileAttributeView(file.toPath(), UserDefinedFileAttributeView.class);
            ByteBuffer buf = ByteBuffer.allocate(view.size("host"));
            view.read("host", buf);
            buf.flip();
            String realHost = Charset.defaultCharset().decode(buf).toString();
            String fileName = file.getName();
            int fileSize = Files.readAllBytes(file.toPath()).length;
            Object[] rowData = new Object[4];
            rowData[0] = fileName;
            rowData[1] = realHost;
            rowData[2] = fileSize + " bytes";
            rowData[3] = "UP-TO-DATE";
            dlTableModel.addRow(rowData);
            dlTable.setModel(dlTableModel);
        }
    }
}
