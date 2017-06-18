package lapr4.red.s3.ipc.n1150943.automaticDownload;

import sun.security.util.ObjectIdentifier;

import java.io.Serializable;

/**
 * Created by João Cardoso - 1150943 on 14-06-2017.
 */
public class DownloadInfo implements Serializable{

    private String originalFileName;
    private DownloadType dt;
    private UpdateType ut;

    /**
     * Local file version
     */
    private int version;

    public String originalFileName() {
        return originalFileName;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public enum DownloadType {
        PERMANENT, ONE_TIME_DOWNLOAD
    }

    public enum UpdateType {
        RENAME, REPLACE;
    }

    public DownloadInfo(String originalFileName, DownloadType dt, UpdateType ut){
        this.originalFileName = originalFileName;
        this.dt = dt;
        this.ut = ut;
        this.version = 1;
    }

    public DownloadType downloadType(){return dt;}
    public UpdateType updateType(){return ut;}

    public int version(){return version;}

}
