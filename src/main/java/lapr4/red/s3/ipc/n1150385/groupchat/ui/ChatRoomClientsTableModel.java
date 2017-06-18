package lapr4.red.s3.ipc.n1150385.groupchat.ui;

import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import lapr4.red.s2.lang.n1150385.beanshell.utils.Pair;
import lapr4.red.s3.ipc.n1150385.groupchat.dto.ChatRoomsResponseDTO;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Created by Ricardo Catalao (1150385)
 */
public class ChatRoomClientsTableModel extends AbstractTableModel {

    public static final int COLUMN_COUNT = 2;
    public static final String[] columnNames = new String[]{"IP Address", "Peer Name"};
    public static final Class<?>[] classes = new Class<?>[]{InetAddress.class, String.class};

    ArrayList<Pair<InetAddress, String>> list = new ArrayList<>();

    public ChatRoomClientsTableModel() {
        super();
    }

    public void add(InetAddress address, String clientName){
        Pair newPair = new Pair<>(address, clientName);
        for(Pair p : list){
            if(p.getKey().equals(newPair.getKey()))
                return;
        }
        list.add(newPair);
        this.fireTableChanged(new TableModelEvent(this));
    }

    public void remove(int index){
        if(index >= 0 && index < list.size()){
            list.remove(index);
            this.fireTableChanged(new TableModelEvent(this));
        }
    }

    public void remove(InetAddress address, String clientName){
        Pair newPair = new Pair<>(address, clientName);
        for(Pair p : list){
            if(p.getKey().equals(newPair.getKey()))
                return;
        }
        list.remove(newPair);
        this.fireTableChanged(new TableModelEvent(this));
    }

    public InetAddress getAddress(int index){
        return list.get(index).getKey();
    }

    public String getClientName(int index){
        return list.get(index).getValue();
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
        Pair obj = list.get(i);
        switch(i1){
            case 0:
                return obj.getKey();
            case 1:
                return obj.getValue();
        }
        return null;
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        return;
    }
}
