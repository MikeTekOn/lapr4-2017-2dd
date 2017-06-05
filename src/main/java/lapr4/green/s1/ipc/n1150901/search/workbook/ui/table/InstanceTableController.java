/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150901.search.workbook.ui.table;

import csheets.core.Workbook;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.table.AbstractTableModel;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPClientWorker;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPClientsManager;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class InstanceTableController extends AbstractTableModel {

    /**
     * The table headers.
     */
    private final String[] columnNames = {"IP Address"};

    /**
     * The manager of clients TCP connections.
     */
    private final CommTCPClientsManager comm;

    /**
     * Map containing the connections and respective IDs.
     */
    private final Map<ConnectionID, CommTCPClientWorker> connections;

    /**
     * The connections list.
     */
    private final List<ConnectionID> list;

    /**
     * The constructor of the controller.
     */
    public InstanceTableController() {
        this.list = new ArrayList<>();
        comm = CommTCPClientsManager.getManager();
        connections = comm.getClients();
        Set<ConnectionID> ids = connections.keySet();
        for (ConnectionID id : ids) {
            this.list.add(id);
        }
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
        return columnNames.length;
    }

    /**
     * It provides the header of the column.
     *
     * @param index The column from which to get the name.
     * @return It return the header of the column.
     */
    @Override
    public String getColumnName(int index) {
        return columnNames[index];
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
        return list.get(rowIndex);
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
     * It gets the instance with that index on the instance's list.
     *
     * @param index The index of the instance to retrieve.
     * @return It returns the instance or null if the index is not valid.
     */
    public ConnectionID provideInstance(int index) {
        if (index < 0 || index >= list.size()) {
            return null;
        }
        return list.get(index);
    }

    public Workbook searchWorbook(String workbookName, ConnectionID id) {
        return comm.searchWorkbookIn(id, workbookName);
    }
}
