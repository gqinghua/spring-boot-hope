package com.data.hope.utils;

import com.data.hope.ReportUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 转换日期
 * @author guoqinghua
 * @since 2022-03-01
 */
public abstract class ReportDateUtils {

    /**
     * 日期转换为指定格式的字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String toString(Date date, String pattern) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期转换为指定格式的字符串
     * @param localDate
     * @param pattern
     * @return
     */
    public static String toString(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 把指定格式的字符串转换为LocalDate
     * @param dateString
     * @param pattern
     * @return
     */
    public static LocalDate fromString(String dateString, String pattern) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(pattern));
    }


    /**
     * LocalDate 转换为Date
     * @param localDate
     * @return
     */
    public static Date toDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * localDateTime 转换为Date
     * @param localDateTime
     * @return
     */
    public static Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * date 转换为LocalDate
     * @param date
     * @return
     */
    public static LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }



    /**
     * date 转换为LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }


    /**
     * 秒转天、时、分、秒
     * @param seconds
     * @return
     */
    public static String formatFromSecond(long seconds) {
        StringBuilder builder = new StringBuilder();

        long hourSeconds = 60 * 60;
        long daySeconds = hourSeconds * 24;

        //天
        long days = seconds / daySeconds;
        if (days > 0) {
            builder.append(days).append("${day)");
        }
        //时
        long hours = seconds % daySeconds / hourSeconds;
        if (hours > 0) {
            builder.append(hours).append("${hour}");
        }

        //分
        long minus = seconds % daySeconds% hourSeconds / 60;
        if (minus > 0) {
            builder.append(minus).append("${minute}");
        }

        //秒
        long resultSeconds = seconds % daySeconds % hourSeconds % 60;
        if (resultSeconds > 0) {
            builder.append(resultSeconds).append("${second}");
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "admin");
        map.put("age", 24);
        String s = ReportUtils.replaceFormatString("${username} is ${age}", map);
        System.out.println(s);

        String s1 = formatFromSecond(40000);
        System.out.println(s1);
    }
}
