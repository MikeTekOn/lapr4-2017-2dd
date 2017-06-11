package lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import lapr4.green.s1.ipc.n1150838.findworkbooks.FileDTO;
import lapr4.green.s1.ipc.n1150838.findworkbooks.FindWorkbooksPublisher;

/**
 *
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class FindPattern {
    public static class Finder extends SimpleFileVisitor<Path> {
        
        private final PathMatcher matcher;
        private int nMatches;
   
        public Finder(String pattern) {
            matcher=FileSystems.getDefault().getPathMatcher(pattern);
        }
        
        public void find(Path file){
            Path pathName=file.getFileName();
            if(pathName!=null && matcher.matches(pathName)){
                FileDTO dto = new FileDTO(pathName.getFileName().toString(), pathName.toString());
               // FindWorkbooksPublisher.getInstance().notifyObservers(dto);
            }
            
        }
        
        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes att){
            find(path);
            return CONTINUE;
        }
        
        @Override
        public FileVisitResult visitFileFailed(Path path, IOException ex){
            System.err.println((ex));
            return CONTINUE;
        }
    }
}
