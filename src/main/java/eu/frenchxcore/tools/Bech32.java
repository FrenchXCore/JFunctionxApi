package eu.frenchxcore.tools;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author FrenchXCore-1
 */
public class Bech32 {

    private static Base64.Decoder Base64Decoder = Base64.getDecoder();
    private static MessageDigest SHA256;

    private final static String CHARSET = "qpzry9x8gf2tvdw0s3jn54khce6mua7l";

    private final static int[] GEN = new int[]{0x3b6a57b2, 0x26508e6d, 0x1ea119fa, 0x3d4233dd, 0x2a1462b3};

    static {
        try {
            SHA256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public static final class Bech {

        public String prefix;
        public byte[] data;
    }

    public static byte[] base64Decode(String pubKey) {
        return Base64Decoder.decode(pubKey);
    }

    public static byte[] convertPublicKey(String b64PubKey) {
        SHA256.reset();
        SHA256.update(Base64Decoder.decode(b64PubKey));
        return Arrays.copyOfRange(SHA256.digest(), 0, 20);
    }

    public static String getTransactionHashToAscii(String b64Tx) {
        byte[] hash = getTransactionHash(b64Tx);
        return toHexAscii(hash);
    }

    public static byte[] getTransactionHash(String b64Tx) {
        SHA256.reset();
        SHA256.update(Base64Decoder.decode(b64Tx));
        return SHA256.digest();
    }

    public static String toHexAscii(byte[] ba) {
        StringBuilder ret = new StringBuilder();
        for (byte i : ba) {
            ret.append(String.format("%02X", i));
        }
        return ret.toString();
    }

    /**
     * ConvertAndEncode converts from a base64 encoded byte string to base32
     * encoded byte string and then to bech32.
     *
     * @param prefix
     * @param data
     * @return
     * @throws java.lang.Exception
     */
    public static String ConvertAndEncode(String prefix, byte[] data) throws Exception {
        try {
            byte[] converted = ConvertBits(data, 8, 5, true);
            return Encode(prefix, converted);
        } catch (Exception ex) {
            throw new Exception("encoding bech32 failed: " + ex.getMessage());
        }
    }

    /**
     * DecodeAndConvert decodes a bech32 encoded string and converts to base64
     * encoded bytes.
     *
     * @param sBech
     * @return
     * @throws java.lang.Exception
     */
    public static Bech DecodeAndConvert(String sBech) throws Exception {
        Bech bech = null;
        try {
            bech = Decode(sBech, 1023);
        } catch (Exception ex) {
            throw new Exception("decoding bech32 failed: " + ex.getMessage());
        }
        byte[] converted;
        try {
            converted = ConvertBits(bech.data, 5, 8, false);
        } catch (Exception ex) {
            throw new Exception("decoding bech32 failed: " + ex.getMessage());
        }
        bech.data = converted;
        return bech;
    }

    /**
     * Decode decodes a bech32 encoded string, returning the human-readable part
     * and the data part excluding the checksum.
     *
     * @param bech
     * @param limit
     * @return
     * @throws java.lang.Exception
     */
    public static Bech Decode(String bech, int limit) throws Exception {
        Bech ret = new Bech();
        // The maximum allowed length for a bech32 string is 90. It must also
        // be at least 8 characters, since it needs a non-empty HRP, a
        // separator, and a 6 character checksum.
        if (bech.length() < 8 || bech.length() > limit) {
            throw new Exception("invalid bech32 string length " + bech.length());
        }
        // Only	ASCII characters between 33 and 126 are allowed.
        for (char c : bech.toCharArray()) {
            if (c < 33 || c > 126) {
                throw new Exception("invalid character in string : '" + c + "'");
            }
        }

        // The characters must be either all lowercase or all uppercase.
        String lower = bech.toLowerCase();
        String upper = bech.toUpperCase();
        if (bech.compareTo(lower) != 0 && bech.compareTo(upper) != 0) {
            throw new Exception("string not all lowercase or all uppercase");
        }

        // We'll work with the lowercase string from now on.
        bech = lower;

        // The string is invalid if the last '1' is non-existent, it is the
        // first character of the string (no human-readable part) or one of the
        // last 6 characters of the string (since checksum cannot contain '1'),
        // or if the string is more than 90 characters in total.
        int one = bech.lastIndexOf("1");
        if (one < 1 || (one + 7) > bech.length()) {
            throw new Exception("invalid index of 1");
        }

        // The human-readable part is everything before the last '1'.
        String prefix = bech.substring(0, one);
        String data = bech.substring(one + 1);

        // Each character corresponds to the byte with value of the index in 'charset'.
        byte[] decoded = toBytes(data);

        if (!VerifyChecksum(prefix, decoded)) {
            String moreInfo = "";
            String checksum = bech.substring(bech.length() - 6);
            try {
                String expected = toChars(Checksum(prefix, data.substring(0, decoded.length - 6).getBytes()));
                moreInfo = "Expected " + expected + ", got " + checksum + ".";
            } catch (Exception ex) {
                throw new Exception("checksum failed. " + moreInfo);
            }
        }
        ret.prefix = prefix;
        // We exclude the last 6 bytes, which is the checksum.
        ret.data = Arrays.copyOfRange(decoded, 0, decoded.length - 6);
        return ret;
    }

    /**
     * Encode encodes a byte slice into a bech32 string with the human-readable
     * part prefix. Note that the bytes must each encode 5 bits (base32).
     *
     * @param prefix
     * @param data
     * @return
     * @throws java.lang.Exception
     */
    public static String Encode(String prefix, byte[] data) throws Exception {
        // Calculate the checksum of the data and append it at the end.
        byte[] checksum = Checksum(prefix, data);
        byte[] combined = new byte[data.length + checksum.length];
        System.arraycopy(data, 0, combined, 0, data.length);
        System.arraycopy(checksum, 0, combined, data.length, checksum.length);

        // The resulting bech32 string is the concatenation of the hrp, the
        // separator 1, data and checksum. Everything after the separator is
        // represented using the specified charset.
        String dataChars = null;
        try {
            dataChars = toChars(combined);
        } catch (Exception ex) {
            throw new Exception("unable to convert data bytes to chars: " + ex.getMessage());
        }
        return prefix + "1" + dataChars;
    }

    /**
     * For more details on the checksum calculation, please refer to BIP 173.
     *
     * @param prefix
     * @param data
     * @return
     */
    public static byte[] Checksum(String prefix, byte[] data) {
        // Convert the bytes to list of integers, as this is needed for the
        // checksum calculation.
        int[] integers = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            integers[i] = (int) (data[i] & 0x00ff);
        }
        int[] prefixExpand = PrefixExpand(prefix);
        int[] padding = new int[]{0, 0, 0, 0, 0, 0};
        int[] values = new int[prefixExpand.length + integers.length + padding.length];
        System.arraycopy(prefixExpand, 0, values, 0, prefixExpand.length);
        System.arraycopy(integers, 0, values, prefixExpand.length, integers.length);
        System.arraycopy(padding, 0, values, prefixExpand.length + integers.length, padding.length);
        int polymod = Polymod(values) ^ 1;
        byte[] res = new byte[6];
        for (int i = 0; i < 6; i++) {
            res[i] = (byte) ((polymod >> ((5 * (5 - i))) & 0x001f));
        }
        return res;
    }

    /**
     * toBytes converts each character in the string 'chars' to the value of the
     * index of the correspoding character in 'charset'.
     *
     * @param string
     * @return
     * @throws java.lang.Exception
     */
    public static byte[] toBytes(String string) throws Exception {
        byte[] decoded = new byte[string.length()];
        int i = 0;
        for (char c : string.toCharArray()) {
            int index = CHARSET.indexOf(c);
            if (index < 0) {
                throw new Exception("invalid character not part of charset: " + c);
            }
            decoded[i++] = (byte) index;
        }
        return decoded;
    }

    /**
     * toChars converts the byte slice 'data' to a string where each byte in
     * 'data' encodes the index of a character in 'charset'.
     *
     * @param data
     * @return
     * @throws java.lang.Exception
     */
    public static String toChars(byte[] data) throws Exception {
        StringBuilder result = new StringBuilder();
        for (byte b : data) {
            int ib = b & 0x00ff;
            if (ib >= CHARSET.length()) {
                throw new Exception("invalid data byte: " + (char) b);
            }
            result.append(CHARSET.charAt(ib));
        }
        return result.toString();
    }

    /**
     * ConvertBits converts a byte slice where each byte is encoding fromBits
     * bits, to a byte slice where each byte is encoding toBits bits.
     *
     * @param data
     * @param fromBits
     * @param toBits
     * @param pad
     * @return
     * @throws java.lang.Exception
     */
    public static byte[] ConvertBits(byte[] data, int fromBits, int toBits, boolean pad) throws Exception {
        if (fromBits < 1 || fromBits > 8 || toBits < 1 || toBits > 8) {
            throw new Exception("only bit groups between 1 and 8 allowed");
        }

        // The final bytes, each byte encoding toBits bits.
        ByteArrayDataOutput regrouped = ByteStreams.newDataOutput();

        // Keep track of the next byte we create and how many bits we have
        // added to it out of the toBits goal.
        byte nextByte = 0;
        int filledBits = 0;

        for (byte b : data) {
            // Discard unused bits.
            b = (byte) ((b & 0x00ff) << (8 - fromBits));

            // How many bits remaining to extract from the input data.
            int remainingFromBits = fromBits;
            while (remainingFromBits > 0) {
                // How many bits remaining to be added to the next byte.
                int remainingToBits = toBits - filledBits;

                // The number of bytes to next extract is the minimum of
                // remFromBits and remToBits.
                int toExtract = remainingFromBits;
                if (remainingToBits < toExtract) {
                    toExtract = remainingToBits;
                }

                // Add the next bits to nextByte, shifting the already
                // added bits to the left.
                nextByte = (byte) (((nextByte & 0x00FF) << toExtract) | ((b & 0x00FF) >> (8 - toExtract)));

                // Discard the bits we just extracted and get ready for
                // next iteration.
                b = (byte) ((b & 0x00FF) << toExtract);
                remainingFromBits -= toExtract;
                filledBits += toExtract;

                // If the nextByte is completely filled, we add it to
                // our regrouped bytes and start on the next byte.
                if (filledBits == toBits) {
                    regrouped.writeByte(nextByte);
                    filledBits = 0;
                    nextByte = 0;
                }
            }
        }

        // We pad any unfinished group if specified.
        if (pad && filledBits > 0) {
            nextByte = (byte) ((nextByte & 0x00FF) << (toBits - filledBits));
            regrouped.writeByte(nextByte);
            filledBits = 0;
            nextByte = 0;
        }

        // Any incomplete group must be <= 4 bits, and all zeroes.
        if (filledBits > 0 && (filledBits > 4 || nextByte != 0)) {
            throw new Exception("invalid incomplete group");
        }

        return regrouped.toByteArray();
    }

    /**
     * For more details on the polymod calculation, please refer to BIP 173.
     *
     * @param values
     * @return
     */
    public static int Polymod(int[] values) {
        int chk = 1;
        for (int v : values) {
            byte b = (byte) (chk >> 25);
            chk = ((chk & 0x1ffffff) << 5) ^ v;
            for (int i = 0; i < 5; i++) {
                if (((b >> i) & 0x01) == 1) {
                    chk ^= GEN[i];
                }
            }
        }
        return chk;
    }

    /**
     * For more details on HRP expansion, please refer to BIP 173.
     *
     * @param prefix
     * @return
     */
    public static int[] PrefixExpand(String prefix) {
        char[] caPrefix = prefix.toCharArray();
        int[] v = new int[caPrefix.length * 2 + 1];
        int j = 0;
        for (int i = 0; i < prefix.length(); i++) {
            v[j++] = caPrefix[i] >> 5 & 0x00ff;
        }
        v[j++] = 0;
        for (int i = 0; i < prefix.length(); i++) {
            v[j++] = caPrefix[i] & 0x001f;
        }

        return v;
    }

    /**
     * For more details on the checksum verification, please refer to BIP 173.
     *
     * @param prefix
     * @param data
     * @return
     */
    public static boolean VerifyChecksum(String prefix, byte[] data) {
        int[] integers = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            integers[i] = data[i] & 0x00ff;
        }
        int[] prefixExpand = PrefixExpand(prefix);
        int[] concat = new int[integers.length + prefixExpand.length];
        System.arraycopy(prefixExpand, 0, concat, 0, prefixExpand.length);
        System.arraycopy(integers, 0, concat, prefixExpand.length, integers.length);
        return Polymod(concat) == 1;
    }

}
