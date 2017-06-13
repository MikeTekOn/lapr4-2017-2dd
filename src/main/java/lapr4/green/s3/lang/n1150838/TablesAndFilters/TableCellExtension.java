/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters;

import csheets.core.Cell;
import csheets.ext.CellExtension;
import csheets.ui.ctrl.UIController;
import java.io.Serializable;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class TableCellExtension extends CellExtension implements Serializable {

    private UIController uiController;
    
    private CellRange range;
    
    private Header header;

    public TableCellExtension(Cell delegate, UIController uiController) {
        super(delegate, TableAndFiltersExtension.NAME);
        this.uiController = uiController;
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
