/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.blue.s2.ipc.n1140822.fileShare;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPClientsManager;
import lapr4.green.s1.ipc.n1150532.comm.CommTCPServer;
import lapr4.green.s1.ipc.n1150532.comm.CommUDPServer;
import lapr4.green.s1.ipc.n1150532.comm.connection.ConnectionID;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class ShareAction extends BaseAction {

    /**
     * The controller.
     */
    private UIController uiController;

    /**
     * The connection.
     */
    private int port;

    private FileNameListDTO fileDTO;

    /**
     * The constructor for the ShareAction
     *
     * @param portNr
     * @param uiController The controller.
     */
    public ShareAction(int portNr, UIController uiController) {
        this.uiController = uiController;
        this.port = portNr;

    }

    /**
     * It gets the name
     *
     * @return The String name.
     */
    @Override
    protected String getName() {
        return "Share";
    }

    @Override
    protected void defineProperties() {
        //TODO
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Map<String, Integer> map = new LinkedHashMap<>();
        try {
            map = fillListOfSharedfiles();
        } catch (IOException ex) {
            Logger.getLogger(ShareAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        fileDTO = new FileNameListDTO(map, CommTCPServer.getServer().provideConnectionPort());

        ShareFrame frame = new ShareFrame(uiController);
        ((HandlerFileNameListDTO) CommUDPServer.getServer().getHandler(fileDTO.getClass())).addObserver(frame);
        ((HandlerFileDTO) CommTCPClientsManager.getManager().getHandler(FileDTO.class)).addObserver(frame);
        CommUDPClient worker = new CommUDPClient(fileDTO, port, 55);

        worker.start();

    }

    public static Map<String, Integer> fillListOfSharedfiles() throws IOException {
        Map<String, Integer> tempMap = new LinkedHashMap<>();
        try {
            File folder = new File(ShareConfiguration.getSharedFolder());
            folder.mkdirs();
            File[] files = folder.listFiles();
            for (File file : files) {
                byte[] fileSize = Files.readAllBytes(file.toPath());
                tempMap.put(file.getName(), fileSize.length);
            }
        } catch (Exception ex) {
            return tempMap;
        }
        return tempMap;
    }

}
