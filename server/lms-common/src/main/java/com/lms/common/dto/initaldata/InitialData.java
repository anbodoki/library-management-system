package com.lms.common.dto.initaldata;

import java.io.Serializable;

public class InitialData implements Serializable {

    private long userCount;
    private long resourcesCount;
    private long clientsCount;
    private long copiesCounts;
    private long borrowedResourcesCount;
    private long criticalCount;


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

    public long getClientsCount() {
        return clientsCount;
    }

    public void setClientsCount(long clientsCount) {
        this.clientsCount = clientsCount;
    }

    public long getCopiesCounts() {
        return copiesCounts;
    }

    public void setCopiesCounts(long copiesCounts) {
        this.copiesCounts = copiesCounts;
    }

    public long getBorrowedResourcesCount() {
        return borrowedResourcesCount;
    }

    public void setBorrowedResourcesCount(long borrowedResourcesCount) {
        this.borrowedResourcesCount = borrowedResourcesCount;
    }

    public long getCriticalCount() {
        return criticalCount;
    }

    public void setCriticalCount(long criticalCount) {
        this.criticalCount = criticalCount;
    }
}
