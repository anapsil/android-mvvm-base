package net.anapsil.mvvmbase.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ana.silva on 16/01/18.
 */

public class DateUtils {
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd";
    public static final String BRAZILIAN_PATTERN = "dd/MM/yyyy";

    public static Date convert(String dateString) throws ParseException {
        return convert(dateString, DEFAULT_PATTERN);
    }

    public static Date convert(String dateString, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());

        return sdf.parse(dateString);
    }

    public static Date addDays(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, amount);
        return c.getTime();
    }

    public static int calculateDaysInPeriod(Date start, Date end) {
        return (int) ((end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24)) + 1;
    }

    public static Calendar convertToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static String getDay(Date date) {
        Calendar calendar = convertToCalendar(date);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {
            return "0" + day;
        } else {
            return String.valueOf(day);
        }
    }

    public static String getMonth(Date date) {
        return new SimpleDateFormat("MMMM", Locale.getDefault()).format(date);
    }
}
