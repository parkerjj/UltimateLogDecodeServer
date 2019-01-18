package com.starstar.decode;


import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;

public class DecryptUtility {

    public static byte[] getDecodeKey(String seed){
        if (seed == null)
            return null;
        String password = (DigestUtils.sha256Hex(seed) + seed).toUpperCase();
        byte[] pByte = DigestUtils.sha256(password.toUpperCase().getBytes(StandardCharsets.UTF_8));
        String originKey = DigestUtils.sha256Hex(seed).toUpperCase();
        originKey = originKey.toUpperCase();

        String eKey = AESEncryptor.encryptWithKey(originKey, pByte);

        return DigestUtils.sha256(eKey.getBytes(StandardCharsets.UTF_8));
    }


    public static String encryptFile(String eStr, byte[] key) throws Exception{
        if (key == null || key.length == 0)
            return eStr;


        try {
            StringBuffer stringBuffer = new StringBuffer();
            String[] lines = eStr.split(System.getProperty("line.separator"));
            for (String line : lines) {
                int rangeStart  = line.indexOf("[[[")+3;
                int rangeEnd    = line.indexOf("]]]");
                if (rangeStart == 0 || rangeStart > rangeEnd){
                    stringBuffer.append(line + "\n<br>");
                    continue;
                }
                String encryptStr = line.substring(line.indexOf("[[[")+3, line.indexOf("]]]"));
                String decryptStr = AESEncryptor.decryptWithKey(encryptStr, key);

                stringBuffer.append(line.substring(0, line.indexOf("[[[")));
                stringBuffer.append(decryptStr + "\n<br>");


            }


            return stringBuffer.toString();
        }catch (Exception e){
            throw e;
        }

    }



}
