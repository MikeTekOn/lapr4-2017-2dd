package lapr4.blue.s2.ipc.n1141570.importExportTxtLink.ui;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import java.util.Set;
import javax.swing.JOptionPane;
import lapr4.blue.s2.ipc.n1141570.importExportTxtLink.ReaderThread;

/**
 *
 * @author Eric
 */
public class UnlinkImportDataAction extends BaseAction {

    /**
     * The user interface controller
     */
    protected UIController uiController;

    /**
     * Creates a new style action.
     *
     * @param uiController the user interface controller
     */
    public UnlinkImportDataAction(UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    protected String getName() {
        return "Unlink import";
    }

    @Override
    protected void defineProperties() {
    }

    /**
     * A simple action to unlink the linked imported file.
     *
     * @param event the event that was fired
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        //IMPORT
        if (ReaderThread.obtainsThreadId() != -1) {

            Set<Thread> setOfThread = Thread.getAllStackTraces().keySet();
            boolean flag = true;
            //Iterate over set to find thread i want to stop
            for (Thread thread : setOfThread) {
                int id = (int) thread.getId();
                if (id == ReaderThread.obtainsThreadId()) {
                    ReaderThread.kill();
                    JOptionPane.showMessageDialog(null, "Now import is Unlinked!.");
                    flag = false;
                }
            }
            if (flag) {
                JOptionPane.showMessageDialog(null, "The import unlink didnt work!", "Unlinking Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ReaderThread.obtainsThreadId() == -1) {
            JOptionPane.showMessageDialog(null, "First you need to import data txt!", "Unlinking Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
