package com.lms.common.dto.cleintapi;

import java.io.Serializable;

public class AddRemoveFavouriteRequest implements Serializable {

    private Long clientId;
    private Long resourceId;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}
