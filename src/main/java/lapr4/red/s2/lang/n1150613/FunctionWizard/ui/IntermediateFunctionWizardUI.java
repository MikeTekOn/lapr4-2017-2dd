/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1150613.FunctionWizard.ui;

import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.core.formula.lang.UnknownElementException;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import lapr4.red.s2.lang.n1150613.FunctionWizard.application.IntermediateFunctionWizardController;

/**
 * Class that is user interface for Function Wizard
 *
 * @author Diogo Guedes
 */
public class IntermediateFunctionWizardUI extends JDialog {

    /**
     * The user interface controller
     */
    private final UIController uiController;

    /**
     * The function wizard controller
     */
    private final IntermediateFunctionWizardController controller;

    private JButton applyBtn;
    private JButton cancelBtn;
    private JComboBox comboIdentifiers;
    private JTextField txtSyntax;
    private JTable tableHelpText;
    private DefaultTableModel defaultTableModel;
    private JTextField resultField;

    private static final int WIDTHW = 300, LENGTH = 200;
    private static final int WIDTH_TABLE = 600, LENGTH_TABLE = 150;
    private static final int SIZE_TXT_FIELD = 40;
    private static final String IDENTIFIER = "Function:";
    private static final String SYNTAX = "Syntax:";
    private static final String HELPTEXT = "Help Test:";
    private static final String OPTIONS = "Options:";
    private static final String[] HEADER = {"Parameter", "Description"};

    /**
     * Build the Function Wizard window
     *
     * @param uIController interface controller
     * @throws UnknownElementException to be caught
     */
    public IntermediateFunctionWizardUI(UIController uIController) throws UnknownElementException {
        setModal(true);
        setTitle("FUNCTION WIZARD");

        this.uiController = uIController;

        controller = new IntermediateFunctionWizardController(uIController);

        add(createComponents());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancel();
            }
        });
        pack();
        setResizable(false);
        setMinimumSize(new Dimension(WIDTHW, LENGTH));
        setLocationRelativeTo(null);
        setVisible(true);

    }

    /**
     * create all ui panels
     *
     * @return all ui panels
     * @throws UnknownElementException to be caught
     */
    private JPanel createComponents() throws UnknownElementException {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(createPanelNorth(), BorderLayout.NORTH);
        panel.add(createPanelCenter(), BorderLayout.CENTER);
        panel.add(createPanelButons(), BorderLayout.SOUTH);
        return panel;
    }

    /**
     * create a panel with the combox function identifiers
     *
     * @return panel with the combox function identifiers
     */
    private JPanel createPanelComboIdentifiers() {
        JPanel p = new JPanel();
        comboIdentifiers = new JComboBox(controller.getFunctions().toArray());
        comboIdentifiers.setSelectedIndex(-1);
        p.setBorder(BorderFactory.createTitledBorder(IDENTIFIER));
        p.add(comboIdentifiers);

        comboIdentifiers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int i;
                    String auxIdentifier = "" + comboIdentifiers.getSelectedItem();
                    resultField.setText("");
                    int size = defaultTableModel.getRowCount();
                    if (size > 0) {
                        for (i = size - 1; i >= 0; i--) {
                            defaultTableModel.removeRow(i);
                        }
                    }

                    if (controller.getDescription(auxIdentifier).size() > 0) {
                        defaultTableModel.fireTableDataChanged();
                        for (Map.Entry<String, String> entry : controller.getDescription(auxIdentifier).entrySet()) {
                            defaultTableModel.addRow(new Object[]{entry.getKey(), entry.getValue()});
                        }

                        txtSyntax.setText(controller.getSyntax(auxIdentifier));
                        applyBtn.setEnabled(true);

                        defaultTableModel.addTableModelListener(
                                new TableModelListener() {
                            @Override
                            public void tableChanged(TableModelEvent evt) {
                                String s = "";
                                int tam = defaultTableModel.getRowCount();
                                for (int j = 0; j < tam; j++) {
                                    if (j != tam - 1) {
                                        System.out.println(tableHelpText.getValueAt(j, 0).toString());
                                        s = s + tableHelpText.getValueAt(j, 0).toString() + ";";
                                    } else {
                                        s = s + tableHelpText.getValueAt(j, 0).toString();
                                       
                                    }
                                    try {
                                        resultField.setText( controller.calculateResult(s, txtSyntax.getText()));
                                    } catch (FormulaCompilationException ex) {
                                        resultField.setText("Invalid parameters");
                                    }
                                }

                            }
                        });

                    } else {
                        txtSyntax.setText("");
                        applyBtn.setEnabled(false);
                    }
                } catch (UnknownElementException u) {
                    System.err.println("UnknownElementException");
                } catch (ArrayIndexOutOfBoundsException a) {
                    System.err.println("ArrayIndexOutOfBoundsException!");
                }
            }
        });

        return p;
    }

    /**
     * create panel with the edit box function syntax
     *
     * @return panel with the edit box function syntax
     */
    private JPanel createPanelSyntax() {
        JPanel p = new JPanel();
        txtSyntax = new JTextField(SIZE_TXT_FIELD);
        p.add(txtSyntax);
        p.setBorder(BorderFactory.createTitledBorder(SYNTAX));
        return p;
    }

    /**
     * create panel with the table function parameters
     *
     * @return panel with the table function parameters
     */
    private JPanel createPanelCenter() {
        JPanel p = new JPanel();

        p.setBorder(BorderFactory.createTitledBorder(HELPTEXT));
        p.add(createTable());
        return p;
    }

    /**
     * create table for function parameters
     *
     * @return table for function parameters
     */
    private JScrollPane createTable() {

        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.LEFT);
        defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 1) {
                    return false;
                } else {
                    return true;
                }
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

    /**
     * create panel with a combobox and edit box
     *
     * @return create panel with a combobox and edit box
     */
    private JPanel createPanelNorth() {
        JPanel north = new JPanel(new BorderLayout());

        north.add(createPanelComboIdentifiers(), BorderLayout.WEST);
        north.add(createPanelSyntax(), BorderLayout.CENTER);

        return north;
    }

    /**
     * create panel for the buttons
     *
     * @return panel for the buttons
     */
    private JPanel createPanelButons() {

        resultField = new JTextField();
        resultField.setPreferredSize(new Dimension(100, 20));

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

    /**
     * create Apply Button
     *
     * @return Apply Button
     */
    private JButton createButtonApply() {
        applyBtn = new JButton("Apply");
        applyBtn.setToolTipText("Insert syntax in formula bar");
        applyBtn.setEnabled(false);
        applyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    apply();
                } catch (FormulaCompilationException ex) {
                    Logger.getLogger(IntermediateFunctionWizardUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return applyBtn;
    }

    /**
     * create Cancel Button
     *
     * @return Cancel Button
     */
    private JButton createButtonCancel() {
        cancelBtn = new JButton("Cancel");
        cancelBtn.setToolTipText("Back to previous screen");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });

        return cancelBtn;
    }

    /**
     * execute when Apply Button is cliked
     */
    private void apply() throws FormulaCompilationException {
        try {
            String aux = resultField.getText();
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

    /**
     * execute when Cancel Button is cliked
     */
    private void cancel() {
        dispose();
    }

}
