package iamdilipkumar.com.androidsecurity.utils;

import android.util.Log;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Class to implement RSA in Android
 *
 * @author dilipkumar4813
 * @since 22/10/17
 */

public class RsaUtils {

    private static PublicKey pubKey;
    private static PrivateKey privKey;

    private static final String RSA = "RSA";

    private static String rsaModulus = "eeb341f32d52cee219d8b3937bc036ab7b49dc353032ff61adfaed9" +
            "4657af34650aa8571ba1";

    /**
     * Method to generate the Private key provided the modulus and exponent
     *
     */
    public static void buildPrivateKey() {
        BigInteger exponent = new BigInteger("10001",16);
        BigInteger modulus=new BigInteger(rsaModulus,16);
        RSAPrivateKeySpec privateSpec = new RSAPrivateKeySpec(modulus, exponent);
        try {

            KeyFactory factory = KeyFactory.getInstance("RSA");
            privKey = factory.generatePrivate(privateSpec);
            Log.d("Private key",privKey.toString());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to initialize RSA Keys
     */
    private static void initialize() {
        if (pubKey == null) {
            generateRsaKeyPair();
        }
    }

    /**
     * Method to generate Public and Private key
     * using RSA algorithm
     */
    private static void generateRsaKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(RSA);
            generator.initialize(512, new SecureRandom());
            KeyPair keyPair = generator.generateKeyPair();
            pubKey = keyPair.getPublic();
            privKey = keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to generate bytes using Cipher class
     *
     * @param text - String to be encrypted
     * @return - bytes
     */
    public static byte[] encrypt(String text) {
        initialize();
        Log.d("Public key", "" + pubKey.toString());
        try {
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return cipher.doFinal(text.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Bytes to include RSA decryption
     *
     * @param src - Bytes to be decrypted
     * @return - Bytes
     */
    public static byte[] decrypt(byte[] src) {
        initialize();
        Log.d("Private key", "" + privKey.toString());
        try {
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, privKey);
            return cipher.doFinal(src);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }
}
