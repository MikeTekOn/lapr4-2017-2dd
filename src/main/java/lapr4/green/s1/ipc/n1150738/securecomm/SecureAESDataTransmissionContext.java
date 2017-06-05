package lapr4.green.s1.ipc.n1150738.securecomm;

import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.output.TeeOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by henri on 6/4/2017.
 */
public class SecureAESDataTransmissionContext implements DataTransmissionContext {

    private static final byte[] raw = new byte[]{
            'C', 'l', 'e', 'a', 'n', 'S', 'h', 'e', 'e', 't', 's', 'L', 'A', 'P', 'R', '4'
    };

//    private WiretappedStream inputTap;
//    private WiretappedStream outputTap;

    public SecureAESDataTransmissionContext(){
//        inputTap = new WiretappedStream();
//        outputTap = new WiretappedStream();
    }

    /**
     * Returns the input stream from where the system shall read the
     * objects to be received through the connection.
     *
     * @param socketInStream the InputStream of the socket
     * @return the ObjectInputStream from where we will read objects received
     */
    @Override
    public ObjectInputStream inputStream(InputStream socketInStream) throws IOException {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            IvParameterSpec iv = new IvParameterSpec(raw);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            return new ObjectInputStream(new CipherInputStream(socketInStream, cipher));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException e) {
            Logger.getLogger(SecureAESDataTransmissionContext.class.getName()).log(Level.SEVERE, "Crypto Setup Failed", e);
        }

        throw new IOException("Cannot use cryptographic stream");
    }

    /**
     * Returns the output stream from where the system should write the
     * objects to be sent through the connection.
     *
     * @param socketOutStream the OutputStream of the socket
     * @return the ObjectOutputStream from where we will read objects received
     */
    @Override
    public ObjectOutputStream outputStream(OutputStream socketOutStream) throws IOException{

        try {
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            IvParameterSpec iv = new IvParameterSpec(raw);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            return new ObjectOutputStream(new CipherOutputStream(socketOutStream, cipher));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException e) {
            Logger.getLogger(SecureAESDataTransmissionContext.class.getName()).log(Level.SEVERE, "Crypto Setup Failed", e);
        }

        throw new IOException("Cannot use cryptographic stream");
    }

//    @Override
//    public WiretappedStream wiretapInput() {
//        return inputTap;
//    }
//
//    @Override
//    public WiretappedStream wiretapOutput() {
//        return outputTap;
//    }

    @Override
    public boolean isSecure() {
        return true;
    }


    @Override
    public String securityDesc() {
        return "Secure (AES)";
    }

}