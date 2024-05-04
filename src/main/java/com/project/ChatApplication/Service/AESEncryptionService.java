package com.project.ChatApplication.Service;



import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

public class AESEncryptionService {

    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // Use 256-bit AES for strong encryption
        return keyGenerator.generateKey();
    }

    public static byte[] encryptMessage(String content, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(content.getBytes());
    }

    public static String decryptMessage(byte[] encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(encryptedData);
        return new String(decrypted);
    }

    // Enhance the AES key with a chaotic map
    public static SecretKey enhanceKeyWithChaoticMap(SecretKey originalKey) {
        byte[] keyBytes = originalKey.getEncoded();
        double r = 3.99; // Logistic map parameter, close to chaos
        double x0 = 0.5; // Initial value
        byte[] enhancedKeyBytes = ChaoticKeyGenerator.enhanceKeyWithLogisticMap(keyBytes, r, x0);
        return new SecretKeySpec(enhancedKeyBytes, "AES");
    }
}
