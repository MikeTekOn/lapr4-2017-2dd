package lapr4.red.s3.ipc.n1150623.MultipleSharing;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.CellImpl;
import csheets.core.Spreadsheet;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.CellDTO;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.ShareContentCellListener;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.StyleListener;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.comm.CellContentDTO;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.comm.CellStyleDTO;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.util.Styles;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;
import java.util.SortedSet;

/**
 * Created by Guilherme Ferreira 1150623 on 16/06/2017.
 */
public class CellHandler {

    private SortedSet<CellDTO> cellsDTO;
    private Spreadsheet spreadsheet;
    private ConnectionID connection;
    private Address start_Address;

    public CellHandler(SortedSet<CellDTO> cellsDTO, Spreadsheet spreadsheet, ConnectionID connection){
        this.cellsDTO = cellsDTO;
        this.spreadsheet = spreadsheet;
        this.connection = connection;
    }

    public void setAddresses(Address ini){
        start_Address = ini;
    }

    private Address firstDTOAddress;

    private MyAddressSortedList resultingAddresses;

    public void start(){
        resultingAddresses = new MyAddressSortedList();
        firstDTOAddress = cellsDTO.first().getAddress();

        for (CellDTO cellDTO : cellsDTO) {
            try { //TODO: the sharedName should became a tool tip text (& highlight changes?)
                Cell cell = getCellfromSpreadSheet(cellDTO.getAddress());
                resultingAddresses.add(cell.getAddress());
                cell.setContent(cellDTO.getContent());
                StylableCell stylableCell = (StylableCell) cell.getExtension(StyleExtension.NAME);


                if (stylableCell != null && cellDTO.getStyleDTO() != null) {
                    Styles.setStyleFromDTO(stylableCell, cellDTO.getStyleDTO());
                }
                cell.addCellListener(new ShareContentCellListener(connection));
                if (cell instanceof CellImpl) {
                    ((CellImpl)cell).addStyleListener(new StyleListener(connection));
                }
            } catch (FormulaCompilationException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     *
     * @param inicial
     * @return the corresponding cell in the correct address of the range chosen by the user
     */
    private Cell getCellfromSpreadSheet(Address inicial){
        int column =  start_Address.getColumn()+(inicial.getColumn()-firstDTOAddress.getColumn());
        int row = start_Address.getRow()+(inicial.getRow()-firstDTOAddress.getRow());
        return spreadsheet.getCell(column, row);
    }


    public void applyStyle(CellStyleDTO dto) {


        Cell cell = getCellfromSpreadSheet(dto.getAddress());
        StylableCell stylableCell = (StylableCell)cell.getExtension(StyleExtension.NAME);
        if (stylableCell != null && dto.getStyleDTO() != null) {
            Styles.setStyleFromDTO(stylableCell, dto.getStyleDTO());
            if (cell instanceof CellImpl) {
                ((CellImpl) cell).updateCellStyle();
            }


        }
    }

    public void setContent(CellContentDTO dto) {
        Cell cell = getCellfromSpreadSheet(dto.getAddress());
        try {
            cell.setContent(dto.getContent());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
