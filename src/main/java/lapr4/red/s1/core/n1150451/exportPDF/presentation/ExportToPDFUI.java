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
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lapr4.red.s1.core.n1150451.exportPDF.application.ExportPDFController;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ExportToPDFUI extends JDialog {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
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
    private TextField rangeField;
    private Checkbox boxSections;
    private JTextField pathField;

    public ExportToPDFUI(UIController uiController, FileChooser chooser) {
        this.uiController = uiController;
        super.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        createComponents();
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
        boxSections = new Checkbox("Sections");
        panel.add(boxSections, grid);

        grid.gridx = 0;
        grid.gridy = 2;
        panel.add(createPathPanel(), grid);

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

        rangeField = new TextField(25);
        grid.gridx = 0;
        grid.gridy = 1;
        panel.add(rangeField, grid);

        JButton exportButton = new JButton("Export");
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExportPDFController c = new ExportPDFController();
                c.initiateExport();
                if (sheetList.getSelectedValue().equals("all")) {
                    c.selectRange(uiController.getActiveWorkbook());
                } else {
                    Iterator<Spreadsheet> it = uiController.getActiveWorkbook().iterator();
                    while (it.hasNext()) {
                        Spreadsheet s = it.next();
                        if(s.getTitle().equals(sheetList.getSelectedValue())){
                            c.selectRange(s);
                            break;
                        }
                    }
                }
                c.selectPath(pathField.getText());
                c.toggleSections();
                c.export();
            }
        });
        grid.gridx = 0;
        grid.gridy = 2;
        panel.add(exportButton, grid);
        return panel;
    }

    private Component createPathPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pathField = new JTextField(25);
        panel.add(pathField);

        //JButton button = createButtonFolder();
        JButton button = new JButton("test");
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
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
        return panel;
    }

    private JButton createButtonFolder() {
        JButton button = new JButton();
        try {
            Image img;
            img = ImageIO.read(getClass().getResource("res/img/reopen.gif"));
            Image newimg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(newimg);
            button.setIcon(icon);
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setContentAreaFilled(false);
        } catch (IOException ex) {
            Logger.getLogger(ExportToPDFUI.class.getName()).log(Level.FINEST, null, ex);
        }
        return button;
    }

}
