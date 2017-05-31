/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150838.findworkbooks;

import java.io.File;

/**
 *
 * @author nunopinto
 */
public class Directory {
    
   private File rootPath;
   
   public Directory(File rootPath){
       if(validatePath(rootPath)==false){
           throw new IllegalStateException();
       }
       rootPath= rootPath.getAbsoluteFile();
   }
   
   public boolean validatePath(File rootPath){
       return rootPath.isDirectory();
   }
    
}
