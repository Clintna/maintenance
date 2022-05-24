package com.bhhyy.aircraft.maintenance.common.tools;

import com.bhhyy.aircraft.maintenance.common.enums.ErrorCodeEnum;

import java.rmi.ServerException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类
 */
public class DateTimeUtil {

    // 日期
    public static final String SFM_DATE = "yyyy-MM-dd";

    // 时间
    public static final String SFM_TIME = "HH:mm:ss";

    // 日期时间
    public static final String SFM_DATETIME = "yyyy-MM-dd HH:mm:ss";

    // 昨天
    public static final String YESTERDAY = "yesterday";

    // 上个月
    public static final String LAST_MONTH = "lastMonth";

    public static final String START_TIME = "00:00:00";

    public static final String END_TIME = "23:59:59";

    /**
     * 获取上个月第一天的日期
     */
    public static Date firstDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTime();
    }

    /**
     * 获取上个月最后一天的日期
     */
    public static Date lastDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 获取昨天的日期
     */
    public static Date yesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 获取指定日期前后N天的日期
     */
    public static Date chooseDay(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, n);
        return calendar.getTime();
    }

    /**
     * 将时间转换成默认格式的字符串
     *
     * @param date 时间对象
     */
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(SFM_DATETIME);
        return sdf.format(date);
    }

    /**
     * 将时间转换成指定格式的字符串
     *
     * @param date 时间对象
     * @param sfm  时间格式字符串
     */
    public static String formatDate(Date date, String sfm) {
        SimpleDateFormat sdf = new SimpleDateFormat(sfm);
        return sdf.format(date);
    }

    /**
     * 获得当天零时零分零秒
     *
     * @return java.sql.Timestamp对象
     */
    public static Timestamp initDateByDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        java.util.Date date = calendar.getTime();
        Timestamp dateSQL = new java.sql.Timestamp(date.getTime());
        return dateSQL;
    }

    /**
     * 将指定格式的时间字符串转为 Date 对象
     *
     * @param dateStr
     * @param formatStr
     * @return
     */
    public static Date parseDate(String dateStr, String formatStr) throws ServerException {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new ServerException(ErrorCodeEnum.FAILED_CODE.getCode().concat(e.getMessage()));
        }
    }

    /**
     * 获取指定日期所在月份当月的总天数
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    /**
     * 获取昨日0点
     *
     * @return
     */
    public static String getStartYesterday() {
        // 获取昨日0点
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return formatDate(calendar.getTime(), SFM_DATETIME);
    }

    /**
     * 获取昨日结束时间
     *
     * @return
     */
    public static String getEndYesterday() {
        // 获取昨日23点
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return formatDate(calendar.getTime(), SFM_DATETIME);
    }

    /**
     * 获取当前时间的前forward时间
     *
     * @param forwardTime 向前多长时间
     * @return
     */
    public static String getBeforeCurrTime(int forwardTime) {
        /* 核心代码 */
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -forwardTime); // 5分钟之前的时间
        Date date = calendar.getTime();
        return formatDate(date, SFM_DATETIME);  // 前五分钟时间
    }


    public static String getStartLastHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return formatDate(calendar.getTime(), SFM_DATETIME);
    }

    public static String getEndLastHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return formatDate(calendar.getTime(), SFM_DATETIME);
    }
}