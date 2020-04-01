package com.xbw.jwt.nimbusjosejwt;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.xbw.jwt.common.Constant;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class JwtUtils {
	private static JWSAlgorithm jwsAlgorithm = JWSAlgorithm.HS256;
	private static RSAKey rsaJWK;

	/**
	 * 生成一个token
	 * 
	 * @param sub
	 * @return
	 * @throws JOSEException
	 */
	public static JWSObject creatToken(String sub, long ttlMillis) {
		Map<String, Object> payloadMap = new HashMap<>();
		// 签发者
		payloadMap.put("iss", Constant.JWT_ISSUER);

		payloadMap.put("sub", sub);

		// 生成时间
		long nowMillis = System.currentTimeMillis();
		payloadMap.put("iat", nowMillis);

		// 过期时间 token失效
		if (ttlMillis >= 0) {
			payloadMap.put("exp", nowMillis + ttlMillis);
		}
		JWSObject jwsObject = null;
		try {
			// 先建立一个头部Header
			JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
			// 建立一个载荷Payload
			Payload payload = new Payload(new JSONObject(payloadMap));

			// 建立一个密钥
			JWSSigner jwsSigner = new MACSigner(Constant.JWT_SECERT.getBytes());

			if ("RS256".equalsIgnoreCase(jwsAlgorithm.getName())) {
//				RSAKey rsaPublicJWK = rsaJWK.toPublicJWK();
//				System.out.println(rsaPublicJWK.toJSONString());
				jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaJWK.getKeyID()).build();
				jwsSigner = new RSASSASigner(rsaJWK);
			}

			// 将头部和载荷结合在一起
			jwsObject = new JWSObject(jwsHeader, payload);

			// 签名
			jwsObject.sign(jwsSigner);
		} catch (KeyLengthException e) {
			e.printStackTrace();
		} catch (JOSEException e) {
			e.printStackTrace();
		}
		// 生成token
		return jwsObject;
	}

	public static Map<String, Object> validateJWT(String token) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			JWSObject jwsObject = JWSObject.parse(token);
			// 获取到载荷
			Payload payload = jwsObject.getPayload();

			// 建立一个解锁密钥
			JWSVerifier jwsVerifier = new MACVerifier(Constant.JWT_SECERT.getBytes());
			if ("RS256".equalsIgnoreCase(jwsAlgorithm.getName())) {
				RSAKey rsaPublicJWK = rsaJWK.toPublicJWK();
//				System.out.println(rsaPublicJWK.toJSONString());
				jwsVerifier = new RSASSAVerifier(rsaPublicJWK);
			}
			// 判断token
			if (jwsObject.verify(jwsVerifier)) {
				resultMap.put("mgs", "{'success':1,'message':'token正常'}");
				// 载荷的数据解析成json对象。
				JSONObject jsonObject = payload.toJSONObject();
				resultMap.put("data", jsonObject);

				// 判断token是否过期
				if (jsonObject.containsKey("exp")) {
					Long expTime = (Long) jsonObject.get("exp");
					Long nowTime = System.currentTimeMillis();
					// 判断是否过期
					if (nowTime > expTime) {
						// 已经过期
						resultMap.clear();
						resultMap.put("mgs", "{'success':0,'message':'token过期'}");
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (JOSEException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	public static void init(JWSAlgorithm alg) {
		if (null != alg) {
			jwsAlgorithm = alg;
		}
		String algValue = jwsAlgorithm.getName();
		System.out.println(algValue);
		if ("RS256".equalsIgnoreCase(jwsAlgorithm.getName())) {
			try {
				rsaJWK = new RSAKeyGenerator(2048).keyID(Constant.JWT_ISSUER).generate();
			} catch (JOSEException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws InterruptedException {
		JWSAlgorithm jwsAlgorithm = JWSAlgorithm.RS256;
		jwsAlgorithm = JWSAlgorithm.HS256;
		init(jwsAlgorithm);

		long ttlMillis = 1000 * 3l;// 过期时间 3秒token失效
		String sub = "admin";
		JWSObject jwsObject = creatToken(sub, ttlMillis);
		String token = jwsObject.serialize();
		System.out.println(token);
		Map<String, Object> resultMap = validateJWT(token);
		System.out.println((String) resultMap.get("mgs"));
		Map<?, ?> map = (Map<?, ?>) JSONValue.parse((String) resultMap.get("mgs"));
		if ((int) map.get("success") == 1) {
			JSONObject jsonObject = (JSONObject) resultMap.get("data");
			System.out.println(jsonObject.get("sub"));
		}
		Thread.sleep(ttlMillis);
		resultMap = validateJWT(token);
		System.out.println(resultMap.get("mgs"));
	}
}