/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT.importTXT.ui;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.FileChooser;
import csheets.ui.ctrl.UIController;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.green.s1.ipc.n1150800.importexportTXT.importTXT.ctrl.ImportDataController;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ImportDataUI extends JFrame {
    
    /**
     * The user interface controller
     */
    private UIController uiController;
    
    /**
     * The file chooser panel
     */
    private FileChooser fileChooser;
    
    /**
     * Creates an instance of ImportDataUI with the
     * @param uiController - user interface controller
     */
    public ImportDataUI(UIController uiController) {
        this.uiController = uiController;
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //TODO: update properties so that the predefined files are of .txt extension
        fileChooser = new FileChooser(this, new Properties());
        createComponents();
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
    
    /**
     * Creates the components of the frame
     */
    private void createComponents() {
        add(fileChooser);
        File fileToRead = fileChooser.getFileToOpen();

        if (fileToRead != null) {
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

            JPanel subPanelRadioButtons = new JPanel();

            JLabel labelFirstLine = new JLabel("The first line of the file represents:");
            JRadioButton radioButtonColumnHeader = new JRadioButton("Column headers");
            JRadioButton radioButtonNormalRow = new JRadioButton("Normal row");
            ButtonGroup group = new ButtonGroup();
            group.add(radioButtonColumnHeader);
            group.add(radioButtonNormalRow);

            subPanelRadioButtons.add(labelFirstLine);
            subPanelRadioButtons.add(radioButtonColumnHeader);
            subPanelRadioButtons.add(radioButtonNormalRow);

            JPanel subPanelButtons = new JPanel();

            JButton buttonConfirm = new JButton("Confirm");
            buttonConfirm.addActionListener((ActionEvent e) -> {
                try {
                /* SEPARATOR CHARACTER */
                char separatorCharacter = txtFieldCharacter.getText().charAt(0);
                
                /* FIRST CELL */
                String addressStr = txtFieldFirstCell.getText();
                String columnStr = String.valueOf(addressStr.charAt(0)).toUpperCase();
                int column = -1;
                for (int i = columnStr.length() - 1; i >= 0; i--) {
                    column += (columnStr.charAt(i) - Address.LOWEST_CHAR + 1)
                            * Math.pow(Address.HIGHEST_CHAR - Address.LOWEST_CHAR + 1,
                                    columnStr.length() - (i + 1));
                }
                int row = Integer.parseInt(String.valueOf(addressStr.charAt(1))) - 1;
                
                Address address = new Address(column, row);
                Cell firstCell = uiController.getActiveSpreadsheet().getCell(address);
                
                /* LAST CELL */
                addressStr = txtFieldLastCell.getText();
                columnStr = String.valueOf(addressStr.charAt(0)).toUpperCase();
                column = -1;
                for (int i = columnStr.length() - 1; i >= 0; i--) {
                    column += (columnStr.charAt(i) - Address.LOWEST_CHAR + 1)
                            * Math.pow(Address.HIGHEST_CHAR - Address.LOWEST_CHAR + 1,
                                    columnStr.length() - (i + 1));
                }
                row = Integer.parseInt(String.valueOf(addressStr.charAt(1))) - 1;
                
                address = new Address(column, row);
                Cell lastCell = uiController.getActiveSpreadsheet().getCell(address);
                
                /* CELL RANGE */
                CellRange range = new CellRange(firstCell, lastCell);
                
                ImportDataController controller = new ImportDataController(uiController, fileToRead, separatorCharacter, range);
                controller.readData();
                
                dispose();
                } catch(IllegalArgumentException | IOException | FormulaCompilationException ex) {
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
            panel.add(subPanelRadioButtons, c);

            c.gridx = 0;
            c.gridy = 2;
            panel.add(subPanelRange, c);

            c.gridx = 0;
            c.gridy = 3;
            panel.add(subPanelButtons, c);

            add(panel);
        } else {
            dispose();
        }

    }
}
