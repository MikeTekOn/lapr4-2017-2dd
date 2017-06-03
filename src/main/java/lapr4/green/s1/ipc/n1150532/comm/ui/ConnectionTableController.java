package lapr4.green.s1.ipc.n1150532.comm.ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

/**
 * The controller of the table window.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class ConnectionTableController extends AbstractTableModel {

    /**
     * The table headers.
     */
    private final String[] columns = {"IP Address", "Host Name"};

    /**
     * The connections list.
     */
    private final List<ConnectionID> list;

    /**
     * The constructor of the controller.
     */
    public ConnectionTableController() {
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
        return String.class;
    }

    /**
     * It gets the value of the matching row and column.
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
        ConnectionID connection = list.get(rowIndex);
        Object data;
        if (columnIndex == 1) {
            data = connection.getAddress().getHostName();
        } else {
            data = connection.getAddress().getHostAddress();
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
     * It adds a new connection to the table. If the connection already exists
     * within the table, it ignores it.
     *
     * @param connection The connection to be added.
     */
    public void addRow(ConnectionID connection) {
        int index = list.indexOf(connection);
        if (index == -1) {
            list.add(connection);
            fireTableDataChanged();
        }
    }

    /**
     * It gets the connection with that index on the file's list.
     *
     * @param index The index of the connection to retrieve.
     * @return It returns the connection or null if the index is not valid.
     */
    public ConnectionID provideConnection(int index) {
        if (index < 0 || index >= list.size()) {
            return null;
        }
        return list.get(index);
    }

    /**
     * It removes a row from the table.
     *
     * @param connection The connection that is represented on the row.
     * @return It returns true if the file is removed or false otherwise.
     */
    private boolean removeRow(ConnectionID connection) {
        int index = list.indexOf(connection);
        if (index != -1) {
            list.remove(index);
            fireTableDataChanged();
            return true;
        }
        return false;
    }

    /**
     * It removes all connections from the list.
     */
    public void clear() {
        list.clear();
        fireTableDataChanged();
    }
}
