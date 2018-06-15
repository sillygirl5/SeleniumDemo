package util;

import logger.Log;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    private static Logger LOGGER = Log.getLogger(MD5Util.class);

    private MD5Util() {
    }

    public static String hexMD5(long timestamp, String token) {
        return hexMD5(String.valueOf(timestamp) + token);
    }

    public static String hexMD5(String str) {
        StringBuilder builder = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] cipherData = digest.digest(str.getBytes());
            for (byte cipher : cipherData) {
                String hexStr = Integer.toHexString(cipher & 0xff);
                builder.append(hexStr.length() == 1 ? "0" + hexStr : hexStr);
            }
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Hex md5 fail.", e);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(hexMD5(1514361762914L, "UER909E0RF9RFER90R"));
    }

}
