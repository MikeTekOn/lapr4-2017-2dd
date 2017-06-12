package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission;

import java.io.*;

/**
 * Represents an open transmission
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 09/06/17.
 */
public class OpenTransmission implements TransmissionStrategy, Serializable {

    @Override
    public ObjectInputStream stream(InputStream in) throws IOException {
        return new ObjectInputStream(in);
    }

    @Override
    public ObjectOutputStream stream(OutputStream out) throws IOException {
        return new ObjectOutputStream(out);
    }

    @Override
    public boolean isSecured() {
        return false;
    }
}
