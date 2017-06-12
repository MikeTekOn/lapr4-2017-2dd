package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.watchdogs;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficCount;

/**
 * An interface to represent a traffic counter listener
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 10/06/17.
 */
public interface TrafficCounterListener {

    /**
     * Fires a new traffic count
     *
     * @param count the new traffic count
     */
    void fireNewTrafficCount(TrafficCount count);
}
