/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1140925.fileShare;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author João
 */
public class ShareFileRegisterTest {
    
    public ShareFileRegisterTest() {
    }

    /**
     * Test of fillListWithFiles method, of class ShareFileRegister.
     */
    @Test
    public void testFillListWithFiles() throws Exception {
        System.out.println("fillListWithFiles");
        
        ShareFileRegister sfReg = new ShareFileRegister();
        
        List<File> listToFill = new ArrayList<>();
        for (File f : sfReg.getFilesExistingInAFolder("./ShareFiles")) {
            listToFill.add(f);
        }
        List<ShareFile> result = sfReg.fillListWithFiles(listToFill);
        
        assertEquals(2, result.size());

    }

    /**
     * Test of getFilesExistingInAFolder method, of class ShareFileRegister.
     */
    @Test
    public void testGetFilesExistingInAFolder() {
        System.out.println("getFilesExistingInAFolder");
        String path = "./FilesShare";
        ShareFileRegister instance = new ShareFileRegister();
        File file = new File("./ShareFiles");
        File[] expResult = file.listFiles();
        File[] result = instance.getFilesExistingInAFolder("./ShareFiles");
        assertArrayEquals(expResult, result);

    }

    /**
     * Test of addFileToList method, of class ShareFileRegister.
     */
    @Test
    public void testAddFileToList() {
        System.out.println("addFileToList");
        ShareFile file = new ShareFile("a", 200);
        ShareFileRegister sfReg = new ShareFileRegister();
        sfReg.addFileToList(file);
        
        assertEquals(1, sfReg.filesList().size());
    }


    /**
     * Test of filesList method, of class ShareFileRegister.
     */
    @Test
    public void testFilesList() {
        System.out.println("filesList");
        ShareFileRegister sfReg = new ShareFileRegister();
        
        sfReg.filesList().add(new ShareFile("a", 20));
        
        List<ShareFile> list = new ArrayList<>();
        list.add(new ShareFile("a", 20));
        List<ShareFile> expResult = list;
        
        List<ShareFile> result = sfReg.filesList();
        
        assertEquals(expResult.size(), result.size());
       
    }

    /**
     * Test of buildFilesList method, of class ShareFileRegister.
     */
    @Test
    public void testBuildFilesList() {
        System.out.println("buildFilesList");
        ShareFileRegister sfReg = new ShareFileRegister();
        
        List<File> result = sfReg.buildFilesList("./ShareFiles");
        
        assertEquals(2, result.size());
        
    }
    
}
