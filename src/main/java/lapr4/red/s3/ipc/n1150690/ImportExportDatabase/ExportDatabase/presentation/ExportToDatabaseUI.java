/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ExportDatabase.presentation;

import csheets.ui.ctrl.UIController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.red.s3.ipc.n1150690.ImportExportDatabase.DatabaseDriver;
import lapr4.red.s3.ipc.n1150690.ImportExportDatabase.ExportDatabase.application.ExportToDatabaseController;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ExportToDatabaseUI extends JDialog {

    /**
     * The width of the JDialog.
     */
    private static final int WIDTH = 500;

    /**
     * The height of the JDialog.
     */
    private static final int HEIGHT = 400;

    /**
     * The controller of this use case.
     */
    private ExportToDatabaseController controller;

    /**
     * The user interface controller.
     */
    private UIController uiController;

    private JComboBox<String> drivers;

    private JTextField txtFieldFirstCell;

    private JTextField txtFieldLastCell;

    private JTextField txtTableName;

    private JTextField txtDatabaseConnection;

    private static JLabel labelErrors;

    private JLabel success;

    private JPanel panel;
    
    private String driver;
    
    private static boolean printSucess;

    public ExportToDatabaseUI(UIController uiController) {
        this.uiController = uiController;
        super.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        super.add(createComponents());
        setFocusable(true);
        setTitle("Export To Database");
        setLocationRelativeTo(null);
        super.setVisible(true);
    }

    private JPanel createComponents() {

        JPanel panelRange = new JPanel(new FlowLayout());
        JLabel labelFirstCell = new JLabel("First Cell:");
        panelRange.add(labelFirstCell);
        txtFieldFirstCell = new JTextField(5);
        panelRange.add(txtFieldFirstCell);
        JLabel labelLastCell = new JLabel("Last Cell:");
        panelRange.add(labelLastCell);
        txtFieldLastCell = new JTextField(5);
        panelRange.add(txtFieldLastCell);

        JPanel panelTableName = new JPanel();
        JLabel labelTableName = new JLabel("Table name:");
        panelTableName.add(labelTableName);
        txtTableName = new JTextField(15);
        panelTableName.add(txtTableName);
        
        JPanel panelDriver = new JPanel();
        JLabel labeDriver = new JLabel("Database Driver:");
        panelDriver.add(labeDriver);
        panelDriver.add(createCombobox());

        JPanel panelConnection = new JPanel();
        JLabel labelDatabaseConnection = new JLabel("Database Connection:");
        panelConnection.add(labelDatabaseConnection);
        txtDatabaseConnection = new JTextField(20);
        txtDatabaseConnection.setText(DatabaseDriver.H2.defaultURL());
        panelConnection.add(txtDatabaseConnection);
        driver = DatabaseDriver.H2.value();

        JButton b = new JButton("Export");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printSucess = true;
                labelErrors.setText("");
                String addressStrFirstCell = txtFieldFirstCell.getText();
                String addressStrLastCell = txtFieldLastCell.getText();
                CellRange cellRange = new CellRange(addressStrFirstCell, addressStrLastCell, uiController);
                controller = new ExportToDatabaseController(uiController, cellRange, txtTableName.getText(), txtDatabaseConnection.getText(), driver);
                try {
                    controller.export(false);
                } catch (Exception ex) {
                    ExportToDatabaseUI.printException(ex.getMessage());
                }
                if (printSucess) {
                    success.setText("Exportation Successful!");
                    success.setForeground(Color.GREEN);
                }
            }
        });

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();

        grid.gridx = 0;
        grid.gridy = 0;
        JLabel labelRange = new JLabel("<html><b>Range of Cells</b></html>");
        panel.add(labelRange, grid);
        grid.gridy = 1;
        panel.add(panelRange, grid);
        grid.gridy = 2;
        panel.add(panelTableName, grid);
        grid.gridy = 3;
        panel.add(panelDriver, grid);
        grid.gridy = 4;
        panel.add(panelConnection, grid);
        grid.gridy = 5;
        panel.add(b, grid);
        grid.gridy = 6;
        success = new JLabel();
        panel.add(success, grid);
        labelErrors = new JLabel();
        grid.gridy = 7;
        panel.add(labelErrors, grid);

        return panel;
    }

    private JComboBox createCombobox() {
        drivers = new JComboBox<>();
        drivers.setPreferredSize(new Dimension(200, 20));

        drivers.addItem(DatabaseDriver.H2.name());
        drivers.addItem(DatabaseDriver.JavaDBEmbedded.name());
        drivers.addItem(DatabaseDriver.MySQL.name());
        drivers.addItem(DatabaseDriver.PostgreSQL.name());

        drivers.setSelectedIndex(0);
        drivers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (drivers.getSelectedIndex() != -1) {
                    if (drivers.getSelectedIndex() == 0) {
                        txtDatabaseConnection.setText(DatabaseDriver.H2.defaultURL());
                        driver = DatabaseDriver.H2.value();
                    }
                    if (drivers.getSelectedIndex() == 1) {
                        txtDatabaseConnection.setText(DatabaseDriver.JavaDBEmbedded.defaultURL());
                        driver = DatabaseDriver.JavaDBEmbedded.value();
                    }
                    if (drivers.getSelectedIndex() == 2) {
                        txtDatabaseConnection.setText(DatabaseDriver.MySQL.defaultURL());
                        driver = DatabaseDriver.MySQL.value();
                    }
                    if (drivers.getSelectedIndex() == 3) {
                        txtDatabaseConnection.setText(DatabaseDriver.PostgreSQL.defaultURL());
                        driver = DatabaseDriver.PostgreSQL.value();
                    }
                }
            }

        });
        return drivers;
    }
    
    public static void printException(String message){
        printSucess = false;
        labelErrors.setText(message);
        labelErrors.setForeground(Color.RED);
    }
    
    public static boolean tableWithTheSameName(){
        int op = JOptionPane.showConfirmDialog(null, "A table with the name already exists! Do you want to delete the existing table?", "Warning", JOptionPane.YES_NO_OPTION);
        if(op == 0){
            return true;
        }
        return false;
    }
   
}
