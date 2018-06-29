package com.lms.common.dto.request;

import com.lms.common.dto.client.SchoolDTO;
import com.lms.common.dto.client.UniversityDTO;
import com.lms.common.dto.response.PagingRequest;

public class SchoolFilteringRequest extends PagingRequest {

    private Long id;
    private String name;
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

    public UniversityDTO getUniversity() {
        return university;
    }

    public void setUniversity(UniversityDTO university) {
        this.university = university;
    }
}
