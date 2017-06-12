package lapr4.blue.s2.ipc.n1151452.netanalyzer.ui;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficEvent;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.util.CustomUtil;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;

/**
 * The Traffic Logger Table Model
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 10/06/17.
 */
public class TrafficLogTableModel extends AbstractTableModel {

    private static final int MAX_ROWS = 15;

    /**
     * The table headers
     */
    private final String[] columns = {"TYPE", "Address", "Traffic"};

    /**
     * The traffic event list
     */
    private final LinkedList<TrafficEvent> list;

    /**
     * The constructor of table model
     */
    TrafficLogTableModel() {

        list = new LinkedList<>();
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (rowIndex < 0 || rowIndex >= list.size() || columnIndex < 0 || columnIndex > 2) {
            return null;
        }

        TrafficEvent event = list.get(rowIndex);
        String value;
        switch (columnIndex) {
            case 0:
                value = event.type().name() + (event.isSecured() ? " [s]" : " [u]");
                break;
            case 1:
                value = String.format("%s:%d", event.ipAddress().getHostAddress(), event.port());
                break;
            default:
                value = CustomUtil.readableByteCount(event.count(), true);
        }

        return value;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    /**
     * Adds a new traffic event to the table.
     *
     * @param event the traffic event to be added.
     */
    void addRow(TrafficEvent event) {
        if (list.size() >= MAX_ROWS) {
            list.pop();
        }
        list.add(event);
        fireTableDataChanged();
    }

    /**
     * It removes all connections from the list.
     */
    public void clear() {
        list.clear();
        fireTableDataChanged();
    }
}
