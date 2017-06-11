package lapr4.blue.s2.ipc.n1151031.searchnetwork;

import eapli.framework.dto.DTO;
import java.io.Serializable;
import java.util.List;

/**
 * The data transfer object for replies.
 * 
 * @author Tiago Correia - 1151031@isep.ipp.pt
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
