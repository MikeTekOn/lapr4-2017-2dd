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

/**
 *
 * @author Nuno Pinto 1150838
 */
public class Table implements Serializable {

    /**
     * The table rows
     */
    private List<Row> cells;
    /**
     * The table range
     */
    private CellRange range;
    /**
     * The table filter
     */
    private String filter;

    public Table(CellRange range, List<Row> cells, String filters) {
        if(cells.size()<2){
            throw new IllegalStateException();
        }
        this.range = range;
        this.cells = cells;
        this.filter = filters;

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

    /**
     * Checks if a table contains any of the given cells
     *
     * @param cells the given cells
     * @return true if the table contains the given cells false otherwise
     */
    public boolean containsCells(List<Cell> cells) {

        for (Row row : this.getCells()) {
            for (Cell cell : cells) {

                if (row.containsCell(cell)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if a table contains the given cell
     *
     * @param cell the given cell
     * @return true if the table contains the given cell false otherwise
     */
    public boolean containsCell(Cell cell) {
        for (Row row : getCells()) {
            if (row.containsCell(cell)) {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param i the given index
     * @return the row on the given index
     */
    public Row getRow(int i) {
        return cells.get(i);
    }

    /**
     * @return the cells
     */
    public List<Row> getCells() {
        return cells;
    }
    /**
     * 
     * @param cell the given cell
     * @return the row that contains the given cell or null otherwise
     */
    public Row getRowByCell(Cell cell) {
        for (Row cell1 : cells) {
            if (cell1.containsCell(cell)) {
                return cell1;
            }
        }
        return null;
    }
    /**
     * 
     * @param ctx the colum name
     * @return the index of a column with the given name
     */
    public int getHeaderIndex(String ctx) {
        return ((HeaderRow) cells.get(0)).getIndexByContent(ctx);
    }

    /**
     * @return the filter
     */
    public String getFilter() {
        return filter;
    }

    /**
     * @param filter the filter to set
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

}
