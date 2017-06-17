package lapr4.blue.s3.core.n1151452.pdfexport.domain;

import csheets.core.Workbook;

import java.util.LinkedList;
import java.util.List;

/**
 * A PDF exportable workbook
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 16/06/17.
 */
public class PdfExportableWorkbook {

    /**
     * The related workbook
     */
    private Workbook workbook;

    /**
     * Constructs a PDF exportable workbook
     *
     * @param aWorkbook the related workbook
     */
    public PdfExportableWorkbook(Workbook aWorkbook) {

        if (aWorkbook == null) throw new IllegalStateException("The workbook can't be null.");

        workbook = aWorkbook;
    }

    /**
     * Exports the spreadsheets into a list of PDF exportable spreadsheets
     *
     * @return a list of PDF exportable spreadsheets
     */
    public Iterable<PdfExportableSpreadsheet> exportSpreadsheets() {

        LinkedList<PdfExportableSpreadsheet> pdfSpreadsheets = new LinkedList<>();

        workbook.forEach((spreadsheet) -> pdfSpreadsheets.add(new PdfExportableSpreadsheet(spreadsheet)));

        return pdfSpreadsheets;
    }

    /**
     * Exports the spreadsheet into a PDF exportable spreadsheet
     *
     * @param i the spreadsheet index
     * @return a  PDF exportable spreadsheets
     */
    public PdfExportableSpreadsheet exportSpreadsheet(int i) {

        return new PdfExportableSpreadsheet(workbook.getSpreadsheet(i));
    }

    /**
     * Exports the macros into a list PDF exportable macro
     *
     * @return a list of PDF exportable macros
     */
    public Iterable<PdfExportableMacro> exportMacros() {

        List<PdfExportableMacro> pdfMacros = new LinkedList<>();

        workbook.getMacroList().getMacroList().forEach((macroWithName -> {

            pdfMacros.add(new PdfExportableMacro(macroWithName));
        }));

        return pdfMacros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PdfExportableWorkbook that = (PdfExportableWorkbook) o;

        return workbook != null ? workbook.equals(that.workbook) : that.workbook == null;
    }

    @Override
    public int hashCode() {
        return workbook != null ? workbook.hashCode() : 0;
    }
}
