/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters.domain;

import csheets.core.Cell;
import java.io.Serializable;
import java.util.List;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.green.s3.lang.n1150838.TablesAndFilters.Header;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class Table implements Serializable {
    
    private List<Cell> bodyCells;
    
    private List<Cell> headerCells;

    private CellRange range;

    private Header header;

    public Table(CellRange range) {
        this.range=range;
    }

    /**
     * @return the range
     */
    public CellRange getRange() {
        return range;
    }

    /**
     * @return the header
     */
    public Header getHeader() {
        return header;
    }

    /**
     * @param range the range to set
     */
    public void setRange(CellRange range) {
        this.range = range;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(Header header) {
        this.header = header;
    }

}
