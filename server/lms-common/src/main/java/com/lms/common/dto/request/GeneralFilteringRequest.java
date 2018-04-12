package com.lms.common.dto.request;

import com.lms.common.dto.response.PagingRequest;

public class GeneralFilteringRequest extends PagingRequest {

    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
