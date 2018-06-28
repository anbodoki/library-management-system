package com.lms.application.controller.clientapi;

import com.lms.client.api.clientapi.ClientApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/client/api/")
public class ClientApiController {

    private final ClientApiService service;

    @Autowired
    public ClientApiController(ClientApiService service) {
        this.service = service;
    }
}
