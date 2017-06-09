package lapr4.blue.s1.lang.n1151159.macros.ui;

import bsh.EvalError;
import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.ui.ctrl.UIController;
import lapr4.blue.s1.lang.n1140822.beanshellwindow.BeanShellClassLoaderController;
import lapr4.blue.s1.lang.n1140822.beanshellwindow.BeanShellResult;
import lapr4.blue.s1.lang.n1151159.macros.MacroController;
import lapr4.blue.s1.lang.n1151159.macros.MacroExtension;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.red.s2.lang.n1150451.multipleMacros.MacroWithName;

/**
 * Represents a dialog to execute macros.
 *
 * @author Ivo Ferro
 */
public class MacroDialog extends JDialog {

    /* Constants */
    private final static int MACRO_TEXT_AREA_ROWS = 10;
    private final static int MACRO_TEXT_AREA_COLUMNS = 30;

    /**
     * The controller of the user interface.
     */
    private UIController uiController;

    /**
     * The macro controller.
     */
    private MacroController macroController = new MacroController();

    /* UI Components */
    private JRadioButton macroLanguageRadioButton;
    private JRadioButton beanShellLanguageRadioButton;
    private JTextArea macroTextArea;
    private JTextField macroOutputTextField;
    private Dimension BUTTON_SIZE = new Dimension(100, 30);
    private JComboBox<Object> comboBox;

    /**
     * Creates an instance of macro dialog.
     *
     * @param uiController the user interface controller
     */
    public MacroDialog(UIController uiController) {
        this.uiController = uiController;
        createComponents();
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Creates the user interface components.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new BorderLayout());

        componentsPanel.add(createMacroLanguagePanel(), BorderLayout.NORTH);
        componentsPanel.add(createMacroTextAreaPanel(), BorderLayout.CENTER);
        componentsPanel.add(createMacroOutputPanel(), BorderLayout.SOUTH);

        componentsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(componentsPanel);
    }

    /**
     * Creates the macro language panel.
     *
     * @return macro language panel
     */
    private JPanel createMacroLanguagePanel() {
        JPanel macroLanguagePanel = new JPanel();

        macroLanguageRadioButton = new JRadioButton("Macro");
        macroLanguageRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                macroTextArea.setText(macroController.getDefaultMacro());
            }
        });
        beanShellLanguageRadioButton = new JRadioButton("Bean Shell");
        beanShellLanguageRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //@1140822 Renato
                    //ONLY FOR THE DEFAULT SCRIPT - TO BE REMOVED IN SECOND ITERATION
                    macroTextArea.setText("");
                    InputStream is = MacroExtension.class.getResourceAsStream("res/script/default_script.bsh");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        macroTextArea.append(line + "\n");
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MacroDialog.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MacroDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(macroLanguageRadioButton);
        buttonGroup.add(beanShellLanguageRadioButton);

        macroLanguageRadioButton.setSelected(true);

        macroLanguagePanel.add(macroLanguageRadioButton);
        macroLanguagePanel.add(beanShellLanguageRadioButton);

        return macroLanguagePanel;
    }

    /**
     * Creates the macro text area panel;
     *
     * @return macro text area panel
     */
    private JPanel createMacroTextAreaPanel() {
        JPanel macroTextAreaPanel = new JPanel(new BorderLayout());
        
        comboBox = new JComboBox<>();
        
        for (MacroWithName macroWithName : uiController.getActiveWorkbook().getMacroList().getMacroList()) {
            comboBox.addItem(macroWithName);
        }
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                macroTextArea.setText(((MacroWithName)comboBox.getSelectedItem()).getMacroCode());
            }
        });
        
        macroTextArea = new JTextArea(MACRO_TEXT_AREA_ROWS, MACRO_TEXT_AREA_COLUMNS);
        macroTextArea.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        macroTextArea.setText(macroController.getDefaultMacro());

        JScrollPane macroTextAreaScrollPane = new JScrollPane(macroTextArea);

        macroTextAreaPanel.add(comboBox, BorderLayout.NORTH);
        macroTextAreaPanel.add(macroTextAreaScrollPane, BorderLayout.CENTER);

        return macroTextAreaPanel;
    }

    /**
     * Creates the macro output panel.
     *
     * @return macro output panel
     */
    private JPanel createMacroOutputPanel() {
        JPanel macroOutputPanel = new JPanel(new BorderLayout());

        macroOutputTextField = new JTextField(MACRO_TEXT_AREA_COLUMNS);
        macroOutputTextField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        macroOutputTextField.setEnabled(false);
        macroOutputTextField.setDisabledTextColor(Color.BLUE);

        JLabel outputLabel = new JLabel("Output:");
        JPanel outputPanel = new JPanel();
        outputPanel.add(outputLabel);
        outputPanel.add(macroOutputTextField);
        outputPanel.setPreferredSize(new Dimension(((int) macroTextArea.getSize().getWidth()), 50));

        macroOutputPanel.add(outputPanel, BorderLayout.NORTH);
        macroOutputPanel.add(createButtonsPanel(), BorderLayout.CENTER);

        return macroOutputPanel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();

        buttonsPanel.add(createRunButton());
        buttonsPanel.add(createClearButton());
        buttonsPanel.add(createCancelButton());

        return buttonsPanel;
    }

    /**
     * Creates the run button.
     *
     * @return run button
     */
    private JButton createRunButton() {
        JButton runButton = new JButton("Run Macro");
        runButton.setPreferredSize(BUTTON_SIZE);

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String macroText = macroTextArea.getText();
                if (!macroText.trim().isEmpty()) {
                    if (macroLanguageRadioButton.isSelected()) {
                        try {
                            Value value = macroController.executeMacro(uiController.getActiveSpreadsheet(), uiController, macroText);
                            macroOutputTextField.setText(value.toString());
                        } catch (MacroCompilationException | IllegalValueTypeException e) {
                            JOptionPane.showMessageDialog(null,
                                    "The inserted macro is not valid.",
                                    "Invalid macro",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (beanShellLanguageRadioButton.isSelected()) {

                        try {
                            BeanShellClassLoaderController beanShellController = new BeanShellClassLoaderController();
                            BeanShellResult result = beanShellController.createAndExecuteScript(macroText, uiController);
                            macroOutputTextField.setText(result.lastResult());
                        } catch (FileNotFoundException | EvalError | MacroCompilationException | IllegalValueTypeException ex) {
                            Logger.getLogger(MacroDialog.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });

        return runButton;
    }

    /**
     * Creates the clear button.
     *
     * @return clear button
     */
    private JButton createClearButton() {
        JButton clearButton = new JButton("Clear");
        clearButton.setPreferredSize(BUTTON_SIZE);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String macroText = macroTextArea.getText();
                if (!macroText.isEmpty()) {
                    int dialogResult = JOptionPane.showConfirmDialog(
                            null,
                            "Do you want to clear all the macro?",
                            "Warning",
                            JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        macroTextArea.setText("");
                    }
                }
            }
        });

        return clearButton;
    }

    /**
     * Creates the cancel button.
     *
     * @return cancel button
     */
    private JButton createCancelButton() {
        JButton cancelButton = new JButton("Close");
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
