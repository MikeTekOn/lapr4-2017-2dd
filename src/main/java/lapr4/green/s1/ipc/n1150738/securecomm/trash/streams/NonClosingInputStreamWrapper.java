package lapr4.green.s1.ipc.n1150738.securecomm.trash.streams;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by henri on 6/4/2017.
 */
public class NonClosingInputStreamWrapper extends InputStream{

    private InputStream in;

    public NonClosingInputStreamWrapper(InputStream in){
       this.in = in;
    }


    @Override
    public int read() throws IOException {
        return in.read();
    }


    @Override
    public void close() throws IOException {
        in.close();
    }
}
