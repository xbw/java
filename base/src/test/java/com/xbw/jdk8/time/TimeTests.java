package com.xbw.jdk8.time;

import com.xbw.base.time.DateUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author xbw
 */
class TimeTests {

    @Test
    void day() {
        System.out.println(DateUtil.startOfDay(new Date()));
        System.out.println(DateUtil.endOfDay(new Date()));
    }

    @Test
    void month() {
        System.out.println(DateUtil.firstDayOfMonth(new Date()));
        System.out.println(DateUtil.lastDayOfMonth(new Date()));
    }

    @Test
    void formatter() {
        System.out.println(DateUtil.TIME_FORMATTER.format(DateUtil.nextDay(new Date())));
        System.out.println(DateUtil.DATE_FORMATTER.format(DateUtil.endOfDay("2012-12-12 12:12:12", false)));
        System.out.println(DateUtil.TIME_FORMATTER.format(DateUtil.endOfDay(System.currentTimeMillis())));
    }
}
