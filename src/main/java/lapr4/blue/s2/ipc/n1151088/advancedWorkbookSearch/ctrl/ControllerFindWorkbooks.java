package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ctrl;

import csheets.core.IllegalValueTypeException;
import csheets.core.Workbook;
import eapli.framework.application.Controller;
import java.io.File;
import java.io.IOException;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.Directory;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.PreviewWorkbookBuilder;

/**
 * @author Diana Silva [1151088@isep.ipp.pt]
 * @author nunopinto
 */
public class ControllerFindWorkbooks implements Controller {
    /** The path to search for cls,etc..files*/
    private Directory rootPath;
    
    /**The builder of workbook*/
    private PreviewWorkbookBuilder previewBuilder;
    private Thread files, preview;
    
    public ControllerFindWorkbooks(String rootPath){
        this.rootPath = new Directory(new File(rootPath));
    }
    /**
     * The call to the method to search the files
     */
    public void searchFiles() {
       files = new Thread(rootPath);
       files.start();
    }
    
    public void stopSearch(){
        if(files!=null)files.stop();
    }
    /**
     * The method to load a workbook
     * @param filePath to the workbook to load
     * @return the workbook
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public Workbook load(String filePath) throws IOException, ClassNotFoundException{
        return this.rootPath.load(new File(filePath));
    }
    
           /**
     * Build the preview workbook with first non-empty cells
     * @param workbook workbook to preview
     * @return the preview builded
     * @throws IllegalValueTypeException if there arent cells filled
     */
    public void buildWorkbookPreview(Workbook workbook) throws IllegalValueTypeException{
        preview= new Thread("preview");
        preview.start();
        previewBuilder=new PreviewWorkbookBuilder(workbook);
        previewBuilder.previewWorkbook();
    }
    
    /**
     * Stop the preview thread
     */
    public void stopPreview(){
        if(preview!=null)preview.stop();
    }
    
}
