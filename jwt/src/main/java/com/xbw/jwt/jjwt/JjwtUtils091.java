package com.xbw.jwt.jjwt;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.xbw.jwt.common.CheckResult;
import com.xbw.jwt.common.Constant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JjwtUtils091 {
	private static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	private static KeyPair keyPair;
	private static Key key;

	/**
	 * 签发JWT
	 *
	 * @param id
	 * @param subject   可以是JSON数据 尽可能少
	 * @param ttlMillis
	 * @return String
	 */
	public static String createJWT(String id, String subject, long ttlMillis) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		if ("RS256".equalsIgnoreCase(signatureAlgorithm.getValue())) {
			key = keyPair.getPrivate();
		}
		String keyBase64 = Base64.getEncoder().encodeToString(key.getEncoded());
		System.out.println("createJWT keyBase64.length():" + keyBase64.length());
		System.out.println("createJWT keyBase64:" + keyBase64);

		JwtBuilder builder = Jwts.builder().setId(id).setSubject(subject) // 主题
				.setIssuer(Constant.JWT_ISSUER) // 签发者
				.setIssuedAt(now) // 签发时间
				.signWith(signatureAlgorithm, key); // 签名算法以及密钥

		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date expDate = new Date(expMillis);
			builder.setExpiration(expDate); // 过期时间
		}

		return builder.compact();
	}

	/**
	 * 验证JWT，并返回结果
	 *
	 * @param jwtStr
	 * @return
	 */
	public static CheckResult validateJWT(String jwtStr) {
		CheckResult checkResult = new CheckResult();
		try {
			Claims claims = parseJWT(jwtStr);
			checkResult.setSuccess(true);
			checkResult.setClaims(claims);
		} catch (ExpiredJwtException e) {
			checkResult.setErrCode(101);
			checkResult.setSuccess(false);
		} catch (SignatureException e) {
			checkResult.setErrCode(102);
			checkResult.setSuccess(false);
		} catch (Exception e) {
			checkResult.setErrCode(103);
			checkResult.setSuccess(false);
		}
		return checkResult;
	}

	/**
	 * 解析JWT字符串
	 *
	 * @param jwt
	 * @return
	 * @throws Exception
	 */
	public static Claims parseJWT(String jwt) throws Exception {
		if ("RS256".equalsIgnoreCase(signatureAlgorithm.getValue())) {
			key = keyPair.getPublic();
		}
		String keyBase64 = Base64.getEncoder().encodeToString(key.getEncoded());
		System.out.println("parseJWT keyBase64.length():" + keyBase64.length());
		System.out.println("parseJWT keyBase64:" + keyBase64);

		return Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
	}

	public static KeyPair genKeyPair() {
		signatureAlgorithm = SignatureAlgorithm.RS256;
		KeyPair keyPair = null;
		try {
			// RSA算法要求有一个可信任的随机数源
			SecureRandom secureRandom = new SecureRandom();
			// 为RSA算法创建一个KeyPairGenerator对象
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			// 利用上面的随机数据源初始化这个KeyPairGenerator对象
			keyPairGenerator.initialize(2048, secureRandom);
			// keyPairGenerator.initialize(2048);
			keyPair = keyPairGenerator.genKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return keyPair;
	}

	public static SecretKey generalKey() {
		signatureAlgorithm = SignatureAlgorithm.HS256;
		// jjwt 0.9.1以上版本，会提示
		// io.jsonwebtoken.security.InvalidKeyException:
		// The signing key's algorithm 'AES' does not equal a valid HmacSHA* algorithm
		// name and cannot be used with HS256.
		SecretKey key = new SecretKeySpec(Constant.JWT_SECERT_SHORT.getBytes(), "AES");
		return key;
	}

	public static void init(SignatureAlgorithm alg) {
		if (null != alg) {
			signatureAlgorithm = alg;
		}
		String algValue = signatureAlgorithm.getValue();
		System.out.println(algValue);

		if ("RS256".equalsIgnoreCase(algValue)) {
			keyPair = genKeyPair();
		} else if ("HS256".equalsIgnoreCase(algValue)) {
			key = generalKey();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;
		signatureAlgorithm = SignatureAlgorithm.HS256;
		init(signatureAlgorithm);

		long ttlMillis = 3000l;
		String sc = createJWT("1", "{'name':'abc','age':123}", ttlMillis);
		System.out.println(sc);

		CheckResult checkResult = validateJWT(sc);
		System.out.println(checkResult.getErrCode());
		if (checkResult.isSuccess()) {
			System.out.println(checkResult.getClaims().getSubject());
		}

		Thread.sleep(ttlMillis);
		checkResult = validateJWT(sc);
		System.out.println(checkResult.getErrCode());

	}
}
