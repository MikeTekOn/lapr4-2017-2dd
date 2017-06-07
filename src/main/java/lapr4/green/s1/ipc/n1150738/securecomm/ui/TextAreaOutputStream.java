package lapr4.green.s1.ipc.n1150738.securecomm.ui;

/**
 * Created by henri on 6/5/2017.
 */
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class TextAreaOutputStream extends OutputStream {

    private final JTextArea textArea;

    public TextAreaOutputStream(final JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }

    @Override
    public void write(int b) throws IOException {

        if (b == '\r')
            return;

        if (b == '\n') {
            String text = textArea.getText();
            if(text.length() > 20) {
                text = text.substring(1, text.length());
            }
            final String finalText = text + String.valueOf((char)b);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    textArea.setText(finalText);
                }
            });


            return;
        }
    }
}