package com.lms.utils;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test
    public void setLengthTest() {
        String result = StringUtils.setLength("123123", 3);
        assertEquals(3, result.length());

        result = StringUtils.setLength(null, 10);
        assertNull(result);
    }

    @Test
    public void trimSafelyTest() {
        String test = "        a";
        test = StringUtils.trimSafely(test);
        assertEquals("a", test);
    }

    @Test
    public void checkNullStringTest() {
        assertEquals("", StringUtils.checkNullString(null));
    }

    @Test
    public void getListFromCommaSeparatedStringTest() {
        String input = "1,2,3,4,5";
        List<String> result = StringUtils.getListFromCommaSeparatedString(input);
        assertNotNull(result);
        assertEquals(5, result.size());
        assertEquals("1", result.get(0));
        assertEquals("5", result.get(4));
    }

    @Test
    public void deleteWhitespaceTest() {
        String input = "1 2 3 4 5";
        String result = StringUtils.deleteWhitespace(input);
        assertEquals("12345", result);
    }
}