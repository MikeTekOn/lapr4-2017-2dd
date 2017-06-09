/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1140822.fileShare;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Map;
import java.util.Observable;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class FileNameListDTO extends Observable implements Serializable{

    private Map<String, Integer> fileDataMap;
   private ConnectionID connection;

    public FileNameListDTO(Map<String, Integer> fileDataMap,ConnectionID connection) {
        this.fileDataMap = fileDataMap;
        this.connection= connection;
    }

    public Map<String, Integer> filesMap() {
        return this.fileDataMap;
    }

    public String getConnectionOwner() {
        return connection.getAddress().toString();
    }

   
    
    public boolean equals(Object otherObject)
    {
        FileNameListDTO otherFileList = (FileNameListDTO)otherObject;
        return this.fileDataMap.equals(otherFileList.fileDataMap);
    }
}
