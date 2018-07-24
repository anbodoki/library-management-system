package com.lms.application.service;

import com.lms.atom.book.service.ResourceService;
import com.lms.atom.borrow.service.ResourceBorrowService;
import com.lms.client.client.service.ClientService;
import com.lms.common.dto.initaldata.InitialData;
import com.lms.security.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class InitialDataServiceImpl implements InitialDataService {

    private final UserService userService;
    private final ResourceService resourceService;
    private final ClientService clientService;
    private final ResourceBorrowService resourceBorrowService;

    @Autowired
    public InitialDataServiceImpl(UserService userService, ResourceService resourceService, ClientService clientService, ResourceBorrowService resourceBorrowService) {
        this.userService = userService;
        this.resourceService = resourceService;
        this.clientService = clientService;
        this.resourceBorrowService = resourceBorrowService;
    }

    @Override
    public InitialData initialData() {
        InitialData initialData = new InitialData();
        initialData.setUserCount(userService.usersCount());
        initialData.setResourcesCount(resourceService.resourcesCount());
        initialData.setClientsCount(clientService.getClientCount());
        initialData.setCopiesCounts(resourceService.getCopiesCount());
        initialData.setBorrowedResourcesCount(resourceBorrowService.getBorrowedResourcesCount());
        initialData.setCriticalCount(resourceBorrowService.getCriticalCount());
        return initialData;
    }
}
