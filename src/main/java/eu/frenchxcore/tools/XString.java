package eu.frenchxcore.tools;

import com.google.protobuf.*;
import okio.ByteString;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HexFormat;
import java.util.List;
import java.util.Map;

public class XString {

    public static String[] LowerCase(List<String> strings) {
        String[] ret = null;
        return strings.toArray(ret);
    }

    public static String toString(ByteString bString) {
        return new String(bString.toByteArray());
    }

    public static String toHexAscii(byte[] ba) {
        StringBuilder ret = new StringBuilder();
        for (byte i : ba) {
            ret.append(String.format("%02X", i));
        }
        return ret.toString();
    }

    public static byte[] fromHexAscii(String input) {
        if (input.length() % 2 != 0) {
            return null;
        }
        byte[] ret = new byte[input.length()/2];
        for (int i = 0 ; i < input.length() ; ) {
            int v = HexFormat.fromHexDigits(input, i, i+2);
            ret[i/2] = (byte) (v & 0x00FF);
            i += 2;
        }
        return ret;
    }

    public static Any anyFromByteArray(byte[] input) throws InvalidProtocolBufferException {
        return Any.parseFrom(input);
    }

}
