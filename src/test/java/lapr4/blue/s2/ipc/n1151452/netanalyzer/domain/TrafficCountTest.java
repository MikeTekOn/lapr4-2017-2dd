package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain;

import org.junit.Test;

/**
 * Tests a traffic count event
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 09/06/17.
 */
public class TrafficCountTest {

    /**
     * Test if secured incoming byte count is non negative
     */
    @Test(expected = IllegalStateException.class)
    public void ensureTrafficCountEventHasNonNegativeSecuredIncomingCount() {
        new TrafficCount(null, -1L, 1L, 1L, 1L);
    }

    /**
     * Test if unsecured incoming byte count is non negative
     */
    @Test(expected = IllegalStateException.class)
    public void ensureTrafficCountEventHasNonNegativeUnsecuredIncomingCount() {
        new TrafficCount(null, 1L, -1L, 1L, 1L);
    }

    /**
     * Test if secured outgoing byte count is non negative
     */
    @Test(expected = IllegalStateException.class)
    public void ensureTrafficCountEventHasNonNegativeSecuredOutgoingCount() {
        new TrafficCount(null, 1L, 1L, -1L, 1L);
    }

    /**
     * Test if unsecured outgoing byte count is non negative
     */
    @Test(expected = IllegalStateException.class)
    public void ensureTrafficCountEventHasNonNegativeUnsecuredOutgoingCount() {
        new TrafficCount(null, 1L, 1L, 1L, -1L);
    }
}