package iamdilipkumar.com.androidsecurity.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

/**
 * Class to implement RSA in Android
 *
 * @author dilipkumar4813
 * @since 22/10/17
 */

public class RsaUtils {

    static PublicKey pubKey;
    static PrivateKey privKey;

    /**
     * Method to generate Public and Private key
     * using RSA algorithm
     *
     */
    public static void generateRsaKeyPair(){
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(512,new SecureRandom());
            KeyPair keyPair = generator.generateKeyPair();
            pubKey = keyPair.getPublic();
            privKey = keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


}
