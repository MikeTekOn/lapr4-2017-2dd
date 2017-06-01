/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150838.findworkbooks.ctrl;

import eapli.framework.application.Controller;
import java.io.File;
import java.io.IOException;
import lapr4.green.s1.ipc.n1150838.findworkbooks.Directory;

/**
 *
 * @author nunopinto
 */
public class ControllerFindWorkbooks implements Controller {
    
    private Directory rootPath;
    
    public ControllerFindWorkbooks(String rootPath){
        this.rootPath = new Directory(new File(rootPath));
    }
    
    public void searchFiles() throws ClassNotFoundException,IOException{
        this.rootPath.searchFiles();
    }
    
}
