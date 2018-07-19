package com.lms.application.controller.atom;

import com.lms.application.security.PermissionCheck;
import com.lms.atom.borrow.service.ResourceBorrowService;
import com.lms.common.dto.atom.resource.ResourceBorrowDTO;
import com.lms.common.dto.request.GeneralFilteringRequest;
import com.lms.common.dto.request.ResourceBorrowFilteringRequest;
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
@RequestMapping(path = "/atom/resource-borrow-api/")
public class ResourceBorrowController {

    private final ResourceBorrowService service;

    @Autowired
    public ResourceBorrowController(ResourceBorrowService service) {
        this.service = service;
    }

    @PostMapping(value = "quick-find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse find(@RequestBody GeneralFilteringRequest generalFilteringRequest) throws Exception {
        ListResult<ResourceBorrowDTO> result = service.find(generalFilteringRequest.getQuery(), generalFilteringRequest.getLimit(), generalFilteringRequest.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse find(@RequestBody ResourceBorrowFilteringRequest request) throws Exception {
        ListResult<ResourceBorrowDTO> result = service.find(request.getIdentifier(),
                request.getIsbn(),
                request.getClientId(),
                request.getFromBorrowTime(),
                request.getToBorrowTime(),
                request.getFromReturnTime(),
                request.getToReturnTime(),
                request.getFromScheduledTime(),
                request.getToScheduledTime(),
                request.getCritical(),
                request.getLimit(), request.getOffset());
        return new ActionResponseWithData<>(result, true);
    }
}
