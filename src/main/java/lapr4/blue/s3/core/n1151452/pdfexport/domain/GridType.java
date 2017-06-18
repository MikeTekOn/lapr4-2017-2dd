package lapr4.blue.s3.core.n1151452.pdfexport.domain;

/**
 * Enumerates the different grid types
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 17/06/17.
 */
public enum GridType {

    /**
     * No grid to print
     */
    NO_GRID("No Grid"),

    /**
     * A solid line grid
     */
    SOLID("Solid Grid"),

    /**
     * A dotted line grid
     */
    DOTTED("Dotted Grid");

    private String designation;

    GridType(String str) {
        designation = str;
    }

    @Override
    public String toString() {
        return designation;
    }
}
