package lapr4.blue.s2.ipc.n1140822.fileShare;

import java.util.Properties;
import csheets.io.NamedProperties;
import java.io.IOException;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class ShareConfiguration {

    /**
     * The download folder.
     */
    public static String downloadFolder;

    /**
     * The shared folder.
     */
    public static String sharedFolder;

    /**
     * The properties.
     */
    private static Properties properties;

    /**
     * Obtains the shared folder.
     *
     * @return the shared folder
     */
    public static String getSharedFolder() {
        return sharedFolder;
    }

    /**
     * Obtains the download folder.
     *
     * @return the download folder
     */
    public static String getDownloadFolder() {
        return downloadFolder;
    }

    /**
     * Modifies the properties.
     *
     * @param props the new properties
     */
    public static void setProperties(Properties props) {
        properties = props;
    }

    /**
     * Changes the path to a new download folder.
     *
     * @param path the new path
     * @throws IOException throws an IO exception if input or output gone wrong
     */
    public static void changeDownloadFolder(String path) throws IOException {
        downloadFolder = path;
        properties.put("share.download.folder", path);
        ((NamedProperties) properties).store("");
    }

    /**
     * Changes the path to a new shared folder.
     *
     * @param path the new path
     * @throws IOException throws an IO exception if input or output gone wrong
     */
    public static void changeSharedFolder(String path) throws IOException {
        sharedFolder = path;
        properties.put("share.shared.folder", path);
        ((NamedProperties) properties).store("");
    }
}
