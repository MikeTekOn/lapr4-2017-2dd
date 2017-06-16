package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ctrl;

import csheets.core.Workbook;
import eapli.framework.application.Controller;
import java.io.File;
import java.io.IOException;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.Directory;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.PreviewWorkbookBuilder;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.SearchPattern;

/**
 * @author Diana Silva [1151088@isep.ipp.pt]
 * @author nunopinto
 */
public class ControllerFindWorkbooks implements Controller {

    /**
     * The path to search for cls,etc..files
     */
    private Directory rootPath;
    private SearchPattern regex;
    private PreviewWorkbookBuilder builder;

    /**
     * The builder of workbook
     */
    private PreviewWorkbookBuilder previewBuilder;
    private Thread files;

    public ControllerFindWorkbooks(String rootPath, String regex) {
        this.rootPath = new Directory(new File(rootPath),regex);
    }

    /**
     * The call to the method to search the files
     */
    public void searchFiles() {
        files = new Thread(rootPath);
        files.start();
    }

    public void stopSearch() {
        if (files != null) {
            files.stop();
        }
    }

    /**
     * The method to load a workbook
     *
     * @param filePath to the workbook to load
     * @return the workbook
     * @throws IOException if the file could not be loaded correctly
     * @throws java.lang.ClassNotFoundException exception
     */
    public Workbook load(String filePath) throws IOException, ClassNotFoundException {
        return this.rootPath.load(new File(filePath));
    }

    /**
     * The method to load a workbook
     *
     * @param filePath to the workbook to load
     * @return the workbook
     * @throws IOException preview interface is not possible
     * @throws ClassNotFoundException load is not possible
     */
    public Workbook loadPrev(String filePath) throws IOException, ClassNotFoundException {
        
        this.builder=new PreviewWorkbookBuilder(filePath);
        return builder.loadPrev();
    }
}
