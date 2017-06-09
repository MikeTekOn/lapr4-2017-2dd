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

    /**
     * Finds a property in the properties file and returns his value.
     *
     * @param in 
     * @param propertyName the name of the property
     * @return the value of the property to search
     */
    public static String getPropertyValue(InputStream in, String propertyName) {
        Properties properties = new Properties();
        InputStream input = in;
        String value = "";
        try {
            properties.load(input);
            input.close();

            value = properties.getProperty(propertyName);
        } catch (IOException ex) {
        }
        return value;
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
