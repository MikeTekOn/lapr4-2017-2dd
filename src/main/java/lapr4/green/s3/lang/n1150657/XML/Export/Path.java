/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Export;

import csheets.ui.FileChooser;
import java.io.File;
import java.util.Properties;
import javax.swing.JFileChooser;

/**
 *
 * @author Sofia
 */
public class Path {

    private Properties properties = new Properties();
    private FileChooser fileChooser;
    public static final String DEFAULT_PATH = "D:\\xml.xml";

    public Path() {
        this.fileChooser = new FileChooser(null, properties);
    }

    public String path() {
        //Cancel option = 1 , Approve option = 0
        int returnVal = 0;
        String selectedPath = DEFAULT_PATH;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                selectedPath = fileChooser.getFileToSave().getAbsolutePath();
            } catch (Exception ex) {
                throw new IllegalArgumentException("Error selecting path");
            }
        }
        return selectedPath;
    }
    

    /**
     * This feature allows you to validate the file extension
     *
     * @param file file
     * @return true or false
     */
    public static boolean validateExtension(File file) {
        return file.getName().endsWith(".xml");
    }

}
