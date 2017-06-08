/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150838.GlobalSearch.domain;

import csheets.core.Cell;
import eapli.framework.dto.DTO;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class CellInfoDTO implements DTO {

    private Cell cell;
    private String cellContentFound;
    private String comentContentFound;
    private int spreadsheetNumber;

    public CellInfoDTO(Cell cell, String cellContentFound, String comentContentFound,int spreadsheetNumber) {
        this.cell = cell;
        this.cellContentFound = cellContentFound;
        this.comentContentFound = comentContentFound;
        this.spreadsheetNumber= spreadsheetNumber;
             

    }

    /**
     * @return the celula
     */
    public Cell getCell() {
        return cell;
    }

    /**
     * @return the cellContentFound
     */
    public String getCellContentFound() {
        return cellContentFound;
    }

    /**
     * @return the comentContentFound
     */
    public String getComentContentFound() {
        return comentContentFound;
    }
    
    /**
     * @return the spreadCheetNumber
     */
    public int getSpreadsheetNumber() {
        return spreadsheetNumber;
    }

    /**
     * clones the DTO
     *
     * @return
     */
    @Override
    public CellInfoDTO clone() {
        return new CellInfoDTO(cell, cellContentFound, getComentContentFound(), getSpreadsheetNumber());
    }


}
