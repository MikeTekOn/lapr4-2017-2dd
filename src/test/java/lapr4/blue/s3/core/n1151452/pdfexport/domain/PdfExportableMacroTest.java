package lapr4.blue.s3.core.n1151452.pdfexport.domain;

import csheets.CleanSheets;
import csheets.core.Spreadsheet;
import csheets.ui.ctrl.UIController;
import lapr4.red.s2.lang.n1150451.multipleMacros.domain.MacroWithName;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 16/06/17.
 */
public class PdfExportableMacroTest {

    /**
     * App's instance
     */
    private CleanSheets app;

    /**
     * Test setup
     *
     * @throws Exception catches any exception
     */
    @Before
    public void setUp() throws Exception {

        // Try to create the CS application object
        CleanSheets.setFlag(true);
        app = new CleanSheets();
        // This will create a workbook with 3 sheets
        app.create();
    }

    /**
     * Tests if macro is null
     */
    @Test(expected = IllegalStateException.class)
    public void ensurePdfExportableMacroHasNonNullMacro() {
        PdfExportableMacro pdfMacro = new PdfExportableMacro(null);
    }

    /**
     * Tests if exported pdf macro contains the macro content
     */
    @Test
    public void ensurePdfExportableMacroExportsMacro() {

        Spreadsheet spreasheet = app.getWorkbooks()[0].getSpreadsheet(0);

        MacroWithName macro = new MacroWithName("Macro1", ";Macro line 1\n;Macro line 2",
                spreasheet, null);

        PdfExportableMacro pdfMacro = new PdfExportableMacro(macro);

        String result = pdfMacro.exportMacro().getContent();

        String expResult1 = "Macro1";
        String expResult2 = ";Macro line 1";
        String expResult3 = ";Macro line 2";

        assert result.contains(expResult1);
        assert result.contains(expResult2);
        assert result.contains(expResult3);
    }

}