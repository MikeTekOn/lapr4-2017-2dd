package lapr4.blue.s1.lang.n1151159.macros.ui;

import bsh.EvalError;
import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.ui.ctrl.UIController;
import lapr4.blue.s1.lang.n1151159.macros.MacroController;
import lapr4.blue.s1.lang.n1151159.macros.compiler.MacroCompilationException;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.blue.s1.lang.n1140822.beanshellwindow.BeanShellClassLoaderController;
import lapr4.blue.s1.lang.n1140822.beanshellwindow.BeanShellResult;
import lapr4.blue.s1.lang.n1151159.macros.MacroExtension;

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
    private Dimension BUTTON_SIZE = new Dimension(115, 30);

    /**
     * Creates an instance of macro dialog.
     *
     * @param uiController the user interface controller
     */
    public MacroDialog(UIController uiController) {
        setLocationRelativeTo(null);
        this.uiController = uiController;
        createComponents();
        pack();
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
           macroLanguageRadioButton .addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    macroTextArea.setText("");
            }
        });
        beanShellLanguageRadioButton = new JRadioButton("Bean Shell");
        beanShellLanguageRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //@1140822 Renato
                    //ONLY FOR THE DEFAULT SCRIPT - TO BE REMOVED IN SECOND ITERATION
                    Scanner scan = new Scanner(new File(MacroExtension.class.getResource("res/script/default_script.bsh").getFile()));
                    while (scan.hasNextLine()) {
                        macroTextArea.append(scan.nextLine()+"\n");
                    }
                } catch (FileNotFoundException ex) {
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
        JPanel macroTextAreaPanel = new JPanel();

        macroTextArea = new JTextArea(MACRO_TEXT_AREA_ROWS, MACRO_TEXT_AREA_COLUMNS);
        macroTextArea.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        JScrollPane macroTextAreaScrollPane = new JScrollPane(macroTextArea);

        macroTextAreaPanel.add(macroTextAreaScrollPane);

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
                            Value value = macroController.executeMacro(uiController.getActiveSpreadsheet(), macroText);
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
