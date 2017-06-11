/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150800.PDFStyle.application;

import com.itextpdf.text.BaseColor;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import lapr4.red.s1.core.n1150451.exportPDF.application.ExportPDFController;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ExportStylePDFController extends ExportPDFController {
    
    /**
     * Builds an instance of ExportStylePDFController with the user interface 
     * controller
     * @param c - the user interface controller
     */
    public ExportStylePDFController(UIController c) {
        super.initiateExport(c);
    }

    @Override
    public void selectPath(String path) {
        super.selectPath(path);
    }

    @Override
    public void selectRange(Spreadsheet ws) {
        super.selectRange(ws);
    }

    @Override
    public void selectRange(Workbook wb) {
        super.selectRange(wb);
    }

    @Override
    public void selectRange(Spreadsheet ws, String text) {
        super.selectRange(ws, text);
    }

    @Override
    public void toggleSections() {
        super.toggleSections();
    }
    
    /**
     * Manages the borders of the cells in the PDF document
     * @param solidBorder
     * @param dottedBorder
     * @param noBorder 
     * @param borderColor
     */
    public void borderConfigurations(boolean solidBorder, boolean dottedBorder, boolean noBorder, BaseColor borderColor) {
        ePDF.manageBorders(solidBorder, dottedBorder, noBorder, borderColor);
    }

    @Override
    public void export() {
        super.export();
    }
}
