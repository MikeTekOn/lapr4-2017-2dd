package lapr4.red.s1.core.n1150623.labelsForContacts;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lapr4.red.s1.core.n1150623.labelsForContacts.domain.Label;
import lapr4.white.s1.core.n4567890.contacts.domain.Event;
import org.bouncycastle.util.test.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Guilherme Ferreira 1150623 on 01/06/2017.
 */
public class LabelsToPDF implements GenericExport<LabelList>{

    List<Label> labels;

    private LabelList list;

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
        this.list = list;
        boolean valid = true;

        System.out.println("+---------+" +"Path: " + list.path + "+-----------+");
        List<Label> l = list.labels();

        for(Label lab : l){
            System.out.println(l.toString());
        }



        labels = list.labels();
        Document doc = null;
        doc = initiatePrinter();
        doc.open();
        Font fontSpecial = new Font(Font.FontFamily.COURIER, 16, Font.BOLD); //Used in the first line and first column
        Font font = new Font(Font.FontFamily.COURIER, 14, Font.NORMAL);

        int contLabels = 1;
        String info[][];
        for (Label label : list.labels()) {
            Paragraph p1 = new Paragraph("\n\n\t\tLabel" + contLabels + ":\n", fontSpecial);


            //ADDS IMAGE
            try {
                Image image2 = Image.getInstance(new URL(label.photo()));
                doc.add(image2);
                doc.add(p1);
            } catch (Exception e){
                try {
                    Image image2 = Image.getInstance("default_img.png");
                } catch (Exception ex){
                    System.out.println("ERRO LOADING IMG");
                }
            }



            PdfPTable table = new PdfPTable(2);
            info = generateTableInformation(label);

            table.setWidthPercentage(50);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {
                    if (j == 0){
                        table.addCell(new PdfPCell(new Phrase(info[i][j], fontSpecial)));
                    }else{
                        table.addCell(new PdfPCell(new Phrase(info[i][j], font)));
                    }
                }
            }


            try {
                doc.add(table);
            } catch (DocumentException e) {
                e.printStackTrace();
                valid = false;
            }
            Paragraph p2 = new Paragraph("\n\n\t\tEvents:", fontSpecial);

            PdfPTable events = new PdfPTable(2);
            String [][]eventInfo = generateEventTableInformation(label);
            if(eventInfo!=null) {
                events.setWidthPercentage(50);

                for (int i = 0; i < eventInfo.length; i++) {
                    for (int j = 0; j < eventInfo[0].length; j++) {
                        if (i == 0) {
                            events.addCell(new PdfPCell(new Phrase(eventInfo[i][j], fontSpecial)));
                        } else {
                            events.addCell(new PdfPCell(new Phrase(eventInfo[i][j], font)));
                        }
                    }
                }


                try {
                    doc.add(p2);
                    doc.add(events);
                    doc.newPage();
                    doc.setMargins(30, 30, 20, 20);
                } catch (DocumentException e) {
                    e.printStackTrace();
                    valid = false;
                }
            }
                    contLabels++;

        }
        doc.close();
        return valid;
    }

    private String[][] generateEventTableInformation(Label label) {
        List<Event> events = label.events();
        if(events.size() > 0) {
            String[][] info = new String[events.size()+1][2];

            info[0][0] = "Date";
            info[0][1] = "Description";
            int cont = 0;
            for (int i = 1; i < info.length; i++) {
                Event e = events.get(cont);
                info[i][0] = e.dueDate().get(Calendar.DAY_OF_MONTH) + "/" + e.dueDate().get(Calendar.MONTH) + "/" + e.dueDate().get(Calendar.YEAR);
                info[i][1] = e.description();
                cont++;
            }

            return info;
        }
        return null;
    }

    String path;

    private Document initiatePrinter() {
        Document document = null;
        selectPath(list.path());
        try {
            document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            document.setMargins(30, 30, 20, 20);
        } catch (DocumentException | FileNotFoundException e) {
            //return null;
        }
        return document;

    }


    public String[][] generateTableInformation(Label label){

        String[][] info = new String[4][2];
        info[0][0] = "Name";
        info[1][0] = "Phone Number";
        info[2][0] = "Email";
        info[3][0] = "Address";

        info[0][1] = label.name();
        info[1][1] = label.phoneNumber();
        info[2][1] = label.email();
        info[3][1] = label.address();

        return info;
    }

    public void selectPath(String path) {

        final Pattern pattern = Pattern.compile(".+\\.pdf");
        if (!pattern.matcher(path).matches()) {
            throw new IllegalArgumentException();
        }
        this.path = path;
    }


}

