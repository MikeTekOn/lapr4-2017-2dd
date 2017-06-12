package lapr4.green.s1.ipc.n1150532.comm;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.TrafficOutputStream;
import lapr4.green.s1.ipc.n1150532.comm.connection.PacketEncapsulatorDTO;

/**
 * A handler for the server UDP to deal with EchoDTO.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class EchoUDPServerHandler implements CommHandler {

    /**
     * The last DTO received.
     */
    private Object lastReceivedDTO;

    /**
     * It stored the received DTO as the last received DTO. Then, it builds a
     * new EchoDTO whose message is the answer of the original and its answer is
     * null.
     *
     * @param dto The received DTO. It is suppose to be an EchoDTO.
     * @param outStream The output in which to write the reply.
     */
    @Override
    public void handleDTO(Object dto, TrafficOutputStream outStream) {
        PacketEncapsulatorDTO encapsulator = (PacketEncapsulatorDTO) dto;
        lastReceivedDTO = encapsulator.getDTO();
        EchoDTO reply = new EchoDTO(((EchoDTO) encapsulator.getDTO()).getAnswer(), null);
        try {
            outStream.write(reply);
        } catch (IOException ex) {
            Logger.getLogger(EchoTCPServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * A getter of the last received DTO.
     *
     * @return It returns the last received DTO.
     */
    @Override
    public Object getLastReceivedDTO() {
        return lastReceivedDTO;
    }

}
