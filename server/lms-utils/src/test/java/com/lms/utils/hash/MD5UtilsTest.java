package com.lms.utils.hash;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MD5UtilsTest {

    @Test
    public void testMd5s() throws Exception {
        byte[] input = "test me".getBytes();
        assertEquals("7b0c2c2cbc980155d71ba3be4d174f56", MD5Utils.md5s(input));
    }
}