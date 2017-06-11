package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain;

import eapli.framework.domain.DomainEventBase;
import eapli.util.DateTime;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.util.CustomUtil;

import java.util.Calendar;

/**
 * An event that holds the traffic count values.
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 09/06/17.
 */
public class TrafficCount extends DomainEventBase {

    private final Long securedIncoming;
    private final Long unsecuredIncoming;
    private final Long securedOutgoing;
    private final Long unsecuredOutgoing;

    /**
     * Builds a traffic count event
     *
     * @param occurredAt         when it occurred
     * @param aSecuredIncoming   the number of bytes the secured incoming connection received
     * @param aUnsecuredIncoming the number of bytes the unsecured incoming connection received
     * @param aSecuredOutgoing   the number of bytes the secured outgoing connection sent
     * @param aUnsecuredOutgoing the number of bytes the unsecured outgoing connection sent
     */
    public TrafficCount(Calendar occurredAt, Long aSecuredIncoming, Long aUnsecuredIncoming, Long aSecuredOutgoing, Long aUnsecuredOutgoing) {

        super((occurredAt == null) ? DateTime.now() : occurredAt);

        if (!CustomUtil.isNullOrNonNegative(aSecuredIncoming) ||
                !CustomUtil.isNullOrNonNegative(aUnsecuredIncoming) ||
                !CustomUtil.isNullOrNonNegative(aSecuredOutgoing) ||
                !CustomUtil.isNullOrNonNegative(aUnsecuredOutgoing)) {
            throw new IllegalStateException("Invalid counter(s)");
        }

        securedIncoming = aSecuredIncoming;
        unsecuredIncoming = aUnsecuredIncoming;
        securedOutgoing = aSecuredOutgoing;
        unsecuredOutgoing = aUnsecuredOutgoing;
    }

    /**
     * Retrieves the secured incoming byte count
     *
     * @return the secured incoming byte count
     */
    public Long securedIncoming() {
        return securedIncoming;
    }

    /**
     * Retrieves the unsecured incoming byte count
     *
     * @return the unsecured incoming byte count
     */
    public Long unsecuredIncoming() {
        return unsecuredIncoming;
    }

    /**
     * Retrieves the secured outgoing byte count
     *
     * @return the secured outgoing byte count
     */
    public Long securedOutgoing() {
        return securedOutgoing;
    }

    /**
     * Retrieves the unsecured outgoing byte count
     *
     * @return the unsecured outgoing byte count
     */
    public Long unsecuredOutgoing() {
        return unsecuredOutgoing;
    }
}
