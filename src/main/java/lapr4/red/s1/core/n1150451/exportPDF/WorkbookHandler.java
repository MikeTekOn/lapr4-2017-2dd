/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150451.exportPDF;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import lapr4.red.s1.core.n1150451.exportPDF.domain.ExportPDF;

/**
 * Class designed to be responsible for getting the cell lists from a given
 * range.
 *
 * @author Diogo Santos
 */
public class WorkbookHandler {

    private final Workbook w;

    public WorkbookHandler(Workbook w) {
        this.w = w;
    }

    /**
     * Returns the list of cells in the entire Workbook.
     *
     * @return cell's list.
     */
    public List<Cell> getListCellsWorkBook() {
        List<Cell> list = new ArrayList<>();
        Iterator<Spreadsheet> it = w.iterator();
        while (it.hasNext()) {
            list.addAll(getListCellsSpreadsheet(it.next()));
        }
        return list;
    }

    /**
     * Returns the list of cells in the given spreadsheet.
     *
     * @param s Selected Spreadsheet
     * @return Cell's list
     */
    public List<Cell> getListCellsSpreadsheet(Spreadsheet s) {
        List<Cell> list = new ArrayList<>();
        list.addAll(getListCellsBetweenRange(s, 0, 127, 0, 52));
        return list;
    }

    /**
     * Returns the list os cells between a numeric range.
     *
     * @param s Selected Spreadsheet
     * @param topLeftRow Minimum value of the row in the range.
     * @param bottomRightRow Maximum value of the row in the given range.
     * @param topLeftColumn Minimum column value.
     * @param topRightColumn Maximum column value.
     * @return Cell's List
     */
    public List<Cell> getListCellsBetweenRange(Spreadsheet s, int topLeftRow, int bottomRightRow, int topLeftColumn, int topRightColumn) {
        List<Cell> list = new ArrayList<>();
        for (int i = topLeftRow; i <= bottomRightRow; i++) {
            for (int j = topLeftColumn; j <= topRightColumn; j++) {
                list.add(s.getCell(j, i));
            }
        }
        return list;
    }

    /**
     * Return the list of cells between the range passed by parameter.
     *
     * @param ws Spreadsheet
     * @param text Range in text format. If the range isn't in the right format
     * (A4:B6, for example) throws an IllegalArgumentException.
     * @param uiC UI Controller
     * @param ePDF exportPDF class for passing the range limits.
     * @return Cells list
     */
    public List<Cell> getListCellsSpreadSheetWithinRange(Spreadsheet ws, String text, UIController uiC, ExportPDF ePDF) {

        final Pattern pattern = Pattern.compile("[A-Z]+[0-9]+:[A-Z]+[0-9]+");
        if (!pattern.matcher(text).matches()) {
            throw new IllegalArgumentException();
        }
        String[] range = text.split(":");
        CellRange cellRange = new CellRange(range[0], range[1], uiC);
        List<Cell> cells = new ArrayList<>();
        Address leftCell = cellRange.getFirstCell().getAddress();
        Address rightCell = cellRange.getLastCell().getAddress();
        ePDF.setLimits(leftCell.getRow(), rightCell.getRow(), leftCell.getColumn(), rightCell.getColumn());
        cells.addAll(ws.getCells(leftCell, rightCell));
        return cells;

    }
}
