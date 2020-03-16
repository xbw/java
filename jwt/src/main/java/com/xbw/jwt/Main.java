package com.xbw.jwt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.xbw.jwt.common.CheckResult;
import com.xbw.jwt.jjwt.JjwtUtils;

import io.jsonwebtoken.SignatureAlgorithm;

public class Main {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, InterruptedException {

		print(JjwtUtils.class, null);
		print(JjwtUtils.class, SignatureAlgorithm.RS256);
//		print(JjwtUtils091.class, null);
//		print(JjwtUtils091.class, SignatureAlgorithm.RS256);
	}

	public static void print(Class<?> clazz, SignatureAlgorithm alg) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException {
//		Method[] methods = clazz.getMethods();
//		for (Method m : methods) {
//			System.out.println(m);
//		}

		Method method = clazz.getMethod("init", SignatureAlgorithm.class);
		method.invoke(clazz.getClass(), alg);
		long ttlMillis = 3000l;
		method = clazz.getMethod("createJWT", String.class, String.class, long.class);
		String sc = (String) method.invoke(clazz.getClass(), "1", "{'name':'abc','age':123}", ttlMillis);
		System.out.println(sc);

		method = clazz.getMethod("validateJWT", String.class);
		CheckResult checkResult = (CheckResult) method.invoke(clazz.getClass(), sc);
		System.out.println(checkResult.getErrCode());
		if (checkResult.isSuccess()) {
			System.out.println(checkResult.getClaims().getSubject());
		}

		Thread.sleep(ttlMillis);
		checkResult = (CheckResult) method.invoke(clazz.getClass(), sc);
		System.out.println(checkResult.getErrCode());
	}

}
