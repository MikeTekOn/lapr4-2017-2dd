package lapr4.blue.s3.core.n1151452.pdfexport.domain;

import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates the PDF index
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 17/06/17.
 * @see com.itextpdf.text.pdf.PdfPageEventHelper
 */
class PdfIndexCreation extends PdfPageEventHelper {

    protected PdfOutline root;

    protected List<IndexEntry> index = new ArrayList<IndexEntry>();

    public PdfIndexCreation() {
    }

    public void setRoot(PdfOutline root) {
        this.root = root;
    }

    public List<IndexEntry> getIndex() {
        return index;
    }

    @Override
    public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text) {

        PdfDestination dest = new PdfDestination(PdfDestination.XYZ, rect.getLeft(), rect.getTop(), 0);

        new PdfOutline(root, dest, text);

        IndexEntry entry = new IndexEntry();

        entry.action = PdfAction.gotoLocalPage(writer.getPageNumber(), dest, writer);

        entry.title = text;

        entry.pageNumber = writer.getPageNumber();

        index.add(entry);
    }

    class IndexEntry {
        PdfAction action;
        String title;
        int pageNumber;

    }
}