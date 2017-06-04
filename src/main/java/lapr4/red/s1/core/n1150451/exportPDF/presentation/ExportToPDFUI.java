/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150451.exportPDF.presentation;

import csheets.CleanSheets;
import csheets.core.Spreadsheet;
import csheets.ui.FileChooser;
import csheets.ui.ctrl.UIController;
import lapr4.red.s1.core.n1150451.exportPDF.application.ExportPDFController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.*;

/**
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ExportToPDFUI extends JDialog {

    private static final int WIDTH = 620;
    private static final int HEIGHT = 420;
    private JList sheetList;

    /**
     * The user interface controller
     */
    protected UIController uiController;

    /**
     * The file chooser to use when prompting the user for the destination path
     */
    protected FileChooser chooser;
    private DefaultListModel<Object> model;
    private JTextField rangeField;
    private JCheckBox boxSections;
    private JTextField pathField;

    public ExportToPDFUI(UIController uiController, FileChooser chooser) {
        this.uiController = uiController;
        super.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        createComponents();
        setFocusable(true);
        setTitle("Export to PDF");
        super.setVisible(true);
    }

    private void createComponents() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.VERTICAL;

        grid.gridx = 0;
        grid.gridy = 0;
        panel.add(createListPanel(), grid);

        grid.insets = new Insets(10, 0, 10, 0);
        grid.gridx = 0;
        grid.gridy = 1;
        boxSections = new JCheckBox("Sections");
        panel.add(boxSections, grid);

        grid.insets = new Insets(10, 0, 10, 0);
        grid.gridx = 0;
        grid.gridy = 2;
        panel.add(createPathPanel(), grid);

        grid.gridx = 0;
        grid.gridy = 3;
        panel.add(createExportButton(), grid);

        grid.fill = GridBagConstraints.EAST;
        grid.anchor = GridBagConstraints.CENTER;
        super.add(panel);
    }

    private Component createListPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.HORIZONTAL;

        sheetList = new JList();
        sheetList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        model = new DefaultListModel<>();
        sheetList.setModel(model);
        sheetList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (sheetList.getSelectedValue().equals("all")) {
                    rangeField.setEditable(false);
                } else {
                    rangeField.setEditable(true);
                }
            }
        });
        model.addElement("all");

        for (Spreadsheet s : uiController.getActiveWorkbook()) {
            model.addElement(s.getTitle());
        }

        JScrollPane pane = new JScrollPane(sheetList);
        pane.setSize(300, 300);
        grid.gridx = 0;
        grid.gridy = 0;
        panel.add(pane, grid);

        JPanel flow = new JPanel(new FlowLayout());
        rangeField = new JTextField(25);
        rangeField.setToolTipText("Example: A4:B7");
        flow.add(new JLabel("Cell Range: "));
        flow.add(rangeField);
        grid.gridx = 0;
        grid.gridy = 1;
        panel.add(flow, grid);
        panel.setBorder(BorderFactory.createTitledBorder("Export range"));
        return panel;
    }

    private Component createPathPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pathField = new JTextField(25);
        panel.add(pathField);

        //JButton button = createButtonFolder();
        JButton button = new JButton();
        Image img=null;
        try {
            img = ImageIO.read(CleanSheets.class.getResource("res/img/open.gif"));
        } catch (IOException ex) {
            Logger.getLogger(ExportToPDFUI.class.getName()).log(Level.FINE, null, ex);
        }
        Image newimg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimg);
        button.setIcon(icon);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        JFileChooser chooser = new JFileChooser(".");
        FileFilter filter = new FileNameExtensionFilter("PDF file", new String[]{"pdf"});
        chooser.setFileFilter(filter);
        chooser.addChoosableFileFilter(filter);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser.showOpenDialog(rootPane);
                if (chooser.getSelectedFile() != null) {
                    pathField.setText(chooser.getSelectedFile().getPath());
                }
            }
        });

        panel.add(button);
        panel.setBorder(BorderFactory.createTitledBorder("Output path"));
        return panel;
    }

    private JButton createExportButton() {

        JButton exportButton = new JButton("Export");
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExportPDFController c = new ExportPDFController();
                c.initiateExport(uiController);
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

                c.export();
                JOptionPane.showMessageDialog(rootPane, "Export sucessful!");
            }
        }
        );
        return exportButton;

    }
}
