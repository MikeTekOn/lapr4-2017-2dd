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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
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

    private JTextField txtFieldFirstCell;

    private JTextField txtFieldLastCell;

    private JTextField txtTableName;

    private JTextField txtDatabaseConnection;
    
    private JLabel labelErrors;

    private JLabel success;

    private JPanel panel;

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

        JPanel panelConnection = new JPanel();
        JLabel labelDatabaseConnection = new JLabel("Database Connection:");
        panelConnection.add(labelDatabaseConnection);
        txtDatabaseConnection = new JTextField(20);
        txtDatabaseConnection.setText("jdbc:h2:..\\db\\csheets-crm-extension");
        panelConnection.add(txtDatabaseConnection);

        JButton b = new JButton("Export");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean successExportation = true;
                String addressStrFirstCell = txtFieldFirstCell.getText();
                String addressStrLastCell = txtFieldLastCell.getText();
                CellRange cellRange = new CellRange(addressStrFirstCell, addressStrLastCell, uiController);
                controller = new ExportToDatabaseController(uiController, cellRange, txtTableName.getText(), txtDatabaseConnection.getText());
                try {
                    controller.export(false);
                    successExportation = true;
                } catch (SQLException ex) {
                    labelErrors.setText(ex.getMessage());
                    labelErrors.setForeground(Color.RED);
                   
                        //if (ex.getErrorCode() == 42101) {
                        System.out.println("ola");
                        tableAlreadyExists();
                    //}
                } catch (InterruptedException ex) {
                    labelErrors.setText(ex.toString());
                    labelErrors.setForeground(Color.RED);
                    Logger.getLogger(ExportToDatabaseUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(successExportation){
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
        panel.add(panelConnection, grid);
        grid.gridy = 4;
        panel.add(b, grid);
        grid.gridy = 5;
        success = new JLabel();
        panel.add(success, grid);
        labelErrors = new JLabel();
        grid.gridy = 6;
        panel.add(success, grid);

        return panel;
    }

    private JDialog tableAlreadyExists() {
        Window parentWindow = SwingUtilities.windowForComponent(txtDatabaseConnection);
        JDialog dialog = new JDialog(parentWindow);
        dialog.setMinimumSize(new Dimension(150, 100));

        JPanel p = new JPanel();
        p.add(new JLabel("There is already a table with the name entered!"));
        JButton insertNewName = new JButton("Enter a new name for the table");
        insertNewName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        p.add(insertNewName);
        
        JButton deteleExistingTable = new JButton("Delete Existing Table");
        insertNewName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.export(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ExportToDatabaseUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ExportToDatabaseUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        p.add(deteleExistingTable);
        dialog.add(p);
        dialog.setTitle("Table Already Exists");
        dialog.pack();
        dialog.setVisible(true);
        
        
        return dialog;
    }
}
