package com.lms.configuration.properties.storage;

import com.lms.configuration.properties.storage.model.ConfigurationProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationPropertyRepository extends JpaRepository<ConfigurationProperty, Long> {
}
