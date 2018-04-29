package com.lms.common.dto.configuration;

import com.lms.common.dto.response.PagingRequest;

public class ConfigurationPropertyFilteringRequest extends PagingRequest {

    private Long id;

    private String code;

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

    public ConfigurationPropertyTypeDTO getType() {
        return type;
    }

    public void setType(ConfigurationPropertyTypeDTO type) {
        this.type = type;
    }
}
