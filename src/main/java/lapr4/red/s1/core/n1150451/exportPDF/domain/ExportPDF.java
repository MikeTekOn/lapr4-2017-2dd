/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150451.exportPDF.domain;

import lapr4.s1.export.ExportStrategy;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
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
        Document doc = null;
        try {
            doc = initiatePrinter();

            Map<Spreadsheet, Set<Cell>> map = getMapBySpreadSheet();
            Font font = new Font(FontFamily.COURIER, 6, Font.NORMAL);
            Font fontSpecial = new Font(FontFamily.COURIER, 6, Font.BOLD); //Used in the first line and first column
            for (Spreadsheet s : map.keySet()) {
                int[][] dataRowColumn = getMinMaxColumnRow(map.get(s));
                PdfPTable table = new PdfPTable(53);

                table.setWidthPercentage(100);
                String[][] lines = getLinesSheet(map.get(s));
                for (int i = 0; i < 128; i++) {
                    for (int j = 0; j < 53; j++) {
                        if (lines[i][j] == null) {
                            table.addCell(" ");
                        } else {
                            if (i == 0 || j == 0) {
                                table.addCell(new PdfPCell(new Phrase(lines[i][j], fontSpecial)));
                            } else {
                                table.addCell(new PdfPCell(new Phrase(lines[i][j], font)));
                            }
                        }
                    }
                }

                if (sections) {
                    Paragraph p = new Paragraph(s.getTitle());
                    doc.add(p);
                }
                doc.add(table);
            }
        } catch (DocumentException e) {
            //FIXME
        }
        doc.close();
        return true;
    }

    private Document initiatePrinter() {
        Document document = null;

        try {
            document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            document.setMargins(1, 1, 1, 1);
        } catch (DocumentException | FileNotFoundException e) {
        }
        return document;

    }

    private Map<Spreadsheet, Set<Cell>> getMapBySpreadSheet() {
        Map<Spreadsheet, Set<Cell>> map = new HashMap<>();
        for (Cell c : list) {
            //If the spreadsheet doesn't exist in the map
            Spreadsheet spread = c.getSpreadsheet();
            Set<Cell> testSpread = map.get(spread);
            Set<Cell> s;
            if (testSpread == null) {
                s = new HashSet();
            } else {
                s = map.get(c.getSpreadsheet());
            }
            s.add(c);
            map.put(spread, s);
        }
        return map;
    }

    private int[][] getMinMaxColumnRow(Set<Cell> cells) {
        int auxColumnMax = 0;
        int auxColumnMin = Integer.MAX_VALUE;
        int auxRowMax = 0;
        int auxRowMin = Integer.MAX_VALUE;
        Set<Cell> toBeRemoved = new HashSet<>();
        for (Cell c : cells) {
            if (c.getAddress().getColumn() >= 52) {
                toBeRemoved.add(c);
            } else {
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

        }
        cells.removeAll(toBeRemoved);
        int[][] vec = new int[2][2];
        vec[0][0] = auxRowMin;
        vec[0][1] = auxRowMax;
        System.out.println("row max: " + auxRowMax);
        vec[1][0] = auxColumnMin;
        System.out.println("aux min: " + auxColumnMin);
        vec[1][1] = auxColumnMax;
        System.out.println("aux max: " + auxColumnMax);
        return vec;
    }

    private String[][] getLinesSheet(Set<Cell> cells) {
        String[][] alCells = new String[128][53];
        for (int i = 0; i < 128; i++) {
            String[] lines = new String[53];
            if (i == 0) {
                lines[0] = " ";
                fillFirstRow(lines);
            } else {
                lines[0] = String.valueOf(i);
            }
            for (Cell c : cells) {
                if (c.getAddress().getRow() == i - 1) {
                    lines[c.getAddress().getColumn() + 1] = c.getValue().toString();
                }
            }
            alCells[i] = lines;
        }
        return alCells;
    }
    private void fillFirstRow(String[] lines) {
        for (int i = 1; i < 27; i++) {
            lines[i]=""+(char)('A'+(i-1));
        }
        for (int i = 1; i < 27; i++) {
            lines[i+26]="A"+(char)('A'+(i-1));
        }
    }
}
