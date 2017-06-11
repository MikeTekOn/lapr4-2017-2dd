/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150451.exportPDF.application;

import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import lapr4.s1.export.ExportContext;

import lapr4.green.s2.core.n1150800.PDFStyle.ExportStylePDF;
import lapr4.green.s2.core.n1150800.PDFStyle.WorkbookWithStylesHandler;

/**
 *
 * @author Diogo Santos
 */
public class ExportPDFController {

    protected ExportStylePDF ePDF;
    private ExportContext exportContext;
    private UIController c;

    
    public void initiateExport(UIController c) {
        ePDF = new ExportStylePDF();
        exportContext = new ExportContext(ePDF);
        this.c = c;
    }

    /*
     * Selects the range of the Workbook.
     * @param wb Workbook
     */
    public void selectRange(Workbook wb) {
        ePDF.selectRange(new WorkbookWithStylesHandler(wb).getListCellsWorkBook());
    }

    /*
     * Selected the range corresponding to the Spreadsheet
     * @param ws 
     */
    public void selectRange(Spreadsheet ws) {
        ePDF.selectRange(new WorkbookWithStylesHandler(ws.getWorkbook()).getListCellsSpreadsheet(ws));
    }

    /**
     * Selected the range corresponding to the Spreadsheet and the the manually
     * introduced range
     *
     * @param ws Selected Spreadsheet
     * @param text textRange in text form (A3:B8, for example)
     */
    public void selectRange(Spreadsheet ws, String text) {
        ePDF.selectRange(new WorkbookWithStylesHandler(ws.getWorkbook()).getListCellsSpreadSheetWithinRange(ws, text, c, ePDF));
    }

    /*
     * Toggles the section options. 
     */
    public void toggleSections() {
        ePDF.toggleSections();
    }

    public void selectPath(String path) {
        ePDF.selectPath(path);
    }

    /*
    Exports to a PDF with the selected settings.
     */
    public void export() {
        exportContext.executeStrategy();
    }

}
