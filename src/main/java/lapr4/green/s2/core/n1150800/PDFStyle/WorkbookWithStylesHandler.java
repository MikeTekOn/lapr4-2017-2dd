/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150800.PDFStyle;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import java.util.List;
import lapr4.red.s1.core.n1150451.exportPDF.WorkbookHandler;
import lapr4.red.s1.core.n1150451.exportPDF.domain.ExportPDF;

/**
 * Class designed to be responsible for getting the cell lists along with their 
 * styles from a given range.
 * 
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class WorkbookWithStylesHandler extends WorkbookHandler {
    
    /**
     * The list of cells with their respective styles
     */
    private List<StylableCell> stylableCells;
    
    /**
     * Builds an instance of the Handler with a
     * @param w - workbook chosen by the user
     */
    public WorkbookWithStylesHandler(Workbook w) {
        super(w);
        stylableCells = new ArrayList<>();
    }
    
    /**
     * Provides a list of cells with their styles
     * @return list of cells with their styles
     */
    public List<StylableCell> getStylableCellsFromWorkbook() {
        List<Cell> cells = getListCellsWorkBook();
        return getStyleFromCells(cells);
    }
    
    /**
     * Provides a list of cells with their styles
     * @return list of cells with their styles
     */
    public List<StylableCell> getStylableCellsFrom(Spreadsheet s) {
        List<Cell> cells = getListCellsSpreadsheet(s);
        return getStyleFromCells(cells);
    }
 
    /**
     * Provides a list of cells with their styles
     * @return list of cells with their styles
     */
    public List<StylableCell> getStylableCellsFromSpreadsheetWithinRange(Spreadsheet s, String strRange, UIController uiC, ExportPDF ePDF) {
        List<Cell> cells = getListCellsSpreadSheetWithinRange(s, strRange, uiC, ePDF);
        return getStyleFromCells(cells);
    }
    
    /**
     * Provides the styles of the list of cells passed
     * @param cells - list of cells from the given ranges
     * @return the list of cells with their styles
     */
    private List<StylableCell> getStyleFromCells(List<Cell> cells) {
        for (Cell cell : cells) {
            stylableCells.add((StylableCell) cell.getExtension(StyleExtension.NAME));
        }
        
        return stylableCells;
    }
    
    
}
