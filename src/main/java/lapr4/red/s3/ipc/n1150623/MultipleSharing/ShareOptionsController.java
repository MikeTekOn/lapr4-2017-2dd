package lapr4.red.s3.ipc.n1150623.MultipleSharing;

import csheets.core.Address;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.CellDTO;


import java.util.SortedSet;

/**
 * Created by Guilherme Ferreira 1150623 on 16/06/2017.
 */
public class ShareOptionsController {

    private SortedSet<CellDTO> cellsDTO;

    private Address startAddress;
    private Address finalAddress;
    private CellHandler handler;

    public ShareOptionsController(CellHandler handler, SortedSet<CellDTO> cellsDTO){
        this.cellsDTO = cellsDTO;
        this.handler = handler;
    }

    public int getColumns() {
        Address first = cellsDTO.first().getAddress();
        Address last = cellsDTO.last().getAddress();
        return first.ColumnDistanceTo(last);
    }

    public int getRows() {
        Address first = cellsDTO.first().getAddress();
        Address last = cellsDTO.last().getAddress();
        return first.ColumnDistanceTo(last);
    }

    public boolean doAlterations(){
        if(startAddress!= null && finalAddress != null && correctRange()){
            handler.setAddresses(startAddress);
            handler.start();
            return true;
        }else{
            return false;
        }
    }

    public boolean setStartAddress(String col, String row){
        try {
            startAddress = new Address(col, row);
        }catch(IllegalArgumentException e){
            return false;
        }
        return true;
    }

    public boolean setFinalAddress(String col, String row){
        try {
            finalAddress = new Address(col, row);
        }catch(IllegalArgumentException e){
            return false;
        }
        return true;
    }

    private boolean correctRange(){
        int col = getColumns();
        int row = getRows();
        return col == startAddress.ColumnDistanceTo(finalAddress)
                && row == startAddress.RowDistanceTo(finalAddress);
    }

    public Address getStartAddress() {
        return startAddress;
    }

    public Address getFinalAddress() {
        return finalAddress;
    }
}
