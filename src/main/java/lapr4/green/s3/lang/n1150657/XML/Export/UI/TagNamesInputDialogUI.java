/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Export.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lapr4.green.s3.lang.n1150657.XML.Export.ElementCleansheet;
import lapr4.green.s3.lang.n1150657.XML.Export.ExportXMLController;

/**
 * FIXXXXX
 *
 * @author Sofia
 */
public class TagNamesInputDialogUI extends JDialog {

    /* UI Components */
    private JTextField workbookTagTextField;
    private JTextField spreadsheetTagTextField;
    private JTextField cellTagTextField;

    private JTextField contentkTagTextField;
    private JTextField fontTagTextField;
    private JTextField foregroundTagTextField;

    private JTextField macroTagTextField;
    private JTextField variableTagTextField;
    private JTextField commentTagTextField;
    private final Dimension BUTTON_SIZE = new Dimension(115, 30);

    private ExportXMLController controller;

    /**
     * Creates an instance of export xml dialog.
     *
     */
    public TagNamesInputDialogUI(ExportXMLController controller) {
        setLocationRelativeTo(null);
        this.controller = controller;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        contentkTagTextField = new JTextField();
        contentkTagTextField.setPreferredSize(BUTTON_SIZE);
        fontTagTextField = new JTextField();
        fontTagTextField.setPreferredSize(BUTTON_SIZE);
        foregroundTagTextField = new JTextField();
        foregroundTagTextField.setPreferredSize(BUTTON_SIZE);
        macroTagTextField = new JTextField();
        macroTagTextField.setPreferredSize(BUTTON_SIZE);
        variableTagTextField = new JTextField();
        variableTagTextField.setPreferredSize(BUTTON_SIZE);
        commentTagTextField = new JTextField();
        commentTagTextField.setPreferredSize(BUTTON_SIZE);

        JLabel workbookLabel = new JLabel("workbook tag");

        workbookLabel.setVisible(true);

        JLabel spreadsheetLabel = new JLabel("spreadsheet tag");

        spreadsheetLabel.setVisible(true);

        JLabel cellLabel = new JLabel("cell tag");

        cellLabel.setVisible(true);

        JLabel content = new JLabel("content tag");

        content.setVisible(true);

        JLabel font = new JLabel("font tag");

        font.setVisible(true);

        JLabel foreground = new JLabel("foreground tag");

        foreground.setVisible(true);

        JLabel macro = new JLabel("macro tag");

        macro.setVisible(true);

        JLabel global = new JLabel("variables tag");

        global.setVisible(true);

        JLabel comment = new JLabel("comment tag");

        comment.setVisible(true);

        northPanel.add(workbookLabel);

        northPanel.add(workbookTagTextField);

        northPanel.add(spreadsheetLabel);

        northPanel.add(spreadsheetTagTextField);

        northPanel.add(cellLabel);

        northPanel.add(cellTagTextField);

        centerPanel.add(content);

        centerPanel.add(contentkTagTextField);

        centerPanel.add(font);

        centerPanel.add(fontTagTextField);

        centerPanel.add(foreground);

        centerPanel.add(foregroundTagTextField);

        southPanel.add(macro);

        southPanel.add(macroTagTextField);

        southPanel.add(global);

        southPanel.add(variableTagTextField);

        southPanel.add(comment);

        southPanel.add(commentTagTextField);

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
                
                String fontTagText = fontTagTextField.getText().toLowerCase();
                String contentTagText = contentkTagTextField.getText().toLowerCase();
                String foregroundTagText = foregroundTagTextField.getText().toLowerCase();
                
                String macroTagText = macroTagTextField.getText().toLowerCase();
                String variableTagText = variableTagTextField.getText().toLowerCase();
                String commentTagText = commentTagTextField.getText().toLowerCase();

                controller.addTagName(ElementCleansheet.WORKBOOK.getElementName(), workbookTagText.trim());
                controller.addTagName(ElementCleansheet.SPREADSHEET.getElementName(), spreadsheetTagText.trim());
                controller.addTagName(ElementCleansheet.CELL.getElementName(), cellTagText.trim());
                controller.addTagName(ElementCleansheet.FONT.getElementName(), fontTagText.trim());
                controller.addTagName(ElementCleansheet.CONTENT.getElementName(), contentTagText.trim());
                controller.addTagName(ElementCleansheet.FOREGROUNG.getElementName(), foregroundTagText.trim());
                controller.addTagName(ElementCleansheet.MACRO.getElementName(), macroTagText.trim());
                controller.addTagName(ElementCleansheet.GLOBAL_VARIABLE.getElementName(), variableTagText.trim());
                controller.addTagName(ElementCleansheet.COMMENT.getElementName(), commentTagText.trim());
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
