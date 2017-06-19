package lapr4.red.s3.ipc.n1150613.NetworkSearchByFile;

import eapli.framework.dto.DTO;
import java.io.Serializable;

/**
 * Creates an object to send a search request for workbook's names.
 *
 * @author Diogo Guedes - 1150613@isep.ipp.pt
 */
public class SearchWorkbookRequestDTO implements Serializable, DTO {

    /**
     * The workbook name for the search.
     */
    private String namePattern;

    private String content;

    /**
     * Creates the DTO for the UDP broadcast request.
     *
     * @param namePattern
     *
     */
    public SearchWorkbookRequestDTO(String namePattern, String content) {
        this.namePattern = namePattern;
        this.content = content;

    }

    /**
     * Returns the workbook name.
     *
     * @return the workbook name
     */
    public String getNamePattern() {
        return namePattern;
    }

    public String getContent() {
        return content;
    }

}
