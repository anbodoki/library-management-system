package com.lms.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private static final long DAY_MILS = 86400000;

	private static SimpleDateFormat MONTH_FORMAT = new SimpleDateFormat("MM/yyyy");
	
	public static Date clearTime(Date date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date(date.getTime()));
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTime();
    }
	
	public static Date shiftDay(Date date, int shiftDay) {
		int days = (int)DAY_MILS * shiftDay;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(date.getTime() + days));
		return cal.getTime();
	}

	public static boolean isSameDay(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
				cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
				cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH));
	}
	
	public static Date truncateDay(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static String formatDate(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(date);
	}

	public static String formatDateTime(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return df.format(date);
	}

    public static int daysBetween(Date start, Date end) {
        return (int) Math.round((end.getTime() - start.getTime()) / 86400000d);
    }

    public static Date truncateTime(Date date) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static Date setDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    public static Date getLastDateInMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return cal.getTime();
    }

    public static boolean areInSameMonthAndYear(Date a, Date b) {
        Calendar calA = Calendar.getInstance();
        calA.setTime(a);

        Calendar calB = Calendar.getInstance();
        calB.setTime(b);

        return calA.get(Calendar.YEAR) == calB.get(Calendar.YEAR) &&
                calA.get(Calendar.MONTH) == calB.get(Calendar.MONTH);
    }

    public static String formatMonth(Date date) {
        return MONTH_FORMAT.format(date);
    }
}