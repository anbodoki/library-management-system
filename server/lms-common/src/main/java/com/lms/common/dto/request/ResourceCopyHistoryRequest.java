package com.lms.common.dto.request;

import com.lms.common.dto.response.PagingRequest;

public class ResourceCopyHistoryRequest extends PagingRequest {

    private String identifier;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
