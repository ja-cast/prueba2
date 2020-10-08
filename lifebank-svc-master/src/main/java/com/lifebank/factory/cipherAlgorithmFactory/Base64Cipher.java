package com.lifebank.factory.cipherAlgorithmFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Cipher implements ICipherAlgorithm {
    public String encrypt(String message){
        Base64.Encoder encoder = Base64.getEncoder();
        String cypheredMessage = encoder.encodeToString(message.getBytes(StandardCharsets.UTF_8) );
        return cypheredMessage;
    }

    public String decrypt(String cypheredMessage){
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(cypheredMessage);

        String message = new String(decodedByteArray);
        return message;
    }
}
