package com.lifebank.factory.cipherAlgorithmFactory;

public interface IHashAlgorithm extends ISecurityAlgorithm{
    String encrypt(String input);
}
