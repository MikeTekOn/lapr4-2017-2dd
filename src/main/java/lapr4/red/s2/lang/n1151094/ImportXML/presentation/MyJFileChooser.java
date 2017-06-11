/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s2.lang.n1151094.ImportXML.presentation;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Eduangelo Ferreira - 1151094
 */
public class MyJFileChooser extends JFileChooser {

    /**
     * This constructor has the purpose of instantiating an object
     */
    public MyJFileChooser() {
        super();
        setFilterExtensionXml();
    }

    /**
     * This method allows you to filter the file, which appears in the window
     * for the user to choose the file.
     */
    private void setFilterExtensionXml() {
        setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                String extension = extension(f);
                if (extension != null) {
                    return extension.equals("xml");
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "*.xml";
            }

            private String extension(File f) {
                String ext = null;
                String s = f.getName();
                int i = s.lastIndexOf(".");
                if (i != -1) {
                    ext = s.substring(i + 1).toLowerCase();
                }
                return ext;
            }
        });
    }
}
