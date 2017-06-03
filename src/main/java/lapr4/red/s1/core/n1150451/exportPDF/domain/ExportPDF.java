/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150451.exportPDF.domain;

import lapr4.s1.export.ExportStrategy;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    public void selectRange(List<Cell> list) {
        this.list = list;
    }

    public void toggleSections() {
        sections = !sections;
    }

    public void selectPath(String path) {
        this.path = path;
    }

    @Override
    public boolean export() {
        try {
            Document doc = initiatePrinter();
            Map<Spreadsheet, Set<Cell>> map = getMapBySpreadSheet();

            for (Spreadsheet s : map.keySet()) {
                int[][] dataRowColumn = getMinMaxColumnRow(map.get(s));
                PdfPTable table = new PdfPTable(dataRowColumn[1][1] - dataRowColumn[1][0]);
                for (Cell c : map.get(s)) {
                    table.addCell(c.getValue().toString());
                }
                if (sections) {
                    Paragraph p = new Paragraph("Spreadsheet " + s.getTitle());
                    doc.add(p);
                }
                doc.add(table);
            }
        } catch (DocumentException e) {
            //FIXME
        }
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
        document.close();
        return document;

    }

    private Map<Spreadsheet, Set<Cell>> getMapBySpreadSheet() {
        Map<Spreadsheet, Set<Cell>> map = new HashMap<>();
        for (Cell c : list) {
            //If the spreadsheet doesn't exist in the map
            Spreadsheet auxSpread = c.getSpreadsheet();
            Set<Cell> s = map.get(c.getSpreadsheet());
            if (auxSpread == null) {
                s = new HashSet();
            }
            s.add(c);
            map.put(auxSpread, s);
        }
        return map;
    }

    private int[][] getMinMaxColumnRow(Set<Cell> cells) {
        int auxColumnMax = 0;
        int auxColumnMin = Integer.MAX_VALUE;
        int auxRowMax = 0;
        int auxRowMin = Integer.MAX_VALUE;
        for (Cell c : cells) {
            if (c.getAddress().getColumn() > auxColumnMax) {
                auxColumnMax = c.getAddress().getColumn();
            }
            if (c.getAddress().getColumn() < auxColumnMin) {
                auxColumnMin = c.getAddress().getColumn();
            }
            if (c.getAddress().getRow() > auxRowMax) {
                auxRowMax = c.getAddress().getRow();
            }
            if (c.getAddress().getRow() < auxRowMin) {
                auxRowMin = c.getAddress().getRow();
            }
        }
        int[][] vec = new int[2][2];
        vec[0][0] = auxRowMin;
        vec[0][1] = auxRowMax;
        vec[1][0] = auxColumnMin;
        vec[1][1] = auxColumnMax;
        return vec;
    }
}
