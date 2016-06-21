package cn.deepai.evillage.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author GaoYixuan
 */
public final class DateUtil {

    public static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;

    // 短日期格式
    public static String SHORT_FORMAT = "yyyy-MM-dd";

    // 标准日期
    public static String STANDARD_FORMAT = "yyyy年MM月dd日";

    public static String MONTH_DAY_FORMAT = "MM-dd";

    public static String MONTH_YEAR_FORMAT = "MM, yyyy";

    private static DateFormat sShortDateFormat;

    private static DateFormat sStandardDateFormat;

    static {
        sShortDateFormat = new SimpleDateFormat(SHORT_FORMAT, Locale.getDefault());
        sStandardDateFormat = new SimpleDateFormat(STANDARD_FORMAT, Locale.getDefault());
    }

    public static String convertToStandardTime(long time) {
        Date date = new Date(time);
        return sStandardDateFormat.format(date);
    }

    /**
     * @param time "2010-11-20 11:10:10"
     */
    public static String convertToStandardTime(String time) {
        Date date = new Date();
        try {
            date = sShortDateFormat.parse(time);
        } catch (Exception e) {

        }
        return sStandardDateFormat.format(date);
    }

    public static String convertToShortFormat(String time) {
        Date date = new Date();
        try {
            date = sShortDateFormat.parse(time);
        } catch (Exception e) {

        }
        return sShortDateFormat.format(date);
    }

    public static String convertToShortFormat(Date date) {
        return sShortDateFormat.format(date);
    }

    public static String convertToString(long time, String format) {
        return convertToString(time, format, Locale.US);
    }

    public static String convertToString(long time, String format, Locale locale) {
        if (time > 0) {
            SimpleDateFormat sf = new SimpleDateFormat(format, locale);
            Date date = new Date(time);
            return sf.format(date);
        }
        return "";
    }

    public static Date getBeforeTime(int beforeDay) {
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(new Date());
        theCa.add(Calendar.DATE, -beforeDay);
        return theCa.getTime();
    }

    public static Date getAfterTime(int afterDay) {
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(new Date());
        theCa.add(Calendar.DATE, afterDay);
        return theCa.getTime();
    }

    public static long dayDiff(long firstTime, long secondTime) {
        long firstDay = firstTime / MILLIS_PER_DAY, secondDay = secondTime / MILLIS_PER_DAY;
        return firstDay - secondDay;
    }

    public static int getYear(long time) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(time);
        return instance.get(Calendar.YEAR);
    }

    public static int getMonth(long time) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(time);
        return instance.get(Calendar.MONTH) + 1;
    }

    public static int getDay(long time) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(time);
        return instance.get(Calendar.DATE);
    }

    public static long todayMin(long onDate) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(onDate);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTimeInMillis();
    }

    public static long todayMax(long onDate) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(onDate);
        instance.set(Calendar.HOUR_OF_DAY, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 59);
        instance.set(Calendar.MILLISECOND, 999);
        return instance.getTimeInMillis();
    }
}