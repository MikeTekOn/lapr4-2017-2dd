/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT;

import csheets.core.Cell;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class CellRange {

    /**
     * The range's first cell
     */
    private Cell firstCell;

    /**
     * The range's last cell
     */
    private Cell lastCell;

    /**
     * Builds a CellRange instance with
     *
     * @param firstCell - the first Cell defined
     * @param lastCell - the last Cell defined
     */
    public CellRange(Cell firstCell, Cell lastCell) {
        if (lastCell.getAddress().getColumn() < firstCell.getAddress().getColumn() 
                || lastCell.getAddress().getRow() < firstCell.getAddress().getRow()) {
            throw new IllegalArgumentException("Invalid range of cells!");
        }

        this.firstCell = firstCell;
        this.lastCell = lastCell;
    }

    /**
     * Returns the first cell in the defined range
     *
     * @return the first cell
     */
    public Cell getFirstCell() {
        return firstCell;
    }

    /**
     * Returns the last cell in the defined range
     *
     * @return the last cell
     */
    public Cell getLastCell() {
        return lastCell;
    }

    /**
     * Returns the number of rows in the defined range
     *
     * @return the number of rows
     */
    public int getRows() {
        if (firstCell == null || lastCell == null) {
            throw new IllegalArgumentException("Range is not defined yet.");
        }

        return (lastCell.getAddress().getRow() - firstCell.getAddress().getRow()) + 1;
    }

    /**
     * Returns the number of columns in the defined range
     *
     * @return the number of columns
     */
    public int getColumns() {
        if (firstCell == null || lastCell == null) {
            throw new IllegalArgumentException("Range is not defined yet.");
        }

        return (lastCell.getAddress().getColumn() - firstCell.getAddress().getColumn()) + 1;
    }
}
