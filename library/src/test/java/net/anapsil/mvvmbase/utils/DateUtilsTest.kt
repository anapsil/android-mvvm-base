package net.anapsil.mvvmbase.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.ParseException
import java.util.*

class DateUtilsTest {
    private val DATE_IN_MILLS = 1525230000000L //02/05/2018
    private val DATE_IN_MILLS_PLUS_9 = 1525921200000L //02/05/2018
    private val SAMPLE_DATE = Date(DATE_IN_MILLS)
    private val SAMPLE_DATE_PLUS_9 = Date(DATE_IN_MILLS_PLUS_9)

    @Test
    @Throws(ParseException::class)
    fun shouldConvertDateWithDefaultPattern() {
        assertEquals(SAMPLE_DATE.time, DateUtils.convert("2018-05-02").time)
    }

    @Test
    @Throws(ParseException::class)
    fun shouldConvertDateWithCustomPattern() {
        assertEquals(SAMPLE_DATE.time, DateUtils.convert("02/05/2018", DateUtils.BRAZILIAN_PATTERN).time)
    }

    @Test
    fun shouldAddDaysToDate() {
        assertEquals(SAMPLE_DATE_PLUS_9.time, DateUtils.addDays(SAMPLE_DATE, 8).time)
    }

    @Test
    fun shouldCalculateDaysInPeriod() {
        assertEquals(9, DateUtils.calculateDaysInPeriod(SAMPLE_DATE, SAMPLE_DATE_PLUS_9).toLong())
    }

    @Test
    fun shouldConvertDateToCalendar() {
        assertEquals(2018, DateUtils.convertToCalendar(SAMPLE_DATE).get(Calendar.YEAR).toLong())
        assertEquals(4, DateUtils.convertToCalendar(SAMPLE_DATE).get(Calendar.MONTH).toLong())
        assertEquals(2, DateUtils.convertToCalendar(SAMPLE_DATE).get(Calendar.DAY_OF_MONTH).toLong())
    }

    @Test
    fun shouldGetDayFromDate() {
        assertEquals("02", DateUtils.getDay(SAMPLE_DATE))
    }

    @Test
    fun shouldGetMonthFromDate() {
        assertEquals("May", DateUtils.getMonth(SAMPLE_DATE))
    }
}