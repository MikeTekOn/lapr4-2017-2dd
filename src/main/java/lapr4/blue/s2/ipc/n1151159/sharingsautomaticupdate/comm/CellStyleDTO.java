package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.comm;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import eapli.framework.dto.DTO;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.util.Styles;

import java.io.Serializable;

/**
 * Represents a data transfer object to store the address of a cell and the respective style.
 *
 * @author Ivo Ferro [1151159]
 */
public class CellStyleDTO implements DTO, Serializable {

    /**
     * The address of the cell.
     */
    private Address address;


    /**
     * The spreadsheet where the cell is located
     */
    private Spreadsheet spreadsheet;

    /**
     * A style DTO with the style to be applied to the cell.
     */
    private StyleDTO styleDTO;

    /**
     * Creates an instance of cell style DTO.
     *
     * @param address  the address of the cell
     * @param styleDTO the style DTO
     */
    public CellStyleDTO(Address address, StyleDTO styleDTO, Spreadsheet spreadsheet) {
        this.address = address;
        this.styleDTO = styleDTO;
        this.spreadsheet = spreadsheet;
    }

    /**
     * Creates a cell DTO from a cell.
     *
     * @param aCell the cell with the information
     * @return the created DTO
     */
    public static CellStyleDTO createFromCell(Cell aCell) {
        StylableCell stylableCell = (StylableCell) aCell.getExtension(StyleExtension.NAME);
        StyleDTO styleDTO = Styles.createStyleDtoFromCell(stylableCell);
        return new CellStyleDTO(aCell.getAddress(), styleDTO, aCell.getSpreadsheet());
    }

    /**
     * Gets the style DTO.
     *
     * @return style DTO
     */
    public StyleDTO getStyleDTO() {
        return styleDTO;
    }

    /**
     * Gets the address of the cell.
     *
     * @return address of the cell
     */
    public Address getAddress() {
        return address;
    }

    public Spreadsheet spreadsheet() {
        return spreadsheet;
    }
}
