/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150800.PDFStyle;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import lapr4.red.s1.core.n1150451.exportPDF.domain.ExportPDF;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ExportStylePDF extends ExportPDF {

    /**
     * The value for the horizontal left alignment in a cell
     */
    private static final int HORIZONTAL_ALIGNMENT_LEFT = 0;

    /**
     * The value for the horizontal center alignment in a cell
     */
    private static final int HORIZONTAL_ALIGNMENT_CENTER = 1;

    /**
     * The value for the horizontal right alignment in a cell
     */
    private static final int HORIZONTAL_ALIGNMENT_RIGHT = 2;

    /**
     * The variable that checks if the border chosen is "Solid Border"
     */
    private boolean solidBorder;

    /**
     * The variable that checks if the border chosen is "Dotted Border"
     */
    private boolean dottedBorder;

    /**
     * The variable that checks if the border chosen is "No Border"
     */
    private boolean noBorder;

    /**
     * The border color
     */
    private BaseColor borderColor;

    /**
     * Builds an instance of ExportStylePDF with the default values
     */
    public ExportStylePDF() {
        solidBorder = false;
        dottedBorder = false;
        noBorder = false;
    }

    @Override
    public void selectRange(List<Cell> list) {
        super.selectRange(list);
    }

    @Override
    public void toggleSections() {
        super.toggleSections();
    }

    @Override
    public void selectPath(String path) {
        super.selectPath(path);
    }

    /**
     * Manages the border configurations
     * @param solidBorder
     * @param dottedBorder
     * @param noBorder
     * @param borderColor
     */
    public void manageBorders(boolean solidBorder, boolean dottedBorder, boolean noBorder, BaseColor borderColor) {
        if (!solidBorder && !dottedBorder && !noBorder) {
            throw new IllegalArgumentException("Select a border configuration!");
        }

        this.solidBorder = solidBorder;
        this.dottedBorder = dottedBorder;
        this.noBorder = noBorder;
        this.borderColor = borderColor;
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

            Font font = new Font(Font.FontFamily.COURIER, 6, Font.NORMAL);
            Font fontSpecial = new Font(Font.FontFamily.COURIER, 6, Font.BOLD); //Used in the first line and first column

            for (Spreadsheet s : map.keySet()) {
                removeHiddenCells(map.get(s));
                PdfPTable table = new PdfPTable(maxColumn - minColumn + 1);
                table.setTotalWidth(PageSize.A4.rotate().getWidth());
                table.setLockedWidth(true);

                Cell[][] cellLines = getCellsSheet(map.get(s));
                String[] rows = new String[128];
                fillFirstColumn(rows);

                String[] columns = new String[53];
                columns[0] = "";
                fillFirstRow(columns);

                for (int i = minRow; i < maxRow + 1; i++) {
                    for (int j = minColumn; j < maxColumn + 1; j++) {
                        if (j == minColumn) {
                            if (i == minRow) {
                                PdfPCell emptyCell = new PdfPCell(new Phrase("", fontSpecial));
                                addBorderTo(emptyCell);
                                emptyCell.setBorder(Rectangle.NO_BORDER);
                                table.addCell(emptyCell); //If it is the upper left cell, adds empty string

                            } else {
                                //else, adds the left column containing the row identifications.
                                PdfPCell rowCell = new PdfPCell(new Phrase(rows[i], fontSpecial));
                                rowCell.setHorizontalAlignment(HORIZONTAL_ALIGNMENT_CENTER);
                                addBorderTo(rowCell);
                                table.addCell(rowCell);
                            }
                        } else if (i == minRow) {
                            //Adds the top row containing the column indentifications
                            PdfPCell columnCell = new PdfPCell(new Phrase(columns[j], fontSpecial));
                            columnCell.setHorizontalAlignment(HORIZONTAL_ALIGNMENT_CENTER);
                            addBorderTo(columnCell);
                            table.addCell(columnCell);
                        } else {
                            Cell cell = cellLines[i][j];
                            StylableCell stylableCell = (StylableCell) cell.getExtension(StyleExtension.NAME);

                            PdfPCell pdfCell = new PdfPCell();

                            /* BORDER */
                            addBorderTo(pdfCell);

                            /* FONT */
                            int style = stylableCell.getFont().getStyle();
                            font.setStyle(style);

                            int size = stylableCell.getFont().getSize();
                            font.setSize(size - 5);

                            /* BACKGROUND COLOR */
                            Color backgroundColor = stylableCell.getBackgroundColor();
                            BaseColor bg = new BaseColor(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue());
                            pdfCell.setBackgroundColor(bg);

                            /* FOREGROUND COLOR */
                            FontSelector selector = new FontSelector();
                            Color foregroundColor = stylableCell.getForegroundColor();
                            font.setColor(foregroundColor.getRed(), foregroundColor.getGreen(), foregroundColor.getBlue());
                            selector.addFont(font);

                            /* HORIZONTAL ALIGNMENT */
                            int horizontalAlignment = stylableCell.getHorizontalAlignment();
                            if (horizontalAlignment == 0) {
                                pdfCell.setHorizontalAlignment(HORIZONTAL_ALIGNMENT_CENTER);
                            }

                            if (horizontalAlignment == 2) {
                                pdfCell.setHorizontalAlignment(HORIZONTAL_ALIGNMENT_LEFT);
                            }

                            if (horizontalAlignment == 4) {
                                pdfCell.setHorizontalAlignment(HORIZONTAL_ALIGNMENT_RIGHT);
                            }

                            /* VERTICAL ALIGNMENT */
                            int verticalAlignment = stylableCell.getVerticalAlignment();
                            pdfCell.setVerticalAlignment(verticalAlignment);

                            /* CONTENT */
                            if (stylableCell.getContent() == null) {
                                pdfCell.setPhrase(new Phrase(""));
                            } else {
                                pdfCell.setPhrase(selector.process(stylableCell.getContent()));
                            }

                            /* ADDING CELL TO TABLE */
                            table.addCell(pdfCell);
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
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if(doc != null) {
            doc.close();    
        }
        
        return true;
    }

    /**
     * Returns the area that the user selected filled with the cells in a matrix
     * position.
     *
     * @param cells Cells of the spreadsheet to be placed in the matrix.
     * @return
     */
    private Cell[][] getCellsSheet(Set<Cell> cells) {
        Cell[][] allCells = new Cell[128][53];
        for (int i = 1; i < 128; i++) {
            Cell[] cellLine = new Cell[53];

            for (Cell c : cells) {
                if (c.getAddress().getRow() == i - 1) {
                    cellLine[c.getAddress().getColumn() + 1] = c;
                }
            }

            allCells[i] = cellLine;
        }
        return allCells;
    }

    /**
     * Fills an array with the row identifiers
     *
     * @param rows - the array to be filled
     */
    private void fillFirstColumn(String[] rows) {
        for (int i = 0; i < 128; i++) {
            if (i == 0) {
                rows[i] = "";
            } else {
                rows[i] = String.valueOf(i);
            }
        }
    }

    @Override
    public void setLimits(int minRow, int maxRow, int minColumn, int maxColumn) {
        super.setLimits(minRow, maxRow, minColumn, maxColumn);
    }

    /**
     * Adds a border to a PDF cell
     * @param cell - the PDF cell
     */
    private void addBorderTo(PdfPCell cell) {
        if (dottedBorder) {
            cell.setCellEvent(new DottedBorderCellEvent(borderColor));
        }

        if (noBorder) {
            cell.setBorder(Rectangle.NO_BORDER);
        }

        if (solidBorder) {
            cell.setUseBorderPadding(true);
            cell.setBorderColor(borderColor);
        }
    }

    /**
     * Nested class event necessary for making the dotted lines of a border
     */
    private class DottedBorderCellEvent implements PdfPCellEvent {

        /**
         * The border color
         */
        private BaseColor color;

        /**
         * Builds an instance of DottedBorderCellEvent with the border color
         * @param color - the border color
         */
        DottedBorderCellEvent(BaseColor color) {
            this.color = color;
        }

        @Override
        public void cellLayout(PdfPCell cell, Rectangle rec, PdfContentByte[] cont) {
            PdfContentByte canvas = cont[PdfPTable.LINECANVAS];
            canvas.setLineDash(3f, 3f);
            canvas.setColorStroke(color);
            canvas.stroke();
        }

    }
}
