package lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.util;

import csheets.ext.style.StylableCell;
import lapr4.blue.s2.ipc.n1151159.sharingsautomaticupdate.comm.StyleDTO;

/**
 * Class with the utility methods to make operations over styles.
 *
 * @author Ivo Ferro [1151159]
 */
public class Styles {

    /**
     * Assigns the style DTO to a styleable cell.
     *
     * @param cell the styleable cell
     * @param dto  the style DTO
     */
    public static void setStyleFromDTO(StylableCell cell, StyleDTO dto) {
        cell.setFormat(dto.getFormat());
        cell.setFont(dto.getFont());
        cell.setHorizontalAlignment(dto.getHorizontalAlignment());
        cell.setVerticalAlignment(dto.getVerticalAlignment());
        cell.setForegroundColor(dto.getForegroundColor());
        cell.setBackgroundColor(dto.getBackgroundColor());
        cell.setBorder(dto.getBorder());
    }

    /**
     * Creates a style DTO from a cell
     *
     * @param cell the styleable cell
     * @return the created style DTO
     */
    public static StyleDTO createStyleDtoFromCell(StylableCell cell) {
        return new StyleDTO(
                cell.getFormat(),
                cell.getFont(),
                cell.getHorizontalAlignment(),
                cell.getVerticalAlignment(),
                cell.getForegroundColor(),
                cell.getBackgroundColor(),
                cell.getBorder());
    }
}
