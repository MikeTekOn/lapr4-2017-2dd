/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT.exportTXT.ctrl;

import csheets.core.Spreadsheet;
import csheets.ui.ctrl.UIController;
import eapli.framework.application.Controller;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.CellDTO;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.green.s1.ipc.n1150800.importexportTXT.FileData;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import lapr4.blue.s2.ipc.n1141570.importExportTxtLink.ctrl.LinkTxtExportController;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ExportDataController implements Controller {

    /**
     * The UI Controller
     */
    private UIController uiController;

    /**
     * The file that will save the data
     */
    private FileData fileToWrite;

    /**
     * The active spreadsheet in the current workbook
     */
    private Spreadsheet activeSpreadsheet;
    
    /**
     * The link txt export controller
     */
    private LinkTxtExportController linkTxtExportController;

    /**
     * Creates an ExportDataController instance with
     * @param uiController - the UI Controller
     * @param fileToWrite - the File that will save the data
     * @param separatorCharacter - the character that separates the data by
     * columns in the file
     * @param cellRange - the range of cells from which the data will be read
     * and saved in the file
     * @param firstLineRepresentsHeaders - a boolean variable that determines if
     * the first line of the file is a line of headers
     */
    public ExportDataController(UIController uiController, File fileToWrite, char separatorCharacter, CellRange cellRange, boolean firstLineRepresentsHeaders) {
        FileData fileData = new FileData(fileToWrite, separatorCharacter, cellRange, firstLineRepresentsHeaders);

        this.uiController = uiController;
        this.fileToWrite = fileData;
        
        this.activeSpreadsheet = uiController.getActiveSpreadsheet();
        
        this.linkTxtExportController = new LinkTxtExportController(this.activeSpreadsheet, fileToWrite, separatorCharacter);
        this.linkTxtExportController.fireLinkWriterThread();
    }

    /**
     * This method writes in the file the data provided from a range of cells
     * @throws IOException
     */
    public void writeData() throws IOException {
        activeSpreadsheet = uiController.getActiveSpreadsheet();

        CellDTO cellList[][] = fileToWrite.getCellsFromRange(activeSpreadsheet);

        fileToWrite.setToFile(cellList);
        
        JOptionPane.showMessageDialog(null, "Export and its link succeded!");
    }
}
