package com.lms.gateway.parsers;

public class CRC16 {

    public static String calculateCRC(String input) {
        int result = 0;
        int start = 99;
        result += start;
        for (int i = 0; i < input.length(); i++) {
            result += input.charAt(i);
        }
        return Integer.toHexString(0x10000 | result).substring(1).toUpperCase();
    }
}