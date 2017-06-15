package lapr4.blue.s2.ipc.n1140822.fileShare;

import csheets.CleanSheets;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class HandlerFileNameDTO implements CommHandler, Serializable {

    /**
     * The last received DTO.
     */
    private Object lastReceivedDTO;

    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        FileNameDTO nameDTO = (FileNameDTO) ((SocketEncapsulatorDTO) dto).getDTO();
        lastReceivedDTO = nameDTO;
        File fileToSend = findFile(nameDTO.fileName);

        try {
            byte[] fileData;
            fileData = Files.readAllBytes(fileToSend.toPath());
            FileDTO fileDTO = new FileDTO(nameDTO.fileName, fileData.length, fileData);
            outStream.write(fileDTO);
        } catch (IOException ex) {
            Logger.getLogger(HandlerFileNameDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Logger.getLogger(HandlerFileNameListDTO.class.getName()).log(Level.INFO, nameDTO.fileName + "-----------" + CleanSheets.getString("received_object"), dto.getClass().toString());
    }

    @Override
    public Object getLastReceivedDTO() {
        return lastReceivedDTO;
    }

    /**
     * Obtains the file that was searched passing its parameter its name.
     *
     * @param fileName the filename passed as parameter
     * @return the file
     */
    private File findFile(String fileName) {
        File folder = new File(ShareConfiguration.getSharedFolder());
        File[] files = folder.listFiles();
        for (File file : files) {
            boolean isEqual = file.getName().equals(fileName);
            if (isEqual) {
                return file;
            }
        }
        return null;
    }

}
