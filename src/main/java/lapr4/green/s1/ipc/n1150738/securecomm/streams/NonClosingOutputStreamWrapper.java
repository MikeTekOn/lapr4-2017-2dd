package lapr4.green.s1.ipc.n1150738.securecomm.streams;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by henri on 6/4/2017.
 */
public class NonClosingOutputStreamWrapper extends OutputStream {

    private OutputStream out;

    public NonClosingOutputStreamWrapper(OutputStream out){
        this.out = out;
    }
    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void close() throws IOException {
        System.out.println("Atempted Closing");
    }
}
