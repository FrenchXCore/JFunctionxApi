package eu.frenchxcore.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/**
 *
 * @author Default
 */
public class XUtils {
    
    public static byte[] convertPublicKey(String pubKey) {
        byte[] ret;
        try {
            byte[] baPubkey = Base64.getDecoder().decode(pubKey);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(baPubkey);
            ret = Arrays.copyOfRange(digest.digest(), 0, 20);
        } catch (NoSuchAlgorithmException ex) {
            ret = null;
        }
        return ret;
    }
    
}