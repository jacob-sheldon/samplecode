package com.jacob.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

/*
 *@title KeyGeneratorUtility
 *@description Generate Keys used by JWT
 *@author Jacob Sheldon
 *@version 1.0
 *@create 7/15/23 4:38 PM
 */
public class KeyGeneratorUtility {
    public static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        return keyPair;
    }
}
