/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT.importTXT.ui;

import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.FileChooser;
import csheets.ui.ctrl.UIController;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.green.s1.ipc.n1150800.importexportTXT.importTXT.ctrl.ImportDataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

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
     * The file to read the data from
     */
    private File fileToRead;

    /**
     * Creates an instance of ImportDataUI with
     *
     * @param uiController - the user interface controller
     * @param fileToRead - the file to read
     */
    public ImportDataUI(UIController uiController, File fileToRead) {
        this.uiController = uiController;
        this.fileToRead = fileToRead;

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
                if (txtFieldCharacter.getText().length() > 1) {
                    throw new IllegalArgumentException("The separator character must have ONLY one character!");
                }

                if (txtFieldCharacter.getText().isEmpty()) {
                    throw new IllegalArgumentException("Choose a separator character!");
                }

                char separatorCharacter = txtFieldCharacter.getText().charAt(0);

                /* FIRST LINE */
                boolean firstLineRepresentsHeaders = false;
                if (!radioButtonColumnHeader.isSelected() && !radioButtonNormalRow.isSelected()) {
                    throw new IllegalArgumentException("Choose a format option for the first line of the file!");
                } else if (radioButtonColumnHeader.isSelected()) {
                    firstLineRepresentsHeaders = true;
                }

                /* CELL RANGE */
                String addressStrFirstCell = txtFieldFirstCell.getText();
                String addressStrLastCell = txtFieldLastCell.getText();

                CellRange cellRange = new CellRange(addressStrFirstCell, addressStrLastCell, uiController);

                ImportDataController controller = new ImportDataController(uiController, fileToRead, separatorCharacter, cellRange, firstLineRepresentsHeaders);
                controller.readData();

                dispose();
            } catch (IllegalArgumentException | IOException | FormulaCompilationException ex) {
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

    }
}
