package com.lifebank.factory.cipherAlgorithmFactory;

import org.springframework.stereotype.Component;

@Component
public class CipherAlgorithmFactory implements ICipherAlgorithmFactory {
    public static final int BASE64=0;
    public static final int DES=1;
    public static final int SHA512=2;

    public ISecurityAlgorithm createCypherAlgorithm(int algorithm){
        switch (algorithm){
            case CipherAlgorithmFactory.BASE64:
                return new Base64Cipher();
            case CipherAlgorithmFactory.DES:
                return new DESCipher();
            case CipherAlgorithmFactory.SHA512:
                return new SHA512Hash();
            default:
                return null;
        }
    }
}
