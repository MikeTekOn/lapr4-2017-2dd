package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission;

import java.io.*;

/**
 * Interface to abstract the transmission implementation (Strategy Pattern)
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 09/06/17.
 */
public interface TransmissionStrategy {

    /**
     * Decorates the input stream in the implemented transmission context stream
     *
     * @param in the input stream to decorate
     * @return the decorated input stream
     */
    ObjectInputStream stream(InputStream in) throws IOException;

    /**
     * Decorates the output stream in the implemented transmission context stream
     *
     * @param out the output stream to decorate
     * @return the decorated output stream
     */
    ObjectOutputStream stream(OutputStream out) throws IOException;

    /**
     * Checks if it is a secure transmission
     *
     * @return true, false otherwise
     */
    boolean isSecured();
}
