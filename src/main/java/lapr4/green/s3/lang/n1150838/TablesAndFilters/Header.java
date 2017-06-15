/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters;

import csheets.core.Cell;
import java.io.Serializable;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class Header implements Serializable {

    private int headerIndex;

    private Cell headerCell;

    public Header(int headerIndex, Cell headerCell) {
        this.headerIndex = headerIndex;
        this.headerCell = headerCell;
    }

    public boolean isHeader(int headerIndex) {
        return this.headerIndex == headerIndex;
    }

    public String getHeaderContent() {
        String content;
        if (headerCell.getFormula() != null) {
            content = headerCell.getValue().toString();
        } else {
            content = headerCell.getContent();
        }
        return content;
    }
    
    public Cell getCell(){
        return headerCell;
    }

}
