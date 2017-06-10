/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150451.multipleMacros.ui;

import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import lapr4.red.s2.lang.n1150451.multipleMacros.MacroWithName;
import lapr4.red.s2.lang.n1150451.multipleMacros.ModifyMacroListController;

/**
 *
 * @author Diogo Santos
 */
public class NewEditMacroDialog extends JDialog {

    private static final int WIDTH = 620;
    private static final int HEIGHT = 420;
    private UIController uiController;
    private MacroWithName macro;
    private JTextField fieldName;
    private JTextArea codeArea;
    private ModifyMacroUI mmUI;

    public NewEditMacroDialog(ModifyMacroUI mmUI, UIController uiController) {
        this.uiController = uiController;
        this.mmUI = mmUI;
        super.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        createComponents();
        setFocusable(true);
        setTitle("Add Macro");
        super.setVisible(true);
    }

    public NewEditMacroDialog(ModifyMacroUI mmUI, UIController uiController, MacroWithName macro) {
        this.uiController = uiController;
        this.macro = macro;
        this.mmUI = mmUI;
        super.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        createComponents();
        setFocusable(true);
        setTitle("Edit Macro");
        super.setVisible(true);
    }

    private void createComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();

        grid.fill = GridBagConstraints.HORIZONTAL;

        grid.gridx = 0;
        grid.gridy = 0;
        mainPanel.add(new JLabel("Macro name:"), grid);
        
        grid.gridy = 1;
        fieldName = new JTextField();
        fieldName.setColumns(20);
        mainPanel.add(fieldName, grid);
        grid.insets = new Insets(10, 0, 10, 0);
        
        
        grid.gridx = 0;
        grid.gridy = 2;
        mainPanel.add(new JLabel("Code:"), grid);
        grid.gridy = 3;
        codeArea = new JTextArea();
        codeArea.setRows(10);
        mainPanel.add(codeArea, grid);

        if (macro != null) {
            fieldName.setText(macro.getName());
            codeArea.setText(macro.getMacroCode());
        }
        grid.gridx = 0;
        grid.gridy = 4;
        mainPanel.add(createButton(), grid);
        add(mainPanel);
    }

    
    private JButton createButton() {
        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyMacroListController c = new ModifyMacroListController();
                if (macro != null) {
                    c.getMacroList(uiController.getActiveWorkbook(), uiController);
                    c.updateMacro(macro.getName(), fieldName.getText(), codeArea.getText(), uiController.getActiveSpreadsheet());
                } else {
                    c.getMacroList(uiController.getActiveWorkbook(), uiController);
                    c.addMacro(fieldName.getText(), codeArea.getText(), uiController.getActiveSpreadsheet());
                }
                mmUI.updateList();
                dispose();
            }
        }
        );
        return save;
    }

}
