/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150800.PDFStyle;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ExportStylePDFThread implements Runnable {

    /**
     * The controller of the "Export Style PDF" use case
     */
    private ExportStylePDFController controller;
    
    /**
     * Builds an instance of the thread with
     * @param controller - the controller of the "Export Style PDF" use case
     */
    public ExportStylePDFThread(ExportStylePDFController controller) {
        this.controller = controller;
    }
    
    @Override
    public void run() {
        controller.export();
    }
    
}
