/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Export.UI;

import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lapr4.green.s3.lang.n1150657.XML.Export.ElementCleansheet;
import lapr4.green.s3.lang.n1150657.XML.Export.ExportXMLController;

/**
 * FIXXXXX
 * @author Sofia
 */
public class TagNamesInputDialogUI extends JDialog{

    /* UI Components */
    private JTextField workbookTagTextField;
    private JTextField spreadsheetTagTextField;
    private JTextField cellTagTextField;
    private final Dimension BUTTON_SIZE = new Dimension(115, 30);

    private ExportXMLController controller;

    /**
     * Creates an instance of export xml dialog.
     *
     */
    public TagNamesInputDialogUI(ExportXMLController controller) {
        setLocationRelativeTo(null);
        this.controller = controller;

        createComponents();
        pack();
    }


    /**
     * Creates the user interface components.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new BorderLayout());
        componentsPanel.add(createLabelsAndTextFieldPanel(), BorderLayout.NORTH);
        componentsPanel.add(createButtonsPanel(), BorderLayout.CENTER);

        add(componentsPanel);
    }

    /**
     * Creates the labels and text fields panel.
     *
     * @return the labels and text fields panel.
     */
    private JPanel createLabelsAndTextFieldPanel() {
        JPanel labelsAndTextFieldPanel = new JPanel(new BorderLayout());
        JPanel northPanel = new JPanel(new FlowLayout());
        JPanel centerPanel = new JPanel(new FlowLayout());
        JPanel southPanel = new JPanel(new FlowLayout());

        this.workbookTagTextField = new JTextField();
        this.workbookTagTextField.setPreferredSize(BUTTON_SIZE);

        this.spreadsheetTagTextField = new JTextField();
        this.spreadsheetTagTextField.setPreferredSize(BUTTON_SIZE);

        this.cellTagTextField = new JTextField();
        this.cellTagTextField.setPreferredSize(BUTTON_SIZE);

        JLabel
                workbookLabel = new JLabel("workbook tag");
        workbookLabel.setVisible(true);

        JLabel spreadsheetLabel = new JLabel("spreadsheet tag");
        spreadsheetLabel.setVisible(true);

        JLabel cellLabel = new JLabel("cell tag");
        cellLabel.setVisible(true);

        northPanel.add(workbookLabel);
        northPanel.add(workbookTagTextField);

        centerPanel.add(spreadsheetLabel);
        centerPanel.add(spreadsheetTagTextField);

        southPanel.add(cellLabel);
        southPanel.add(cellTagTextField);

        labelsAndTextFieldPanel.add(northPanel, BorderLayout.NORTH);
        labelsAndTextFieldPanel.add(centerPanel, BorderLayout.CENTER);
        labelsAndTextFieldPanel.add(southPanel, BorderLayout.SOUTH);

        return labelsAndTextFieldPanel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();

        buttonsPanel.add(createSaveButton());
        buttonsPanel.add(createCancelButton());

        return buttonsPanel;
    }

    /**
     * Creates the save button.
     *
     * @return save button
     */
    private JButton createSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(BUTTON_SIZE);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String workbookTagText = workbookTagTextField.getText().toLowerCase();
                String spreadsheetTagText = spreadsheetTagTextField.getText().toLowerCase();
                String cellTagText = cellTagTextField.getText().toLowerCase();

                controller.addTagName(ElementCleansheet.WORKBOOK.getElementName(),workbookTagText);
                controller.addTagName(ElementCleansheet.SPREADSHEET.getElementName(),spreadsheetTagText);
                controller.addTagName(ElementCleansheet.CELL.getElementName(),cellTagText);
                dispose();
            }
        });

        return saveButton;
    }

    /**
     * Creates the cancel button.
     *
     * @return cancel button
     */
    private JButton createCancelButton() {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(BUTTON_SIZE);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        return cancelButton;
    }
    
    
}
