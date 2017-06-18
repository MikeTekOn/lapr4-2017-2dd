package lapr4.red.s3.ipc.n1150385.groupchat.ui;

import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import lapr4.red.s3.ipc.n1150385.groupchat.dto.ChatRoomsResponseDTO;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class ChatRoomsTableModel extends AbstractTableModel {

    public static final int COLUMN_COUNT = 2;
    public static final String[] columnNames = new String[]{"IP Address", "Room Name"};
    public static final Class<?>[] classes = new Class<?>[]{InetAddress.class, String.class};

    ArrayList<ChatRoomsResponseDTO> list = new ArrayList<>();

    public ChatRoomsTableModel() {
        super();
    }

    public void add(ChatRoomsResponseDTO obj){
        if(!list.contains(obj)){
            list.add(obj);
            this.fireTableChanged(new TableModelEvent(this));
        }
    }

    public void remove(ChatRoomsResponseDTO obj){
        if(list.contains(obj)){
            list.remove(obj);
            this.fireTableChanged(new TableModelEvent(this));
        }
    }

    public ConnectionID getChatRoomConnectionID(int index){
        return list.get(index).owner;
    }

    public String getChatRoomName(int index){
        return list.get(index).chatRoomName;
    }

    public void clear(){
        this.list.clear();
        this.fireTableChanged(new TableModelEvent(this));
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public String getColumnName(int i) {
        return columnNames[i];
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return classes[i];
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        ChatRoomsResponseDTO obj = list.get(i);
        switch(i1){
            case 0:
                return obj.owner.getAddress();
            case 1:
                return obj.chatRoomName;
        }
        return null;
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        return;
    }
}
