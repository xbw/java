package com.xbw.base.time;

import org.joda.time.*;

import java.util.Date;
import java.util.Objects;

public class JodaUtil {
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        DateTimeZone.setDefault(DateTimeZone.forID("Asia/Shanghai"));
    }

    /**
     * 获取当前系统的时间（毫秒级）
     * @return
     */
    public static long getCurrentSecondMillis() {
        return DateTimeUtils.currentTimeMillis();
    }

    /**
     * 获取当天的开始时间
     * @return
     */
    public static long getStartOfDay() {
        return getStartOfDay(new Date());
    }

    /**
     * 获取某天的开始时间
     * @param date
     * @return
     */
    public static long getStartOfDay(Date date) {
        DateTime dateTime = new DateTime(date);
        DateTime startOfDay = dateTime.withTimeAtStartOfDay();
        return startOfDay.getMillis();
    }

    /**
     * 获取当天的结束时间
     * @return
     */
    public static long getEndOfDay() {
        return getEndOfDay(new Date());
    }

    /**
     * 获取某天的结束时间
     * @param date
     * @return
     */
    public static long getEndOfDay(Date date) {
        DateTime dateTime = new DateTime(date);
        DateTime endOfDay = dateTime.millisOfDay().withMaximumValue();
        return endOfDay.getMillis();
    }

    /**
     * 获取现在距离今天结束还有多长时间
     * @return
     */
    public static long endOfToday() {
        DateTime nowTime = new DateTime();
        DateTime endOfDay = nowTime.millisOfDay().withMaximumValue();
        return endOfDay.getMillis() - nowTime.getMillis();
    }

    /**
     * 计算两个日期的相隔天数
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getBetweenDay(long startTime, long endTime) {
        DateTime startDay = new DateTime(startTime);
        DateTime endDay = new DateTime(endTime);
        return Days.daysBetween(startDay, endDay).getDays();
    }

    /**
     * 对比两个时间是否同一天
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isEqualsSameDay(long startTime, long endTime) {
        LocalDate startDay = new LocalDate(startTime);
        LocalDate endDay = new LocalDate(endTime);
        return startDay.equals(endDay);
    }

    /**
     * 生成指定的时间
     * @param year  年
     * @param month 月
     * @param day   日
     * @param hour  时
     * @param min   分
     * @param sec   秒
     * @param msec  毫秒
     * @return
     */
    public static Date generateDate(int year, int month, int day, int hour, int min, int sec, int msec) {
        DateTime dt = new DateTime(year, month, day, hour, min, sec, msec);
        return dt.toDate();
    }

    public static Date generateDate(int year, int month, int day, int hour, int min, int sec) {
        DateTime dt = new DateTime(year, month, day, hour, min, sec);
        return dt.toDate();
    }

    public static Date generateDate(int year, int month, int day) {
        LocalDate localDate = new LocalDate(year, month, day);
        return localDate.toDate();
    }

    public static Date generateDateTime(int hour, int min, int sec, int msec) {
        LocalTime localTime = new LocalTime(hour, min, sec, msec);
        return localTime.toDateTimeToday().toDate();
    }

    public static Date generateDateTime(int hour, int min, int sec) {
        LocalTime localTime = new LocalTime(hour, min, sec);
        return localTime.toDateTimeToday().toDate();
    }

    /**
     * 格式化日期，转化为string
     * @param date
     * @param format
     * @return
     */
    public static String formatDateToString(Date date, String format) {
        if (Objects.isNull(format)) {
            format = DEFAULT_FORMAT;
        }
        DateTime dt = new DateTime(date);
        return dt.toString(format);
    }

    public static String formatDateToString(long time, String format) {
        if (Objects.isNull(format)) {
            format = DEFAULT_FORMAT;
        }
        DateTime dt = new DateTime(time);
        return dt.toString(format);
    }

    /**
     * 格式化日期，转化为string（默认为：yyyy-MM-dd HH:mm:ss）
     * @param date
     * @return
     */
    public static String formatDateToString(Date date) {
        return formatDateToString(date, DEFAULT_FORMAT);
    }

    public static String formatDateToString(long time) {
        return formatDateToString(time, DEFAULT_FORMAT);
    }

}