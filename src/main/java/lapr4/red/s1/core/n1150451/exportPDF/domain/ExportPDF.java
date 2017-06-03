/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150451.exportPDF.domain;

import lapr4.s1.export.ExportStrategy;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import csheets.core.Cell;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diogo Santos
 */
public class ExportPDF implements ExportStrategy {

    private String path;
    private List<Cell> list;
    private boolean sections;

    public ExportPDF() {

    }

    public void selectRange(List<Cell> list){
        this.list=list;
    }
    
    public void toggleSections() {
        sections = !sections;
    }
    
    public void selectPath(String path){
        this.path=path;
    }
    
    @Override
    public boolean export() {
        return true;
    }

    private Document initiatePrinter() {
        Document document = null;

        try {
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
        } catch (DocumentException | FileNotFoundException e) {
            //FIXME
        }
        return document;

    }
}
