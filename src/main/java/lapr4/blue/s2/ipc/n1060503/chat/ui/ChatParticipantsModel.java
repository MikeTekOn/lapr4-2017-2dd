/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1060503.chat.ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import lapr4.blue.s2.ipc.n1060503.chat.connection.UserChatDTO;

/**
 * It represents the Panel for the chat.
 *
 * @author Sofia Gonçalves (1150657)
 */
public final class ChatParticipantsModel extends AbstractTableModel {
    
    /**
     * The table headers.
     */
    private final String[] columns = {        
        "Icon", 
        "Nickname",
        "Status"};

    /**
     * The connections list.
     */
    private final List<UserChatDTO> list;

    /**
     * The constructor of the controller.
     */
    public ChatParticipantsModel() {
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
        UserChatDTO ucp = list.get(rowIndex);
        Object data;
        switch (columnIndex) {
            case 0:                
//                data = ucp.getImage();
                data = new ImageIcon();// FIXME  image received with errors
                break;
            case 1:
                data = ucp.getUserChatProfileNickname();
                break;
            default:
                data = ucp.getStatus();
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
     * It adds a new connection to the table. If the connection already exists
     * within the table, it ignores it.
     *
     * @param ucp
     */
    public void addRow(UserChatDTO ucp) {
        boolean flag = true;
        for(UserChatDTO u : list){
            if(ucp.getUserChatProfileNickname().equals(u.getUserChatProfileNickname())){
                flag = false;
                break;
            }
        }
        if (flag) {
            list.add(ucp);
            fireTableDataChanged();
        }
    }

    /**
     * It gets the connection with that index on the file's list.
     *
     * @param index The index of the connection to retrieve.
     * @return It returns the connection or null if the index is not valid.
     */
    public UserChatDTO getUserChatDTO(int index) {
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
    private boolean removeRow(UserChatDTO ucp) {
        int index = list.indexOf(ucp);
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
