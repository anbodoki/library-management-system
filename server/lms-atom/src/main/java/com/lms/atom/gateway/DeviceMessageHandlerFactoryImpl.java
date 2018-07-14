package com.lms.atom.gateway;

import com.lms.atom.book.service.ResourceService;
import com.lms.atom.borrow.service.ResourceBorrowService;
import com.lms.client.client.service.ClientService;
import com.lms.gateway.DeviceMessageHandlerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class DeviceMessageHandlerFactoryImpl extends DeviceMessageHandlerFactory {

    private Logger logger = LoggerFactory.getLogger(DeviceMessageHandlerFactoryImpl.class);

    private final ResourceBorrowService resourceBorrowService;
    private final ResourceService resourceService;
    private final ClientService clientService;

    @Autowired
    public DeviceMessageHandlerFactoryImpl(ResourceBorrowService resourceBorrowService, ResourceService resourceService, ClientService clientService) {
        this.resourceBorrowService = resourceBorrowService;
        this.resourceService = resourceService;
        this.clientService = clientService;
    }

    @Override
	public String processSubmit(String bookId, String clientId, Date date) {
		return null;
	}

	@Override
	public String processCheckBook(String bookIdentifier) {
		return null;
	}

	@Override
	public String processCheckClient(String clientCardId) {
		return null;
	}
}