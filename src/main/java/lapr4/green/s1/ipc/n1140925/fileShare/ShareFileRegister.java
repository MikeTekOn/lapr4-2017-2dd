/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1140925.fileShare;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author João
 */
public class ShareFileRegister implements Serializable {

    /**
     * files list
     */
    private List<ShareFile> filesList;

    public ShareFileRegister() {
        filesList = new ArrayList<>();

    }

    /**
     * Fill the list with files
     *
     * @return
     * @throws IOException
     */
    public List<ShareFile> fillListWithFiles(List<File> listToFill) throws IOException {
        List<ShareFile> shareFilesInfoList = new ArrayList<>();
        for (File file : listToFill) {

            ShareFile fileToAdd = new ShareFile(file.getName(), Integer.parseInt(String.valueOf(file.length())));

            shareFilesInfoList.add(fileToAdd);

        }
        return shareFilesInfoList;

    }

    /**
     * Get the files existing in a folder
     *
     * @return
     */
    public File[] getFilesExistingInAFolder(String path) {
        File folder = new File(path);
        return (folder.listFiles());
    }

    /**
     * Add a single file
     *
     */
    public void addFileToList(ShareFile file) {
        if (!this.filesList.contains(file)) {
            this.filesList.add(file);
        }
    }

    /**
     * Return the files list
     *
     * @return
     */
    public List<ShareFile> filesList() {
        return this.filesList;
    }

    /**
     * Inserts all the files from a folder into a list and return it.
     *
     * @return list
     */
    public List<File> buildFilesList(String folderPath) {
        File folder = new File(folderPath);
        List<File> list = new ArrayList<>();

        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                list.add(file);
            }
        }
        return list;
    }
    
    
}
