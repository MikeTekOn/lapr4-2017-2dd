/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150657.chat;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import lapr4.green.s1.ipc.n1150657.chat.ui.ChatPanel;

/**
 * The concept of a server message. Each conversation is one ServeMessage.
 *
 * @author Sofia
 */
public class ServerMessage implements Runnable {

    /**
     * The message.
     */
    String message;

    /**
     * The address.
     */
    InetAddress address;

    /**
     * If it is running.
     */
    private boolean isRunning;

    /**
     * The constructor.
     *
     * @param message The message.
     * @param address The address.
     */
    public ServerMessage(String message, InetAddress address) {
        this.message = message;
        //messages.put(address, message);
        this.address = address;
        isRunning = true;
    }

    /**
     * It adds messages.
     *
     * @param message The message.
     * @param address The address.
     */
    public void addMessage(String message, InetAddress address) {
        this.message = message;
        this.address = address;
    }

    /**
     * The run application for the thread.
     */
    @Override
    public void run() {
        while (isRunning) {
            try {
                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException e) {
                //does nothing
            }

            if (!ChatPanel.listMessage.contains(address.getHostName())) {
                ChatPanel.listMessage.add(address.getHostName());
                List<String> list = new ArrayList<>();
                list.add(address.getHostName() + ": " + message);
                ChatPanel.map.put(address.getHostName(), list);
            } else {
                ChatPanel.map.get(address.getHostName()).add(address.getHostName() + ": " + message);
            }
        }
    }

    /**
     * It stops the thread run.
     */
    public synchronized void stopThread() {
        isRunning = false;
    }

    /**
     * Verifies if the threads is running.
     *
     * @return Return true if it is running or false otherwise.
     */
    public synchronized boolean IsThreadRunning() {
        return isRunning;
    }

}
