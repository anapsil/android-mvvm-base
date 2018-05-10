package net.anapsil.mvvmbase.utils;

import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateUtilsTest {
    private final long DATE_IN_MILLS = 1525230000000L; //02/05/2018
    private final long DATE_IN_MILLS_PLUS_9 = 1525921200000L; //02/05/2018
    private final Date SAMPLE_DATE = new Date(DATE_IN_MILLS);
    private final Date SAMPLE_DATE_PLUS_9 = new Date(DATE_IN_MILLS_PLUS_9);

    @Test
    public void shouldConvertDateWithDefaultPattern() throws ParseException {
        assertEquals(SAMPLE_DATE.getTime(), DateUtils.convert("2018-05-02").getTime());
    }

    @Test
    public void shouldConvertDateWithCustomPattern() throws ParseException {
        assertEquals(SAMPLE_DATE.getTime(), DateUtils.convert("02/05/2018", DateUtils.BRAZILIAN_PATTERN).getTime());
    }

    @Test
    public void shouldAddDaysToDate() {
        assertEquals(SAMPLE_DATE_PLUS_9.getTime(), DateUtils.addDays(SAMPLE_DATE, 8).getTime());
    }

    @Test
    public void shouldCalculateDaysInPeriod() {
        assertEquals(9, DateUtils.calculateDaysInPeriod(SAMPLE_DATE, SAMPLE_DATE_PLUS_9));
    }

    @Test
    public void shouldConvertDateToCalendar() {
        assertEquals(2018, DateUtils.convertToCalendar(SAMPLE_DATE).get(Calendar.YEAR));
        assertEquals(4, DateUtils.convertToCalendar(SAMPLE_DATE).get(Calendar.MONTH));
        assertEquals(2, DateUtils.convertToCalendar(SAMPLE_DATE).get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void shouldGetDayFromDate() {
        assertEquals("02", DateUtils.getDay(SAMPLE_DATE));
    }

    @Test
    public void shouldGetMonthFromDate() {
        assertEquals("May", DateUtils.getMonth(SAMPLE_DATE));
    }
}