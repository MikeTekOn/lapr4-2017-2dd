package lapr4.blue.s2.ipc.n1141570.importExportTxtLink.ui;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import java.util.Set;
import javax.swing.JOptionPane;
import lapr4.blue.s2.ipc.n1141570.importExportTxtLink.WriterThread;

/**
 *
 * @author Eric
 */
public class UnlinkExportDataAction extends BaseAction {

    /**
     * The user interface controller.
     */
    protected UIController uiController;

    /**
     * The flag boolean.
     */
    private boolean flag;

    /**
     * Creates a new style action.
     *
     * @param uiController the user interface controller
     */
    public UnlinkExportDataAction(UIController uiController) {
        this.uiController = uiController;
        this.flag = true;
    }

    @Override
    protected String getName() {
        return "Unlink export";
    }

    @Override
    protected void defineProperties() {
    }

    /**
     * A simple action to unlink the linked exported file.
     *
     * @param event the event that was fired
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        //EXPORT
        if (WriterThread.obtainsThreadId() != -2) {

            Set<Thread> setOfThread = Thread.getAllStackTraces().keySet();

            //Iterate over set to find thread i want to stop
            for (Thread thread : setOfThread) {
                int id = (int) thread.getId();
                if (id == WriterThread.obtainsThreadId()) {
                    WriterThread.kill();
                    System.out.println("Unlink stopped!\n");
                    JOptionPane.showMessageDialog(null, "Now export is Unlinked!.");
                }
                System.out.printf("Id da thread a comparar: %d\n", id);
            }

        } else if (WriterThread.obtainsThreadId() == -2) {
            JOptionPane.showMessageDialog(null, "First you need to export data txt!", "Unlinking Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
