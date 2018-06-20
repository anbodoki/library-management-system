package com.lms.common.dto.initaldata;

import java.io.Serializable;

public class InitialData implements Serializable {

    private long userCount;
    private long resourcesCount;

    public long getUserCount() {
        return userCount;
    }

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

    public long getResourcesCount() {
        return resourcesCount;
    }

    public void setResourcesCount(long resourcesCount) {
        this.resourcesCount = resourcesCount;
    }
}
