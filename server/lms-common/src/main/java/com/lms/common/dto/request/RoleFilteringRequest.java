package com.lms.common.dto.request;

import com.lms.common.dto.response.PagingRequest;

public class RoleFilteringRequest extends PagingRequest {

    private Long id;
    private String name;

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
}
