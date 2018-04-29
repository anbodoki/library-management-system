package com.lms.configuration.properties.storage.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "configurationproperty")
public class ConfigurationProperty implements Serializable {

    private static final long serialVersionUID = -3009157732242241607L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String value;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private ConfigurationPropertyType type;
    @Transient
    private Integer numberValue;
    @Transient
    private String stringValue;
    @Transient
    private Date dateValue;
    @Transient
    private Boolean logicalValue;

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

    public ConfigurationPropertyType getType() {
        return type;
    }

    public void setType(ConfigurationPropertyType type) {
        this.type = type;
    }

    public Integer getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(Integer numberValue) {
        this.numberValue = numberValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public Boolean getLogicalValue() {
        return logicalValue;
    }

    public void setLogicalValue(Boolean logicalValue) {
        this.logicalValue = logicalValue;
    }
}
