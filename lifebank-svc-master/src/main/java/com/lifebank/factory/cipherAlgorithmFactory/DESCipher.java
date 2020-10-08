package com.lifebank.factory.cipherAlgorithmFactory;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DESCipher implements ICipherAlgorithm {

    private static final String CRYPT_ALGORITHM = "DESede";
    private static final String PADDING = "DESede/CBC/NoPadding";
    private static final String CHAR_ENCODING = "UTF-8";

    private static final byte[] MY_KEY = "5oquil2oo2vb63e8ionujny6".getBytes();//24-byte
    private static final byte[] MY_IV = "3oco1v52".getBytes();//8-byte

    public String encrypt(String text) {
        String retVal = null;

        try {
            final SecretKeySpec secretKeySpec = new SecretKeySpec(MY_KEY, CRYPT_ALGORITHM);
            final IvParameterSpec iv = new IvParameterSpec(MY_IV);
            final Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
            final byte[] encrypted = cipher.doFinal(text.getBytes(CHAR_ENCODING));
            retVal = new String(HexBin.encode(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    public String decrypt(String text) {
        String retVal = null;

        try {
            final SecretKeySpec secretKeySpec = new SecretKeySpec(MY_KEY, CRYPT_ALGORITHM);
            final IvParameterSpec iv = new IvParameterSpec(MY_IV);
            final Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
            final byte[] decrypted = cipher.doFinal(HexBin.decode(text));
            retVal = new String(decrypted, CHAR_ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

}
