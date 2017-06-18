package lapr4.blue.s3.core.n1151452.pdfexport.domain;

/**
 * Enumerates the print options
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 17/06/17.
 */
public enum PrintArea {

    /**
     * Print whole workbook
     */
    WORKBOOK("Workbook"),

    /**
     * Print the active spreadsheet
     */
    ACTIVE_SPREADSHEET("Active Spreadsheet"),

    /**
     * Print the selection
     */
    SELECTION("Selection"),

    /**
     * Print a range of cells
     */
    RANGE("Range");

    private String designation;

    PrintArea(String str) {

        designation = str;
    }

    @Override
    public String toString() {
        return designation;
    }
}
