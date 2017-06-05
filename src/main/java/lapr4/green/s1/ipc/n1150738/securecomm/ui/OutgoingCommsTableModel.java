package lapr4.green.s1.ipc.n1150738.securecomm.ui;

import lapr4.green.s1.ipc.n1150532.comm.CommTCPClientWorker;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPClientsManager;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import lapr4.green.s1.ipc.n1150532.comm.connection.NewConnectionMadeEvent;
import lapr4.green.s1.ipc.n1150738.securecomm.NewConnectionOnMangerEvent;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by henri on 6/5/2017.
 */
public class OutgoingCommsTableModel extends AbstractTableModel  implements Observer {

    public static final String[] COLUMNS = {"Destination", "Transmission Context", "Data"};

    CommTCPClientsManager manager;

    List<CommTCPClientWorker> workers;

    public OutgoingCommsTableModel() {
        manager = CommTCPClientsManager.getManager();
        workers = new LinkedList<>();
        manager.getClients().forEach((ConnectionID id, CommTCPClientWorker wk)->{workers.add(wk);});
        manager.addObserver(this);
    }

    @Override
    public int getRowCount() {
        return manager.getClients().size();
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CommTCPClientWorker wk = workers.get(rowIndex);
        switch(columnIndex){
            case 0:
                return wk.getServerIPAddress();
            case 1:
                return wk.getTransmissionContext().securityDesc();
            case 2:
                return "...";
        }
        return null;
    }


    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof NewConnectionOnMangerEvent){
            workers.clear();
            manager.getClients().forEach((ConnectionID id, CommTCPClientWorker wk)->{workers.add(wk);});
            fireTableDataChanged();
        }
    }
}
