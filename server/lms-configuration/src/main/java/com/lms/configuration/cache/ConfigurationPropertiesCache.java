package com.lms.configuration.cache;

import com.lms.configuration.properties.storage.model.ConfigurationProperty;

import java.util.concurrent.ConcurrentHashMap;

public class ConfigurationPropertiesCache {

    private static ConcurrentHashMap<String, ConfigurationProperty> properties = new ConcurrentHashMap<>();

    public static ConfigurationProperty get(String code) {
        return properties.get(code);
    }

    public static void put(ConfigurationProperty property) {
        properties.put(property.getCode(), property);
    }

    public static void clear() {
        properties.clear();
    }
}
