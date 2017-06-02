/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT.importTXT.ctrl;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;
import eapli.framework.application.Controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.CellDTO;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class ImportDataController implements Controller {

    /**
     * Files can only be of the following extension type
     */
    private static final String VALID_EXTENSION = "txt";

    /**
     * The UI Controller
     */
    private UIController uiController;

    /**
     * The file that contains the data to be imported
     */
    private File fileToRead;

    /**
     * The character that separates columns in the file
     */
    private char separatorCharacter;

    /**
     * The range of cells that will be filled with the information obtained from
     * the file
     */
    private CellRange cellRange;

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
     *
     */
    public ImportDataController(UIController uiController, File fileToRead, char separatorCharacter, CellRange cellRange) {
        this.fileToRead = fileToRead;
        if (!fileIsOfExtension(VALID_EXTENSION)) {
            throw new IllegalArgumentException("Chosen file is not valid.");
        }

        this.uiController = uiController;
        this.separatorCharacter = separatorCharacter;
        this.cellRange = cellRange;
    }

    /**
     * Verifies if the file extension is a valid extension
     *
     * @param validExtension - the valid extension
     *
     */
    private boolean fileIsOfExtension(String validExtension) {
        String fileName = fileToRead.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        
        return fileExtension.equalsIgnoreCase(validExtension);
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

        CellDTO cellList[][] = getFileData();

        int i, j;
        for (i = 0; i < cellList.length; i++) {
            for (j = 0; j < cellList[0].length; j++) {
                CellDTO cdto = cellList[i][j];
                String content = cdto.getContent();
                Cell cell = activeSpreadsheet.getCell(cdto.getAddress());
                cell.setContent(content);
            }
        }

    }

    /**
     * Reads and returns the file data
     *
     * @return a list of cells containing the data read from a file
     * @throws FileNotFoundException
     * @throws IOException
     */
    private CellDTO[][] getFileData() throws FileNotFoundException, IOException {
        FileInputStream stream = new FileInputStream(fileToRead);
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));

        String line;
        int columns = cellRange.getColumns();
        int rows = cellRange.getRows();

        String data[][] = new String[rows][columns];

        int i, j, row = 0;
        while ((line = br.readLine()) != null) {
            if (!line.isEmpty()) {
                String[] rowData = line.split(String.valueOf(separatorCharacter));
                for (i = 0; i < columns; i++) {
                    data[row][i] = rowData[i];
                }
                row++;
            }
        }

        br.close();

        Cell firstCell = cellRange.getFirstCell();

        int firstRow = firstCell.getAddress().getRow();
        int firstColumn = firstCell.getAddress().getColumn();

        CellDTO cellList[][] = new CellDTO[rows][columns];

        for (i = 0; i < rows; i++) {
            for (j = 0; j < columns; j++) {
                Address address = activeSpreadsheet.getCell(firstColumn + j, firstRow + i).getAddress();
                CellDTO cell = new CellDTO(address, data[i][j]);
                cellList[i][j] = cell;
            }
        }

        return cellList;
    }
}
