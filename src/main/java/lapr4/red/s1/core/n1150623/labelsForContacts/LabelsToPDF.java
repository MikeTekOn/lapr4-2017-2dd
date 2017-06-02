package lapr4.red.s1.core.n1150623.labelsForContacts;

import com.itextpdf.text.Document;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import lapr4.red.s1.core.n1150623.labelsForContacts.domain.Label;

import java.io.FileOutputStream;
import java.io.OutputStream;
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
     *   |            T             | Phone Number |  225 698 885      |
     *   |            O             +--------------+-------------------+
     *   |                          |              |example@mail.pt    |
     *   |            H             | Email        |example2@mail.com  |
     *   |            E             +--------------+-------------------+
     *   |            R             |              |Rua Exemplo1, 79   |
     *   |            E             | Address      |Avenida Exemplo2   |
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
        try {
            Document d = new Document();
            OutputStream fos = new FileOutputStream("out.pdf");
            PdfReader reader = new PdfReader("out.pdf");
            PdfWriter writer = PdfWriter.getInstance(d, fos);
            PdfDocument pdf = new PdfDocument();

        }catch(Exception e){
            System.err.println("ERRO: OUTPUT EXEPTION");
            valid = false;
        }

        if(valid) {

            int contLabels = 0;
            Paragraph p1 = new Paragraph();
            List<ListItem> text = null;
            for (Label label : list.labels()) {
                //Label number
                p1.add("Label " + contLabels + ":");
                contLabels++;

                //table



            }
        }
        return valid;
    }
}

