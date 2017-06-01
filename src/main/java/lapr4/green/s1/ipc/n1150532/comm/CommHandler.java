package lapr4.green.s1.ipc.n1150532.comm;

import java.io.ObjectOutputStream;

/**
 *
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public interface CommHandler {

    public void handleDTO(Object dto, ObjectOutputStream outStream);

    public Object getLastReceivedDTO();

}
