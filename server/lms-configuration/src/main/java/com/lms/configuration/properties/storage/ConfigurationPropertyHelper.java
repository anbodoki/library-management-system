package com.lms.configuration.properties.storage;

import com.lms.common.dto.configuration.ConfigurationPropertyDTO;
import com.lms.common.dto.configuration.ConfigurationPropertyTypeDTO;
import com.lms.configuration.properties.storage.model.ConfigurationProperty;
import com.lms.configuration.properties.storage.model.ConfigurationPropertyType;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationPropertyHelper {

    public static ConfigurationProperty toEntity(ConfigurationPropertyDTO configurationProperty) {
        if (configurationProperty == null) {
            return null;
        }
        ConfigurationProperty result = new ConfigurationProperty();
        result.setId(configurationProperty.getId());
        result.setCode(configurationProperty.getCode());
        result.setValue(configurationProperty.getValue());
        result.setDescription(configurationProperty.getDescription());
        result.setType(ConfigurationPropertyType.valueOf(configurationProperty.getType().name()));
        return result;
    }

    public static ConfigurationPropertyDTO fromEntity(ConfigurationProperty configurationProperty) {
        if (configurationProperty == null) {
            return null;
        }
        ConfigurationPropertyDTO result = new ConfigurationPropertyDTO();
        result.setId(configurationProperty.getId());
        result.setCode(configurationProperty.getCode());
        result.setValue(configurationProperty.getValue());
        result.setDescription(configurationProperty.getDescription());
        result.setType(ConfigurationPropertyTypeDTO.valueOf(configurationProperty.getType().name()));
        return result;
    }

    public static List<ConfigurationProperty> toEntities(List<ConfigurationPropertyDTO> configurationProperties) {
        if (configurationProperties == null) {
            return null;
        }
        List<ConfigurationProperty> result = new ArrayList<>();
        for (ConfigurationPropertyDTO configurationProperty : configurationProperties) {
            result.add(toEntity(configurationProperty));
        }
        return result;
    }

    public static List<ConfigurationPropertyDTO> fromEntities(List<ConfigurationProperty> configurationProperties) {
        if (configurationProperties == null) {
            return null;
        }
        List<ConfigurationPropertyDTO> result = new ArrayList<>();
        for (ConfigurationProperty configurationProperty : configurationProperties) {
            result.add(fromEntity(configurationProperty));
        }
        return result;
    }
}
