package com.xbw.jwt.jose4j;

import org.jose4j.json.JsonUtil;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
//import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.UUID;

//@Service
public class AuthorizationService {

	/**
	 * keyId,公钥,私钥 都是用 createKeyPair 方法生成
	 */
	private static String keyId = "f8d1ac8ffb66409e819bff99f256244e";
	private static String privateKeyStr = "{\"kty\":\"RSA\",\"kid\":\"f8d1ac8ffb66409e819bff99f256244e\",\"alg\":\"RS256\",\"n\":\"hkQpYrvV-IATUfibtkyt_7q_7M0shEDfOp06c-3nmNJdyzblQO3xd0cohkhbDsi8jeXTveYnzZCJINWG_WGOxPXr_5PgnFLhq8DCNetsarB_8ZCjZ-Tgp6f4mFHe2hHkyJlsnVY_U5_hR0QwfJE5Q7yHoNSM368QFtynst-ycToE654TjwZwgyyLIjlYPz-foHGOzAtYvnpuZH3ghAQwq1cKnyWrvHe2eRUK3ZYthB5zzFrbNosYucMMEowWpmZqEohdRlyud_IHimKJ-8KghGVkHAkO9Crn4c9nqBb87w6SrvLcmnEhAcYYLhvbNJ5y5trA5VQrymi2Pa0ZwRO9YQ\",\"e\":\"AQAB\",\"d\":\"DmgR6L4u31Mev5TlYFIp-V6YaVg6hpZPXYKxWu8UApnP5pYlUyo01o-UEZSB8LwpPzCB38ZqTuxLSnKe4WEuaIPaprm_SAa8oCk2rYJtR6VYlRrn7aXpVRP44J8oQk3ZNoz_0oIGmXp2OadfbH5InrQD-YXU98tY9psGg3LtOqjPCYQ-hrUkpr27mdXPLPnc4GQKAf0yThP9_fX3tq9v_FrvglzdbET32oOWtpKiW2Y43PrW0zxxbkwoE7SuX94UK_SvB0nViq7fr28h_LMAAk4ipH0tJZEP3p4d8oguN9b38mEGv6k76E7eWsFRcgDqtYEX9ntT7TDk5_448KlCgQ\",\"p\":\"zo_RpMRpVsSpWf3chvalpvrnQGEDuZa40J3YXVu2b7dGAos5Wfb1_KLhACXZgVMQXizmxS9JyBLrM6jZo9SWCJToDlN4rKfvlqu4D_ICCQE0BGaLQTUCk4qcOZw2BJi9nqval1EsOutAMIl_nfaKrT7P08yXnlfOiKaGKzISYW0\",\"q\":\"pma_CL_-1b5s-PLM6Hs9ShyfxVDY3fGlzOLArqyOv6Z9hf3rvqdsBikU1aIi3TEIr90Pws3DCcfXUnKNYqLDIMLpAn_RxjwwqW6U34qXEHmmRbFrr_C7yk30GxeYtxlCG5JZd6VNNv8fb-JdeD5IUKKpc_TLUW7uj7S-oktbh0U\",\"dp\":\"UetZojW-7QlrfGQgfGn_Aj0JT0_qUUUEIMAo2cWQkbemjUW3xgUaQ0o5X3yjSRWvLRozn0oEwdM28jLptX6OzfJ4IY5bEmY7r46EndZFllqizYf5fC_QyMC0-mgISERnFzFirJYlq2w29cjiErx-_PwnIgFGtCZwo2MfXKwr-_U\",\"dq\":\"ayXN7Px0Q34S_VqaxzhUBtzXpF6ixodLk9qO5-sFIDM8sIMIwgNcc7sQR1_Xw0ZHqbZW58FGzY15AQXyNIlJzFa1Y36avWMUkoU6J5c-HsW2YzoTdX817Na1UJypIX2KK-bY5Unzpm4w-AJy3XJ9bi7PnnMKWK1tv-tKtYaAsWE\",\"qi\":\"Cs6_cC2Xm0oLww-aYvXumZ7HrPbIYSPUpj3_bOaZctSQO4GMwb7kqDpig5iRNxO8ae-QVlHnqaN6e-nuu6illixPXF3ZNKBXNGr8LRnFaIu332gUnEOuX3Nu0iSa2ay8rvCqc77namkVLNZFrSmVqZsy8ForLGxhTUN8i0Jis-A\"}";
	private static String publicKeyStr = "{\"kty\":\"RSA\",\"kid\":\"f8d1ac8ffb66409e819bff99f256244e\",\"alg\":\"RS256\",\"n\":\"hkQpYrvV-IATUfibtkyt_7q_7M0shEDfOp06c-3nmNJdyzblQO3xd0cohkhbDsi8jeXTveYnzZCJINWG_WGOxPXr_5PgnFLhq8DCNetsarB_8ZCjZ-Tgp6f4mFHe2hHkyJlsnVY_U5_hR0QwfJE5Q7yHoNSM368QFtynst-ycToE654TjwZwgyyLIjlYPz-foHGOzAtYvnpuZH3ghAQwq1cKnyWrvHe2eRUK3ZYthB5zzFrbNosYucMMEowWpmZqEohdRlyud_IHimKJ-8KghGVkHAkO9Crn4c9nqBb87w6SrvLcmnEhAcYYLhvbNJ5y5trA5VQrymi2Pa0ZwRO9YQ\",\"e\":\"AQAB\"}";

	public static long accessTokenExpirationTime = 60 * 60 * 24;

	// jws创建token
	public String createToken(String account) {
		try {
			// Payload
			JwtClaims claims = new JwtClaims();
			claims.setGeneratedJwtId();
			claims.setIssuedAtToNow();
			// expire time
			NumericDate date = NumericDate.now();
			date.addSeconds(accessTokenExpirationTime);
			claims.setExpirationTime(date);
			claims.setNotBeforeMinutesInThePast(1);
			claims.setSubject("YOUR_SUBJECT");
			claims.setAudience("YOUR_AUDIENCE");
			// 添加自定义参数,必须是字符串类型
			claims.setClaim("account", account);

			// jws
			JsonWebSignature jws = new JsonWebSignature();
			// 签名算法RS256
			jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
			jws.setKeyIdHeaderValue(keyId);
			jws.setPayload(claims.toJson());
			/*
			 * RsaJsonWebKey jwk = null; try { jwk = RsaJwkGenerator.generateJwk(2048); }
			 * catch (JoseException e) { e.printStackTrace(); } jwk.setKeyId(keyId);
			 */
			// PrivateKey privateKey = jwk.getPrivateKey();
			PrivateKey privateKey = new RsaJsonWebKey(JsonUtil.parseJson(privateKeyStr)).getPrivateKey();
			jws.setKey(privateKey);

			// get token
			String idToken = jws.getCompactSerialization();
			return idToken;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * jws校验token
	 *
	 * @param token
	 * @return 返回 用户账号
	 * @throws JoseException
	 */
	public String verifyToken(String token) {
		try {
			JwtConsumer consumer = new JwtConsumerBuilder().setRequireExpirationTime()
					.setMaxFutureValidityInMinutes(5256000).setAllowedClockSkewInSeconds(30).setRequireSubject()
					// .setExpectedIssuer("")
					.setExpectedAudience("YOUR_AUDIENCE")
					/*
					 * RsaJsonWebKey jwk = null; try { jwk = RsaJwkGenerator.generateJwk(2048); }
					 * catch (JoseException e) { e.printStackTrace(); } jwk.setKeyId(keyId);
					 */
					// .setVerificationKey(jwk.getPublicKey())
					.setVerificationKey(new RsaJsonWebKey(JsonUtil.parseJson(publicKeyStr)).getPublicKey()).build();

			JwtClaims claims = consumer.processToClaims(token);
			if (claims != null) {
				System.out.println("认证通过！");
				String account = (String) claims.getClaimValue("account");
				System.out.println("token payload携带的自定义内容:用户账号account=" + account);
				return account;
			}
		} catch (JoseException e) {
			e.printStackTrace();
		} catch (InvalidJwtException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 创建jwk keyId , 公钥 ，秘钥
	 */
	public static void createKeyPair() {
		String keyId = UUID.randomUUID().toString().replaceAll("-", "");
		RsaJsonWebKey jwk = null;
		try {
			jwk = RsaJwkGenerator.generateJwk(2048);
		} catch (JoseException e) {
			e.printStackTrace();
		}
		jwk.setKeyId(keyId);
		// 采用的签名算法 RS256
		jwk.setAlgorithm(AlgorithmIdentifiers.RSA_USING_SHA256);
		String publicKey = jwk.toJson(RsaJsonWebKey.OutputControlLevel.PUBLIC_ONLY);
		String privateKey = jwk.toJson(RsaJsonWebKey.OutputControlLevel.INCLUDE_PRIVATE);

		System.out.println("keyId=" + keyId);
		System.out.println();
		System.out.println("公钥 publicKeyStr=" + publicKey);
		System.out.println();
		System.out.println("私钥 privateKeyStr=" + privateKey);
	}

	public static void main(String[] args) {
		createKeyPair();
	}
}
