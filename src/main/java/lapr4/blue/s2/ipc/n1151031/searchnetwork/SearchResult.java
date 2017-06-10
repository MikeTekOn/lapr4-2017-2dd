package lapr4.blue.s2.ipc.n1151031.searchnetwork;

import java.net.InetAddress;

/**
 * A class that represents a result of a workbook's name network search.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class SearchResult {

    /**
     * The connection address.
     */
    private InetAddress address;

    /**
     * The workbook's name.
     */
    private String workbookName;

    /**
     * Creates the SearchResult object.
     *
     * @param address the address of the file host
     * @param workbookName the workbook's name
     */
    public SearchResult(InetAddress address, String workbookName) {
        this.address = address;
        this.workbookName = workbookName;
    }

    /**
     * Returns the address of teh host.
     *
     * @return the address
     */
    public InetAddress getAddress() {
        return address;
    }

    /**
     * Returns the workbook's name.
     *
     * @return the workbookName
     */
    public String getWorkbookName() {
        return workbookName;
    }
}
