package com.lms.gateway;

import java.util.Date;

public abstract class DeviceMessageHandlerFactory {

    public abstract String processSubmit(String bookId, String clientId, Date date) throws Exception;

    public abstract String processCheckBook(String bookIdentifier) throws Exception;

    public abstract String processCheckClient(String clientCardId) throws Exception;
}