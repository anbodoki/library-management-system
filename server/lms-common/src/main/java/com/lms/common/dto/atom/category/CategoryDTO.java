package com.lms.common.dto.atom.category;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private Long id;
    @NotNull
    private String code;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String color;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
