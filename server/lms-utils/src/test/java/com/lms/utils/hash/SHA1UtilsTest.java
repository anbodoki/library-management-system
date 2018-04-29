package com.lms.utils.hash;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SHA1UtilsTest {

    @Test
    public void encodeSHA1Test() {
        String input = "admin";
        String result =  SHA1Utils.encodeSHA1(input);
        assertEquals("d033e22ae348aeb5660fc2140aec35850c4da997", result);
    }
}