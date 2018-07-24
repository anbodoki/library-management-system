package com.lms.integration;

public class LanguageUtils {

    public static String detectLanguage(String input) {
        input = input.toLowerCase();
        if (input.contains("ა")
                || input.contains("ე")
                || input.contains("ი")
                || input.contains("ო")
                || input.contains("უ")) {
            return "ka";
        }
        if (input.contains("а")
                || input.contains("е")
                || input.contains("и")
                || input.contains("я")
                || input.contains("о")
                || input.contains("ю")
                || input.contains("у")) {
            return "ru";
        }
        return "en";
    }
}
