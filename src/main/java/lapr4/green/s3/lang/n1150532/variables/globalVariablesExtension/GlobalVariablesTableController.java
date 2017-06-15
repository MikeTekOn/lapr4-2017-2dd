package lapr4.green.s3.lang.n1150532.variables.globalVariablesExtension;

import csheets.core.Value;
import csheets.core.formula.Expression;
import eapli.framework.application.Controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import lapr4.green.s3.lang.n1150532.variables.Variable;
import lapr4.red.s2.lang.n1150623.globalVariables.VarContentor;

/**
 * The controller of the table window.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class GlobalVariablesTableController extends AbstractTableModel implements Controller {

    /**
     * The table headers.
     */
    private final String[] columns = {"Name", "Index", "Value"};

    /**
     * The variables list.
     */
    private final List<GlobalVariableInformationDTO> list;

    /**
     * The constructor of the controller.
     */
    public GlobalVariablesTableController() {
        this.list = new ArrayList<>();
    }

    /**
     * It provides the total row count of the table.
     *
     * @return It returns the size of the file's list.
     */
    @Override
    public int getRowCount() {
        return list.size();
    }

    /**
     * It provides the total column count of the table.
     *
     * @return It returns the size of the headers' array.
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * It provides the header of the column.
     *
     * @param columnIndex The column from which to get the name.
     * @return It return the header of the column.
     */
    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    /**
     * It provides the column's object class.
     *
     * @param columnIndex The column from which to get the class.
     * @return It returns the class of the objects within the column.
     */
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 1:
                return Integer.class;
            case 2:
                return Value.class;
            default:
                return String.class;
        }
    }

    /**
     * It gets the object of the matching row and column.
     *
     * @param rowIndex The row to be searched.
     * @param columnIndex The column to be searched.
     * @return It returns the object shown in the matching row and column.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= list.size() || columnIndex < 0 || columnIndex > 2) {
            return null;
        }
        GlobalVariableInformationDTO variable = list.get(rowIndex);
        Object data;
        switch (columnIndex) {
            case 1:
                data = variable.getIndex();
                break;
            case 2:
                data = variable.getValue();
                break;
            default:
                String name = variable.getName();
                if (name.startsWith("@")) {
                    name = name.substring(1);
                }
                data = name;
                break;
        }
        return data;
    }

    /**
     * It indicates if the cell is editable.
     *
     * @param row The row to be analyzed.
     * @param column The column to be analyzed.
     * @return It always returns false. This table is not editable.
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    /**
     * It adds a new variable to the table. If it already exists, it replaces
     * the information.
     *
     * @param variable The variable information to be added.
     */
    public void addRow(GlobalVariableInformationDTO variable) {
        list.remove(variable);
        list.add(variable);
        Collections.sort(list);
        fireTableDataChanged();
    }

    /**
     * It builds the table's content with the variables within the container.
     *
     * @param variables The container of the global variables.
     */
    public void setContent(VarContentor variables) {
        list.clear();
        for (Variable variable : variables.variables()) {
            for (Map.Entry<Integer, Expression> entry : variable.getIndexes().entrySet()) {
                list.add(new GlobalVariableInformationDTO(variable.getName(), entry.getKey(), entry.getValue()));
            }
        }
        Collections.sort(list);
        fireTableDataChanged();
    }

    /**
     * It removes all global variables information from the list.
     */
    public void clear() {
        list.clear();
        fireTableDataChanged();
    }
}
