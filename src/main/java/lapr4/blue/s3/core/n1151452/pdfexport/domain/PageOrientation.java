package lapr4.blue.s3.core.n1151452.pdfexport.domain;

import com.itextpdf.text.Rectangle;

/**
 * Enumerates the page orientation type
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 17/06/17.
 */
public enum PageOrientation {

    /**
     * Portrait orientation
     */
    PORTRAIT("Portrait"),

    /**
     * Landscape orientation
     */
    LANDSCAPE("Landscape");

    private String designation;

    private Rectangle pageOrientation;

    PageOrientation(String str) {
        designation = str;
    }

    @Override
    public String toString() {
        return designation;
    }

    public Rectangle orientation(Rectangle pageSize) {

        if (designation.equalsIgnoreCase("Landscape")) return pageSize.rotate();

        return pageSize;
    }
}
