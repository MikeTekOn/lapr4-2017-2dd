package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.watchdogs;

import java.util.Observer;

/**
 * Abstract implementation of a traffic watchdogs (singleton pattern), during his life cycle it is listening for traffic
 * events occurring.
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 10/06/17.
 */
public abstract class TrafficWatchdog implements Observer {

    /**
     * Boolean that tells if the watchdog is active or not
     */
    private boolean on;

    /**
     * Builds a traffic watchdog
     */
    TrafficWatchdog() {
        on = false;
    }

    /**
     * Checks if traffic watchdog is active
     *
     * @return true if it is active, false otherwise
     */
    public boolean isOn() {
        return on;
    }

    /**
     * Activates the watchdog
     */
    public void turnOn() {
        on = true;
    }

    /**
     * Deactivates the watchdog
     */
    public void turnOff() {
        on = false;
    }
}
