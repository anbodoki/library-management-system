package com.lms.configuration.properties.service;

import com.lms.common.dto.configuration.ConfigurationPropertyDTO;
import com.lms.common.dto.configuration.ConfigurationPropertyTypeDTO;
import com.lms.common.dto.response.ComboObject;
import com.lms.common.dto.response.ListResult;
import com.lms.configuration.exception.ConfigurationException;
import com.lms.configuration.properties.storage.ConfigurationPropertyRepository;
import com.lms.configuration.properties.storage.ConfigurationPropertyStorage;
import com.lms.configuration.properties.storage.model.ConfigurationProperty;
import com.lms.configuration.properties.storage.model.ConfigurationPropertyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ConfigurationPropertyServiceImpl implements ConfigurationPropertyService {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private final ConfigurationPropertyStorage storage;

    private final ConfigurationPropertyRepository repository;

    @Autowired
    public ConfigurationPropertyServiceImpl(ConfigurationPropertyStorage storage, ConfigurationPropertyRepository repository) {
        this.storage = storage;
        this.repository = repository;
    }


    @Override
    public ListResult<ConfigurationPropertyDTO> find(String query, int limit, int offset) throws Exception {
        return null;
    }

    @Override
    public ListResult<ConfigurationPropertyDTO> find(Long id, String code, ConfigurationPropertyTypeDTO type, int limit, int offset) throws Exception {
        return null;
    }

    @Override
    public ConfigurationPropertyDTO update(ConfigurationPropertyDTO configurationProperty) throws ConfigurationException {
        return null;
    }

    @Override
    public ConfigurationPropertyDTO save(ConfigurationPropertyDTO configurationProperty) throws ConfigurationException {
        return null;
    }

    @Override
    public void delete(long id) throws Exception {

    }

    @Override
    public List<ComboObject> getTypes() {
        return null;
    }

    @Override
    public ConfigurationProperty get(String code) {
        return null;
    }
}
