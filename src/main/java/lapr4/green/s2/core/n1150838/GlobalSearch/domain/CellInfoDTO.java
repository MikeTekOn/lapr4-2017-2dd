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
    private String workbookName;
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
    
    public void setWorkbookName(String name){
        this.workbookName=name;
    }

    /**
     * clones the DTO
     *
     * @return
     */
    @Override
    public CellInfoDTO clone() {
        return new CellInfoDTO(cell,  getSpreadsheetNumber(), getWorkbook(),getWorkbookName());
    }


}
