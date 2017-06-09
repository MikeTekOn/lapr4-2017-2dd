/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1140822.fileShare;

import java.io.Serializable;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class FileNameDTO  implements Serializable {
    String fileName;

    public FileNameDTO(String fileName) {
        this.fileName = fileName;
    }
    
}
