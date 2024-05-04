package com.project.ChatApplication.Service;



public class ChaoticKeyGenerator {

    // Example using the Logistic Map for demonstration
    public static byte[] enhanceKeyWithLogisticMap(byte[] originalKey, double r, double x0) {
        byte[] enhancedKey = new byte[originalKey.length];
        double x = x0;

        for (int i = 0; i < originalKey.length; i++) {
            x = r * x * (1 - x);
            int mapValue = (int) (256 * x); // Scale x to byte size
            enhancedKey[i] = (byte) (originalKey[i] ^ mapValue); // XOR original key with map value
        }

        return enhancedKey;
    }
}
