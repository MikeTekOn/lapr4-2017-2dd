package lapr4.red.s3.ipc.n1150613.NetworkSearchByFile.ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lapr4.blue.s2.ipc.n1151031.searchnetwork.SearchResults;

/**
 * A class to control the search workbook in the network results table.
 *
 * @author Diogo Guedes - 1150613@isep.ipp.pt
 */
public class NetworkSearchTableController extends AbstractTableModel {

    /**
     * The table headers.
     */
    private final String[] columns = {"Host", "Workbook Name", "SS #", "Spreadsheet Names", "active"};

    /**
     * The search results list.
     */
    private final List<SearchResults> list;

    /**
     * The constructor of the controller.
     */
    public NetworkSearchTableController() {
        this.list = new ArrayList<>();
    }

    /**
     * Returns the number of search results.
     *
     * @return the size of the list.
     */
    @Override
    public int getRowCount() {
        return list.size();
    }

    /**
     * Provides the total column count of the table.
     *
     * @return the size of the headers array.
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Provides the header of the column.
     *
     * @param columnIndex The column from which to get the name.
     * @return It return the header of the column.
     */
    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    /**
     * Provides the column's object class.
     *
     * @param columnIndex The column from which to get the class.
     * @return the class of the objects within the column.
     */
    @Override
    public Class getColumnClass(int columnIndex) {
        return String.class;
    }

    /**
     * Gets the value of the matching row and column.
     *
     * @param rowIndex The row to be searched.
     * @param columnIndex The column to be searched.
     * @return the object shown in the matching row and column.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= list.size() || columnIndex < 0 || columnIndex > 3) {
            return null;
        }
        SearchResults result = list.get(rowIndex);
        Object data = result;

        if (columnIndex == 0) {
            data = result.getAddress().getHostName();
        } else if (columnIndex == 1) {
            data = result.getWorkbookName();
        } else if (columnIndex == 2) {
            data = result.getSpreadsheetList().size();
        } else if (columnIndex == 3) {
            StringBuilder spreadsheetNames = new StringBuilder();
            for (int i = 0; i < result.getSpreadsheetList().size(); i++) {
                if (i == result.getSpreadsheetList().size() - 1) {
                    spreadsheetNames.append(result.getSpreadsheetList().get(i).getTitle());
                } else {
                    spreadsheetNames.append(result.getSpreadsheetList().get(i).getTitle() + ", ");
                }
            }
            data = spreadsheetNames.toString();
        } else if (columnIndex == 4) {

        }
        return data;
    }

    /**
     * Indicates if the cell is editable.
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
     * Adds a new search result to the table. If the result already exists
     * within the table, it ignores it.
     *
     * @param result the result to add
     */
    public void addRow(SearchResults result) {
        int index = list.indexOf(result);
        if (index == -1) {
            list.add(result);
            fireTableDataChanged();
        }
    }

    /**
     * Gets the search result with that index on the file's list.
     *
     * @param index The index of the connection to retrieve.
     * @return the result or null if the index is not valid.
     */
    public SearchResults provideConnection(int index) {
        if (index < 0 || index >= list.size()) {
            return null;
        }
        return list.get(index);
    }

    /**
     * It removes all search results from the list.
     */
    public void clear() {
        list.clear();
        fireTableDataChanged();
    }
}
