/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters.presentation;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import csheets.ui.ctrl.FocusOwnerAction;
import csheets.ui.ctrl.SelectionEvent;
import csheets.ui.ctrl.SelectionListener;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import lapr4.green.s2.core.n1150838.GlobalSearch.presentation.CustomListString;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.TableAndFiltersExtension;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.application.TableAndFiltersController;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.domain.Table;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class TableAndFiltersPane extends JPanel implements SelectionListener  {

    private JLabel labelRange;

    private final String labelRangePreFix = "Table: ";

    private TableList modelTable;
    
    private JList listTables;
    
    private CustomListString modelFilters;
    
    private JList listFilters;
    
    private JTextField field;

    private TableAndFiltersController ctrl;
    
    /* Creates a new Table and Filters panel.
     *
     * @param uiController the user interface controller
     */
    public TableAndFiltersPane(UIController uiController) {
        super();
        // Configures panel
        uiController.addSelectionListener(this);
        ctrl = new TableAndFiltersController(uiController);
        build();
        setName(TableAndFiltersExtension.NAME);
        setVisible(true);
 

    }

    public void build() 
    {
        BoxLayout box = new BoxLayout(this,BoxLayout.Y_AXIS);
        setLayout(box);
        
       add(buildTableList());
        JPanel panelButtons = new JPanel();
        panelButtons.add(buildDefineButton());
        panelButtons.add(buildRemoveButton());
        add(panelButtons);
        add(buildFiltersList());
        add(buildFiltersField());
        panelButtons = new JPanel();
        panelButtons.add(buttonAdd());
        panelButtons.add(buttonRemove());
        add(panelButtons);

    }

    private JScrollPane buildTableList() {
        modelTable = new TableList(new ArrayList());
         listTables = new JList(modelTable);
         listTables.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.locationToIndex(evt.getPoint());
                if (index >= 0) {
                   
                }

            }
        });
        listTables.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listTables.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Tables: "));
        JScrollPane pane = new JScrollPane(listTables);
        pane.setPreferredSize(new Dimension(100, 100));
        return pane;
    }
    
    private void buildTableContent(){
        modelTable.removeAll();
        for (Table activeTable : ctrl.activeTables()) {
            modelTable.addElement(activeTable);
        }
        listTables.updateUI();
    }
    
    private JScrollPane buildFiltersList(){
        modelFilters = new CustomListString(new ArrayList());
         listFilters = new JList(modelFilters);
        listFilters.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listFilters.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Filters: "));
        JScrollPane pane = new JScrollPane(listFilters);
        pane.setPreferredSize(new Dimension(100, 100));
        return pane;
    }
    
    private JPanel buildFiltersField(){
        JPanel pane = new JPanel(new BorderLayout());

        JLabel insert = new JLabel("Insert Filters");
         field = new JTextField();
         field.setPreferredSize(new Dimension(150,30));
         pane.add(insert,BorderLayout.WEST);
         pane.add(field,BorderLayout.CENTER);

         return pane;
                 
    }
    private JButton buttonAdd(){
        JButton button = new JButton("Add");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }

        });
        
        return button;   
    }
       private JButton buttonRemove(){
        JButton button = new JButton("Remove");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }

        });
        
        return button;   
    }
    
    @Override
    public void selectionChanged(SelectionEvent event){

        Spreadsheet spreadsheet = event.getSpreadsheet();

        if(ctrl != null && spreadsheet!=null 
               ){
            buildTableContent();
            
            
        }
    }

    private JButton buildDefineButton() {
       
        JButton button = new JButton("Define Table");
        button.addActionListener(new CreateNewTableAction());
        
        return button;
    }

    private JButton buildRemoveButton() {
     
        JButton button = new JButton("Remove Table");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object table = listTables.getSelectedValue();
                if(table!=null){
                    modelTable.setSelectedItem((String) table);
                    ctrl.removeTable(modelTable.getSelectedItem());
                    modelTable.removeElement(modelTable.getSelectedItem());
                    listTables.updateUI();
                  
                }else{
                    JOptionPane.showMessageDialog(null, "Select a table to remove!", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }

        });
        
        return button;
    }

    protected class CreateNewTableAction extends FocusOwnerAction implements ActionListener {

        @Override
        protected String getName() {
            return "Add table";
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ArrayList<Cell> selectedCells = focusOwner.getSelectedCellsList();
            int size = selectedCells.size();
            if (size == 0) {
                JOptionPane.showMessageDialog(null, "Select cells to define a table", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (ctrl.setSelectedCells(selectedCells)) {
                if (ctrl.isAvailableToDefine()) {

                    if (ctrl.defineTable()) {
                        modelTable.addElement(ctrl.getTable());
                        listTables.updateUI();
                        JOptionPane.showMessageDialog(null, "Table defined!", "Sucess", JOptionPane.INFORMATION_MESSAGE);
             
                    } else {
                        JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "There is a table already defined on the given range!", "Error", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

}
