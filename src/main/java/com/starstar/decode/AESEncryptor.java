package com.starstar.decode;


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * Created by Parker on 15/7/2.
 * This is property of IO Future Tech Corporation
 * Copy or send any of property of the corporation may reserves the right to legal proceedings
 */


public class AESEncryptor {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";





    /**
     * AES加密字符串
     *
     * @param content
     *            需要被加密的字符串
     * @return 密文
     */
    public static String encryptWithKey(String content , byte[] key) {
        try {
            byte[] contentData = content.getBytes(StandardCharsets.UTF_8);
            byte[] ivB = "ABCDEFGHIJKLMNOP".getBytes(StandardCharsets.UTF_8);
            IvParameterSpec iv = new IvParameterSpec(ivB);
            SecretKeySpec skeySpec = new SecretKeySpec(key , "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(contentData);
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }



    public static String decryptWithKey(String encrypted, byte[] key) throws Exception{
        try {
            byte[] ivB = "ABCDEFGHIJKLMNOP".getBytes(StandardCharsets.UTF_8);
            IvParameterSpec iv = new IvParameterSpec(ivB);
            SecretKeySpec skeySpec = new SecretKeySpec(key , "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] originEncrypted = Base64.decodeBase64(encrypted);
            byte[] original = cipher.doFinal(originEncrypted);

            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception ex) {

        }
        return encrypted;
    }

    public static byte[] SHA256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));

            return hash;
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }






}

