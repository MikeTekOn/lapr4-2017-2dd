/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT.importTXT.ctrl;

import csheets.core.Spreadsheet;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;
import eapli.framework.application.Controller;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.CellDTO;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.green.s1.ipc.n1150800.importexportTXT.FileData;

import java.io.File;
import java.io.IOException;
import lapr4.blue.s2.ipc.n1141570.importExportTxtLink.ctrl.LinkTxtController;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ImportDataController implements Controller {

    /**
     * The UI Controller
     */
    private UIController uiController;

    /**
     * The Link Txt Controller
     */
    private LinkTxtController linkTxtController;

    /**
     * The file that contains the data to be imported
     */
    private FileData fileToRead;

    /**
     * The active spreadsheet in the current workbook
     */
    private Spreadsheet activeSpreadsheet;

    /**
     * Creates an ImportDataController instance with
     *
     * @param uiController - the UI Controller
     * @param fileToRead - the File containing the data
     * @param separatorCharacter - the character that separates the data by
     * columns in the file
     * @param cellRange - the range of cells that will be filled with the data
     * @param firstLineRepresentsHeaders - a boolean variable that determines if
     * the first line of the file is a line of headers
     *
     */
    public ImportDataController(UIController uiController, File fileToRead, char separatorCharacter, CellRange cellRange, boolean firstLineRepresentsHeaders, Spreadsheet spreadsheet) {
        FileData fileData = new FileData(fileToRead, separatorCharacter, cellRange, firstLineRepresentsHeaders);

        this.uiController = uiController;
        this.fileToRead = fileData;

        this.linkTxtController = new LinkTxtController(fileToRead, separatorCharacter, cellRange, firstLineRepresentsHeaders, this.uiController, spreadsheet);
        this.linkTxtController.fireLinkReaderThread();
    }

    /**
     * This method reads the data from the file and fills a range of cells with
     * the given data
     *
     * @throws IOException
     * @throws FormulaCompilationException
     */
    public void readData() throws IOException, FormulaCompilationException {
        activeSpreadsheet = uiController.getActiveSpreadsheet();

        CellDTO cellList[][] = fileToRead.getFileData(activeSpreadsheet);

        fileToRead.fillCells(cellList);

    }
}
