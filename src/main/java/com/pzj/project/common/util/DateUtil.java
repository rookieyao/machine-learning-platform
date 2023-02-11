package com.pzj.project.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author rookie
 * @Date 2021/4/26 15:29
 * @Description
 **/
public class DateUtil {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat yyMMddHHmmssFormat = new SimpleDateFormat("yyMMddHHmmss");
    private static SimpleDateFormat yyMMddFormat = new SimpleDateFormat("yyMMdd");
    private static SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat hHmmssFormat = new SimpleDateFormat("HHmmss");

    public static String getYYMMddHHmmssStr(Date date){
        return yyMMddHHmmssFormat.format(date);
    }

    public static String getYYMMddStr(Date date){
        return yyMMddFormat.format(date);
    }

    public static String getYYYYMMddStr(Date date){
        return yyyyMMddFormat.format(date);
    }

    public static String getHHmmssStr(Date date){
        return hHmmssFormat.format(date);
    }

    public static long getNowTimestamp() {

        return Instant.now().toEpochMilli();
    }

    public static String getTimeDifference(Timestamp formatTime1, Timestamp formatTime2) {
        long t1 = formatTime1.getTime();
        long t2 = formatTime2.getTime();
        int hours=(int) ((t1 - t2)/(1000*60*60));
        int minutes=(int) (((t1 - t2)/1000-hours*(60*60))/60);
//        int second=(int) ((t1 - t2)/1000-hours*(60*60)-minutes*60);
        return ""+hours+"小时"+minutes+"分";
    }

    public static int getTimeDifferenceHours(Timestamp formatTime1, Timestamp formatTime2) {
        long t1 = formatTime1.getTime();
        long t2 = formatTime2.getTime();
        int hours=(int) ((t1 - t2)/(1000*60*60));
        int minutes=(int) (((t1 - t2)/1000-hours*(60*60))/60);
//        int second=(int) ((t1 - t2)/1000-hours*(60*60)-minutes*60);
        return hours;
    }

    public static String getTimeMinuteDifference(Timestamp formatTime1, Timestamp formatTime2) {
        long t1 = formatTime1.getTime();
        long t2 = formatTime2.getTime();
        int hours=(int) ((t1 - t2)/(1000*60*60));
        int minutes=(int) (((t1 - t2)/1000-hours*(60*60))/60);
//        int second=(int) ((t1 - t2)/1000-hours*(60*60)-minutes*60);
        return String.valueOf(hours*60+minutes);
    }


    public static String formatMinuteToHour(String s) {

        Integer minutes = Integer.valueOf(s);
        int minute = minutes % 60;
        int i = (minutes - minute) / 60;
        return i+"小时"+minute+"分";
    }

    /**
     * 获取指定月份第一天
     */
    public static String getFirstDay(int year, int month) {
        // 获取Calendar类的实例
        Calendar c = Calendar.getInstance();
        // 设置年份
        c.set(Calendar.YEAR, year);
        // 设置月份，因为月份从0开始，所以用month - 1
        c.set(Calendar.MONTH, month - 1);
        // 设置日期
        c.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(c.getTime());
    }

    /**
     * 获取两个时间之间的日期
     */
    public static List<String> getDatesBetweenDate(LocalDate startDate, LocalDate endDate) {
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        List<LocalDate> LocalDateList = IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
        List<String> collect = LocalDateList.stream().map(localDateTime -> {
            return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }).collect(Collectors.toList());
        return collect;
    }
}
