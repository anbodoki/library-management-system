package com.lms.application.controller.configuration;

import com.lms.application.security.PermissionCheck;
import com.lms.common.dto.configuration.ConfigurationPropertyDTO;
import com.lms.common.dto.configuration.ConfigurationPropertyFilteringRequest;
import com.lms.common.dto.request.GeneralFilteringRequest;
import com.lms.common.dto.response.ActionResponse;
import com.lms.common.dto.response.ActionResponseWithData;
import com.lms.common.dto.response.ListResult;
import com.lms.configuration.exception.ConfigurationException;
import com.lms.configuration.properties.service.ConfigurationPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/configuration/configuration-property-api/")
public class ConfigurationPropertyController {

    private final ConfigurationPropertyService configurationPropertyService;

    @Autowired
    public ConfigurationPropertyController(ConfigurationPropertyService configurationPropertyService) {
        this.configurationPropertyService = configurationPropertyService;
    }

    @PostMapping(value = "quick-find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody GeneralFilteringRequest generalFilteringRequest) throws Exception {
        ListResult<ConfigurationPropertyDTO> result = configurationPropertyService.find(generalFilteringRequest.getQuery(), generalFilteringRequest.getLimit(), generalFilteringRequest.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody ConfigurationPropertyFilteringRequest filteringRequest) throws Exception {
        ListResult<ConfigurationPropertyDTO> result = configurationPropertyService.find(filteringRequest.getId(), filteringRequest.getCode(),
                filteringRequest.getType(), filteringRequest.getLimit(), filteringRequest.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "update")
    @PermissionCheck
    public ActionResponse update(@RequestBody @Validated ConfigurationPropertyDTO configurationProperty) throws Exception {
        ConfigurationPropertyDTO result = configurationPropertyService.update(configurationProperty);
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "save")
    @PermissionCheck
    public ActionResponse save(@RequestBody @Validated ConfigurationPropertyDTO configurationProperty) throws ConfigurationException {
        ConfigurationPropertyDTO result = configurationPropertyService.save(configurationProperty);
        return new ActionResponseWithData<>(result, true);
    }

    @DeleteMapping(value = "delete/{id}")
    @PermissionCheck
    public ActionResponse delete(@PathVariable long id) throws Exception {
        configurationPropertyService.delete(id);
        return new ActionResponse(true);
    }

    @GetMapping(value = "configuration-property-types", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse getTypes() throws Exception {
        return new ActionResponseWithData<>(configurationPropertyService.getTypes(), true);
    }
}
