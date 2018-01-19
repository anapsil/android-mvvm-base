package net.anapsil.mvvmbase.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ana.silva on 19/01/18.
 */

public class StringUtils {
    private static final String MD5 = "MD5";

    public static String generateMd5(String value) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(MD5);
        md.update(value.getBytes());

        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();

        for (byte b : digest) {
            String h = Integer.toHexString(0xFF & b);
            while (h.length() < 2) {
                h = "0" + h;
            }
            sb.append(h);
        }
        return sb.toString();
    }

    public static String capitalizeFirstLetter(String value) {
        if (value == null || value.length() == 0) {
            return value;
        }
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }
}
