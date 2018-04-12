package com.lms.common.dto.response;

public class ActionResponseWithData<T> extends ActionResponse {

    public ActionResponseWithData(T data, Boolean isSuccess) {
        super(isSuccess);
        setData(data);
    }

    public ActionResponseWithData(T data, Boolean isSuccess, String message) {
        super(isSuccess, message);
        setData(data);
    }

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
