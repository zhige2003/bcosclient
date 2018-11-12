/*
 * @(#)EncryptUtil.java	1.00 2014-9-19
 * 
 * Team: IDP
 * Department: Media Service Product Department
 * Copyright (C) 2014 EASTCOM-BUPT Information Technology Co.,Ltd. All rights reserved.
 * Use is subject to license terms. 
 */
package com.block.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;


/**
 * @author ChenJie
 * 
 */
@SuppressWarnings("restriction")
public class EncryptUtil {
	
	// �㷨����
	final static String KEY_ALGORITHM = "AES";
	// �ӽ����㷨/ģʽ/��䷽ʽ
	final static String algorithmStr = "AES/CBC/PKCS7Padding";
	// �ӽ��� ��Կ
	static String secret = "20170705161101aa";
	// �ӽ��� ��ʼ������
	static String iv = "c8a1f5165ae04afa";
	
	private static Key key;
	private static Cipher cipher;
	
	public static void init(byte[] keyBytes) {
		// �����Կ����16λ����ô�Ͳ���.  ���if �е����ݺ���Ҫ
		int base = 16;
		if (keyBytes.length % base != 0) {
			int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
			byte[] temp = new byte[groups * base];
			Arrays.fill(temp, (byte) 0);
			System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
			keyBytes = temp;
		}
		System.out.print("��Կ : " + new String(keyBytes));  
        /*//��ӡ��Կ  
        for (int i = 0; i < keyBytes.length; i++) {  
            System.out.printf("%x", keyBytes[i]);  
        } */
		// ��ʼ��
		Security.addProvider(new BouncyCastleProvider());
		// ת����JAVA����Կ��ʽ
		key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
		try {
			// ��ʼ��cipher
			cipher = Cipher.getInstance(algorithmStr, "BC");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
	}
	 
	/**
	 * ���ܷ���
	 *
	 * @param content
	 *            Ҫ���ܵ��ַ���
	 * @param keyBytes
	 *            ������Կ
	 * @return
	 */
	public static String encryptAES(byte[] content) {
		String encryptResult = null;
		init(secret.getBytes());
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv.getBytes()));
			byte[] encryptedText = cipher.doFinal(content);
			encryptResult = new String(Hex.encode(encryptedText));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptResult;
	}
	/**
	 * ���ܷ���
	 *
	 * @param encryptedData
	 *            Ҫ���ܵ��ַ���
	 * @param keyBytes
	 *            ������Կ
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String decryptAES(byte[] encryptedData) throws UnsupportedEncodingException {
		String decryptResult = null;
		init(secret.getBytes());
		try {
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv.getBytes()));
			byte[] decryptText = cipher.doFinal(encryptedData);
			decryptResult = new String(decryptText, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptResult;
	}
	
	public static String md5encrypt(String inputText) {
		if (inputText == null || "".equals(inputText.trim())) {
			throw new IllegalArgumentException("Please input encrypt text");
		}

		String encryptText = null;
		try {
			MessageDigest m = MessageDigest.getInstance("md5");
			m.update(inputText.getBytes("utf-8"));
			byte s[] = m.digest();
			return hex(s);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encryptText;
	}

	private static String hex(byte[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}
	
    /** 
     * base64���� 
     * @param str 
     * @return string 
     */  
	public static byte[] base64Decode(String str){  
	    byte[] bt = null;  
	    try {
	        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
	        bt = decoder.decodeBuffer(str); 
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }
       return bt; 
    }  
    
    /** 
     * @param args 
     */
	/*public static void main(String[] args) throws UnsupportedEncodingException {
        //String str1 = new sun.misc.BASE64Encoder().encode("���".getBytes());
        //System.out.println(str1);
        System.out.println(base64Decode("I4qQHjRGY4Gvuepym0sreQ=="));
		
	    //���ģ�secret��iv
	    String content = " 1as~!���������й���b.c4#()";
	    System.out.println("���ģ�" + content);
	    System.out.println("secret:" + secret + " iv:" + iv);
	    //���ܷ���
	    String enResult = encryptAES(content.getBytes());
	    System.out.println("\n���ܺ�����ݣ�" + enResult);
	    //���ܷ���
	    String deResult = decryptAES(base64Decode("I4qQHjRGY4Gvuepym0sreQ=="));
	    System.out.println("\n���ܺ�����ݣ�" + deResult);
    }*/

}
