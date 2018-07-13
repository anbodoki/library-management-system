package com.lms.atom.borrow.storage.model;

import com.lms.atom.book.storage.model.ResourceCopy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class ResourceBorrow implements Serializable {

    private static final long serialVersionUID = -3009157732242241607L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ResourceCopy resourceCopy;
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

    public ResourceCopy getResourceCopy() {
        return resourceCopy;
    }

    public void setResourceCopy(ResourceCopy resourceCopy) {
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
