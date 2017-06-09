
package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch;

import csheets.core.Workbook;
import eapli.framework.application.Controller;
import java.io.File;
import java.io.IOException;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.PreviewWorkbookBuilder;
import lapr4.green.s1.ipc.n1150838.findworkbooks.Directory;

/**
 * Update class due to second sprint new requirements
 * @author Diana Silva [1151088@isep.ipp.pt]
 * @author nunopinto
 */
public class ControllerFindWorkbooks implements Controller {
    /**
     * The path to search for cls,etc.. files 
     */
    private Directory rootPath;
    private Thread files;
    private PreviewWorkbookBuilder previewBuilder;
    
    public ControllerFindWorkbooks(String rootPath, Workbook workbook){
        this.rootPath = new Directory(new File(rootPath));
        this.previewBuilder=new PreviewWorkbookBuilder(workbook);
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
    
    public void buildWorkbookPreview(Workbook workbook){
        previewBuilder.buildPreviewArea();
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
    
}
