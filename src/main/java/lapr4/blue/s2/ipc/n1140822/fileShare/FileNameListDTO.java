package lapr4.blue.s2.ipc.n1140822.fileShare;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Map;
import java.util.Observable;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionIDImpl;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class FileNameListDTO extends Observable implements Serializable {

    /**
     * The file data map.
     */
    private Map<String, Integer> fileDataMap;
    
    /**
     * The connection id.
     */
    private ConnectionID connection;
    
    /**
     * The TCP port.
     */
    private int tcpPort;

    public FileNameListDTO(Map<String, Integer> fileDataMap, int tcpPort) {
        this.fileDataMap = fileDataMap;
        this.tcpPort = tcpPort;

    }

    /**
     * Obtains the TCP port.
     * 
     * @return the TCP port
     */
    public int tcpPort()
    {
        return this.tcpPort;
    }
    
    /**
     * Obtains the files map.
     * 
     * @return the files map
     */
    public Map<String, Integer> filesMap() {
        return this.fileDataMap;
    }

    /**
     * Obtains the connection owner.
     * 
     * @return the connection owner
     */
    public String getConnectionOwner() {
        return connection.getAddress().toString();
    }

    /**
     * Builds the connection id from InetAddress.
     * 
     * @param addr the InetAddress
     */
    public void buildConnectionID(InetAddress addr) {
        this.connection = new ConnectionIDImpl(addr, tcpPort);
    }

    /**
     * Obtains the connection id.
     * 
     * @return 
     */
    public ConnectionID connID() {
        return connection;
    }

    /**
     * Obtains true if equals to the object passed as parameter, false otherwise.
     * 
     * @param otherObject the object passed as parameter.
     * @return true if equals to the object passed as parameter, false otherwise.
     */
    public boolean equals(Object otherObject) {
        FileNameListDTO otherFileList = (FileNameListDTO) otherObject;
        return this.fileDataMap.equals(otherFileList.fileDataMap);
    }
}
