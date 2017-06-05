package lapr4.red.s1.core.n1150623.labelsForContacts;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lapr4.red.s1.core.n1150623.labelsForContacts.domain.Label;
import lapr4.white.s1.core.n4567890.contacts.domain.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 * Created by Guilherme Ferreira 1150623 on 01/06/2017.
 */
public class LabelsToPDF implements GenericExport<LabelList>{


    private PdfWriter pdf;

    public LabelsToPDF(){
        //For ORM
    }

    /**
     *
     *
     * Exports information from labels to a PDF file.
     *
     *  In the file, there will, in different pages, the following table:
     *  (just an example to - roughly - show the output that is made)
     *
     *    Label N:
     *
     *   +--------------------------+--------------+-------------------+
     *   |                          |              |                   |
     *   |            P             | Name         | "Contacts Name"   |
     *   |            H             +--------------+-------------------+
     *   |            O             |              |  913 568 546      |
     *   |            T             | Phone Number |                   |
     *   |            O             +--------------+-------------------+
     *   |                          |              |  example@mail.pt  |
     *   |            H             | Email        |                   |
     *   |            E             +--------------+-------------------+
     *   |            R             |              |  Rua Exemplo1, 79 |
     *   |            E             | Address      |                   |
     *   +--------------------------+--------------+-------------------+
     *
     *      Event List
     *
     *   +-----------+----------+------------------------+
     *   | DAY       |   Hour   |         Event          |
     *   +-----------+----------+------------------------+
     *   | 21/02     |  18h00   | Event1_description     |
     *   | 21/02     |  19h30   | Event2_description     |
     *   | 01/03     |  14h15   | Event3_description     |
     *   +-----------+----------+------------------------+
     *
     *      ///// END OF PAGE /////
     *
     * @param list - list of lables to print
     * @return true if output was able to be generated; false otherwise;
     */
    @Override
    public boolean export(LabelList list){
        boolean valid = true;

        Document doc = null;
        doc = initiatePrinter();
        doc.open();
        Font fontSpecial = new Font(Font.FontFamily.COURIER, 6, Font.BOLD); //Used in the first line and first column


        int contLabels = 1;
            Paragraph p1 = new Paragraph();
            for (Label label : list.labels()) {
                doc.addTitle("Label " + contLabels + ":");
             /*ADDS IMAGE
               try {
                    Image image2 = Image.getInstance(new URL(label.photo()));
                    doc.add(image2);
                } catch (BadElementException | IOException e){
                    System.out.println("ERRO LOADING IMG");
                } catch (DocumentException e) {
                    e.printStackTrace();
                    valid = false;
                }
            */
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(90);
                table.addCell(new PdfPCell(new Phrase("Name", fontSpecial)));
                table.addCell(new PdfPCell(new Phrase(label.name(), fontSpecial)));
                table.addCell(new PdfPCell(new Phrase("Address", fontSpecial)));
                table.addCell(new PdfPCell(new Phrase(label.address(), fontSpecial)));
                table.addCell(new PdfPCell(new Phrase("Email", fontSpecial)));
                table.addCell(new PdfPCell(new Phrase(label.email(), fontSpecial)));
                table.addCell(new PdfPCell(new Phrase("Phone Number", fontSpecial)));
                table.addCell(new PdfPCell(new Phrase(label.phoneNumber(), fontSpecial)));
                Paragraph p = new Paragraph();
                try {
                    doc.add(p);
                    doc.add(table);
                } catch (DocumentException e) {
                    e.printStackTrace();
                    valid = false;
                }
                Paragraph p2 = new Paragraph("Events:");

                PdfPTable events = new PdfPTable(2);
                table.setWidthPercentage(90);
                table.addCell(new PdfPCell(new Phrase("Date ", fontSpecial)));
                table.addCell(new PdfPCell(new Phrase("Description", fontSpecial)));

                List<Event> event = label.events();
                for (Event e : event) {
                    events.addCell(e.dueDate().toString());
                    events.addCell(e.description());
                }

                Paragraph p3 = new Paragraph();
                try {
                    doc.add(p2);
                    doc.add(events);
                    doc.add(p3);
                } catch (DocumentException e) {
                    e.printStackTrace();
                    valid = false;
                }
                doc.newPage();
                contLabels++;
            }
        doc.close();
        return valid;
    }

    File file;

    private Document initiatePrinter() {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource("path/to/folder.pdf");
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Document document = null;

        try {
            document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            document.setMargins(5, 5, 5, 5);
        } catch (DocumentException | FileNotFoundException e) {
        }
        return document;

    }
}

