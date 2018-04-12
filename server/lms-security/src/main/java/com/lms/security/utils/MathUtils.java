package com.lms.security.utils;

public class MathUtils {

    public static long calculatePageNum(long count, long limit) {
        int num = (int) (count / limit);
        if (count % limit == 0) {
            return num;
        } else {
            return num + 1;
        }
    }
}
