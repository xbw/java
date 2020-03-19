package com.xbw.base.regex;

public class RegexDemo {

	public static void main(String[] args) {
		String regex = "20\\d\\d";
		System.out.println("2019".matches(regex)); // true
		System.out.println("2100".matches(regex)); // false
	}
}
