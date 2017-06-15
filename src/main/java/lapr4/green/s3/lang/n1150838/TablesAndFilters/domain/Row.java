/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters.domain;

import csheets.core.Cell;
import java.util.Iterator;



/**
 *
 * @author NunoPinto 1150838
 */
public abstract class Row implements Iterable {
    
    /**
     *Checks if the row contains the given cell
     * @param c the cell
     * @return true if the row contains the cell
     */
    public abstract boolean containsCell(Cell c);
    /**
     * 
     * @return the iterator to this row
     */
    @Override
     public abstract Iterator iterator();
    
}
