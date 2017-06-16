package lapr4.red.s3.ipc.n1150613.NetworkSearchByFile;

import eapli.framework.dto.DTO;
import java.io.Serializable;
import java.util.List;

/**
 * The data transfer object for replies.
 * 
 * @author Diogo Guedes - 1150613@isep.ipp.pt
 */
public class SearchWorkbookResponseDTO implements DTO, Serializable {

    /**
     * The list of search results.
     */
    private final List<SearchResults> list;

    /**
     * The full reply constructor.
     *
     * @param list the list of search results
     */
    public SearchWorkbookResponseDTO(List<SearchResults> list) {
        this.list = list;
    }

    /**
     * Returns the list of search results.
     *
     * @return the list of search results
     */
    public List<SearchResults> getSearchResultsList() {
        return list;
    }
}
