/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters.domain;

import csheets.core.Cell;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author NunoPinto 1150838
 */
public class DataRow extends Row implements Serializable  {
    /**
     * The data row
     */
    private List<Cell> row;

    public DataRow(List<Cell> row) {
        this.row = row;
    }

    @Override
    public boolean containsCell(Cell c) {
        for (Cell cell : row) {
            if (cell.getAddress().equals(c.getAddress())) {
                return true;
            }
        }
        return false;
    }
    /**
     * adds the given cell 
     * @param d the given cell
     * @return true if the cell was added
     */
    public boolean add(Cell d) {
        return row.add(d);
    }

    @Override
    public Iterator iterator() {
        return row.iterator();
    }

    @Override
    public Cell getCellAt(int i) {
        return row.get(i);
    }
    
    
}
