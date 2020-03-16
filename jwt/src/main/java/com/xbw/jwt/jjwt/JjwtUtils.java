package com.xbw.jwt.jjwt;

import java.security.Key;
import java.security.KeyPair;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import com.xbw.jwt.common.CheckResult;
import com.xbw.jwt.common.Constant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JjwtUtils {
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
				.signWith(key, signatureAlgorithm); // 签名算法以及密钥
//				.signWith(signatureAlgorithm, key); // 签名算法以及密钥

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

		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
//		return Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
	}

	public static void init(SignatureAlgorithm alg) {
		if (null != alg) {
			signatureAlgorithm = alg;
		}
		String algValue = signatureAlgorithm.getValue();
		System.out.println(algValue);

		if ("RS256".equalsIgnoreCase(algValue)) {
			keyPair = Keys.keyPairFor(signatureAlgorithm);
		} else if ("HS256".equalsIgnoreCase(algValue)) {
			key = new SecretKeySpec(Constant.JWT_SECERT.getBytes(), "HmacSHA256");
			key = Keys.secretKeyFor(signatureAlgorithm);
			key = Keys.hmacShaKeyFor(Constant.JWT_SECERT.getBytes());
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
