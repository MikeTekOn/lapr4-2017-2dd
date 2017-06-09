package lapr4.blue.s2.ipc.n1141570.importExportTxtLink;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ui.ctrl.UIController;
import java.awt.Font;
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
 * @author Eric
 */
public class ReaderThread implements Runnable {

    /**
     * The file to read from.
     */
    private final File fileToRead;

    /**
     * The separator character.
     */
    private final char separatorCharacter;

    /**
     * The cell range.
     */
    private final CellRange cellRange;

    /**
     * The first line that represents headers boolean.
     */
    private final boolean firstLineRepresentsHeaders;

    /**
     * The active spreadsheet.
     */
    private final Spreadsheet activeSpreadsheet;

    /**
     * The reader thread.
     */
    private final Thread readerThread;

    /**
     * Creates a new reader thread.
     *
     * @param fileToRead
     * @param seperatorCharacter
     * @param cellRange
     * @param firstLineRepresentsHeaders
     * @param activeSpreadsheet
     * @param uiController
     */
    public ReaderThread(File fileToRead, char seperatorCharacter, CellRange cellRange, boolean firstLineRepresentsHeaders, UIController uiController, Spreadsheet activeSpreadsheet) {
        this.fileToRead = fileToRead;
        this.separatorCharacter = seperatorCharacter;
        this.cellRange = cellRange;
        this.firstLineRepresentsHeaders = firstLineRepresentsHeaders;
        this.activeSpreadsheet = activeSpreadsheet;

        this.readerThread = new Thread(this);
        this.readerThread.start();
    }

    @Override
    public void run() {

        while (true) {
            try {
                System.out.println("I AM THE SYNCHRONIZATION THREAD!\n");
                readData();
            } catch (FormulaCompilationException | IOException e) {
                e.printStackTrace();
            }

            //update every seconds
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * This method reads the data from the file and fills a range of cells with
     * the given data
     *
     * @throws IOException
     * @throws FormulaCompilationException
     */
    private void readData() throws IOException, FormulaCompilationException {
        CellDTO cellList[][] = getFileData();
        fillCells(cellList);
    }

    /**
     * Reads and returns the file data
     *
     * @return a list of cells containing the data read from a file
     * @throws FileNotFoundException
     * @throws IOException
     */
    private CellDTO[][] getFileData() throws FileNotFoundException, IOException {
        FileInputStream stream = new FileInputStream(this.fileToRead);
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));

        String line;
        int columns = 0;
        int rows = 0;
        int i, j;
        int maxColumns = 0;

        // to count the number of rows and columns in the file
        while ((line = br.readLine()) != null) {
            if (!line.isEmpty()) {
                String[] rowData = line.split(String.valueOf(separatorCharacter));
                rows++;
                columns = rowData.length;
                if (columns >= maxColumns) {
                    maxColumns = columns;
                }
            }
        }
        br.close();

        // to store in the matrix of strings the value read from file
        stream = new FileInputStream(this.fileToRead);
        br = new BufferedReader(new InputStreamReader(stream));
        String data[][] = new String[rows][maxColumns];
        i = 0;
        int row = 0;
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

        //to fill the cells DTO matrix with the information from data Strings matrix information
        Cell firstCell = cellRange.getFirstCell();
        int firstRow = firstCell.getAddress().getRow();
        int firstColumn = firstCell.getAddress().getColumn();
        CellDTO cellList[][] = new CellDTO[rows][maxColumns];

        for (i = 0; i < rows; i++) {
            for (j = 0; j < maxColumns; j++) {
                Address address = this.activeSpreadsheet.getCell(firstColumn + j, firstRow + i).getAddress();
                CellDTO cell;
                if (data[i][j] != null) {
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
     * Fills the cells with the data received from the parameter.
     *
     * @param cellList - matrix containing the data
     * @throws FormulaCompilationException
     */
    public void fillCells(CellDTO[][] cellList) throws FormulaCompilationException {
        int i, j;
        int max = 0;
        for (i = 0; i < cellList.length; i++) {
            for (j = 0; j < cellList[i].length; j++) {
                CellDTO cdto = cellList[i][j];
                String content = cdto.getContent();
                Cell cell = this.activeSpreadsheet.getCell(cdto.getAddress());
                cell.setContent(content);

            }
        }

        //to bold the titles
        if (firstLineRepresentsHeaders) {
            for (int l = 0; l < cellList[0].length; l++) {
                if (cellList[0][l].getContent() != null) {
                    CellDTO cdto = cellList[0][l];
                    String content = cdto.getContent();
                    Cell cell = this.activeSpreadsheet.getCell(cdto.getAddress());
                    cell.setContent(content);
                    StylableCell stylableCell = (StylableCell) cell.getExtension(StyleExtension.NAME);
                    stylableCell.setFont(new Font(stylableCell.getFont().getFamily(),
                            stylableCell.getFont().getStyle() & Font.BOLD, stylableCell.getFont().getSize()));

                }

            }
        }

    }
}
