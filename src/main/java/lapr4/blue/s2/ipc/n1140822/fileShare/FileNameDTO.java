package lapr4.blue.s2.ipc.n1140822.fileShare;

import java.io.Serializable;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class FileNameDTO implements Serializable {

    String fileName;

    public FileNameDTO(String fileName) {
        this.fileName = fileName;
    }

}
