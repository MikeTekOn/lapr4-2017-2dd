/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1140822.fileShare;

import java.util.Properties;
import csheets.io.NamedProperties;
import java.io.IOException;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class ShareConfiguration {

//    public static String getSharedFolder()
//    {
//        return "C:\\Users\\Renato\\Desktop\\Shared";
//    }
//      public static String getDownloadFolder()
//    {
//        return "C:\\Users\\Renato\\Desktop\\Download";
//    }
    public static String downloadFolder;
    public static String sharedFolder;
    private static Properties properties;

    public static String getSharedFolder() {
        return sharedFolder;
    }

    public static String getDownloadFolder() {
        return downloadFolder;
    }

    public static void setProperties(Properties props) {
        properties = props;
    }

    public static void changeDownloadFolder(String path) throws IOException {
        downloadFolder = path;
        properties.put("share.download.folder", path);
        ((NamedProperties) properties).store("");
    }

    public static void changeSharedFolder(String path) throws IOException {
        sharedFolder = path;
        properties.put("share.shared.folder", path);
        ((NamedProperties) properties).store("");
    }
}
