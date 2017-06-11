package lapr4.blue.s2.ipc.n1151031.searchnetwork;

import csheets.core.Spreadsheet;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.List;

/**
 * Used to save the results of a workbook search.
 *
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class SearchResults implements Serializable {

    /**
     * The address of the host that has the workbooks.
     */
    private InetAddress address;

    /**
     * The workbook's name.
     */
    private String workbookName;

    /**
     * The list of spreadsheets in the workbook.
     */
    private List<Spreadsheet> spreadsheetList;

    /**
     * Creates an instance of SearchResults.
     *
     * @param workbookName the workbook name
     * @param spreadsheetList the list of spreadsheets in the workbook
     * @param address the address
     */
    public SearchResults(String workbookName, List<Spreadsheet> spreadsheetList, InetAddress address) {
        this.workbookName = workbookName;
        this.spreadsheetList = spreadsheetList;
        this.address = address;
    }

    /**
     * Returns the workbook name.
     *
     * @return the workbookName
     */
    public String getWorkbookName() {
        return workbookName;
    }

    /**
     * Returns the list of spreadsheets.
     *
     * @return the spreadsheetList
     */
    public List<Spreadsheet> getSpreadsheetList() {
        return spreadsheetList;
    }

    /**
     * Returns the address.
     *
     * @return the address
     */
    public InetAddress getAddress() {
        return address;
    }

}
