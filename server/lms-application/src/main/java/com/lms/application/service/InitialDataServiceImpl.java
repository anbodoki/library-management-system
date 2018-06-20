package com.lms.application.service;

import com.lms.atom.book.service.ResourceService;
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

    @Autowired
    public InitialDataServiceImpl(UserService userService, ResourceService resourceService) {
        this.userService = userService;
        this.resourceService = resourceService;
    }

    @Override
    public InitialData initialData() {
        InitialData initialData = new InitialData();
        initialData.setUserCount(userService.usersCount());
        initialData.setResourcesCount(resourceService.resourcesCount());
        return initialData;
    }
}
