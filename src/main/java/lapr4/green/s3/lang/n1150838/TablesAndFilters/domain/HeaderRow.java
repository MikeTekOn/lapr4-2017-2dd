/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150838.TablesAndFilters.domain;

import csheets.core.Cell;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author NunoPinto 1150838
 */
public class HeaderRow extends Row implements Serializable {

    private List<Header> row;

    public HeaderRow(List<Header> row) {
        this.row = row;
    }

    @Override
    public boolean containsCell(Cell c) {
        for (Header header : row) {
            if (header.getCell().getAddress().equals(c.getAddress())) {
                return true;
            }
        }
        return false;
    }

    public boolean add(Header d) {
        return row.add(d);
    }

    @Override
    public Iterator iterator() {
        List<Cell> cells = new ArrayList();
        for (Header header : row) {
            cells.add(header.getCell());
        }
        return cells.iterator();
    }
    
    public int getIndexByContent(String content){
        for (Header header : row) {
            if(header.getHeaderContent().equals(content)){
                return header.getHeaderIndex();
            }
        }
        return -1;
    }

    @Override
    public Cell getCellAt(int i) {
        return row.get(i).getCell();
    }

}
