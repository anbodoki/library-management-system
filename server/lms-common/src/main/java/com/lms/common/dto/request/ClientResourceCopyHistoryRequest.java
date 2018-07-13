package com.lms.common.dto.request;

import com.lms.common.dto.response.PagingRequest;

public class ClientResourceCopyHistoryRequest extends PagingRequest {

    private Long clientId;
    private boolean current;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public boolean getCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
