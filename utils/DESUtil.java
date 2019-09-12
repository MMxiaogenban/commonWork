package com.nwd.algo.common.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
/**
 * DES加密和解密。
 * 
 * @author WJ
 * @date 2015-08-30
 */
public class DESUtil {
	
	public static void main(String[] args) {
		
		try {
			//解码密钥
			String password="Z*$Mcs8CcNd*Z0Ly#w$nOYSlV!kEhJKM";
			DESUtil d=new DESUtil(password);
			String mingwen=d.decrypt("CuaxeOT+9klIuHTvjqggsw==");			
			System.out.println(mingwen);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
    /** 安全密钥 */
    private String keyData ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCZklMmyKm122gYEyOdA9zqR7a3TIb8VyAI9U8M9qLXzK3j0V49UsyNM3Kwxj8Bw0OGKyx4hUVfgJzf8QyYxXOwguooQmX1bma7twGVhimGLBymxoxku6TbV8OTvoKD4OlByoPhfrO+MbM6O42XGvXBX+qrQctzdQAxy3kUziTxUQIDAQAB";
    /**
     * 功能：构造
     * 
	 * @author WJ
	 * @date 2015-08-30
     */
    public DESUtil() {
    }
 
    /**
     * 功能：构造
     * 
	 * @author WJ
	 * @date 2015-08-30
     * @param key
     */
    public DESUtil(String key) {
        this.keyData = key;
    }
 
    /**
     * 功能：加密 (UTF-8)
     * 
	 * @author WJ
	 * @date 2015-08-30
     * @param source
     *      源字符串
     * @return String
     * @throws UnsupportedEncodingException
     *       编码异常
     */
    public String encrypt(String source) throws UnsupportedEncodingException {
        return encrypt(source, "UTF-8");
    }
 
    /**
     * 
     * 功能：解密 (UTF-8)
     * 
	 * @author WJ
	 * @date 2015-08-30
     * @param encryptedData
     *      被加密后的字符串
     * @return String
     * @throws UnsupportedEncodingException
     *       编码异常
     */
    public String decrypt(String encryptedData)
            throws UnsupportedEncodingException {
        return decrypt(encryptedData, "UTF-8");
    }
 
    /**
     * 功能：加密
     * 
	 * @author WJ
	 * @date 2015-08-30
     * @param source
     *      源字符串
     * @param charSet
     *      编码
     * @return String
     * @throws UnsupportedEncodingException
     *       编码异常
     */
    public String encrypt(String source, String charSet)
            throws UnsupportedEncodingException {
        String encrypt = null;
        byte[] ret = encrypt(source.getBytes(charSet));
        encrypt = new String(Base64.encodeBase64(ret));
        return encrypt;
    }
 
    /**
     * 
     * 功能：解密
     * 
	 * @author WJ
	 * @date 2015-08-30
     * @param encryptedData
     *      被加密后的字符串
     * @param charSet
     *      编码
     * @return String
     * @throws UnsupportedEncodingException
     *       编码异常
     */
    public String decrypt(String encryptedData, String charSet)
            throws UnsupportedEncodingException {
        String descryptedData = null;
        byte[] ret = descrypt(Base64.decodeBase64(encryptedData));
        descryptedData = new String(ret, charSet);
        return descryptedData;
    }
 
    /**
     * 加密数据 用生成的密钥加密原始数据
     * 
     * @param primaryData
     *      原始数据
     * @return byte[]
	 * @author WJ
	 * @date 2015-08-30
     */
    private byte[] encrypt(byte[] primaryData) {
 
        /** 取得安全密钥 */
 
        /** DES算法要求有一个可信任的随机数源 */
        SecureRandom sr = new SecureRandom();
 
        /** 使用原始密钥数据创建DESKeySpec对象 */
        DESKeySpec dks = null;
        try {
            dks = new DESKeySpec(keyData.getBytes());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
 
        /** 创建一个密钥工厂 */
        SecretKeyFactory keyFactory = null;
        try {
            keyFactory = SecretKeyFactory.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
 
        /** 用密钥工厂把DESKeySpec转换成一个SecretKey对象 */
        SecretKey key = null;
        try {
            key = keyFactory.generateSecret(dks);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
 
        /** Cipher对象实际完成加密操作 */
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
 
        /** 用密钥初始化Cipher对象 */
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, sr);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
 
        /** 正式执行加密操作 */
        byte encryptedData[] = null;
        try {
            encryptedData = cipher.doFinal(primaryData);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
 
        /** 返回加密数据 */
        return encryptedData;
    }
 
    /**
     * 用密钥解密数据
     * 
     * @param encryptedData
     *      加密后的数据
     * @return byte[]
	 * @author WJ
	 * @date 2015-08-30
     */
    private byte[] descrypt(byte[] encryptedData) {
 
        /** DES算法要求有一个可信任的随机数源 */
        SecureRandom sr = new SecureRandom();
 
        /** 取得安全密钥 */
 
        /** 使用原始密钥数据创建DESKeySpec对象 */
        DESKeySpec dks = null;
        try {
            dks = new DESKeySpec(keyData.getBytes());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
 
        /** 创建一个密钥工厂 */
        SecretKeyFactory keyFactory = null;
        try {
            keyFactory = SecretKeyFactory.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
 
        /** 用密钥工厂把DESKeySpec转换成一个SecretKey对象 */
        SecretKey key = null;
        try {
            key = keyFactory.generateSecret(dks);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
 
        /** Cipher对象实际完成加密操作 */
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
 
        /** 用密钥初始化Cipher对象 */
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, sr);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
 
        /** 正式执行解密操作 */
        byte decryptedData[] = null;
        try {
            decryptedData = cipher.doFinal(encryptedData);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
 
        return decryptedData;
    }

 
}