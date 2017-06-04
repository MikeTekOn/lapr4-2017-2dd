/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s1.core.n1150451.exportPDF.application;

import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import lapr4.red.s1.core.n1150451.exportPDF.domain.ExportPDF;
import lapr4.red.s1.core.n1150451.exportPDF.domain.WorkbookHandler;
import lapr4.s1.export.ExportContext;

import java.util.regex.Pattern;

/**
 *
 * @author Diogo Santos
 */
public class ExportPDFController {

    private ExportPDF ePDF;
    private ExportContext exportContext;
    private UIController c;

    public void initiateExport(UIController c) {
        ePDF = new ExportPDF();
        exportContext = new ExportContext(ePDF);
        this.c = c;
    }

    public void selectRange(Workbook wb) {
        ePDF.selectRange(new WorkbookHandler(wb).getListCellsWorkBook());
    }

    public void selectRange(Spreadsheet ws) {
        ePDF.selectRange(new WorkbookHandler(ws.getWorkbook()).getListCellsSpreadsheet(ws));
    }

    public void selectRange(Spreadsheet ws, String text) {
        ePDF.selectRange(new WorkbookHandler(ws.getWorkbook()).getListCellsSpreadSheetWithinRange(ws, text, c, ePDF));
    }

    public void toggleSections() {
        ePDF.toggleSections();
    }

    public void selectPath(String path) {
        ePDF.selectPath(path);
    }

    public void export() {
        exportContext.executeStrategy();
    }


}
