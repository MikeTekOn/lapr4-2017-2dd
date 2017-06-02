/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT.exportTXT.ctrl;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.ui.ctrl.UIController;
import eapli.framework.application.Controller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.CellDTO;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ExportDataController implements Controller {

    /**
     * Files can only be of the following extension type
     */
    private static final String VALID_EXTENSION = ".txt";

    /**
     * The UI Controller
     */
    private UIController uiController;

    /**
     * The file that will save the data
     */
    private File fileToWrite;

    /**
     * The character that separates columns in the file
     */
    private char separatorCharacter;

    /**
     * The range of cells that keep the data to be stored in the file
     */
    private CellRange cellRange;

    /**
     * The active spreadsheet in the current workbook
     */
    private Spreadsheet activeSpreadsheet;

    /**
     * Creates an ExportDataController instance with
     * @param uiController - the UI Controller
     * @param fileToWrite - the File that will save the data
     * @param separatorCharacter - the character that separates the data by
     * columns in the file
     * @param cellRange - the range of cells from which the data will be read
     * and saved in the file
     */
    public ExportDataController(UIController uiController, File fileToWrite, char separatorCharacter, CellRange cellRange) {
        if (!fileIsOfExtension(VALID_EXTENSION)) {
            throw new IllegalArgumentException("Chosen file is not valid.\n");
        }

        this.uiController = uiController;
        this.fileToWrite = fileToWrite;
        this.separatorCharacter = separatorCharacter;
        this.cellRange = cellRange;
    }

    /**
     * Verifies if the file extension is a valid extension
     * @param validExtension - the valid extension
     * @return
     */
    private boolean fileIsOfExtension(String validExtension) {
        String fileName = fileToWrite.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

        return fileExtension.equalsIgnoreCase(validExtension);
    }

    /**
     * This method writes in the file the data provided from a range of cells
     * @throws IOException 
     */
    public void writeData() throws IOException {
        activeSpreadsheet = uiController.getActiveSpreadsheet();

        CellDTO cellList[][] = getCellsFromRange();

        setToFile(cellList);
    }

    /**
     * This method obtains the containt from the cells in the defined range
     * @return the cells' content
     */
    public CellDTO[][] getCellsFromRange() {
        int rows = cellRange.getRows();
        int columns = cellRange.getColumns();

        Cell firstCell = cellRange.getFirstCell();

        int firstRow = firstCell.getAddress().getRow();
        int firstColumn = firstCell.getAddress().getColumn();

        CellDTO cellList[][] = new CellDTO[rows][columns];

        int i, j;
        for (i = 0; i < rows; i++) {
            for (j = 0; j < columns; j++) {
                Cell cell = activeSpreadsheet.getCell(firstColumn + j, firstRow + i);
                CellDTO cdto = CellDTO.createFromCell(cell);
                cellList[i][j] = cdto;
            }
        }

        return cellList;
    }
    
    /**
     * Writes the data from the cells into a file with
     * @param cellList - the list of cells that contains the data to be written
     * into a file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void setToFile(CellDTO[][] cellList) throws FileNotFoundException, IOException {
        FileOutputStream stream = new FileOutputStream(fileToWrite);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(stream));

        String line;

        int i, j;
        for (i = 0; i < cellList.length; i++) {
            line = "";
            for (j = 0; j < cellList[0].length; j++) {
                line = line + cellList[i][j].getContent() + separatorCharacter;
            }
            bw.write(line);
            bw.newLine();
        }

        bw.flush();

        bw.close();
    }
}
