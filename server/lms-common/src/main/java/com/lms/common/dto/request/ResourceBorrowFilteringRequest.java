package com.lms.common.dto.request;

import com.lms.common.dto.response.PagingRequest;

import java.util.Date;

public class ResourceBorrowFilteringRequest extends PagingRequest {

    private String identifier;
    private String isbn;
    private Long clientId;
    private Date fromBorrowTime;
    private Date toBorrowTime;
    private Date fromReturnTime;
    private Date toReturnTime;
    private Date fromScheduledTime;
    private Date toScheduledTime;
    private Boolean critical;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Date getFromBorrowTime() {
        return fromBorrowTime;
    }

    public void setFromBorrowTime(Date fromBorrowTime) {
        this.fromBorrowTime = fromBorrowTime;
    }

    public Date getToBorrowTime() {
        return toBorrowTime;
    }

    public void setToBorrowTime(Date toBorrowTime) {
        this.toBorrowTime = toBorrowTime;
    }

    public Date getFromReturnTime() {
        return fromReturnTime;
    }

    public void setFromReturnTime(Date fromReturnTime) {
        this.fromReturnTime = fromReturnTime;
    }

    public Date getToReturnTime() {
        return toReturnTime;
    }

    public void setToReturnTime(Date toReturnTime) {
        this.toReturnTime = toReturnTime;
    }

    public Date getFromScheduledTime() {
        return fromScheduledTime;
    }

    public void setFromScheduledTime(Date fromScheduledTime) {
        this.fromScheduledTime = fromScheduledTime;
    }

    public Date getToScheduledTime() {
        return toScheduledTime;
    }

    public void setToScheduledTime(Date toScheduledTime) {
        this.toScheduledTime = toScheduledTime;
    }

    public Boolean getCritical() {
        return critical;
    }

    public void setCritical(Boolean critical) {
        this.critical = critical;
    }
}
