/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters.presentation;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.ui.ctrl.FocusOwnerAction;
import csheets.ui.ctrl.SelectionEvent;
import csheets.ui.ctrl.SelectionListener;
import csheets.ui.ctrl.UIController;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.TableAndFiltersExtension;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.TableCellExtension;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.application.TableAndFiltersController;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class TableAndFiltersPane extends JPanel implements SelectionListener {

    private JLabel labelRange;

    private final String labelRangePreFix = "Table: ";

    private TableAndFiltersController ctrl;

    /* Creates a new Table and Filters panel.
     *
     * @param uiController the user interface controller
     */
    public TableAndFiltersPane(UIController uiController) {
        super();
        // Configures panel
        BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(box);
        uiController.addSelectionListener(this);
        ctrl = new TableAndFiltersController(uiController);
        build();
        this.setName(TableAndFiltersExtension.NAME);
        this.setVisible(true);

    }

    public void build() {
        this.add(buildTableLabel());
        JPanel panelButtons = new JPanel();
        panelButtons.add(buildDefineButton());
        panelButtons.add(buildRemoveButton());

        this.add(panelButtons);
    }

    private JPanel buildTableLabel() {
        JPanel mainPanel = new JPanel();
        labelRange = new JLabel(labelRangePreFix + "not defined");
        mainPanel.add(labelRange);
        return mainPanel;
    }

    private JPanel buildDefineButton() {
        JPanel panelButton = new JPanel();
        JButton button = new JButton("Define Table");
        button.addActionListener(new CreateNewTableAction());
        panelButton.add(button);
        return panelButton;
    }

    private JPanel buildRemoveButton() {
        JPanel panelButton = new JPanel();
        JButton button = new JButton("Remove Table");
        button.addActionListener(new RemoveTableAction());
        panelButton.add(button);
        return panelButton;
    }

    @Override
    public void selectionChanged(SelectionEvent event) {
        Cell cell = event.getCell();
        if (cell == null) {
            return;
        }

        TableCellExtension cellTable = (TableCellExtension) cell.getExtension(TableAndFiltersExtension.NAME);

        if (cellTable == null) {
            return;
        } else {
            CellRange range = cellTable.getRange();
            if (range == null) {
                labelRange.setText(labelRangePreFix + "not defined");
            } else {
                labelRange.setText(labelRangePreFix + range.getFirstCell() + ":" + range.getLastCell());
            }
        }
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
                  
                        ctrl.defineTable();
                    
                      
                    labelRange.setText(labelRangePreFix + ctrl.getRange().getFirstCell() + ":" + ctrl.getRange().getLastCell());
                     JOptionPane.showMessageDialog(null, "Table defined!", "Sucess", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null, "There is a table already defined on the given range!", "Error", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

    protected class RemoveTableAction extends FocusOwnerAction implements ActionListener {

        @Override
        protected String getName() {
            return "Remove table";
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Cell activeCell = focusOwner.getSelectedCell();
            TableCellExtension tableCell = (TableCellExtension) activeCell.getExtension(TableAndFiltersExtension.NAME);
            CellRange range = tableCell.getRange();

            if (range != null) {
                ctrl.removeTable(range);
                labelRange.setText(labelRangePreFix + "not defined");
                JOptionPane.showMessageDialog(null, "Table removed!", "Sucess", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "The selected cell doesnt bellong to a table", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

}
