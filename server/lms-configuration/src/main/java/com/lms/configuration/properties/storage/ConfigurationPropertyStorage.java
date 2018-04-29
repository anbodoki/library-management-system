package com.lms.configuration.properties.storage;

import com.lms.common.dto.response.ListResult;
import com.lms.configuration.properties.storage.model.ConfigurationProperty;
import com.lms.configuration.properties.storage.model.ConfigurationPropertyType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ConfigurationPropertyStorage {

    @PersistenceContext
    private EntityManager em;

    public ListResult<ConfigurationProperty> find(String query, int limit, int offset) {
        return null;
    }

    public ListResult<ConfigurationProperty> find(Long id, String code, ConfigurationPropertyType configurationPropertyType, int limit, int offset) {
        return null;
    }

    public ConfigurationProperty get(String code) {
        return null;
    }
}
