/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150451.exportPDF.domain;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import lapr4.s1.export.ExportStrategy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Diogo Santos
 */
public class ExportPDF implements ExportStrategy {

    private String path;
    private List<Cell> list;
    private boolean sections;

    //Table limits
    private int minRow = 0;
    private int maxRow = 127;
    private int minColumn = 0;
    private int maxColumn = 52;

    private HashMap<String, Anchor> mapAnchor = new HashMap<>();

    public ExportPDF() {

    }

    public void selectRange(List<Cell> list) {
        this.list = list;
    }

    public void toggleSections() {
        sections = !sections;
    }

    public void selectPath(String path) {

        final Pattern pattern = Pattern.compile(".+\\.pdf");
        if (!pattern.matcher(path).matches()) {
            throw new IllegalArgumentException();
        }
        this.path = path;
    }

    @Override
    public boolean export() {
        Document doc = null;
        try {
            doc = initiatePrinter();

            Map<Spreadsheet, Set<Cell>> map = getMapBySpreadSheet();

            if (sections) {

                createFrontPage(doc, map);
                doc.newPage();
            }
            Font font = new Font(FontFamily.COURIER, 6, Font.NORMAL);
            Font fontSpecial = new Font(FontFamily.COURIER, 6, Font.BOLD); //Used in the first line and first column
            for (Spreadsheet s : map.keySet()) {
                removeHiddenCells(map.get(s));
                PdfPTable table = new PdfPTable(maxColumn - minColumn + 1);

                table.setWidthPercentage(100);
                String[][] lines = getLinesSheet(map.get(s));
                for (int i = minRow; i < maxRow + 1; i++) {
                    for (int j = minColumn; j < maxColumn + 1; j++) {
                        if (j == minColumn) {
                            if (i == minRow) {
                                table.addCell(new PdfPCell(new Phrase(lines[0][0], fontSpecial)));
                            } else {
                                table.addCell(new PdfPCell(new Phrase(lines[i][0], fontSpecial)));
                            }
                        } else if (i == minRow) {
                            table.addCell(new PdfPCell(new Phrase(lines[0][j], fontSpecial)));

                        } else {
                            if (lines[i][j] == null) {
                                table.addCell(" ");
                            } else {
                                table.addCell(new PdfPCell(new Phrase(lines[i][j], font)));
                            }
                        }
                    }
                }

                if (sections) {
                    Anchor anchorTarget
                            = new Anchor(s.getTitle());
                    anchorTarget.setName(s.getTitle());
                    Paragraph p = new Paragraph();
                    p.add(anchorTarget);
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

    private void removeHiddenCells(Set<Cell> cells) {
        Set<Cell> toBeRemoved = new HashSet<>();
        for (Cell c : cells) {
            if (c.getAddress().getColumn() >= 52 || c.getAddress().getRow() > 127) {
                toBeRemoved.add(c);
            }
        }

        cells.removeAll(toBeRemoved);
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
            lines[i] = "" + (char) ('A' + (i - 1));
        }
        for (int i = 1; i < 27; i++) {
            lines[i + 26] = "A" + (char) ('A' + (i - 1));
        }
    }

    public void setLimits(int minRow, int maxRow, int minColumn, int maxColumn) {
        this.minRow = minRow;
        this.maxRow = maxRow + 1;
        this.minColumn = minColumn;
        this.maxColumn = maxColumn + 1;
    }

    private void createFrontPage(Document doc, Map<Spreadsheet, Set<Cell>> map) {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(50);
        for (Spreadsheet s : map.keySet()) {
            Anchor anchor
                    = new Anchor(s.getTitle());
            anchor.setReference("#" + s.getTitle());
            Paragraph p = new Paragraph();
            p.add(anchor);

            mapAnchor.put(s.getTitle(), anchor);
            table.addCell(new PdfPCell(new Phrase(p)));

        }
        try {
            doc.add(table);
        } catch (DocumentException ex) {
            //Logger.getLogger(ExportPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
