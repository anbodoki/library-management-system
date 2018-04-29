package com.lms.configuration.cache;

import com.lms.common.dto.response.ListResult;
import com.lms.configuration.properties.storage.ConfigurationPropertyRepository;
import com.lms.configuration.properties.storage.ConfigurationPropertyStorage;
import com.lms.configuration.properties.storage.model.ConfigurationProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.text.SimpleDateFormat;

@Service
@Transactional
public class ConfigurationPropertyCacheService {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private final ConfigurationPropertyRepository repository;

    private final ConfigurationPropertyStorage storage;

    @Autowired
    public ConfigurationPropertyCacheService(ConfigurationPropertyRepository repository, ConfigurationPropertyStorage storage) {
        this.repository = repository;
        this.storage = storage;
    }

    @PostConstruct
    public void postConstruct() {
        ListResult<ConfigurationProperty> properties = storage.find(null, -1, -1);
        for (ConfigurationProperty property : properties.getResultList()) {
            setOriginalValue(property);
            ConfigurationPropertiesCache.put(property);
        }
    }

    public ConfigurationProperty get(String code) {
        ConfigurationProperty fromCache = ConfigurationPropertiesCache.get(code);
        if (fromCache != null) {
            return fromCache;
        }
        ConfigurationProperty fromRepo = repository.findByCode(code);
        if (fromRepo == null) {
            return null;
        }
        setOriginalValue(fromRepo);
        ConfigurationPropertiesCache.put(fromRepo);
        return fromRepo;
    }

    private void setOriginalValue(ConfigurationProperty property) {
        try {
            switch (property.getType()) {
                case NUMBER:
                    property.setNumberValue(Integer.parseInt(property.getValue()));
                    break;
                case TEXT:
                    property.setStringValue(property.getValue());
                    break;
                case DATE:
                    property.setDateValue(formatter.parse(property.getValue()));
                    break;
                case LOGICAL:
                    property.setLogicalValue(Boolean.valueOf(property.getValue()));
                    break;
            }
        } catch (Exception ignored) {
        }
    }

    @PreDestroy
    public void preDestroy() {
        ConfigurationPropertiesCache.clear();
    }
}
