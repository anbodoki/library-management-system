package com.lms.application.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

public class Messages {

    private static final Logger log = LoggerFactory.getLogger(com.lms.security.messages.Messages.class);

    private static Properties messages;

    public static String get(String key) {
        if (messages == null) {
            messages = new Properties();
            try {
                messages.load(com.lms.security.messages.Messages.class.getResourceAsStream("/mes.properties"));
            } catch (IOException ex) {
                log.error("Error occurred during reading messages", ex);
            }
        }
        return messages.getProperty(key, key);
    }

    public static String get(String key, Object... params) {
        return MessageFormat.format(get(key), params);
    }
}