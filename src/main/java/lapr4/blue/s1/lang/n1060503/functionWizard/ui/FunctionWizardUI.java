/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s1.lang.n1060503.functionWizard.ui;

import csheets.core.formula.lang.UnknownElementException;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import lapr4.blue.s1.lang.n1060503.functionWizard.FunctionWizardController;

/**
 *
 * @author Pedro Fernandes
 */
public class FunctionWizardUI extends JDialog{
    
    /** The user interface controller */
    private UIController uiController;
    
    /** The function wizard controller */
    private FunctionWizardController controller;
    
    private JButton applyBtn;
    private JButton cancelBtn;
    private JComboBox comboIdentifiers;
    private JTextField txtSyntax;
    private JTable tableHelpText;
    private DefaultTableModel defaultTableModel;
    
    private static final int WIDTHW = 300, LENGTH = 200;
    private static final int SIZE_TXT_FIELD = 50;
    private static final String IDENTIFIER = "Function:";
    private static final String SYNTAX = "Syntax:";
    private static final String HELPTEXT = "Help Test:";
    private static final String OPTIONS = "Options:";
    private static final String[] column={"Parameter", "Description"};
    
    public FunctionWizardUI(UIController uIController) throws UnknownElementException{
        setModal(true);
        setTitle("FUNCTION WIZARD");
        this.uiController = uIController;
        
        controller = new FunctionWizardController(uIController);
        
        add(createComponents());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancel();
            }
        });
        pack();
        setResizable(true);
        setMinimumSize(new Dimension(WIDTHW, LENGTH));
        setLocationRelativeTo(null);
        setVisible(true);
    
    }
    
    private JPanel createComponents() throws UnknownElementException {
        JPanel panel = new JPanel(new BorderLayout());
        
        panel.add(createPanelNorth(), BorderLayout.NORTH);
        panel.add(createPanelCenter(), BorderLayout.CENTER);
        panel.add(createPanelButons(), BorderLayout.SOUTH);
        return panel;
    }
    
    private JPanel createPanelComboIdentifiers(){
        JPanel p = new JPanel();
        comboIdentifiers = new JComboBox(controller.getFunctions().toArray());
        p.setBorder(BorderFactory.createTitledBorder(IDENTIFIER));
        p.add(comboIdentifiers);
        
        comboIdentifiers.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                try {
                    /**
                     * comboIdentifiers.getSelectedItem().toString()
                     * txtSyntax.setText(controller.getFunction());
                     * */
                    defaultTableModel = new DefaultTableModel();
                    defaultTableModel.addColumn(column);
                    for (Map.Entry<String,String> entry : controller.getFunction("IF").entrySet()) {
                        defaultTableModel.addRow(new Object[] { entry.getKey(), entry.getValue() });
                    }                    
                } catch (UnknownElementException ex) {
                    Logger.getLogger(FunctionWizardUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        return p;
    }
    
    private JPanel createPanelSyntax(){
        JPanel p = new JPanel();
        txtSyntax = new JTextField(SIZE_TXT_FIELD);
        p.add(txtSyntax);
        p.setBorder(BorderFactory.createTitledBorder(SYNTAX));
        return p;
    }
    
    private JPanel createPanelCenter(){
        JPanel p = new JPanel();        
        tableHelpText = createTable();
        
        p.setBorder(BorderFactory.createTitledBorder(HELPTEXT));
        p.add(tableHelpText);
        return p;
    }
    
    private JTable createTable(){   
        defaultTableModel = new DefaultTableModel(column, 3);   
        //defaultTableModel.addColumn(column);
        tableHelpText = new JTable(defaultTableModel);
        tableHelpText.add(new JScrollPane());
       
        return tableHelpText;
    }

    private JPanel createPanelNorth(){
        JPanel north = new JPanel();  
        
        north.add(createPanelComboIdentifiers(), BorderLayout.WEST);
        north.add(createPanelSyntax(), BorderLayout.CENTER);
        
        return north;
    }
    
    private JPanel createPanelButons() {

        FlowLayout l = new FlowLayout();
        l.setVgap(5);

        JPanel p = new JPanel(l);
        
        p.setBorder(BorderFactory.createTitledBorder(OPTIONS));
        JButton bt1 = createButtonApply();
        JButton bt2 = createButtonCancel();

        getRootPane().setDefaultButton(bt1);

        p.add(bt1);
        p.add(bt2);

        return p;
    }
    
    private JButton createButtonApply(){
        applyBtn = new JButton("Apply");
        applyBtn.setMnemonic(KeyEvent.VK_A);
        applyBtn.setToolTipText("Insert syntax in formula bar");
        applyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    apply();
            }
        });

        return applyBtn;
    }
    
    private JButton createButtonCancel(){
        cancelBtn = new JButton("Cancel");
        cancelBtn.setMnemonic(KeyEvent.VK_C);
        cancelBtn.setToolTipText("Back to previous screen");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });

        return cancelBtn;
    }
    
    private void apply(){
    }
    
    private void cancel(){
        dispose();
    }
    
}
