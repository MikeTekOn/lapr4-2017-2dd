/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150838.findworkbooks;

import csheets.core.Workbook;
import csheets.io.Codec;
import csheets.io.CodecFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author nunopinto
 */
public class Directory {

    private File rootPath;
    private enum searchExtensions{
        cls,csv
    };

    public Directory(File rootPath) {
        if (validatePath(rootPath) == false) {
            throw new IllegalStateException();
        }
        this.rootPath = rootPath.getAbsoluteFile();
    }

    public boolean validatePath(File rootPath) {
        return rootPath.isDirectory();
    }
    
    public void searchFiles() throws IOException, ClassNotFoundException{
        search(rootPath);
    }

    private void search(File file) throws IOException, ClassNotFoundException {
        
        //do you have permission to read this directory?
        if (file.canRead()) {
            for (File temp : file.listFiles()) {
                if (temp.isDirectory()) {
                    search(temp);
                } else {
                    if (isExtensionFile(temp.getName())) {
                        WorkbookDTO dto = new WorkbookDTO(load(temp),temp.getName());
                        FindWorkbooksPublisher.getInstance().notifyObservers(dto);
                    }

                }
            }

        }
    }
    
    public boolean isExtensionFile(String fileName){
        String tokens[] = fileName.split("\\.");
        if(tokens.length==0 || tokens.length==1) return false;
        String extension= tokens[tokens.length-1];
        searchExtensions values[]=searchExtensions.values();
        for (int i = 0; i < values.length; i++) {
            if(values[i].toString().equals(extension)){
                return true;
            }
        }
        return false;
    }
    
    	/**
	 * Loads a workbook from the given file.
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
					if (stream != null)
						stream.close();
				} catch (IOException e) {}
			}
                 return workbook;
		} else
			throw new IOException("Codec could not be found");
                
                
	}



}
