/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters;

import java.io.Serializable;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class Header implements Serializable {
    
    private int headerIndex;
    
    private String headerContent;
    
    
    public Header(int headerIndex , String headerContent){
        this.headerIndex=headerIndex;
        this.headerContent=headerContent;
    }
    
    public boolean isHeader(int headerIndex){
        return this.headerIndex==headerIndex;
    }
    
    public boolean isHeader(String headerContent){
        return this.headerContent.equals(headerContent);
    }
    
}
