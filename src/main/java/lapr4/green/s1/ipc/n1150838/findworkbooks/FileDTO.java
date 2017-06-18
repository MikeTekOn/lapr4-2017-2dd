/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150838.findworkbooks;

import csheets.core.Workbook;
import eapli.framework.dto.DTO;
import java.io.Serializable;
import java.util.Objects;

/**
 * DTO to transfer file information through events
 * @author nunopinto
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

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileDTO other = (FileDTO) obj;
        if (!Objects.equals(this.fileName, other.fileName)) {
            return false;
        }
        if (!Objects.equals(this.filePath, other.filePath)) {
            return false;
        }
        return true;
    }
    
    



    
}
