package com.lms.common.dto.atom;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SendMailRequest implements Serializable {

    @NotNull
    public Long clientId;
    @NotNull
    public String subject;
    @NotNull
    public String message;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
