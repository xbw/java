package com.xbw.base.time;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

public class JodaDemo {

	public static void main(String[] args) {
		testLocalDate();
		testDateTime();
//		testLocalDate();
//		DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
//		System.out.println(dateTime.plusDays(90).toString("E MM/dd/yyyy HH:mm:ss.SSS"));
//		System.out.println(dateTime.plusDays(45).toString("E MM/dd/yyyy HH:mm:ss.SSS"));
//		System.out.println(dateTime.plusDays(45).plusMonths(1).toString("E MM/dd/yyyy HH:mm:ss.SSS"));
//		System.out.println(dateTime.plusDays(45).plusMonths(1).dayOfWeek().withMaximumValue()
//				.toString("E MM/dd/yyyy HH:mm:ss.SSS"));
	}

	public static void testDateTime() {
		Instant instant = Instant.now();
		System.out.println(instant.getMillis());
		DateTime dateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2019-12-31 23:59:59");
		dateTime = new DateTime(instant.getMillis());
		System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));
		System.out.println(dateTime.getWeekyear());
		System.out.println(dateTime.dayOfWeek().getAsShortText());
		System.out.println(dateTime.getDayOfWeek());
		System.out.println(dateTime.getDayOfMonth());
		System.out.println(dateTime.getDayOfYear());
		System.out.println(dateTime.plusDays(99).toString("yyyy-MM-dd HH:mm:ss"));
		System.out.println(dateTime.plusDays(-99).toString("yyyy-MM-dd"));
		System.out.println(dateTime.minusDays(99).toString("yyyy-MM-dd"));
	}

	public static void testLocalDate() {
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate.dayOfMonth().getAsShortText());
	}

	public static void testLocalTime() {
	}

	public static void testLocalDateTime() {
	}
}
