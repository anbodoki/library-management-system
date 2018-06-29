package com.lms.common.dto.client;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SchoolDTO implements Serializable {

    private Long id;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private UniversityDTO university;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UniversityDTO getUniversity() {
        return university;
    }

    public void setUniversity(UniversityDTO university) {
        this.university = university;
    }
}
