package com.lms.common.dto.atom.resource;

import java.io.Serializable;
import java.util.Date;

public class ResourceBorrowDTO implements Serializable {

    private Long id;
    private ResourceCopyDTO resourceCopy;
    private Long clientId;
    private Date borrowTime;
    private Date returnTime;
    private boolean critical;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceCopyDTO getResourceCopy() {
        return resourceCopy;
    }

    public void setResourceCopy(ResourceCopyDTO resourceCopy) {
        this.resourceCopy = resourceCopy;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public boolean getCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }
}
