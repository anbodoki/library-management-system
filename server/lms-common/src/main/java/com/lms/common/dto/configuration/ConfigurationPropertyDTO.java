package com.lms.common.dto.configuration;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ConfigurationPropertyDTO implements Serializable {

    private Long id;
    @NotNull
    private String code;
    @NotNull
    private String value;
    private String description;
    @NotNull
    private ConfigurationPropertyTypeDTO type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ConfigurationPropertyTypeDTO getType() {
        return type;
    }

    public void setType(ConfigurationPropertyTypeDTO type) {
        this.type = type;
    }
}
