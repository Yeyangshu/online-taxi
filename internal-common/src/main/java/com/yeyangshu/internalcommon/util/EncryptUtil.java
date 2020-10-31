package com.yeyangshu.internalcommon.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 加密工具类
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 10:24
 */
@Slf4j
public class EncryptUtil {

    /**
     * 密钥，8 lengths
     */
    private static final String KEY = "eVvz2^Bd";

    /**
     * 加密数据
     *
     * @param message 明文
     * @return 密文
     */
    public static byte[] encrypt(String message) {
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(KEY.getBytes(StandardCharsets.UTF_8));
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(KEY.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                InvalidKeySpecException | InvalidAlgorithmParameterException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * 解密数据
     *
     * @param message 密文
     * @return 明文
     */
    public static String decrypt(String message) {
        try {
            byte[] byteSrc = convertHexString(message);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(KEY.getBytes(StandardCharsets.UTF_8));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(KEY.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] retByte = cipher.doFinal(byteSrc);
            return new String(retByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static byte[] convertHexString(String ss) {
        byte[] digest = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }
        return digest;
    }

    public static String toHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (byte value : b) {
            String plainText = Integer.toHexString(0xff & value);
            if (plainText.length() < 2) {
                plainText = "0" + plainText;
            }
            hexString.append(plainText);
        }
        return hexString.toString();
    }

    /**
     * 手机号加密
     *
     * @param phoneNumber 明文手机号
     * @return 密文手机号
     */
    public static String encryptionPhoneNumber(String phoneNumber) {
        log.info("手机号===" + phoneNumber);
        String passengerPhoneNum = null;
        try {
            String phoneNumbers = java.net.URLEncoder.encode(phoneNumber, "utf-8").toLowerCase();
            passengerPhoneNum = toHexString(EncryptUtil.encrypt(phoneNumbers)).toUpperCase();
            log.info("加密手机号===" + passengerPhoneNum);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return passengerPhoneNum;
    }

    /**
     * 手机号解密
     *
     * @param phoneNumber 手机号密文
     * @return 手机号
     */
    public static String decryptionPhoneNumber(String phoneNumber) {
        log.info("加密的手机号===" + phoneNumber);
        String phoneNumbers = null;
        try {
            phoneNumbers = java.net.URLDecoder.decode(decrypt(phoneNumber), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("解密后的手机号===" + phoneNumbers);
        return phoneNumbers;
    }


    public static void main(String[] args) {
        System.out.println(toHexString(encrypt("11111111111")).toUpperCase());
        System.out.println(decrypt("5D5209F55A41F7970CD777B0F9E094AC"));


        System.out.println(encryptionPhoneNumber("11111111111"));
        String phone = "5D5209F55A41F7970CD777B0F9E094AC";
        String newPhone = decryptionPhoneNumber(phone);
        System.out.println("===" + newPhone);
    }


}
