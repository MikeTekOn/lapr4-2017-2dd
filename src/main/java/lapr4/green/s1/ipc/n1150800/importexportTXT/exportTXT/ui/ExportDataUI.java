/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT.exportTXT.ui;

import csheets.ui.ctrl.UIController;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.green.s1.ipc.n1150800.importexportTXT.exportTXT.ctrl.ExportDataController;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ExportDataUI extends JFrame {
    
    /**
     * The user interface controller
     */
    private UIController uiController;
    
    /**
     * The file to write data into
     */
    private File fileToWrite;
    
    /**
     * Creates an instance of ExportDataUI with
     * @param uiController - the user interface controller
     * @param fileToWrite - the file to write
     */
    public ExportDataUI(UIController uiController, File fileToWrite) {
        this.uiController = uiController;
        this.fileToWrite = fileToWrite;
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createComponents();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    /**
     * Creates the components of the frame
     */
    private void createComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        JPanel subPanelCharacter = new JPanel();

        JLabel labelCharacter = new JLabel("Choose the separator character:");
        JTextField txtFieldCharacter = new JTextField(2);

        subPanelCharacter.add(labelCharacter);
        subPanelCharacter.add(txtFieldCharacter);

        JPanel subPanelRange = new JPanel();

        JLabel labelFirstCell = new JLabel("First Cell:");
        JTextField txtFieldFirstCell = new JTextField(5);

        JLabel labelLastCell = new JLabel("Last Cell:");
        JTextField txtFieldLastCell = new JTextField(5);

        subPanelRange.add(labelFirstCell);
        subPanelRange.add(txtFieldFirstCell);
        subPanelRange.add(labelLastCell);
        subPanelRange.add(txtFieldLastCell);

        JPanel subPanelButtons = new JPanel();

        JButton buttonConfirm = new JButton("Confirm");
        buttonConfirm.addActionListener((ActionEvent e) -> {
            try {
                /* SEPARATOR CHARACTER */
                if(txtFieldCharacter.getText().length() > 1) {
                    throw new IllegalArgumentException("The separator character must have ONLY one character!");
                }
                
                if(txtFieldCharacter.getText().isEmpty()) {
                    throw new IllegalArgumentException("Choose a separator character!");
                }
                
                char separatorCharacter = txtFieldCharacter.getText().charAt(0);

                /* CELL RANGE */
                String addressStrFirstCell = txtFieldFirstCell.getText();
                String addressStrLastCell = txtFieldLastCell.getText();

                CellRange cellRange = new CellRange(addressStrFirstCell, addressStrLastCell, uiController);

                ExportDataController controller = new ExportDataController(uiController, fileToWrite, separatorCharacter, cellRange, false);
                controller.writeData();

                dispose();
            } catch (IllegalArgumentException | IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener((ActionEvent e) -> {
            dispose();
        });

        subPanelButtons.add(buttonConfirm);
        subPanelButtons.add(buttonCancel);

        c.gridx = 0;
        c.gridy = 0;
        panel.add(subPanelCharacter, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(subPanelRange, c);

        c.gridx = 0;
        c.gridy = 2;
        panel.add(subPanelButtons, c);

        add(panel);

    }
}
