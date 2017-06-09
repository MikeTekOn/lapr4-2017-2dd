package lapr4.blue.s2.ipc.n1141570.importExportTxtLink.ctrl;

import csheets.ui.ctrl.UIController;
import java.io.File;
import lapr4.blue.s2.ipc.n1141570.importExportTxtLink.ReaderThread;
import lapr4.green.s1.ipc.n1150800.importexportTXT.FileData;
import lapr4.green.s1.ipc.n1150800.importexportTXT.importTXT.ctrl.ImportDataController;

/**
 *
 * @author Eric
 */
public class LinkTxtController {

    /**
     * The user interface controller.
     */
    private UIController uiController;

    /**
     * The import data controller.
     */
    private ImportDataController importDataController;

    /**
     * The reader thread.
     */
    private ReaderThread readerThread;

    /**
     * The file that contains the data to be imported
     */
    private FileData fileToRead;

    /**
     * Creates a new Link Txt Controller.
     *
     * @param importDataController the import data controller
     */
    public LinkTxtController(ImportDataController importDataController) {
        this.importDataController = importDataController;
    }

    public FileData obtainsFileToRead(){
        return this.fileToRead;
    }


    public void fireLinkReaderThread() {
        this.readerThread = new ReaderThread(this);
    }

    public boolean fireLinkWriterThread() {
        //TODO
        return true;
    }

}
