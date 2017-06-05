/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150901.search.workbook.ui.table;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class InstanceTableUI extends JFrame {

    /**
     * The table itself.
     */
    private JTable table;

    /**
     * The scrol for the table.
     */
    private JScrollPane scrollPane;

    /**
     * The "Select" button.
     */
    private JButton button1;

    /**
     * The "Cancel" button.
     */
    private JButton button2;

    /**
     * The controller of the table.
     */
    private final InstanceTableController theController;

    /**
     * The constructor of the table UI.
     */
    public InstanceTableUI() {
        super();
        theController = new InstanceTableController();
        createUserInterface();
    }

    /**
     * It builds the graphical user interface of the table.
     */
    private void createUserInterface() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        panel.add(createTable(), c);

        c.weightx = 2.0;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(createButtonPanel(), c);

        add(panel);

        pack();
        setTitle("Choose Instance");
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setAlwaysOnTop(true);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        Dimension dim = new Dimension(50, 30);

        button1 = new JButton("Select");
        button1.setPreferredSize(dim);
        button1.addActionListener(new Select());
        button1.setEnabled(false);
        button1.setMargin(new Insets(0, 0, 0, 0));

        button2 = new JButton("Cancel");
        button2.setPreferredSize(dim);
        button2.addActionListener(new Exit());
        button2.setMargin(new Insets(0, 0, 0, 0));

        panel.add(button1);
        panel.add(button2);

        return panel;
    }

    /**
     * It builds the JTable.
     */
    private JScrollPane createTable() {
        final int bottomUpPadding = 5;
        final int sidesPadding = 20;
        final Dimension size = new Dimension(400, 125);

        setLayout(new GridLayout(1, 1));
        table = new JTable(theController);
        table.setAutoCreateRowSorter(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(false);
        table.setPreferredScrollableViewportSize(size);
        table.getSelectionModel().addListSelectionListener(new List());
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(bottomUpPadding, sidesPadding, bottomUpPadding, sidesPadding));
        return scrollPane;
    }

    private void buildSummaryTable(Workbook wbFound) {
        JDialog dialog = new JDialog();
        String[] columnNames = {"SpreadSheet Name", "Cell 1", "Cell 2", "Cell 3"};
        String[][] data = new String[50][50];

        Iterator<Spreadsheet> it = wbFound.iterator();
        int i = 0;
        int j = 0;
        while (it.hasNext()) {
            data[0][i] = it.next().getTitle();
            i++;
            Address address1 = new Address(0, 0);
            Address address2 = new Address(52, 106);
            Set<Cell> cells = it.next().getCells(address1, address2);
            for (Cell c : cells){
                if (!c.getContent().isEmpty()){
                    data[j][i] = c.getContent();
                    j++;
                    if (j == 3){
                        break;
                    }
                }
            }
        }

        JTable summaryTable = new JTable(data, columnNames);
        JScrollPane scroll = new JScrollPane(summaryTable);
        dialog.add(scroll);
        dialog.setLocationRelativeTo(null);
        dialog.setMinimumSize(new Dimension(getWidth(), getHeight()));
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        dialog.setAlwaysOnTop(true);
    }

    /**
     * It provides the instance matching the selected row.
     *
     * @return It returns the instance associated with the selected row.
     */
    public ConnectionID getSelectedRowFile() {
        return theController.provideInstance(table.getSelectedRow());
    }

    /**
     * An inner class that implements the action listener for the select button.
     */
    private class Select implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.setAlwaysOnTop(true);
            String name = JOptionPane.showInputDialog(frame, "What is the name of the workbook that you want to search?");
            Workbook wbFound = theController.searchWorbook(name, getSelectedRowFile());
            if (wbFound != null) {
                buildSummaryTable(wbFound);
            } else {
                JOptionPane.showMessageDialog(frame, "The workbook was not found!", "Information",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * An inner class that implements the action listener for the select button.
     */
    private class Exit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    /**
     * An inner class that implements the list selection listener for the table.
     */
    private class List implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent lse) {
            button1.setEnabled(true);
        }
    }

}
