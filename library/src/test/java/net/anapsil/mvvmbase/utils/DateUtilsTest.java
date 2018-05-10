package net.anapsil.mvvmbase.utils;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateUtilsTest {
    private final long DATE_IN_MILLS = 1525230000000l; //02/05/2018

    @Test
    public void shouldConvertDateWithDefaultPattern() {
    }

    @Test
    public void shouldConvertDateWithCustomPattern() {
    }

    @Test
    public void shouldAddDaysToDate() {
    }

    @Test
    public void shouldCalculateDaysInPeriod() {
    }

    @Test
    public void shouldConvertDateToCalendar() {
        Date sampleDate = new Date(DATE_IN_MILLS);

        assertEquals(2018, DateUtils.convertToCalendar(sampleDate).get(Calendar.YEAR));
        assertEquals(4, DateUtils.convertToCalendar(sampleDate).get(Calendar.MONTH));
        assertEquals(2, DateUtils.convertToCalendar(sampleDate).get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void shouldGetDayFromDate() {
        Date sampleDate = new Date(DATE_IN_MILLS);

        assertEquals("02", DateUtils.getDay(sampleDate));
    }

    @Test
    public void shouldGetMonthFromDate() {
        Date sampleDate = new Date(DATE_IN_MILLS);

        assertEquals("May", DateUtils.getMonth(sampleDate));
    }
}