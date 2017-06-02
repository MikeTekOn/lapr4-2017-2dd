/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150800.importexportTXT;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.formula.compiler.FormulaCompilationException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.CellDTO;

/**
 *
 * @author Pedro Chilro (1150800@isep.ipp.pt)
 */
public class FileData {
    
    /**
     * Files can only be of the following extension type
     */
    private static final String VALID_EXTENSION = "txt";
    
    /**
     * The file from which the data will be read from / written to
     */
    private File file;
    
    /**
     * The character that separates columns in the file
     */
    private char separatorCharacter;

    /**
     * The range of cells
     */
    private CellRange cellRange;
    
    /**
     * The active spreadsheet in the current workbook
     */
    private Spreadsheet activeSpreadsheet;
    
    /**
     * Builds an instance of FileData with
     * @param file - the file
     * @param separatorCharacter - the separator character
     * @param cellRange - a range of cells
     */
    public FileData(File file, char separatorCharacter, CellRange cellRange) {
        this.file = file;
        if (!fileIsOfExtension(VALID_EXTENSION)) {
            throw new IllegalArgumentException("Chosen file is not valid.\n");
        }
        
        this.separatorCharacter = separatorCharacter;
        this.cellRange = cellRange;
    }
    
    public boolean fileIsOfExtension(String validExtension) {
        String fileName = file.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

        return fileExtension.equalsIgnoreCase(validExtension);
    }

    /**
     * Reads and returns the file data
     *
     * @param activeSpreadsheet
     * @return a list of cells containing the data read from a file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public CellDTO[][] getFileData(Spreadsheet activeSpreadsheet) throws FileNotFoundException, IOException {
        FileInputStream stream = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        this.activeSpreadsheet = activeSpreadsheet;
        
        String line;
        int columns = cellRange.getColumns();
        int rows = cellRange.getRows();

        String data[][] = new String[rows][columns];

        int i, j, row = 0;
        while ((line = br.readLine()) != null) {
            if (!line.isEmpty()) {
                String[] rowData = line.split(String.valueOf(separatorCharacter));
                for (i = 0; i < rowData.length; i++) {
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
                CellDTO cell;
                if(data[i][j] != null) {
                    cell = new CellDTO(address, data[i][j]);
                } else {
                    cell = new CellDTO(address, "");
                }
                cellList[i][j] = cell;
            }
        }

        return cellList;
    }
    
    /**
     * Fills the cells with the data from the
     * @param cellList - bidimensional array containing the data
     * @throws FormulaCompilationException 
     */
    public void fillCells(CellDTO[][] cellList) throws FormulaCompilationException {
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
     * This method obtains the containt from the cells in the defined range
     * @param activeSpreadsheet
     * @return the cells' content
     */
    public CellDTO[][] getCellsFromRange(Spreadsheet activeSpreadsheet) {
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
    public void setToFile(CellDTO[][] cellList) throws FileNotFoundException, IOException {
        FileOutputStream stream = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(stream));

        String line;

        int i, j;
        for (i = 0; i < cellList.length; i++) {
            line = "";
            for (j = 0; j < cellList[0].length; j++) {
                if(!(j == cellList[0].length - 1)) {
                    line = line + cellList[i][j].getContent() + separatorCharacter;
                } else {
                    line = line + cellList[i][j].getContent();
                }
            }
            bw.write(line);
            bw.newLine();
        }

        bw.flush();

        bw.close();
    }
}
