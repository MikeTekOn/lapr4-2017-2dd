/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150800.AdvancedFunctionWizard.presentation;

import csheets.core.IllegalValueTypeException;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.core.formula.lang.UnknownElementException;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import lapr4.red.s2.lang.n1150613.FunctionWizard.ui.IntermediateFunctionWizardUI;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class AdvancedFunctionWizardUI extends IntermediateFunctionWizardUI {

    /**
     * The panel border text
     */
    private static final String SYNTAX_TREE = "Abstract Syntax Tree:";

    /**
     * The panel that contains the tree
     */
    private JPanel panelTree;

    /**
     * Builds an instance of AdvanceFunctionWizardUI with the given parameters
     *
     * @param uIController - the user interface controller
     * @throws UnknownElementException
     */
    public AdvancedFunctionWizardUI(UIController uIController) throws UnknownElementException {
        super(uIController);

        add(createComponents());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setResizable(false);
        setMinimumSize(new Dimension(WIDTHW, LENGTH));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    protected JPanel createComponents() throws UnknownElementException {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(createPanelNorth(), BorderLayout.NORTH);
        panel.add(createPanelCenter(), BorderLayout.CENTER);
        panel.add(createPanelButons(), BorderLayout.SOUTH);
        return panel;
    }

    @Override
    protected JPanel createPanelComboIdentifiers() {
        JPanel p = new JPanel();
        comboIdentifiers = new JComboBox(controller.getFunctions().toArray());
        comboIdentifiers.setSelectedIndex(-1);
        p.setBorder(BorderFactory.createTitledBorder(IDENTIFIER));
        p.add(comboIdentifiers);

        comboIdentifiers.addActionListener((ActionEvent e) -> {
            try {
                int i;
                String auxIdentifier = "" + comboIdentifiers.getSelectedItem();
                resultField.setText("");
                int size = defaultTableModel.getRowCount();
                if (txtSyntax.getText().length() == 0) {
                    for (i = size - 1; i >= 0; i--) {
                        defaultTableModel.removeRow(i);
                    }
                }

                if (auxIdentifier != null) {
                    defaultTableModel.fireTableDataChanged();
                    try {
                        for (Map.Entry<String, String> entry : controller.getDescription(auxIdentifier).entrySet()) {
                            defaultTableModel.addRow(new Object[]{entry.getKey(), entry.getValue()});
                        }
                        setTextToCursorPosition(auxIdentifier, 0);

                    } catch (UnknownElementException u) {
                        defaultTableModel.addRow(new Object[]{"Parameter1", "1st Value"});
                        defaultTableModel.addRow(new Object[]{"Parameter2", "2nd Value"});
                        setTextToCursorPosition(auxIdentifier, 1);
                    }

                    applyBtn.setEnabled(true);

                } else {
                    txtSyntax.setText("");
                    applyBtn.setEnabled(false);
                }
            } catch (UnknownElementException u) {
                System.err.println("UnknownElementException");
            } catch (ArrayIndexOutOfBoundsException a) {
                System.err.println("ArrayIndexOutOfBoundsException!");
            }
        });

        return p;
    }

    /**
     * Sets the function text into the cursor position
     *
     * @param auxIdentifier - the function string identifier
     * @param type - the type of syntax (either 0 - functions loaded from a
     * properties file or 1 - operators and functions from java.util.Math)
     * @throws UnknownElementException
     */
    private void setTextToCursorPosition(String auxIdentifier, int type) throws UnknownElementException {
        int position = txtSyntax.getCaretPosition();
        String function = controller.getSyntax(auxIdentifier, type);
        try {
            if (!txtSyntax.getText().isEmpty()) {
                function = function.substring(1);
            }
            txtSyntax.getDocument().insertString(position, function, null);
        } catch (BadLocationException ex) {
            Logger.getLogger(IntermediateFunctionWizardUI.class.getName()).log(Level.FINEST, null, ex);
        }
    }

    @Override
    protected JPanel createPanelSyntax() {
        JPanel p = new JPanel();
        txtSyntax = new JTextField(SIZE_TXT_FIELD);
        controller.updatableEditBox(txtSyntax);

        txtSyntax.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateResult();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateResult();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateResult();
            }

            /**
             * Updates the result box and the AST.
             */
            private void updateResult() {
                try {
                    resultField.setText(controller.calculateResult(txtSyntax.getText()));
                    panelTree.removeAll();
                    JTree jtree = controller.syntaxTree();
                    JScrollPane viewerScrollPane = new JScrollPane(jtree);
                    viewerScrollPane.setPreferredSize(new Dimension(600, 250));

                    panelTree.add(viewerScrollPane);
                    panelTree.revalidate();
                    panelTree.repaint();
                } catch (FormulaCompilationException | IllegalValueTypeException | ArrayIndexOutOfBoundsException ex) {
                    resultField.setText("Invalid parameters");
                }
            }

        });

        p.add(txtSyntax);
        p.setBorder(BorderFactory.createTitledBorder(SYNTAX));
        return p;
    }
    
    private JPanel createPanelTable() {
        JPanel p = new JPanel();

        p.setBorder(BorderFactory.createTitledBorder(HELPTEXT));
        p.add(createTable());

        return p;
    }

    @Override
    protected JScrollPane createTable() {
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.LEFT);
        defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                /* The cells from the Help Table should not be editable */
                return false;
            }
        };
        defaultTableModel.setColumnIdentifiers(HEADER);
        tableHelpText = new JTable(defaultTableModel);
        tableHelpText.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        tableHelpText.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableHelpText.getColumnModel().getColumn(1).setCellRenderer(dtcr);
        tableHelpText.getColumnModel().getColumn(1).setPreferredWidth(300);
        tableHelpText.setEnabled(true);

        tableHelpText.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrPane = new JScrollPane(tableHelpText);
        scrPane.setPreferredSize(new Dimension(WIDTH_TABLE, LENGTH_TABLE));
        scrPane.setAutoscrolls(true);

        return scrPane;
    }

    @Override
    protected JPanel createPanelCenter() {
        JPanel p = new JPanel();

        p.add(createPanelTable(), BorderLayout.NORTH);
        p.add(createPanelSyntaxTree(), BorderLayout.SOUTH);

        return p;
    }

    private JPanel createPanelSyntaxTree() {
        panelTree = new JPanel();
        panelTree.setPreferredSize(new Dimension(620, 280));
        panelTree.setBorder(BorderFactory.createTitledBorder(SYNTAX_TREE));

        return panelTree;
    }

    @Override
    protected JPanel createPanelButons() {
        resultField = new JTextField();
        resultField.setPreferredSize(new Dimension(200, 20));

        FlowLayout l = new FlowLayout();
        l.setVgap(5);
        JPanel t = new JPanel();
        t.add(resultField);
        JPanel p = new JPanel(l);
        JPanel p2 = new JPanel(new BorderLayout());

        t.setBorder(BorderFactory.createTitledBorder("Result:"));
        p.setBorder(BorderFactory.createTitledBorder(OPTIONS));
        JButton bt1 = createButtonApply();
        JButton bt2 = createButtonCancel();

        getRootPane().setDefaultButton(bt1);

        p.add(bt1);
        p.add(bt2);

        p2.add(p, BorderLayout.CENTER);
        p2.add(t, BorderLayout.WEST);

        return p2;
    }

    @Override
    protected JButton createButtonApply() {
        applyBtn = new JButton("Apply");
        applyBtn.setToolTipText("Insert syntax in formula bar");
        applyBtn.setEnabled(false);
        applyBtn.addActionListener((ActionEvent e) -> {
            try {
                apply();
            } catch (FormulaCompilationException ex) {
                Logger.getLogger(IntermediateFunctionWizardUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        return applyBtn;
    }

    @Override
    protected void apply() throws FormulaCompilationException {
        try {
            String aux = txtSyntax.getText();
            if (!aux.isEmpty()) {
                controller.insertSyntaxFormulaBar(aux);
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Edit box is empty!",
                        "Function Wizard",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (FormulaCompilationException | java.lang.IllegalArgumentException i) {
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid Formula!\n"
                    + "Check if all parameters has valid values!\n\n"
                    + "e.g.  = IF( A1 > 2; \"abc\"; \"xpto\") ",
                    "Function Wizard",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
