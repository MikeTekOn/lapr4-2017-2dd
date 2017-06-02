package lapr4.green.s1.ipc.n1150532.comm.connection;

import eapli.framework.dto.DTO;
import java.io.Serializable;
import java.net.DatagramPacket;
import lapr4.green.s1.ipc.n1150532.comm.CommHandler;

/**
 * A encapsulation DTO to provide the received DTO along with the DatagramPacket who transport it.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class PacketEncapsulatorDTO implements DTO, Serializable {

    /**
     * The Datagram received.
     */
    private final DatagramPacket packet;

    /**
     * The handler to deal with the received DTO.
     */
    private final CommHandler handler;

    /**
     * The received DTO.
     */
    private final Object receivedDTO;

    /**
     * The full constructor of the encapsulation.
     * 
     * @param thePacket The Datagram Packer to encapsulate.
     * @param theHandler The handler to deal with the received DTO.
     * @param theDTO The received DTO.
     */
    public PacketEncapsulatorDTO(DatagramPacket thePacket, CommHandler theHandler, Object theDTO) {
        packet = thePacket;
        handler = theHandler;
        receivedDTO = theDTO;
    }

    /**
     * A getter to the Datagram Packet.
     *
     * @return It returns the encapsulated Datagram Packet.
     */
    public DatagramPacket getPacket(){
        return packet;
    }

    /**
     * A getter to the DTO handler.
     *
     * @return It returns the handler capable of dealing with the received DTO.
     */
    public CommHandler getHandler(){
        return handler;
    }

    /**
     * A getter to the received DTO.
     *
     * @return It returns the received DTO.
     */
    public Object getDTO(){
        return receivedDTO;
    }

}
