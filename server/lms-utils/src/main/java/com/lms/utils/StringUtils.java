package com.lms.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    private static final int INDEX_NOT_FOUND = -1;

    public static String setLength(String str, int length) {
        if (str != null) {
            return str.length() > length ? str.substring(0, length).trim() : str.trim();
        }
        return null;
    }

    public static String trimSafely(String input) {
        if (input != null) {
            input = input.trim();
        }

        return input;
    }

    public static String trimSafely(String input, int maxLength) {
        if (input != null) {
            input = input.trim();
        }

        return setLength(input, maxLength);
    }

    public static String checkNullString(String input) {
        return input == null ? "" : input.trim();
    }

    public static String replaceStringDoubleQuotesWithSingle(String str) {
        return str.replaceAll("\"", "'");
    }

    public static String toUpperCase(String value) {
        return value == null ? null : value.trim().toUpperCase();
    }

    public static String toLowerCase(String value) {
        return value == null ? null : value.trim().toLowerCase();
    }

    public static List<String> getListFromCommaSeparatedString(String str) {
        List<String> result = new ArrayList<>();
        String text = str;
        while (true) {
            if (!text.contains(",")) {
                result.add(text);
                break;
            }
            String currency = text.substring(0, text.indexOf(","));
            text = text.substring(text.indexOf(",") + 1);
            result.add(currency);
        }
        return result;
    }

    public static String stripStart(final String str, final String stripChars) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        int start = 0;
        if (stripChars == null) {
            while (start != strLen && Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else if (stripChars.isEmpty()) {
            return str;
        } else {
            while (start != strLen && stripChars.indexOf(str.charAt(start)) != INDEX_NOT_FOUND) {
                start++;
            }
        }
        return str.substring(start);
    }

    public static String stripEnd(final String str, final String stripChars) {
        int end;
        if (str == null || (end = str.length()) == 0) {
            return str;
        }
        if (stripChars == null) {
            while (end != 0 && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.isEmpty()) {
            return str;
        } else {
            while (end != 0 && stripChars.indexOf(str.charAt(end - 1)) != INDEX_NOT_FOUND) {
                end--;
            }
        }
        return str.substring(0, end);
    }

    public static String deleteWhitespace(String str) {
        StringBuilder buffer = new StringBuilder();
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                buffer.append(str.charAt(i));
            }
        }
        return buffer.toString();
    }

}