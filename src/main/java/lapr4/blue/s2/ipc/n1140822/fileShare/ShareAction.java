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

    private ConnectionID connection;

    private FileNameListDTO fileDTO;

    /**
     * The constructor for the ShareAction
     *
     * @param connection
     * @param uiController The controller.
     */
    public ShareAction(int portNr, UIController uiController, ConnectionID connection) {
        this.uiController = uiController;
        this.port = portNr;
        this.connection = connection;
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
      

        fileDTO = new FileNameListDTO(map, connection);

        ((HandlerFileNameListDTO) CommUDPServer.getServer().getHandler(fileDTO.getClass())).addObserver(new ShareFrame(uiController, connection));
        CommUDPClient worker = new CommUDPClient(fileDTO, port, 55);
        worker.start();

    }

    private  Map<String, Integer> fillListOfSharedfiles() throws IOException {
         Map<String, Integer> tempMap = new LinkedHashMap<>();
        File folder = new File(ShareConfiguration.getSharedFolder());
        File[] files = folder.listFiles();
        for (File file : files) {
               tempMap.put(file.getName(), Files.readAllBytes(file.toPath()).length);
        }
        return tempMap;
    }
}
