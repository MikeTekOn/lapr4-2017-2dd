/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.black.s1.ipc.n2345678.comm.sharecells;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.StyleDTO;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.util.Styles;

import java.io.Serializable;
import java.lang.Comparable;

/**
 * This class implements a "version" of the Cell that is suitable for communication 
 * without serializing the all graph of the sheet.<p>
 * Need to include at least: getAddress() and getContent()<p>
 * Need to see the best way to get a set of CellDTO from a set o regular Cells <p>
 * (see SortedSet SpreadsheetImpl.getCells(Address address1, Address address2)).
 * @author alexandrebraganca
 */
public class CellDTO implements Serializable, Comparable<CellDTO> {

    /**
     * A style DTO with the style to be applied to the cell.
     */
    private StyleDTO styleDTO;
    
    private Address address;
    private String content;
    
    public CellDTO(Address addr, String cont) {
        address=addr;
        content=cont;
    }

    /**
     * Creates a cell DTO from a cell.
     *
     * @param aCell the cell with the information
     * @return the created DTO
     */
    public static CellDTO createFromCell(Cell aCell) {
        CellDTO cellDTO = new CellDTO(aCell.getAddress(), aCell.getContent());
        StylableCell stylableCell = (StylableCell)aCell.getExtension(StyleExtension.NAME);
        if (stylableCell != null) {
            cellDTO.setStyleDTO(Styles.createStyleDtoFromCell(stylableCell));
        }
        return cellDTO;
    }

    public void setStyleDTO(StyleDTO styleDTO) {
        this.styleDTO = styleDTO;
    }

    /**
     * Gets the style DTO.
     *
     * @return style DTO
     */
    public StyleDTO getStyleDTO() {
        return styleDTO;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public String getContent() {
        return content;
    }

    @Override
    public int compareTo(CellDTO o) {
	return address.compareTo(o.getAddress());
    }
}
