/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150451.multipleMacros.ui;

import csheets.core.Spreadsheet;
import csheets.ui.FileChooser;
import csheets.ui.ctrl.UIController;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lapr4.red.s2.lang.n1150451.multipleMacros.MacroWithName;
import lapr4.red.s2.lang.n1150451.multipleMacros.ModifyMacroListController;

/**
 *
 * @author Diogo Santos
 */
public class ModifyMacroUI extends JDialog {

    private static final int WIDTH = 620;
    private static final int HEIGHT = 420;
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
    private JList macroList;
    private ModifyMacroUI thisDialog = this;

    public ModifyMacroUI(UIController uiController) {
        this.uiController = uiController;
        super.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        createComponents();
        setFocusable(true);
        setTitle("Edit Macros");
        super.setVisible(true);
    }

    private void createComponents() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.VERTICAL;

        grid.gridx = 0;
        grid.gridy = 0;
        panel.add(createListPanel(), grid);

        grid.gridx = 0;
        grid.gridy = 1;
        panel.add(createButtons(), grid);

        grid.fill = GridBagConstraints.EAST;
        grid.anchor = GridBagConstraints.CENTER;
        super.add(panel);
    }

    private Component createListPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.HORIZONTAL;

        macroList = new JList();
        macroList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        model = new DefaultListModel<>();
        macroList.setModel(model);

        updateList();

        JScrollPane pane = new JScrollPane(macroList);
        pane.setSize(300, 300);
        grid.gridx = 0;
        grid.gridy = 0;
        panel.add(pane, grid);
        return panel;
    }

    public void updateList() {
        model.removeAllElements();
        for (MacroWithName m : uiController.getActiveWorkbook().getMacroList().getMacroList()) {
            model.addElement(m.getName());
        }
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton newButton = new JButton("New");
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewEditMacroDialog(thisDialog, uiController);
                updateList();
            }
        });
        panel.add(newButton);
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (macroList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(rootPane, "You must select a macro first.");
                } else {
                    new NewEditMacroDialog(thisDialog, uiController, uiController.getActiveWorkbook().getMacroList().getMacroByName((String) macroList.getSelectedValue()));
                }
            }
        });
        panel.add(editButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (macroList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(rootPane, "You must select a macro first.");
                } else {
                    ModifyMacroListController c = new ModifyMacroListController();
                    c.deleteMacro((String) macroList.getSelectedValue());
                    updateList();
                }
            }
        });

        panel.add(deleteButton);
        return panel;
    }
}
