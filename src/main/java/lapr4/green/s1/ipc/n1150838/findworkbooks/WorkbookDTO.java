/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150838.findworkbooks;

import csheets.core.Workbook;
import java.io.Serializable;

/**
 *
 * @author nunopinto
 */
public class WorkbookDTO implements Serializable, Comparable<WorkbookDTO>{
    
    private Workbook workbook;
    private String fileName;
    
    public WorkbookDTO(Workbook workbook,String fileName){
        this.workbook=workbook;
        this.fileName=fileName;
    }
    
    /**
     * @return the workbook
     */
    public Workbook getWorkbook() {
        return workbook;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    @Override
    public int compareTo(WorkbookDTO o) {
        return getFileName().compareTo(o.getFileName());
    }
    
    @Override
    public WorkbookDTO clone() {
        return new WorkbookDTO(workbook,fileName);
    }

    
}
