/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150838.GlobalSearch.util;

import java.util.Observable;

/**
 *Singleton to notify the observers that a new file was found !
 * @author nunopinto
 */
public class GlobalSearchPublisher extends Observable {
    
    private static final GlobalSearchPublisher instance = new GlobalSearchPublisher();

    @Override
    public void notifyObservers(Object b) {
        super.setChanged();
        super.notifyObservers(b); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * this object
     * @return 
     */
    public static GlobalSearchPublisher getInstance(){
        return instance;
    }
    
    
}
