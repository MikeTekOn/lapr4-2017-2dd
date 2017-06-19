/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.red.s3.ipc.n1150613.NetworkSearchByFile;

import csheets.core.Workbook;
import csheets.io.Codec;
import csheets.io.CodecFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diogo Guedes
 */
public class Directory implements Runnable {

    private File rootPath;

    private List<FileDTO> dtoList;

    /**
     * Acceptable extensions
     */
    private enum searchExtensions {
        cls
    };

    public Directory(File rootPath) {
        if (validatePath(rootPath) == false) {
            throw new IllegalStateException();
        }
        this.rootPath = rootPath.getAbsoluteFile();
        dtoList = new ArrayList<>();
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

    /**
     * public method to call a private and recursive one
     */
    public void searchFiles() throws IOException, ClassNotFoundException {
        search(rootPath);
    }

    /**
     * the algorithm to search for files
     *
     * @param file
     */
    private void search(File file) throws IOException, ClassNotFoundException {

        //do you have permission to read this directory?
        if (file.canRead()) {
            try {

                for (File temp : file.listFiles()) {
                    if (temp.isDirectory()) {
                        search(temp);
                    } else {
                        if (isExtensionFile(temp.getName())) {

                            FileDTO dto = new FileDTO(temp.getName(), temp.getAbsolutePath());
                            dtoList.add(dto);
                        }

                    }
                }

            } catch (NullPointerException ex) {
                //THIS SHOULD NOT HAPPEN
                //File.listFiles() returns null when a path is not acessible
            }

        }

    }

    /**
     * checks if the file has the acceptables extensions
     *
     * @param fileName
     * @return
     */
    public boolean isExtensionFile(String fileName) {
        String tokens[] = fileName.split("\\.");
        if (tokens.length == 0 || tokens.length == 1) {
            return false;
        }
        String extension = tokens[tokens.length - 1];
        searchExtensions values[] = searchExtensions.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].toString().equals(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Loads a workbook from the given file.
     *
     * @param file the file in which the workbook is stored
     * @throws IOException if the file could not be loaded correctly
     * @throws java.lang.ClassNotFoundException exception
     */
    public Workbook load(File file) throws IOException, ClassNotFoundException {
        System.out.println("0");
        Codec codec = new CodecFactory().getCodec(file);
        System.out.println("5");
        if (codec != null) {
            FileInputStream stream = null;
            Workbook workbook;
            try {
                // Reads workbook data 
                System.out.println("1");
                stream = new FileInputStream(file);
                System.out.println("2");
                workbook = codec.read(stream);
                System.out.println("3");
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
        try {
            searchFiles();
        } catch (IOException ex) {
            Logger.getLogger(Directory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Directory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<FileDTO> getDTO() {
        return dtoList;
    }

}
