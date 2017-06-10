package lapr4.blue.s2.ipc.n1151452.netanalyzer.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Util class with helper util methods.
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 09/06/17.
 */
public class CustomUtil {

    private static final int MAX_PORT = 65536;
    private static final int MIN_PORT = 1025;

    /**
     * Validates a TCP/UDP port
     *
     * @param port a TCP/UDP port to validate
     * @return true if valid, false otherwise
     */
    public static boolean validatePort(int port) {

        return (port >= MIN_PORT && port <= MAX_PORT);
    }

    /**
     * Validates if a {@link Long} is non negative or negative
     *
     * @param num the long to validate
     * @return true if valid, false otherwise
     */
    public static boolean isNullOrNonNegative(Long num) {

        return !(num == null || num < 0);
    }

    /**
     * Returns the number of bytes the object
     *
     * @param obj the object to get count
     * @return the number of bytes the object has
     * @throws IOException I/O Exception
     */
    public static Long length(Object obj) throws IOException {

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {

                oos.writeObject(obj);
            }
            return (long) bos.size();
        }
    }
}
