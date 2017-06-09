/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150838.GlobalSearch.domain;

import csheets.core.Cell;
import csheets.core.Workbook;
import eapli.framework.dto.DTO;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class CellInfoDTO implements DTO {

    private Cell cell;
    private Workbook workbook;
    private int spreadsheetNumber;

    public CellInfoDTO(Cell cell, int spreadsheetNumber,Workbook workbook) {
        this.cell = cell;
        this.workbook=workbook;
        this.spreadsheetNumber= spreadsheetNumber;
             

    }

    /**
     * @return the celula
     */
    public Cell getCell() {
        return cell;
    }
    
    
    /**
     * @return the workbookName
     */
    public Workbook getWorkbookName() {
        return workbook;
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
        return new CellInfoDTO(cell,  getSpreadsheetNumber(), getWorkbookName());
    }


}
