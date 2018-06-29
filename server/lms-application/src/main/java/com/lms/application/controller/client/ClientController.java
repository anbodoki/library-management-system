package com.lms.application.controller.client;

import com.lms.application.security.PermissionCheck;
import com.lms.client.client.service.ClientService;
import com.lms.common.dto.client.ClientDTO;
import com.lms.common.dto.request.ClientFilteringRequest;
import com.lms.common.dto.request.GeneralFilteringRequest;
import com.lms.common.dto.response.ActionResponse;
import com.lms.common.dto.response.ActionResponseWithData;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/client/client-api/")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "quick-find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody GeneralFilteringRequest generalFilteringRequest) throws Exception {
        ListResult<ClientDTO> result = clientService.find(generalFilteringRequest.getQuery(), generalFilteringRequest.getLimit(), generalFilteringRequest.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody ClientFilteringRequest filteringRequest) throws Exception {
        ListResult<ClientDTO> result = clientService.find(filteringRequest.getId(), filteringRequest.getFirstName(),
                filteringRequest.getLastName(), filteringRequest.getEmail(), filteringRequest.getPhone(),
                filteringRequest.getSchoolId(), filteringRequest.getLimit(), filteringRequest.getOffset());
        return new ActionResponseWithData<>(result, true);
    }
}
