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

    private List<Header> headerCells;

    private CellRange range;
    
    private Filter filters;

    public Table(CellRange range, List<Cell> bodyCells, List<Header> headerCells,Filter filters) {
        this.range = range;
        this.bodyCells = bodyCells;
        this.headerCells = headerCells;
        this.filters=filters;
    }

    /**
     * @return the range
     */
    public CellRange getRange() {
        return range;
    }

    /**
     * @param range the range to set
     */
    public void setRange(CellRange range) {
        this.range = range;
    }

    public boolean containsCells(List<Cell> cells) {

        for (Cell bodyCell : bodyCells) {
            for (Cell cell : cells) {
                if (bodyCell.getAddress().equals(cell.getAddress())) {
                    return true;
                }
            }
        }

        for (Header header : headerCells) {
            for (Cell cell : cells) {
                if (header.getCell().getAddress().equals(cell.getAddress())) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean containsCell(Cell cell) {
        for (Cell bodyCell : bodyCells) {
            if (bodyCell.getAddress().equals(cell.getAddress())) {
                return true;
            }
        }
        for (Header header : headerCells) {
            if (header.getCell().getAddress().equals(cell.getAddress())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean insertFilter(String filter){
        
        if(filter.startsWith("=")){
            filters.getFormulas().add(filter);
            return true;
        }
        return false;
    }

    /**
     * @return the filters
     */
    public Filter getFilters() {
        return filters;
    }

}
