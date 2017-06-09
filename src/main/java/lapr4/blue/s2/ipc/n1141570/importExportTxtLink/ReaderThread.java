package lapr4.blue.s2.ipc.n1141570.importExportTxtLink;

import lapr4.blue.s2.ipc.n1141570.importExportTxtLink.ctrl.LinkTxtController;
import lapr4.green.s1.ipc.n1150800.importexportTXT.FileData;
import lapr4.green.s1.ipc.n1150800.importexportTXT.importTXT.ctrl.ImportDataController;

/**
 *
 * @author Eric
 */
public class ReaderThread implements Runnable {

    /**
     * The import data controller.
     */
    private LinkTxtController linkTxtController;
    
    /**
     * The reader thread.
     */
    private Thread readerThread;
    
    private FileData fileData;

    /**
     * Creates a new reader thread.
     *
     * @param importDataController the import data controller.
     */
    public ReaderThread(LinkTxtController linkTxtController) {
        this.readerThread = new Thread(this);
        this.linkTxtController = linkTxtController;
        this.readerThread.start();
        
    }

    @Override
    public void run() {

        while (true) {
            try {
                System.out.println("I AM THE SYNCHRONIZATION THREAD!\n");
                 this.fileData = this.linkTxtController.obtainsFileToRead();
                //this.importDataController.readData();
            } catch (Exception e) {
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
 

}
