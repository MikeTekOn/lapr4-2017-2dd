/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150838.GlobalSearch.presentation;

import csheets.core.Cell;
import csheets.core.Workbook;
import eapli.framework.dto.DTO;

/**
 *
 * @author Nuno Pinto 1150838
 */
public class CellInfoDTO implements DTO {
    /**
     * The cell that matchs the given regex
     */
    private Cell cell;
    /**
     * The wokbook were the cell was found
     */
    private Workbook workbook;
    /**
     * The workbook name
     */
    private String workbookName;
    /**
     * Number of the spreadsheet that the cell was found
     */
    private int spreadsheetNumber;

    public CellInfoDTO(Cell cell, int spreadsheetNumber,Workbook workbook,String workbookName) {
        this.cell = cell;
        this.workbook=workbook;
        this.workbookName=workbookName;
        this.spreadsheetNumber= spreadsheetNumber;
             

    }

    /**
     * @return the celula
     */
    public Cell getCell() {
        return cell;
    }
    
    
    /**
     * @return the workbook
     */
    public Workbook getWorkbook() {
        return workbook;
    }
        /**
     * @return the workbookName
     */
    public String getWorkbookName() {
        return workbookName;
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
     * @throws java.lang.CloneNotSupportedException it´s not possible to clone
     */
    @Override
    public CellInfoDTO clone() throws CloneNotSupportedException {
        return new CellInfoDTO(cell,  getSpreadsheetNumber(), getWorkbook(),getWorkbookName());
    }


}
