package lapr4.blue.s2.ipc.n1151031.searchnetwork;

import eapli.framework.dto.DTO;
import java.io.Serializable;

/**
 * Creates an object to send a search request for workbook's names.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class SearchWorkbookRequestDTO implements Serializable, DTO {

    /**
     * The workbook name for the search.
     */
    private String workbookName;

    /**
     * Creates the DTO for the UDP broadcast request.
     *
     * @param workbookName the workbook name
     */
    public SearchWorkbookRequestDTO(String workbookName) {
        this.workbookName = workbookName;
    }

    /**
     * Returns the workbook name.
     *
     * @return the workbook name
     */
    public String getWorkbookName() {
        return workbookName;
    }
}
