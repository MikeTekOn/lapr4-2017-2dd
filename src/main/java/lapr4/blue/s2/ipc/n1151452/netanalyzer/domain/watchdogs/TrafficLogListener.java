package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.watchdogs;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficEvent;

/**
 * An interface to represent a traffic log listener
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 10/06/17.
 */
public interface TrafficLogListener {

    /**
     * Fires a new traffic event
     *
     * @param event the new traffic event
     */
    void fireNewTrafficEvent(TrafficEvent event);
}
