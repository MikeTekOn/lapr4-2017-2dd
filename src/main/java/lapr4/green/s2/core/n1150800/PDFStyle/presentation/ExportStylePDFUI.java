/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150800.PDFStyle.presentation;

import lapr4.green.s2.core.n1150800.PDFStyle.domain.ExportStylePDFThread;
import com.itextpdf.text.BaseColor;
import csheets.core.Spreadsheet;
import csheets.ext.style.StyleExtension;
import csheets.ui.FileChooser;
import csheets.ui.ctrl.UIController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import lapr4.green.s2.core.n1150800.PDFStyle.application.ExportStylePDFController;
import lapr4.red.s1.core.n1150451.exportPDF.presentation.ExportToPDFUI;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ExportStylePDFUI extends ExportToPDFUI {

    /**
     * The button that checks if the border chosen is "Solid Border"
     */
    private JRadioButton radioButtonSolidBorder;

    /**
     * The button that checks if the border chosen is "Dotted Border"
     */
    private JRadioButton radioButtonDottedBorder;

    /**
     * The button that checks if the border chosen is "No Border"
     */
    private JRadioButton radioButtonNoBorder;

    /**
     * The border color
     */
    private BaseColor borderColor;

    /**
     * Builds an instance of ExportStylePDFUI with the user interface
     * controller and a file chooser instance
     * @param uiController - the user interface controller
     * @param chooser - the file chooser
     */
    public ExportStylePDFUI(UIController uiController, FileChooser chooser) {
        super(uiController, chooser);
        borderColor = new BaseColor(BaseColor.BLACK.getRGB()); // Default border color is Black
    }

    @Override
    protected void createComponents() {
        mainPanel = new JPanel(new GridBagLayout());

        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.VERTICAL;

        grid.gridx = 0;
        grid.gridy = 2;
        mainPanel.add(createBorderConfigurationPanel(), grid);

        super.createComponents();
    }

    public JPanel createBorderConfigurationPanel() {
        JPanel panel = new JPanel();

        radioButtonSolidBorder = new JRadioButton("Solid Border");
        radioButtonDottedBorder = new JRadioButton("Dotted Border");
        radioButtonNoBorder = new JRadioButton("No Border");
        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonSolidBorder);
        group.add(radioButtonDottedBorder);
        group.add(radioButtonNoBorder);

        panel.add(radioButtonSolidBorder);
        panel.add(radioButtonDottedBorder);
        panel.add(radioButtonNoBorder);
        panel.add(createBorderColorButton());
        panel.setBorder(BorderFactory.createTitledBorder("Configure Border"));

        return panel;
    }

    private JButton createBorderColorButton() {
        JButton button = new JButton(new ImageIcon(StyleExtension.class.getResource("res/img/color_bg.gif")));
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(25, 25));
        button.addActionListener((ActionEvent e) -> {
            Color color = JColorChooser.showDialog(
                    null, "Choose Border Color", Color.BLACK);
            borderColor = new BaseColor(color.getRed(), color.getGreen(), color.getBlue());
        });
        return button;
    }

    @Override
    protected JButton createExportButton() {
        JButton exportButton = new JButton("Export");
        exportButton.addActionListener((ActionEvent e) -> {
            ExportStylePDFController c = new ExportStylePDFController(uiController);
            if (sheetList.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(rootPane, "You didn't select any item from the list.");
                return;
            }
            if (sheetList.getSelectedValue().equals("all")) {
                c.selectRange(uiController.getActiveWorkbook());
            } else {
                Iterator<Spreadsheet> it = uiController.getActiveWorkbook().iterator();
                Spreadsheet s = null;
                while (it.hasNext()) {
                    s = it.next();
                    if (s.getTitle().equals(sheetList.getSelectedValue())) {
                        break;
                    }
                }
                if (rangeField.getText().trim().equals("")) {
                    c.selectRange(s);
                } else {
                    try {
                        c.selectRange(s, rangeField.getText());
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(rootPane, "Inserted Range is invalid. The range should be in A1:B2 format.");
                        return;
                    }
                }
            }
            try {
                
                c.selectPath(pathField.getText());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(rootPane, "Inserted path is invalid. It should be a path to a pdf.");
                return;
            }
            
            if (boxSections.isSelected()) {
                c.toggleSections();
            }
            
            try {
                c.borderConfigurations(radioButtonSolidBorder.isSelected(), radioButtonDottedBorder.isSelected(), radioButtonNoBorder.isSelected(), borderColor);
            } catch(IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            ExportStylePDFThread exportThread = new ExportStylePDFThread(c);
            exportThread.run();
            JOptionPane.showMessageDialog(rootPane, "Export sucessful!");
        });
        return exportButton;

    }
}
