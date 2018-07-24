package com.lms.integration;

import com.lms.atom.book.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@Transactional
public class DataIntegrationService {

    private final ResourceService resourceService;

    @Autowired
    public DataIntegrationService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostConstruct
    public void postConstruct() {

    }
}
