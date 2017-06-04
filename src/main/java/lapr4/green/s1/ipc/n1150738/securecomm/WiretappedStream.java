package lapr4.green.s1.ipc.n1150738.securecomm;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Represents a OutputStream that can be wiretapped.
 *
 * This stream redirects all its incoming bytes to all of the
 * attached wiretappers (outputstreams).
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class WiretappedStream extends OutputStream {

    private Queue<OutputStream> wiretappers;

    public WiretappedStream(){
        wiretappers = new java.util.concurrent.ConcurrentLinkedDeque<OutputStream>();
    }

    public void attach(OutputStream wiretapper){
        wiretappers.add(wiretapper);
    }

    public void detach(OutputStream wiretapper){
        wiretappers.remove(wiretapper);
    }

    public void transferTappers(WiretappedStream dest){
        for (OutputStream wiretapper : wiretappers) {
            dest.attach(wiretapper);
        }
    }

    public void detachAll(){
        wiretappers.clear();
    }

    @Override
    public void write(int b) throws IOException {
        for (OutputStream wiretapper : wiretappers) {
            wiretapper.write(b);
        }
    }
}
