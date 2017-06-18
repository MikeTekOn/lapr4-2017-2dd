/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.black.s1.ipc.n2345678.comm.sharecells;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author alexandrebraganca
 */
public class RequestSharedCellsDTO implements Serializable {
    
    private String spreadsheetName;
    
    private SortedSet<CellDTO> sharedcells;
    
    private Address address1;
    
    private Address address2;

    private String shareName;

    public RequestSharedCellsDTO(String dstName, Spreadsheet srcSheet, Address addr1, Address addr2, String shareName) {
        spreadsheetName=dstName;
        address1=addr1;
        address2=addr2;
        this.shareName = shareName;

        // build the serializable set of cells
        sharedcells=new TreeSet<CellDTO>(); 
        
        SortedSet<Cell> cells=srcSheet.getCells(addr1, addr2);
        for (Cell aCell: cells) {
            sharedcells.add(CellDTO.createFromCell(aCell));
        }
    }
    
    public SortedSet<CellDTO> getCells() {
        return sharedcells;
    }
    
    public Address getAddress1() {
        return address1;
    }
            
    public Address getAddress2() {
        return address2;
    }
    
    public String getSpreadsheetName() {
        return spreadsheetName;
    }

    public String getShareName(){
        return shareName;
    }
    
    public static RequestSharedCellsDTO createFromRange(String dstName, Spreadsheet sheet, Address addr1, Address addr2, String shareName) {
        return new RequestSharedCellsDTO(dstName, sheet, addr1, addr2, shareName);
    }
}
