package com.xbw.jdk8.time;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author xbw
 */
class JodaTests {
    @Test
    void dateTime() {
        DateTime dateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2019-12-31 23:59:59");
        assertEquals(1577807999000L, dateTime.getMillis());
        assertEquals(2019, dateTime.getYear());
        assertEquals(19, dateTime.getYearOfCentury());
        assertEquals(2020, dateTime.getWeekyear());
        assertEquals("星期二", dateTime.dayOfWeek().getAsShortText());
        assertEquals(2, dateTime.getDayOfWeek());
        assertEquals(31, dateTime.getDayOfMonth());
        assertEquals(365, dateTime.getDayOfYear());
        assertEquals("2020-04-08 23:59:59", dateTime.plusDays(99).toString("yyyy-MM-dd HH:mm:ss"));
        assertEquals("2019-09-23", dateTime.plusDays(-99).toString("yyyy-MM-dd"));
        assertEquals("2019-09-23", dateTime.minusDays(99).toString("yyyy-MM-dd"));
        assertEquals(12, dateTime.getMonthOfYear());
    }

    @Test
    void instant() {
        Instant instant = Instant.now();
        DateTime dateTime = new DateTime(instant.getMillis());
        System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));
        System.out.println("getYear(): " + dateTime.getYear());
        System.out.println("getYearOfCentury(): " + dateTime.getYearOfCentury());
        System.out.println("getWeekyear(): " + dateTime.getWeekyear());
        System.out.println("dayOfWeek(): " + dateTime.dayOfWeek().getAsShortText());
        System.out.println("getDayOfWeek(): " + dateTime.getDayOfWeek());
        System.out.println("getDayOfMonth(): " + dateTime.getDayOfMonth());
        System.out.println("getDayOfYear(): " + dateTime.getDayOfYear());
        System.out.println("plusDays(99): " + dateTime.plusDays(99).toString("yyyy-MM-dd HH:mm:ss"));
        System.out.println("plusDays(-99): " + dateTime.plusDays(-99).toString("yyyy-MM-dd"));
        System.out.println("minusDays(99): " + dateTime.minusDays(99).toString("yyyy-MM-dd"));
        System.out.println("getMonthOfYear(): " + dateTime.getMonthOfYear());
    }
}
