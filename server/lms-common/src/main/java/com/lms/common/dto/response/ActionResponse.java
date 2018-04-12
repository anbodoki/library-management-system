package com.lms.common.dto.response;

public class ActionResponse {

    private Boolean success;

    private String message;

    private int code;

    private String detailedMessage;

    public ActionResponse(Boolean success) {
        this.success = success;
    }

    public ActionResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ActionResponse(Boolean success, String message, int code) {
        this.success = success;
        this.message = message;
        this.code = code;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public void setDetailedMessage(String detailedMessage) {
        this.detailedMessage = detailedMessage;
    }
}
