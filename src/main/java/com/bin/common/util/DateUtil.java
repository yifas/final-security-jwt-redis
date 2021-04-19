package com.bin.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 时间工具类
 *
 * @author Administrator
 */
public class DateUtil {

    private static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat SDF_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("GMT+8");

    /**
     * 获取当前时间的YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return SDF_TIME.format(new Date());
    }

    /**
     * 日期比较，如果s>=e 返回true 否则返回false
     * @param s
     * @param e
     * @return
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        //        return fomatDate(s).getTime() >=fomatDate(e).getTime();
        return s.compareTo(e) > 0;
    }

    /**
     * 格式化日期
     * @param date
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前时间的后i天
     * @param i
     * @return
     */
    public static String getAddDay(int i) {
        String currentTime = DateUtil.getTime();
        GregorianCalendar gCal = new GregorianCalendar(
                Integer.parseInt(currentTime.substring(0, 4)),
                Integer.parseInt(currentTime.substring(5, 7)) - 1,
                Integer.parseInt(currentTime.substring(8, 10)));
        gCal.add(GregorianCalendar.DATE, i);
        return SDF_DATE_FORMAT.format(gCal.getTime());
    }

    /**
     * 获取当前时间的后i天
     * 精确到秒
     * @param i
     * @return
     */
    public static String getAddDayTime(int i) {
        Date date = new Date(System.currentTimeMillis() + i * 24 * 60 * 60 * 1000);
        return SDF_TIME.format(date);
    }

    /**
     * 获取当前时间的+多少秒
     * 精确到秒
     * @param i
     * @return
     */
    public static String getAddDaySecond(int i) {
        Date date = new Date(System.currentTimeMillis() + i * 1000);
        return SDF_TIME.format(date);
    }

    /**
     * 获取今天开始时间
     */
    public static Date getToDayStartTime() {
        Calendar calendar = Calendar.getInstance(TIME_ZONE);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取今天结束时间
     */
    public static Date getToDayEndTime() {
        Calendar calendar = Calendar.getInstance(TIME_ZONE);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 获取当前时间前一个月的时间
     * @return 上个月时间
     */
    public static Date getLastMonthTime() {
        // 过去一月
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    public static Date getDaysStartTime(int day) {
        Calendar calendar = Calendar.getInstance(TIME_ZONE);
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getTo15DaysEndTime() {
        Calendar calendar = Calendar.getInstance(TIME_ZONE);
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -15);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(getTo15DaysEndTime());
        System.out.println(format1);
    }
}
