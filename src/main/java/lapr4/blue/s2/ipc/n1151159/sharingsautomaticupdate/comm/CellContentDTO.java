package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.comm;

import csheets.core.Address;
import csheets.core.Cell;
import eapli.framework.dto.DTO;

import java.io.Serializable;

/**
 * Represents a data transfer object to store the content of a cell.
 *
 * @author Ivo Ferro [1151159]
 */
public class CellContentDTO implements DTO, Serializable {

    /**
     * The address of the cell being shared.
     */
    private Address address;

    /**
     * The content of the cell being shared.
     */
    private String content;

    /**
     * Creates a cell content DTO.
     *
     * @param address the address of the cell
     * @param content the content of the cell
     */
    public CellContentDTO(Address address, String content) {
        this.address = address;
        this.content = content;
    }

    /**
     * Creates a cell DTO from a cell.
     *
     * @param aCell the cell with the information
     * @return the created DTO
     */
    public static CellContentDTO createFromCell(Cell aCell) {
        return new CellContentDTO(aCell.getAddress(), aCell.getContent());
    }

    /**
     * Gets the address of the cell.
     *
     * @return address of the cell
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Gets the content of the cell.
     *
     * @return content of the cell
     */
    public String getContent() {
        return content;
    }
}
