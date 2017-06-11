package lapr4.blue.s2.ipc.n1141570.importExportTxtLink.ctrl;

import csheets.core.Spreadsheet;
import csheets.ui.ctrl.UIController;
import java.io.File;
import lapr4.blue.s2.ipc.n1141570.importExportTxtLink.ReaderThread;
import lapr4.green.s1.ipc.n1150800.importexportTXT.CellRange;
import lapr4.green.s1.ipc.n1150800.importexportTXT.FileData;

/**
 *
 * @author Eric
 */
public class LinkTxtImportController {

    /**
     * The file to read from.
     */
    private File fileToRead;

    /**
     * The file data.
     */
    private FileData fileData;

    /**
     * The separator character.
     */
    private char separatorCharacter;

    /**
     * The cell range.
     */
    private CellRange cellRange;

    /**
     * The first line that represents headers boolean.
     */
    private boolean firstLineRepresentsHeaders;

    /**
     * The user interface controller.
     */
    private UIController uiController;

    /**
     * The active spreadsheet.
     */
    private Spreadsheet activeSpreadsheet;

    /**
     * The reader thread.
     */
    private ReaderThread readerThread;

    /**
     * Creates a new Link Txt Controller.
     *
     * @param fileToRead the file to read
     * @param separatorCharacter the separator character
     * @param cellRange the cell range
     * @param firstLineRepresentsHeaders the boolean that represents the choose if first line represents headers or not
     * @param uiController the UI controller
     * @param activeSpreadsheet the active spreadsheet
     */
    public LinkTxtImportController(File fileToRead, char separatorCharacter, CellRange cellRange, boolean firstLineRepresentsHeaders, UIController uiController, Spreadsheet activeSpreadsheet) {
        this.fileToRead = fileToRead;
        this.separatorCharacter = separatorCharacter;
        this.cellRange = cellRange;
        this.firstLineRepresentsHeaders = firstLineRepresentsHeaders;
        this.uiController = uiController;
        this.activeSpreadsheet = activeSpreadsheet;
    }

    /**
     * Creates a new link reader thread.
     */
    public void fireLinkReaderThread() {
        this.readerThread = new ReaderThread(this.fileToRead, this.separatorCharacter, this.cellRange, this.firstLineRepresentsHeaders, this.uiController, this.activeSpreadsheet);
    }

}
