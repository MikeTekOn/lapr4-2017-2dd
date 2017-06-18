package lapr4.blue.s3.core.n1151452.pdfexport.application;

import csheets.core.Address;
import csheets.core.Cell;
import eapli.framework.application.Controller;
import lapr4.blue.s3.core.n1151452.pdfexport.domain.PdfExport;
import lapr4.blue.s3.core.n1151452.pdfexport.domain.PdfExportBuilder;
import lapr4.blue.s3.core.n1151452.pdfexport.presentation.PdfOptionsDTO;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * A controller class to manage the communication between UI and Domain
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 18/06/17.
 */
@SuppressWarnings("Duplicates")
public class PdfExportController implements Controller {

    /**
     * Exports to PDF the selected options
     *
     * @param options the selected options
     * @return true if successful, false otherwise
     */
    public boolean export(PdfOptionsDTO options) {

        PdfExportBuilder builder = new PdfExportBuilder(options.getActiveCells());

        builder.withGridType(options.getGridType()).withGridColor(options.getGridColor())
                .withPrintArea(options.getPrintArea()).withPageOrientation(options.getOrientation())
                .withPageSize(options.getPageSize()).withPath(options.getPath());

        if (options.withHeaders()) builder.withHeaders();
        if (options.withMacros()) builder.withSection(PdfExport.MACROS);
        if (options.withFormulas()) builder.withSection(PdfExport.FORMULAS);
        if (options.withComments()) builder.withSection(PdfExport.COMMENTS);

        return builder.build().export();
    }
}
