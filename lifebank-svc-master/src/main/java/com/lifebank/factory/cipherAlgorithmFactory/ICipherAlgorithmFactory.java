package com.lifebank.factory.cipherAlgorithmFactory;

public interface ICipherAlgorithmFactory {
    public ISecurityAlgorithm createCypherAlgorithm(int algorithm);
}
