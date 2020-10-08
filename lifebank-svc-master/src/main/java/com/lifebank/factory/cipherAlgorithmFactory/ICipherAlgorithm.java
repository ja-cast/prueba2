package com.lifebank.factory.cipherAlgorithmFactory;

public interface ICipherAlgorithm extends ISecurityAlgorithm{
    String encrypt(String input);
    String decrypt(String input);
}
