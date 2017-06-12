package lapr4.blue.s2.ipc.n1151452.netanalyzer.domain.transmission;

import lapr4.green.s1.ipc.n1150738.securecomm.SecureAESDataTransmissionContext;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Represents a AES encrypted transmission
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 09/06/17.
 * @author Henrique [1150738@isep.ipp.pt]
 */
public class AESEncryptedTransmission implements TransmissionStrategy, Serializable {

    private static final byte[] raw = "cleansheetslapr4".getBytes(); // 16 bytes

    @Override
    public ObjectInputStream stream(InputStream in) throws IOException {

        Cipher cipher;
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            cipher = Cipher.getInstance("AES/CTR/NoPadding");
            IvParameterSpec iv = new IvParameterSpec(raw);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

        } catch (Exception e) {
            Logger.getLogger(SecureAESDataTransmissionContext.class.getName()).log(Level.SEVERE, "Encryption setup failed.", e);
            throw new IOException("Failed to create stream.");
        }

        return new ObjectInputStream(new CipherInputStream(in, cipher));
    }

    @Override
    public ObjectOutputStream stream(OutputStream out) throws IOException {
        Cipher cipher;
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            cipher = Cipher.getInstance("AES/CTR/NoPadding");
            IvParameterSpec iv = new IvParameterSpec(raw);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

        } catch (Exception e) {
            Logger.getLogger(SecureAESDataTransmissionContext.class.getName()).log(Level.SEVERE, "Encryption setup failed.", e);
            throw new IOException("Failed to create stream.");
        }

        return new ObjectOutputStream(new CipherOutputStream(out, cipher));
    }

    @Override
    public boolean isSecured() {
        return true;
    }
}
