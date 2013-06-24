package com.selesse.marathontrainer.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Utility date class for the few common date operations used.
 */
public class DateUtils {

     /**
     * Returns a ceiling of the days between an older and younger date. The order is important
     * if you care about the sign of the result.
     */
    public static int getDaysBetween(Date olderDate, Date youngerDate) {
        long millisecondDiff = olderDate.getTime() - youngerDate.getTime();

        return (int) Math.ceil((millisecondDiff / (1000 * 60 * 60 * 24.0)));
    }

    /**
     * Get the next day from a day... i.e. tomorrow.
     */
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    /**
     * Get the next day at midnight.
     */
    public static Date getNextDayAtMidnight(Date date) {
        Date tomorrow = getNextDay(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tomorrow);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Check whether the {@link Date} is today.
     */
    public static boolean isToday(Date referenceDate) {
        // We do this by setting both today and the reference date to midnight and comparing using
        // Date's compareTo
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date today = calendar.getTime();

        calendar.setTime(referenceDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date potentiallyToday = calendar.getTime();

        return today.compareTo(potentiallyToday) == 0;
    }
}
