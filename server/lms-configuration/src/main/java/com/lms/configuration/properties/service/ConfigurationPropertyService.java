package com.lms.configuration.properties.service;

import com.lms.common.dto.configuration.ConfigurationPropertyDTO;
import com.lms.common.dto.configuration.ConfigurationPropertyTypeDTO;
import com.lms.common.dto.response.ComboObject;
import com.lms.common.dto.response.ListResult;
import com.lms.configuration.exception.ConfigurationException;
import com.lms.configuration.properties.storage.model.ConfigurationProperty;

import java.util.List;

public interface ConfigurationPropertyService {

    ListResult<ConfigurationPropertyDTO> find(String query, int limit, int offset) throws Exception;

    ListResult<ConfigurationPropertyDTO> find(Long id, String code, ConfigurationPropertyTypeDTO type, int limit, int offset) throws Exception;

    ConfigurationPropertyDTO update(ConfigurationPropertyDTO configurationProperty) throws ConfigurationException;

    ConfigurationPropertyDTO save(ConfigurationPropertyDTO configurationProperty) throws ConfigurationException;

    void delete(long id) throws Exception;

    List<ComboObject> getTypes();

    ConfigurationProperty get(String code);
}
