/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150613.NetworkSearchByFile;

import csheets.core.Workbook;
import eapli.framework.dto.DTO;
import java.io.Serializable;

/**
 * DTO to transfer file information through events
 * @author Diogo Guedes
 */
public class FileDTO implements Serializable, Comparable<FileDTO>, DTO {

    private String fileName;
    private String filePath;
    
    public FileDTO(String fileName,String filePath){
        this.fileName=fileName;
        this.filePath=filePath;
    }
    

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }
    
        /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    @Override
    public int compareTo(FileDTO o) {
        return getFileName().compareTo(o.getFileName());
    }
    /**
     * clones the DTO
     * @return 
     */
    @Override
    public FileDTO clone() {
        return new FileDTO(fileName,filePath);
    }



    
}
