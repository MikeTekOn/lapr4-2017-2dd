/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

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
     * Updates the value of a property.
     *
     * @param propertyName the property to update
     * @param newValue the new value
     * @param in the InputStreamReader
     * @param path the path to the properties file
     * @return True if the property was changed
     * @throws IOException
     */
    public static boolean updatePropertyValue(String propertyName, String newValue, InputStreamReader in, String path) throws IOException {
        BufferedReader buffer = new BufferedReader(in);

        ArrayList<String> lines = new ArrayList<>();
        String readLine = "";
        while ((readLine = buffer.readLine()) != null) {
            lines.add(readLine);
        }

        PrintWriter writer = new PrintWriter(new File(path).getAbsolutePath());
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
