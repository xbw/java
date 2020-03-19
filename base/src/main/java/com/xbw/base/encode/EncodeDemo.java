package com.xbw.base.encode;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;

public class EncodeDemo {

	public static void main(String[] args) {
		String str_zh_CN = "你好，世界！";
		String str_en = "Hello World!";

		testURL(str_zh_CN);

		testBase64(str_zh_CN);

		testHashCode(str_zh_CN);
		testHashCode(str_en);
		testHashCode("AaAaAa");// 0x7460e8c0
		testHashCode("BBAaBB");// 0x7460e8c0

		testMessageDigest(str_en, "MD2");
		testMessageDigest(str_en, "MD5");
		testMessageDigest(str_en, "SHA");
		testMessageDigest(str_en, "SHA-1");
		testMessageDigest(str_en, "SHA-224");
		testMessageDigest(str_en, "SHA-256");
		testMessageDigest(str_en, "SHA-384");
		testMessageDigest(str_en, "SHA-512");

		testKey("xbw", "DSA");
	}

	public static void testURL(String src) {
		System.out.println("=====testURL=====");
		try {
			String temp = URLEncoder.encode(src, StandardCharsets.UTF_8.name());
			System.out.println(temp);

			temp = URLDecoder.decode(temp, StandardCharsets.UTF_8.name());
			System.out.println(temp);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void testBase64(String src) {
		System.out.println("=====testBase64=====");
		byte[] temp = Base64.getEncoder().encode(src.getBytes());
//		System.out.println(Base64.getEncoder().withoutPadding().encodeToString(str.getBytes()));
//		System.out.println(Base64.getUrlEncoder().encodeToString(src.getBytes()));
		System.out.println(new String(temp));

		temp = Base64.getDecoder().decode(temp);
//		System.out.println(new String(Base64.getUrlDecoder().decode(Base64.getUrlEncoder().encodeToString(src.getBytes()))));
		System.out.println(new String(temp));

		src = new sun.misc.BASE64Encoder().encode(src.getBytes());
		System.out.println(src);
		try {
			temp = new sun.misc.BASE64Decoder().decodeBuffer(src);
			System.out.println(new String(temp));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testHashCode(String src) {
		System.out.println("=====testHashCode=====");
		System.out.println("src:" + src + ">>0x" + Integer.toHexString(src.hashCode()));// 0x8cd05dd0
	}

	public static void testMessageDigest(String src, String algorithm) {
		System.out.println("=====testMessageDigest=====");
		try {
			// 创建一个MessageDigest实例:
			MessageDigest md = MessageDigest.getInstance(algorithm);

			// 反复调用update输入数据:
			md.update(src.getBytes());
			byte[] result = md.digest(); // 16 bytes: 68e109f0f40ca72a15e05cc22786f8e6
			System.out.println("algorithm:" + algorithm + ">>" + new BigInteger(1, result).toString(16));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static void testKey(String src, String algorithm) {
		System.out.println("=====testKey=====");
		try {
			java.security.KeyPairGenerator keygen = java.security.KeyPairGenerator.getInstance(algorithm);

			// 如果设定随机产生器就用如相代码初始化
			SecureRandom secrand = new SecureRandom();
			secrand.setSeed(src.getBytes()); // 初始化随机产生器
			keygen.initialize(2048, secrand); // 初始化密钥生成器

			// 否则
//			keygen.initialize(2048);

			// 生成密钥公钥 pubkey 和私钥 prikey
			KeyPair keys = keygen.generateKeyPair(); // 生成密钥组
			PublicKey pubkey = keys.getPublic();
			PrivateKey prikey = keys.getPrivate();
			byte[] publicKeyBytes = pubkey.getEncoded();
			byte[] privateKeyBytes = prikey.getEncoded();

			String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKeyBytes);
			String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKeyBytes);

			System.out.println("publicKeyBase64.length():" + publicKeyBase64.length());
			System.out.println("publicKeyBase64:" + publicKeyBase64);

			System.out.println("privateKeyBase64.length():" + privateKeyBase64.length());
			System.out.println("privateKeyBase64:" + privateKeyBase64);

			// 分别保存在 myprikey.dat 和 mypubkey.dat 中 , 以便下次不在生成
			// 生成密钥对的时间比较长
			java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(
					new java.io.FileOutputStream("myprikey.dat"));
			out.writeObject(privateKeyBase64);
			out.close();

			out = new java.io.ObjectOutputStream(new java.io.FileOutputStream("mypubkey.dat"));
			out.writeObject(publicKeyBase64);
			out.close();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
