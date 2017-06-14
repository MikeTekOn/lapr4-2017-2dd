package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch;

import csheets.core.Workbook;
import csheets.io.Codec;
import csheets.io.CodecFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.FindPattern.Finder;
import lapr4.green.s1.ipc.n1150838.findworkbooks.FileDTO;
import lapr4.green.s1.ipc.n1150838.findworkbooks.FindWorkbooksPublisher;

/**
 *
 * @author nunopinto
 * @author Diana Silva [1151088@isep.ipp.pt] 
 *         update due to pattern search criteria
 */
public class Directory implements Runnable{

    private File rootPath;
    private static final String PATTERN="*.cls";
    private SearchPattern searchPattern;
    private String regex;

    /**
     * Acceptable extensions
     */
    private enum searchExtensions {
        cls
    };

    public Directory(File rootPath, String regex) {
        if (validatePath(rootPath) == false) {
            throw new IllegalStateException();
        }
        if (validateRegex(regex)==false){
            throw new IllegalStateException();
        }
        this.rootPath = rootPath.getAbsoluteFile();
        this.searchPattern= new SearchPattern(regex);
        this.regex=regex;
    }

    /**
     * valides if the given path is a directory
     *
     * @param rootPath
     * @return
     */
    public boolean validatePath(File rootPath) {
        return rootPath.isDirectory();
    }
    
    public boolean validateRegex(String regex){
        boolean test = true;
        try {
            Pattern.compile(regex);
        } catch (PatternSyntaxException exception) {
            test = false;
        }
        return test;
    }

    /**
     * public method to call a private and recursive one
     */
    public void searchFiles() {
        search(rootPath);
    }

    /**
     * the algorithm to search for files
     *
     * @param file
     */
    private void search(File file) {

        //do you have permission to read this directory?
        if (file.canRead()){
            try{
            for (File temp : file.listFiles()) {
                if (temp.isDirectory()) {
                    search(temp);
                } else {
                  
                    if (searchPattern.acceptExp(temp.getName()) ) {
                        if( searchPattern.checkIfMatches(temp.getName())){
                        FileDTO dto = new FileDTO(temp.getName(), temp.getAbsolutePath());
                        FindWorkbooksPublisher.getInstance().notifyObservers(dto);
                        }
                    }

                }
            }
            }catch(NullPointerException ex){
                //THIS SHOULD NOT HAPPEN
                //File.listFiles() returns null when a path is not acessible
            }

        }
    }
    
//    /**
//     * the algorithm to search for files
//     *
//     * @param file
//     */
//    private void searchPattern(File file) throws IOException {
//
//        //do you have permission to read this directory?
//        if (file.canRead()){
//         
//                Finder finder=new Finder(PATTERN);  
//               // Path startingDir= Paths.get(file.lis)
//                Path found=Files.walkFileTree(file.listFiles()[0].toPath(), finder);
// 
//        }
//    }
//    

    /**
     * Loads a workbook from the given file.
     *
     * @param file the file in which the workbook is stored
     * @throws IOException if the file could not be loaded correctly
     * @throws java.lang.ClassNotFoundException exception
     */
    public Workbook load(File file) throws IOException, ClassNotFoundException {
        Codec codec = new CodecFactory().getCodec(file);
        if (codec != null) {
            FileInputStream stream = null;
            Workbook workbook;
            try {
                // Reads workbook data
                stream = new FileInputStream(file);
                workbook = codec.read(stream);
            } finally {
                try {
                    if (stream != null) {
                        stream.close();
                    }
                } catch (IOException e) {
                }
            }
            return workbook;
        } else {
            throw new IOException("Codec could not be found");
        }

    }
    
        @Override
    public void run() {
        searchFiles();
    }


}
