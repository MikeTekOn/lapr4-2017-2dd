package lapr4.green.s1.ipc.n1150532.comm;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class CommTCPClientsManager {

    private final Map<String,CommTCPClientWorker> clients;
    private Map<Class, CommHandler> handlers;

    public CommTCPClientsManager(){
        clients = new HashMap<>();
        handlers = new HashMap<>();
    }

    public void addHandler(Class dto, CommHandler handler) {
        handlers.put(dto, handler);
    }

    public CommHandler getHandler(Class dto) {
        return handlers.get(dto);
    }

    public void requestConnectionTo(String serverAddress, int portNumber){
        //TODO
    }

    private void addClient(String serverAddress, CommTCPClientWorker worker){
        clients.put(serverAddress, worker);
    }

}
