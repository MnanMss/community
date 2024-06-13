package com.cdu.community.server.shared.infrastructure.jwt;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
/**
 * @author mila
 */
// 用于执行AES加密和解密操作的实用程序类
// 通过给定的密钥对输入进行加密和解密
public class AESEncryptionService {
    private static final String ALGORITHM = "AES";
    private final String secret;

    // 构造函数，接受一个密钥作为参数
    public AESEncryptionService(String secret) {
        this.secret = secret;
    }

    // 对输入字符串进行加密并返回加密后的字符串
    public String encryptString(String input) {
        try {
            SecretKeySpec key = new SecretKeySpec(secret.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            return null;
        }
    }

    // 对经过编码的输入字符串进行解密并返回解密后的字符串
    public String decryptString(String encodedInput) {
        try {
            SecretKeySpec key = new SecretKeySpec(secret.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedBytes = Base64.getDecoder().decode(encodedInput);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            return null;
        }
    }
}
