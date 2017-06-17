package lapr4.blue.s3.core.n1151452.pdfexport.domain;

import com.itextpdf.text.*;
import lapr4.blue.s1.lang.n1151159.macros.Macro;
import lapr4.red.s2.lang.n1150451.multipleMacros.domain.MacroWithName;

/**
 * A PDF exportable macro
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 16/06/17.
 */
public class PdfExportableMacro {

    /**
     * The related macro
     */
    private final MacroWithName macro;

    /**
     * Constructs a PDF exportable macro
     * @param aMacro the related macro
     */
    public PdfExportableMacro(MacroWithName aMacro) {

        if (aMacro == null) throw new IllegalStateException("Macro can't be null.");

        macro = aMacro;
    }

    /**
     * Transforms the macro into an exportable paragraph
     *
     * @return the macro into an exportable paragraph
     */
    public Paragraph exportMacro() {

        Font titleFont = new Font(Font.FontFamily.HELVETICA, 12f, Font.BOLD, BaseColor.BLACK);
        Font bodyFont = new Font(Font.FontFamily.HELVETICA, 10f, Font.NORMAL, BaseColor.BLACK);

        Phrase macroTitle = new Phrase(macro.getName() + "\n", titleFont);

        Paragraph pdfMacro = new Paragraph();
        pdfMacro.add(macroTitle);

        String[] macroLines = macro.getMacroCode().split("\n");
        List macroList = new List(List.ORDERED);
        macroList.setPostSymbol("\t");
        for (String line:
             macroLines) {

            ListItem item = new ListItem(line, bodyFont);
            item.setAlignment(Element.ALIGN_JUSTIFIED);
            macroList.add(item);
        }
        pdfMacro.add(macroList);

        return pdfMacro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PdfExportableMacro that = (PdfExportableMacro) o;

        return macro != null ? macro.equals(that.macro) : that.macro == null;
    }

    @Override
    public int hashCode() {
        return macro != null ? macro.hashCode() : 0;
    }
}
