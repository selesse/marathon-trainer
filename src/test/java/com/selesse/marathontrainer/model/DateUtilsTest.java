package com.selesse.marathontrainer.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateUtilsTest {
    private Date today;

    @Before
    public void setUp() {
        today = new Date();
    }

    @Test
    public void testCorrectlyComputeDaysBetweenWithCalendar() {
        int daysBetweenExpected = 10;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_YEAR, daysBetweenExpected);
        Date tomorrow = calendar.getTime();

        assertEquals(daysBetweenExpected, DateUtils.getNumberOfDaysBetween(today, tomorrow));
    }

    @Test
    public void testCorrectlyComputeDaysBetweenWithoutCalendar() {
        Date tomorrow = new Date(today.getTime() + DateUtils.MILLISECONDS_PER_DAY);

        assertEquals(1, DateUtils.getNumberOfDaysBetween(today, tomorrow));
    }

    @Test
    public void testNegativeDaysBetween() {
        Date fourDaysAgo = new Date(today.getTime() - 4 * DateUtils.MILLISECONDS_PER_DAY);

        assertEquals(-4, DateUtils.getNumberOfDaysBetween(today, fourDaysAgo));
    }

    @Test
    public void testZeroDaysBetweenTodayAndToday() {
        assertEquals(0, DateUtils.getNumberOfDaysBetween(today, today));
    }

    @Test
    public void testDaysBetweenCorrectlyRoundsUp() {
        // keep in mind we're expecting a ceiling, so we could add 1 millisecond and expect a "1"
        Date quarterDayAway = new Date(today.getTime() + (DateUtils.MILLISECONDS_PER_DAY / 4));

        assertEquals(1, DateUtils.getNumberOfDaysBetween(today, quarterDayAway));
    }

    @Test
    public void testTodayIsToday() {
        assertTrue(DateUtils.isToday(today));
    }

    @Test
    public void testTomorrowIsNotToday() {
        assertFalse(DateUtils.isToday(new Date(today.getTime() + DateUtils.MILLISECONDS_PER_DAY)));
    }

    @Test
    public void testNextDayAtMidnight() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date thirtyMinutesBeforeMidnight = calendar.getTime();

        Date tomorrowMidnight = DateUtils.getNextDayAtMidnight(thirtyMinutesBeforeMidnight);

        long timeDifference = tomorrowMidnight.getTime() - thirtyMinutesBeforeMidnight.getTime();

        assertEquals(1000 * 60 * 30, timeDifference);
    }

    @Test
    public void testNextDayAtMidnightHasProperValues() {
        Date tomorrowMidnight = DateUtils.getNextDayAtMidnight(today);
    }
}
