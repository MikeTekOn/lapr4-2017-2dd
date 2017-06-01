/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s1.ipc.n1150838.findworkbooks;

import java.util.Observable;

/**
 *
 * @author nunopinto
 */
public class FindWorkbooksPublisher extends Observable {
    
    private static final FindWorkbooksPublisher instance = new FindWorkbooksPublisher();

    @Override
    public void notifyObservers(Object b) {
        super.setChanged();
        super.notifyObservers(b); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static FindWorkbooksPublisher getInstance(){
        return instance;
    }
    
    
}
