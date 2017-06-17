/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters.presentation;

import csheets.core.Cell;
import csheets.core.CellImpl;
import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import csheets.core.SpreadsheetImpl;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ui.ctrl.EditEvent;
import csheets.ui.ctrl.EditListener;
import csheets.ui.ctrl.FocusOwnerAction;
import csheets.ui.ctrl.SelectionEvent;
import csheets.ui.ctrl.SelectionListener;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.TableAndFiltersExtension;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.application.TableAndFiltersController;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.domain.DataRow;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.domain.Row;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.domain.Table;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.exception.InvalidTableException;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class TableAndFiltersPane extends JPanel implements SelectionListener {

    private TableList modelTable;

    private JList listTables;

    private final String PREFIX = "Used Filter: ";
    private JTextField field;

    private JLabel labelFilters;

    private TableAndFiltersController ctrl;

    private UIController uiCtrl;

    /* Creates a new Table and Filters panel.
     *
     * @param uiController the user interface controller
     */
    public TableAndFiltersPane(UIController uiController) {
        super();
        // Configures panel
        CreateNewListener listener = new CreateNewListener();
        uiController.addEditListener(listener);
        uiController.addSelectionListener(this);
        ctrl = new TableAndFiltersController(uiController);
        build();
        this.uiCtrl = uiController;
        setName(TableAndFiltersExtension.NAME);
        setVisible(true);

    }

    public void build() {

        setLayout(new BorderLayout());
        JPanel upper = new JPanel(new BorderLayout());
        upper.add(buildTableList(), BorderLayout.CENTER);

        JPanel panelButtons = new JPanel();
        panelButtons.add(buildDefineButton());
        panelButtons.add(buildRemoveButton());
        upper.add(panelButtons, BorderLayout.SOUTH);
        add(upper, BorderLayout.NORTH);
        JPanel bottom = new JPanel(new BorderLayout());
        add(buildLabelFilters(), BorderLayout.CENTER);
        bottom.add(buildFiltersField(), BorderLayout.CENTER);
        panelButtons = new JPanel();
        panelButtons.add(buttonAdd());
        panelButtons.add(buttonRemove());
        bottom.add(panelButtons, BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);

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
                    modelTable.setSelectedItem((String) modelTable.getElementAt(index));
                    labelFilters.setText(PREFIX + modelTable.getSelectedItem().getFilter());
                }

            }
        });
        listTables.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listTables.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Tables: "));
        JScrollPane pane = new JScrollPane(listTables);
        pane.setPreferredSize(new Dimension(100, 300));
        pane.setMinimumSize(new Dimension(100, 300));
        return pane;
    }

    private void buildTableContent() {
        modelTable.removeAll();
        for (Table activeTable : ctrl.activeTables()) {
            modelTable.addElement(activeTable);
        }

        listTables.updateUI();
    }

    private JPanel buildFiltersField() {
        JPanel pane = new JPanel(new GridLayout(1, 1));

        JLabel insert = new JLabel("Insert Filters");
        field = new JTextField();
        field.setPreferredSize(new Dimension(150, 30));
        pane.add(insert);
        pane.add(field);

        return pane;

    }

    private JButton buttonAdd() {
        JButton button = new JButton("Add");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String table = ((String) listTables.getSelectedValue());
                if (table != null) {
                    modelTable.setSelectedItem(table);
                    try {

                        List<Row> rows = ctrl.verifyFormula(modelTable.getSelectedItem(), field.getText());
                        resetStyle(modelTable.getSelectedItem());
                        updateInvalidRows(rows);
                        modelTable.getSelectedItem().setFilter(field.getText());
                        labelFilters.setText(PREFIX + modelTable.getSelectedItem().getFilter());
                    } catch (FormulaCompilationException | IllegalValueTypeException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Formula!", "Error", JOptionPane.WARNING_MESSAGE);

                    } catch (IndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Index!", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Select a table to add the Filter!", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }

        });

        return button;
    }

    private JButton buttonRemove() {
        JButton button = new JButton("Remove");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String table = ((String) listTables.getSelectedValue());
                if (table != null) {

                    modelTable.setSelectedItem(table);
                    modelTable.getSelectedItem().setFilter("none");
                    resetStyle(modelTable.getSelectedItem());
                    labelFilters.setText(PREFIX + modelTable.getSelectedItem().getFilter());

                } else {
                    JOptionPane.showMessageDialog(null, "Select a table to remove his filter!", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }

        });

        return button;
    }

    @Override
    public void selectionChanged(SelectionEvent event) {

        Spreadsheet spreadsheet = event.getSpreadsheet();
        labelFilters.setText(PREFIX + "none");
        if( spreadsheet!=null){
            
        }
        if (ctrl != null && spreadsheet != null) {
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
                if (table != null) {
                    modelTable.setSelectedItem((String) table);
                    ctrl.removeTable(modelTable.getSelectedItem());
                    modelTable.removeElement(modelTable.getSelectedItem());
                    listTables.updateUI();

                } else {
                    JOptionPane.showMessageDialog(null, "Select a table to remove!", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }

        });

        return button;
    }

    private void updateInvalidRows(List<Row> rows) {

        for (Row row : rows) {
            for (Object object : row) {

                Cell cell = (Cell) object;
                StylableCell sCell = (StylableCell) cell.getExtension(StyleExtension.NAME);
                cell.addCellListener(sCell);
                sCell.setForegroundColor(sCell.getBackgroundColor());

                ((CellImpl) cell).updateCellStyle();
            }
        }

    }

    private void resetStyle(Table d) {
        for (int i = 1; i < d.getCells().size(); i++) {
            DataRow row = (DataRow) d.getRow(i);
            for (Object object : row) {
                Cell cell = (Cell) object;
                StylableCell sCell = (StylableCell) cell.getExtension(StyleExtension.NAME);
                cell.addCellListener(sCell);
                sCell.setForegroundColor(Color.BLACK);
                ((CellImpl) cell).updateCellStyle();
            }

        }
    }

    private JLabel buildLabelFilters() {
        labelFilters = new JLabel("Filters Used: none ");
        return labelFilters;
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
                try {
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
                } catch (InvalidTableException ex) {
                     JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

    protected class CreateNewListener implements EditListener {
        
        @Override
        public void workbookModified(EditEvent event) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    
                    Cell activeCell = uiCtrl.getActiveCell();
                    activeCell=uiCtrl.getActiveSpreadsheet().getCell(activeCell.getAddress().getColumn(), activeCell.getAddress().getRow()-1);
                    Table d = ((SpreadsheetImpl) uiCtrl.getActiveSpreadsheet()).getTable(activeCell);
                    if (d != null && !d.getFilter().equals("none")) {
                        
                        List<Row> rows;
                        try {
                            
                            rows = ctrl.verifyFormula(d, d.getFilter());
                            resetStyle(d);
                            updateInvalidRows(rows);
                        } catch (FormulaCompilationException | IllegalValueTypeException ex) {
                            resetStyle(d);
                            JOptionPane.showMessageDialog(null, "Invalid Formula!", "Error", JOptionPane.WARNING_MESSAGE);

                        } catch (IndexOutOfBoundsException ex) {
                            resetStyle(d);
                            JOptionPane.showMessageDialog(null, "Invalid Index!", "Error", JOptionPane.WARNING_MESSAGE);
                        }

                    }
                }

            });

        }

    }

}
