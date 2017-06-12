/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1140822.fileShare;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;
import lapr4.green.s1.ipc.n1150532.comm.connection.SocketEncapsulatorDTO;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class HandlerFileDTO extends Observable implements CommHandler {

    private Object lastReceivedDto;

    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        FileOutputStream fileOut = null;
        try {
            FileDTO fileDTO = (FileDTO) ((SocketEncapsulatorDTO) dto).getDTO();
            this.lastReceivedDto = fileDTO;
            File folder = new File(ShareConfiguration.getDownloadFolder());

            folder.mkdirs();
            File file = new File(ShareConfiguration.getDownloadFolder() + "/" + fileDTO.getFileName());

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
