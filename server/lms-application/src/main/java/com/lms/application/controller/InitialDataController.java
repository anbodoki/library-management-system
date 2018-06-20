package com.lms.application.controller;

import com.lms.application.service.InitialDataService;
import com.lms.common.dto.initaldata.InitialData;
import com.lms.common.dto.response.ActionResponse;
import com.lms.common.dto.response.ActionResponseWithData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/application/initial-data/")
public class InitialDataController {

    private final InitialDataService initialDataService;

    @Autowired
    public InitialDataController(InitialDataService initialDataService) {
        this.initialDataService = initialDataService;
    }

    @GetMapping(value = "initial-data")
    public ActionResponse initialData() throws Exception {
        InitialData result = initialDataService.initialData();
        return new ActionResponseWithData<>(result, true);
    }
}
