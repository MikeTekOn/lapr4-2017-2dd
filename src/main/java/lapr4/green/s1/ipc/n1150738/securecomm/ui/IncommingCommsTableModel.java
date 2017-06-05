package lapr4.green.s1.ipc.n1150738.securecomm.ui;

import lapr4.green.s1.ipc.n1150532.comm.*;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import lapr4.green.s1.ipc.n1150738.securecomm.NewConnectionOnMangerEvent;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by henri on 6/5/2017.
 */
public class IncommingCommsTableModel extends AbstractTableModel  implements Observer {

    public static final String[] COLUMNS = {"Source", "Transmission Context", "Data"};

    CommTCPServer server;

    List<CommTCPServerWorker> workers;

    public IncommingCommsTableModel() {
        server = CommTCPServer.getServer();
        workers = new LinkedList<>();
        server.getWorkers().forEach(workers::add);
    }

    @Override
    public int getRowCount() {
        return workers.size();
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
        CommTCPServerWorker wk = workers.get(rowIndex);
        switch(columnIndex){
            case 0:
                return wk.getRemoteAdress();
            case 1:
                return wk.getTransmissionContext().securityDesc();
            case 2:
                return "...";
        }
        return null;
    }


    @Override
    public void update(Observable o, Object arg) {

    }
}
