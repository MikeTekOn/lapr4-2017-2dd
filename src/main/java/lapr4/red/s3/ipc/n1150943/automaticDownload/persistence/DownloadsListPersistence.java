package lapr4.red.s3.ipc.n1150943.automaticDownload.persistence;

import lapr4.red.s3.ipc.n1150943.automaticDownload.DownloadInfo;

import java.io.*;
import java.util.Map;

/**
 * Created by João Cardoso on 14-06-2017.
 */
public class DownloadsListPersistence {

    public static void saveList(Map<String,DownloadInfo> downloads){
        try{
            FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir"));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(downloads);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String,DownloadInfo> getDownloads(){
        Map<String,DownloadInfo> downloads = null;
        try{
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir"));
            ObjectInputStream in = new ObjectInputStream(fileIn);
            downloads = (Map<String,DownloadInfo>) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return downloads;
    }

    public static DownloadInfo getLatestVersionInfo(String originalFileName) {
           return getDownloads().get(getLatestVersion(originalFileName));
    }

    /**
     * Gets the original name of the file if it has versions
     * @param fileName
     * @return
     */
    public static String getOriginalFileName (String fileName) {
        return getDownloads().get(fileName).originalFileName();
    }

    public static String getLatestVersion(String fileName) {
        Map<String,DownloadInfo> downloads = getDownloads();
        Map<String,DownloadInfo> fileVersions = getDownloads();
        DownloadInfo originalInfo = downloads.get(fileName);
        if(originalInfo.downloadType() == DownloadInfo.DownloadType.ONE_TIME_DOWNLOAD){
            return fileName;
        }else{
            if(originalInfo.updateType()==DownloadInfo.UpdateType.RENAME){
                return fileName;
            }
        }
        for (Map.Entry<String,DownloadInfo> e : downloads.entrySet()){
            if(e.getValue().originalFileName().equals(fileName)){
                fileVersions.put(e.getKey(),e.getValue());
            }
        }
        int latestVersion = 1;
        String latestVersionName = fileName;
        for (Map.Entry<String,DownloadInfo> e : fileVersions.entrySet()){
            int version = e.getValue().version();
            if(version>=latestVersion){
                latestVersionName = e.getKey();
            }
        }

        return latestVersionName;
    }
}
