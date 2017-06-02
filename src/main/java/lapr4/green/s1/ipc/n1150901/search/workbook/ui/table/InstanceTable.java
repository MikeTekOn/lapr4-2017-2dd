/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150901.search.workbook.ui.table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class InstanceTable extends JFrame {

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
    private JButton button;

    /**
     * The controller of the table.
     */
    private final InstanceTableController theController;

    /**
     * The constructor of the table UI.
     */
    public InstanceTable() {
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

    private JButton createButtonPanel() {

        button = new JButton("Select");

        button.setPreferredSize(new Dimension(50, 30));
        button.addActionListener(new Select());
        button.setEnabled(false);
        button.setMargin(new Insets(0, 0, 0, 0));

        return button;
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

    /**
     * It provides the instance matching the selected row.
     *
     * @return It returns the instance associated with the selected row.
     */
    public String getSelectedRowFile() {
        return theController.provideInstance(table.getSelectedRow());
    }

    /**
     * An inner class that implements the action listener for the select button.
     */
    private class Select implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }

    /**
     * An inner class that implements the list selection listener for the table.
     */
    private class List implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent lse) {
            button.setEnabled(true);
        }
    }

}
