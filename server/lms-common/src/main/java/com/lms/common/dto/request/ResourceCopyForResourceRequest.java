package com.lms.common.dto.request;

public class ResourceCopyForResourceRequest extends GeneralFilteringRequest {

    private Long resourceId;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}
