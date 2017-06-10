package lapr4.blue.s2.ipc.n1151031.searchnetwork;

import eapli.framework.dto.DTO;
import java.io.Serializable;
import java.net.InetAddress;

/**
 * @FIXME
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class SearchWorkbookResponseDTO implements DTO, Serializable {

    /**
     * The TCP server's IP address in which to send the connection request.
     */
    private final InetAddress serverIP;

    private String workbookName;

    private String summary;

    /**
     * The full reply constructor.
     *
     * @param serverIP
     */
    public SearchWorkbookResponseDTO(InetAddress serverIP) {
        this.serverIP = serverIP;
        this.workbookName = "testName";
        //this.summary = "testSummary";
    }

    /**
     * A getter to the TCP server's IP address.
     *
     * @return It returns the TCP server's address in which to send the TCP
     * connection request.
     */
    public InetAddress getServerIP() {
        return serverIP;
    }

    public String getWorkbookName() {
        return workbookName;
    }

    public String getSummary() {
        return summary;
    }

}
