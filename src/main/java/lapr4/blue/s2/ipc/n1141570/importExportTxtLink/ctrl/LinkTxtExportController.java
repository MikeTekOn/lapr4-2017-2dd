package lapr4.blue.s2.ipc.n1141570.importExportTxtLink.ctrl;

import csheets.core.Spreadsheet;
import java.io.File;
import lapr4.blue.s2.ipc.n1141570.importExportTxtLink.WriterThread;

/**
 *
 * @author Eric
 */
public class LinkTxtExportController {

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
     * Creates a new instance of LinkTxtExportController
     *
     * @param activeSpreadsheet the active spreadsheet
     * @param fileToWrite the file to write the data txt
     * @param separatorCharacter the separator character chosen
     */
    public LinkTxtExportController(Spreadsheet activeSpreadsheet, File fileToWrite, char separatorCharacter) {
        this.activeSpreadsheet = activeSpreadsheet;
        this.fileToWrite = fileToWrite;
        this.separatorCharacter = separatorCharacter;
    }

    /**
     * Creates a new link writer thread.
     */
    public void fireLinkWriterThread() {
        WriterThread writerThread = new WriterThread(activeSpreadsheet, fileToWrite, separatorCharacter);
    }
}
