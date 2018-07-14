package com.lms.gateway.model;

import com.lms.gateway.ProtocolConfig;
import com.lms.gateway.parsers.MessageDecodeUtils;

import java.util.Date;

public class RequestSubmitMessageData implements MessageData {

    private String bookId;
    private String clientId;
    private Date date;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String customToString() {
        String result = "";
        result += bookId +
                ProtocolConfig.MSG_DATA_DELIMITER +
                clientId +
                (date != null ? ProtocolConfig.MSG_DATA_DELIMITER + MessageDecodeUtils.formatter.format(date) : "");
        return result;
    }
}
