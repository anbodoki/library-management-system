package com.lms.common.dto.cleintapi;

import java.io.Serializable;

public class AddRemoveFavouriteRequest implements Serializable {

    private Long resourceId;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}
