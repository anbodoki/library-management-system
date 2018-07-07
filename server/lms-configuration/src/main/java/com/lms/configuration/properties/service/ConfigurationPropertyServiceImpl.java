package com.lms.configuration.properties.service;

import com.lms.common.dto.configuration.ConfigurationPropertyDTO;
import com.lms.common.dto.configuration.ConfigurationPropertyTypeDTO;
import com.lms.common.dto.response.ComboObject;
import com.lms.common.dto.response.ListResult;
import com.lms.configuration.cache.ConfigurationPropertiesCache;
import com.lms.configuration.exception.ConfigurationException;
import com.lms.configuration.messages.Messages;
import com.lms.configuration.properties.storage.ConfigurationPropertyHelper;
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
        ListResult<ConfigurationProperty> properties = storage.find(query, limit, offset);
        ListResult<ConfigurationPropertyDTO> result = properties.copy(ConfigurationPropertyDTO.class);
        result.setResultList(ConfigurationPropertyHelper.fromEntities(properties.getResultList()));
        return result;
    }

    @Override
    public ListResult<ConfigurationPropertyDTO> find(Long id, String code, ConfigurationPropertyTypeDTO type, int limit, int offset) throws Exception {
        ListResult<ConfigurationProperty> properties = storage.find(id, code, ConfigurationPropertyType.valueOf(type.name()), limit, offset);
        ListResult<ConfigurationPropertyDTO> result = properties.copy(ConfigurationPropertyDTO.class);
        result.setResultList(ConfigurationPropertyHelper.fromEntities(properties.getResultList()));
        return result;
    }

    @Override
    public ConfigurationPropertyDTO update(ConfigurationPropertyDTO configurationProperty) throws ConfigurationException {
        ConfigurationProperty old = repository.findByCode(configurationProperty.getCode());
        if (!old.getType().name().equals(configurationProperty.getType().name()) || !old.getCode().equals(configurationProperty.getCode())) {
            throw new ConfigurationException(Messages.get("unableToModifyTypeAndCode"));
        }
        ConfigurationProperty saved = repository.save(ConfigurationPropertyHelper.toEntity(configurationProperty));
        setOriginalValue(saved);
        ConfigurationPropertiesCache.put(saved);
        return ConfigurationPropertyHelper.fromEntity(saved);
    }

    @Override
    public ConfigurationPropertyDTO save(ConfigurationPropertyDTO configurationProperty) throws ConfigurationException {
        ConfigurationProperty byCode = repository.findByCode(configurationProperty.getCode());
        if (byCode == null) {
            ConfigurationProperty saved = repository.save(ConfigurationPropertyHelper.toEntity(configurationProperty));
            setOriginalValue(saved);
            ConfigurationPropertiesCache.put(saved);
            return ConfigurationPropertyHelper.fromEntity(saved);
        } else {
            throw new ConfigurationException(Messages.get("propertyWithThisCodeAlreadyExists"));
        }
    }

    @Override
    public void delete(long id) throws Exception {
        repository.delete(id);
    }

    @Override
    public List<ComboObject> getTypes() {
        List<ComboObject> result = new ArrayList<>();
        for (ConfigurationPropertyType userType : ConfigurationPropertyType.values()) {
            result.add(new ComboObject(userType.name(), Messages.get(ConfigurationPropertyType.class.getSimpleName() + "_" + userType.name())));
        }
        return result;
    }

    @Override
    public ConfigurationProperty get(String code) {
        ConfigurationProperty property = ConfigurationPropertiesCache.get(code);
        if (property == null) {
            property = storage.get(code);
            if (property == null) {
                return null;
            }
            setOriginalValue(property);
            ConfigurationPropertiesCache.put(property);
            return property;
        } else {
            return property;
        }
    }

    public void setOriginalValue(ConfigurationProperty property) {
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
}
