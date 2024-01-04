package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("MagicNumber")
public class MD5Encoder {
    private final MessageDigest encoder;

    public MD5Encoder() {
        try {
            encoder = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String encode(String message) {
        byte[] array = encoder.digest(message.getBytes());
        StringBuilder hash = new StringBuilder();

        for (byte b : array) {
            hash.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }

        return hash.toString();
    }
}
