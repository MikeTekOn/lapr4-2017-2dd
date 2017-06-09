/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150838.GlobalSearch.presentation;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.compiler.FormulaCompilationException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import lapr4.green.s2.core.n1150838.GlobalSearch.application.GlobalSearchController;

/**
 *
 * @author nunopinto
 */
public class ConfigPane extends JDialog {
    
    private final Dimension dimension = new Dimension(700,300);
    private ListString modelAvailableTypes;
    private JList availableTypes;
     private ListString modelSelectedTypes;
    private JList selectedTypes;
     private ListString modelFormulas;
    private JList formulas;
    private JTextField fieldFormulas;
    private GlobalSearchController ctrl;
    private JCheckBox commentsBox;
    
    
    
    public ConfigPane(GlobalSearchController ctrl){
        super(new JFrame(),"Filters");
        this.ctrl=ctrl;
        initComponents();
        setPreferredSize(dimension);
        setModal(true);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    public void initComponents(){
        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        
        JPanel typesPanelList  = new JPanel();
        BoxLayout boxLayoutX = new BoxLayout(typesPanelList,BoxLayout.X_AXIS);
        typesPanelList.add(listAvailableTypes());
        typesPanelList.add(buttonAdd());
        typesPanelList.add(listSelectedTypes());
        typesPanelList.add(buttonRemove());
        JPanel formulas = new JPanel();
        JPanel formulasList = new JPanel();
        BoxLayout boxLayoutY = new BoxLayout(formulas,BoxLayout.X_AXIS);
        formulasList.add(listFormulas());
        formulasList.add(buttonRemoveFormula());
        formulas.setLayout(boxLayoutY);
        formulas.add(panelInsertFormula());
        formulas.add(formulasList);
        
        
        mainPanel.add(typesPanelList);
        mainPanel.add(formulas);
        add(mainPanel);
        
    }
    
    public JScrollPane listAvailableTypes(){
        
        modelAvailableTypes = new ListString(new ArrayList());
        for (Enum type : Value.Type.values()) {
            modelAvailableTypes.addElement(type.toString());
        }
        availableTypes = new JList(modelAvailableTypes);
        availableTypes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        availableTypes.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Available Types: "));
        JScrollPane scrollPane = new JScrollPane(availableTypes);
        scrollPane.setPreferredSize(new Dimension(200,100));
        return scrollPane;
    }
        public JScrollPane listSelectedTypes(){
        
        modelSelectedTypes = new ListString(new ArrayList());
        selectedTypes = new JList(modelSelectedTypes);
        selectedTypes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectedTypes.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Types to include: "));
        JScrollPane scrollPane = new JScrollPane(selectedTypes);
        scrollPane.setPreferredSize(new Dimension(200,100));
        return scrollPane;
    }

    private JButton buttonAdd() {
        JButton buttonAdd = new JButton("Add");
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) availableTypes.getSelectedValue();
                if(selectedType!=null){
                    modelSelectedTypes.addElement(selectedType);
                    modelAvailableTypes.removeElement(selectedType);
                    selectedTypes.updateUI();
                    availableTypes.updateUI();
                    
                }else{
                     JOptionPane.showMessageDialog(null,
                            "Select a type to add",
                            "Invalid selection",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return buttonAdd;
    }
    private JButton buttonRemove() {
        JButton buttonRemove = new JButton("Remove");
        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) selectedTypes.getSelectedValue();
                if(selectedType!=null){
                    modelAvailableTypes.addElement(selectedType);
                    modelSelectedTypes.removeElement(selectedType);
                    availableTypes.updateUI();
                    selectedTypes.updateUI();
                }else{
                     JOptionPane.showMessageDialog(null,
                            "Select a type to remove",
                            "Invalid selection",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return buttonRemove;
    }
    
       public JScrollPane listFormulas(){
        
        modelFormulas = new ListString(new ArrayList());
        formulas = new JList(modelFormulas);
        formulas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        formulas.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Formulas to include: "));
        JScrollPane scrollPane = new JScrollPane(formulas);
        scrollPane.setPreferredSize(new Dimension(200,100));
        return scrollPane;
    }
    
     private JButton buttonRemoveFormula() {
        JButton buttonRemove = new JButton("Remove");
        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) formulas.getSelectedValue();
                
                if(selectedType!=null){
                    modelFormulas.removeElement(selectedType);
                    formulas.updateUI();
                }else{
                     JOptionPane.showMessageDialog(null,
                            "Select a formula to remove",
                            "Invalid selection",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return buttonRemove;
    }
     private JPanel panelInsertFormula(){
         JPanel mainPanel = new JPanel(new BorderLayout());
         JPanel panel = new JPanel();
      
         fieldFormulas = new JTextField();
         fieldFormulas.setPreferredSize(new Dimension(120,30));
         panel.add(new JLabel("Insert formulas:"));
         panel.add(fieldFormulas);
         panel.add(buttonAddFormula());
         mainPanel.add(panel,BorderLayout.CENTER);
         commentsBox = new JCheckBox("Include comments");
         mainPanel.add(commentsBox,BorderLayout.SOUTH);
         
         return mainPanel;
     }
     
         private JButton buttonAddFormula() {
        JButton buttonAdd = new JButton("Add");
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String formula = fieldFormulas.getText();
                try {
                    if(formula.length()!=0 && ctrl.validateFormula(formula)){
                        modelFormulas.addElement(formula);
                        formulas.updateUI();
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "Write a formula to add",
                                "Invalid selection",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (FormulaCompilationException | IllegalValueTypeException ex) {
                    JOptionPane.showMessageDialog(null,
                                "Invalid Formula!",
                                "Invalid Formula",
                                JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return buttonAdd;
    }
         
    public List<String> typesToInclude(){
      return modelSelectedTypes.getValues();
    }
    
    public List<String> formulasToInclude(){
        return modelFormulas.getValues();
    }
    
    public boolean includeComments(){
        return commentsBox.isSelected();
    }
    
}
