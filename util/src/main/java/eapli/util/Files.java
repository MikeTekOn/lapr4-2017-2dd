/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

/**
 * utility class for file manipulation.
 *
 * @author Paulo Gandra Sousa
 */
public class Files {

    private Files() {
        // to make sure this is an utility class
    }

    public static String currentDirectory() {
        return new java.io.File(".").getAbsolutePath();
    }

    public static String ensureExtension(final String filename, final String extension) {
        if (!filename.endsWith(extension)) {
            return filename + extension;
        } else {
            return filename;
        }
    }

    
    public static boolean updatePropertyValue(String propertyName, String newValue, URL resource) throws IOException{
        File file = new File(resource.toString());
        BufferedReader buffer = null;
        
        try {
            buffer = new BufferedReader(new FileReader(file));
        } catch (IOException ex) {
            throw new IOException("The file is unreachable!");
        }

        ArrayList<String> lines = new ArrayList<>();
        String readLine = "";
        while ((readLine = buffer.readLine()) != null) {
            lines.add(readLine);
        }

        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        for (String line : lines) {
            if (line.contains(propertyName)) {
                String[] splittedLine = line.split("=");
                String newLine = splittedLine[0].trim() + "=" + newValue;
                line = newLine;
            }
            writer.println(line);
        }
        writer.close();
        buffer.close();
        return true;
    }
}
