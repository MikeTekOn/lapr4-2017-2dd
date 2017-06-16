package lapr4.blue.s2.ipc.n1140822.fileShare;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;
import lapr4.red.s3.ipc.n1150943.automaticDownload.DownloadInfo;
import lapr4.red.s3.ipc.n1150943.automaticDownload.persistence.DownloadsListPersistence;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class HandlerFileDTO extends Observable implements CommHandler {

    /**
     * The last received DTO.
     */
    private Object lastReceivedDto;

    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        FileOutputStream fileOut = null;
        try {
            FileDTO fileDTO = (FileDTO) ((SocketEncapsulatorDTO) dto).getDTO();
            this.lastReceivedDto = fileDTO;
            File folder = new File(ShareConfiguration.getDownloadFolder());

            folder.mkdirs();
            //Added by João Cardoso - 1150943
                //verify file type of download and give file name depending on that
            String filename = fileDTO.getFileName();
            DownloadInfo di = null;
            boolean found = false;

            Map<String,DownloadInfo> downloads = DownloadsListPersistence.getDownloads();
            if(downloads==null){
                downloads = new HashMap<>();
                DownloadsListPersistence.saveList(downloads);
            }else{
                for (Map.Entry<String,DownloadInfo> e : downloads.entrySet()){
                    if (e.getValue().originalFileName().equals(filename)){
                        found = true;
                        di = e.getValue();
                    }
                }
            }

            //Added by João Cardoso - 1150943
            // if the file already exists (is an update) verify the update method, if it is rename it is needed
            // to change the filename, if it is replace the name stays the same
            if(found){
                if(di.updateType()==DownloadInfo.UpdateType.RENAME){
                    Map<String,DownloadInfo> downloadInfoMap = DownloadsListPersistence.getDownloads();
                    String[]aux = filename.split("."); // separate extension from file name
                    String[]aux2 = aux[1].split("-"); // if already has a version separates de filename from the version
                    String newName = aux2[0]+"-"+"V"+downloadInfoMap.get(filename).version() + "." + aux[1];
                    filename = newName;
                }
            }

            File file = new File(ShareConfiguration.getDownloadFolder() + "/" + filename);
            fileOut = new FileOutputStream(file);
            fileOut.write(fileDTO.getFileData());
            fileOut.flush();
            UserDefinedFileAttributeView view = Files.getFileAttributeView(file.toPath(), UserDefinedFileAttributeView.class);
            view.write("host", Charset.defaultCharset().encode(((SocketEncapsulatorDTO) dto).getSocket().getInetAddress().toString()));
            setChanged();
            notifyObservers();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HandlerFileDTO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HandlerFileDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileOut.close();
            } catch (Exception ex) {
                Logger.getLogger(HandlerFileDTO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Object getLastReceivedDTO() {
        return lastReceivedDto;
    }

}
