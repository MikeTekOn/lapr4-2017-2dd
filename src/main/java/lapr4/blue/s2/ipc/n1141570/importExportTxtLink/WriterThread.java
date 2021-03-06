package lapr4.blue.s2.ipc.n1141570.importExportTxtLink;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.black.s1.ipc.n2345678.comm.sharecells.CellDTO;

/**
 *
 * @author Eric
 */
public class WriterThread implements Runnable {

    /**
     * The active spreadsheet.
     */
    private final Spreadsheet activeSpreadsheet;

    /**
     * The file to write the txt data.
     */
    private final File fileToWrite;

    /**
     * The separator character.
     */
    private final char separatorCharacter;

    /**
     * The writer thread.
     */
    private final Thread writerThread;

    /**
     * The cells DTO's matrix.
     */
    private CellDTO[][] cellsDTO;

    /**
     * The current thread id.
     */
    private static long threadId = -2;

    /**
     * The is running boolean.
     */
    private static volatile boolean isRunning;

    /**
     * Creates a new instance of writer thread.
     *
     * @param activeSpreadsheet the active spreadsheet
     * @param fileToWrite the file to write the txt data
     * @param separatorCharacter the separator character chosen
     */
    public WriterThread(Spreadsheet activeSpreadsheet, File fileToWrite, char separatorCharacter) {
        this.activeSpreadsheet = activeSpreadsheet;
        this.fileToWrite = fileToWrite;
        this.separatorCharacter = separatorCharacter;

        isRunning = true;
        this.writerThread = new Thread(this);
        this.writerThread.start();
    }

    @Override
    public void run() {

        while (isRunning) {

            threadId = Thread.currentThread().getId();
            //System.out.println("I AM THE SYNCHRONIZATION THREAD!\n");
            this.cellsDTO = getCellsFromRange(this.activeSpreadsheet);
            try {
                setToFile(cellsDTO);
            } catch (IOException ex) {
                Logger.getLogger(WriterThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            //update every 2 seconds
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadId = -2;
    }

    /**
     * Kills the current thread.
     */
    public static void kill() {
        isRunning = false;
    }

    /**
     * Obtains the current thread id.
     *
     * @return the current thread id
     */
    public static long obtainsThreadId() {
        return WriterThread.threadId;
    }

    /**
     * This method obtains the contains from the cells in the defined range
     *
     * @param activeSpreadsheet
     *
     * @return the cells content
     */
    public CellDTO[][] getCellsFromRange(Spreadsheet activeSpreadsheet) {
        int rows = activeSpreadsheet.getRowCount() + 1;
        int columns = activeSpreadsheet.getColumnCount() + 1;

        CellDTO cellList[][] = new CellDTO[rows][columns];

        int i, j;
        for (i = 0; i < rows; i++) {
            for (j = 0; j < columns; j++) {
                Cell cell = activeSpreadsheet.getCell(j, i);
                CellDTO cdto = CellDTO.createFromCell(cell);
                cellList[i][j] = cdto;
            }
        }

        return cellList;
    }

    /**
     * Writes the data from the cells into a file
     *
     * @param cellList - the list of cells that contains the data to be written
     * into a file
     * @throws FileNotFoundException throws this exception if does not find the
     * file
     * @throws IOException throws this exception if error inputing or outputing
     */
    public void setToFile(CellDTO[][] cellList) throws FileNotFoundException, IOException {
        FileOutputStream stream = new FileOutputStream(this.fileToWrite);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(stream));

        String line;

        int i, j;
        //rows
        for (i = 0; i < cellList.length; i++) {
            line = "";
            //columns
            for (j = 0; j < cellList[i].length; j++) {
                if (!(j == cellList[i].length - 1) && !cellList[i][j].getContent().equalsIgnoreCase("")) {
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
