/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.ui.ctrl.UIController;

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
     * Regular expression pattern to validate a cell address
     */
    private static final String PATTERN_ADDRESS = "[a-zA-Z]+[1-9][0-9]*";
    
    /**
     * Regular expression that returns the letters of a valid cell address
     */
    private static final String PATTERN_RETURNS_LETTER = "[1-9][0-9]*";
    
    /**
     * Regular expression that returns the number of a valid cell address
     */
    private static final String PATTERN_RETURNS_NUMBER = "[a-zA-Z]+";

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
     * Builds a CellRange instance with
     *
     * @param strAddressFirstCell - the address in string format of the first
     * cell
     * @param strAddressLastCell - the address in string format of the last cell
     * @param uiController - the user interface controller
     */
    public CellRange(String strAddressFirstCell, String strAddressLastCell, UIController uiController) {
        if(strAddressFirstCell.isEmpty() || strAddressLastCell.isEmpty()) {
            throw new IllegalArgumentException("Cell addresses can't be empty!");
        }
        
        if(!strAddressFirstCell.matches(PATTERN_ADDRESS) || !strAddressLastCell.matches(PATTERN_ADDRESS)) {
            throw new IllegalArgumentException("Cell address is not valid!\nIt should follow the pattern e.g: 'A1'");
        }
        
        Cell firstCell = buildCellFromStringAddress(strAddressFirstCell, uiController);
        Cell lastCell = buildCellFromStringAddress(strAddressLastCell, uiController);

        if (lastCell.getAddress().getColumn() < firstCell.getAddress().getColumn()
                || lastCell.getAddress().getRow() < firstCell.getAddress().getRow()) {
            throw new IllegalArgumentException("Invalid range of cells!");
        }

        this.firstCell = firstCell;
        this.lastCell = lastCell;
    }

    /**
     * This method builds a cell from a string formatted address
     * @param strAddress - the cell address in string format
     * @param uiController - the user interface controler
     * @return 
     */
    private Cell buildCellFromStringAddress(String strAddress, UIController uiController) {
        //String columnStr = String.valueOf(strAddress.charAt(0)).toUpperCase();
        String columnStr = strAddress.split(PATTERN_RETURNS_LETTER)[0].toUpperCase();
        int column = -1;
        for (int i = columnStr.length() - 1; i >= 0; i--) {
            column += (columnStr.charAt(i) - Address.LOWEST_CHAR + 1)
                    * Math.pow(Address.HIGHEST_CHAR - Address.LOWEST_CHAR + 1,
                            columnStr.length() - (i + 1));
        }
//        int row = Integer.parseInt(String.valueOf(strAddress.charAt(1))) - 1;
        int row = Integer.parseInt(String.valueOf(strAddress.split(PATTERN_RETURNS_NUMBER)[1])) - 1;

        Address address = new Address(column, row);
        Cell cell = uiController.getActiveSpreadsheet().getCell(address);
        
        return cell;
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
            throw new IllegalArgumentException("Range is not defined yet!");
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
            throw new IllegalArgumentException("Range is not defined yet!");
        }

        return (lastCell.getAddress().getColumn() - firstCell.getAddress().getColumn()) + 1;
    }
}
