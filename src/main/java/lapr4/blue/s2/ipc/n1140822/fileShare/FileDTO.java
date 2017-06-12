package lapr4.blue.s2.ipc.n1140822.fileShare;

import java.io.Serializable;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class FileDTO implements Serializable {

    private String fileName;
    private int fileSize;
    private byte[] fileData;

    public FileDTO(String fileName, int fileSize, byte[] fileData) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileData = fileData;
    }

    /**
     * Obtains the file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Obtains the file size.
     *
     * @return the file size
     */
    public int getFileSize() {
        return fileSize;
    }

    /**
     * Obtains the file data.
     *
     * @return the file data.
     */
    public byte[] getFileData() {
        return fileData;
    }

}
