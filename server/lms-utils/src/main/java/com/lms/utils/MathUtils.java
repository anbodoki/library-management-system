package com.lms.utils;

import java.util.Calendar;
import java.util.Date;

public class MathUtils {

    public static final int INITIAL_RATING = 80;
    public static final int MAX_RATING = 100;
    public static final int MIN_RATING = 0;
    public static final int ALERT_RATING = 50;
    public static final int TASK_COMPLETE_ON_TIME_RATING = 1;
    public static final double HIGH_PRIORITY_TASK_PENALTY = 0.05;
    public static final double MEDIUM_PRIORITY_TASK_PENALTY = 0.03;
    public static final double LOW_PRIORITY_TASK_PENALTY = 0.02;


    public static long calculatePageNum(long count, long limit) {
        int num = (int) (count / limit);
        if (count % limit == 0) {
            return num;
        } else {
            return num + 1;
        }
    }

    /*
     * Pass negative number if you want previous date
     */
    public static Date getBeforeOrAfterSeveralDaysDate(int days) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Date getEndOfTheDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getStartOfTheDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 1);
        return cal.getTime();
    }
}
