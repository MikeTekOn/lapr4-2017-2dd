package lapr4.blue.s3.core.n1151088.searchReplace;

import java.util.Observable;

/**
 *
 * Singleton to notify the observers that replace was concluded
 * @author Diana Silva [1151088@isep.ipp.pt]
 */
public class SearchReplacePublisher extends Observable {
     private static final SearchReplacePublisher instance = new SearchReplacePublisher();

    @Override
    public void notifyObservers(Object b) {
        super.setChanged();
        super.notifyObservers(b); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return this object
     */
    public static SearchReplacePublisher getInstance() {
        return instance;
    }
}


