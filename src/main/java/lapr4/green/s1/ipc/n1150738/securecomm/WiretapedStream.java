package lapr4.green.s1.ipc.n1150738.securecomm;

import com.ibm.icu.util.Output;

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
public class WiretapedStream extends OutputStream {

    private Queue<OutputStream> wiretappers;

    public WiretapedStream(){
        wiretappers = new java.util.concurrent.ConcurrentLinkedDeque<OutputStream>();
    }

    public void attach(OutputStream wiretapper){
        wiretappers.add(wiretapper);
    }

    public void detach(OutputStream wiretapper){
        wiretappers.remove(wiretapper);
    }

    @Override
    public void write(int b) throws IOException {
        for (OutputStream wiretapper : wiretappers) {
            wiretapper.write(b);
        }
    }
}
