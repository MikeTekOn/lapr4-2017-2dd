/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters.domain;

import csheets.core.Cell;
import java.io.Serializable;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class Header implements Serializable {

    /**
     * The header index
     */
    private int headerIndex;
    /**
     * The header cell
     */
    private Cell headerCell;

    public Header(int headerIndex, Cell headerCell) {
        this.headerIndex = headerIndex;
        this.headerCell = headerCell;
    }

    /**
     *
     * @param headerIndex the given index
     * @return true if the given index is equal to this index
     */
    public boolean isHeader(int headerIndex) {
        return this.getHeaderIndex() == headerIndex;
    }
    /**
     * 
     * @return this header contet
     */
    public String getHeaderContent() {
        String content;
        if (headerCell.getFormula() != null) {
            content = headerCell.getValue().toString();
        } else {
            content = headerCell.getContent();
        }
        return content;
    }

    /**
     *
     * @return this cell
     */
    public Cell getCell() {
        return headerCell;
    }

    /**
     * @return the headerIndex
     */
    public int getHeaderIndex() {
        return headerIndex;
    }

    /**
     * @param headerIndex the headerIndex to set
     */
    public void setHeaderIndex(int headerIndex) {
        this.headerIndex = headerIndex;
    }

}
