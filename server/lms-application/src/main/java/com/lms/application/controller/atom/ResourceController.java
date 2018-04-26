package com.lms.application.controller.atom;

import com.lms.application.security.PermissionCheck;
import com.lms.atom.book.service.ResourceService;
import com.lms.common.dto.atom.resource.ResourceDTO;
import com.lms.common.dto.request.GeneralFilteringRequest;
import com.lms.common.dto.request.ResourceFilteringRequest;
import com.lms.common.dto.response.ActionResponse;
import com.lms.common.dto.response.ActionResponseWithData;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "atom/resource-api/")
@PermissionCheck
public class ResourceController {

    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping(value = "quick-find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody GeneralFilteringRequest generalFilteringRequest) throws Exception {
        ListResult<ResourceDTO> result = resourceService.find(generalFilteringRequest.getQuery(), generalFilteringRequest.getLimit(), generalFilteringRequest.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody ResourceFilteringRequest request) throws Exception {
        ListResult<ResourceDTO> result = resourceService.find(request.getId(),
                request.getName(),
                request.getAuthor(),
                request.getSubName(),
                request.getEdition(),
                request.getPublisher(),
                request.getFromEditionDate(), request.getToEditionDate(),
                request.getLanguage(),
                request.getIsbn(),
                request.getUdc(),
                request.getIdentifier(),
                request.getResourceType(),
                request.getMaterialTypeCode(),
                request.getFromCreationDate(), request.getToCreationDate(),
                request.getFromModificationDate(), request.getToModificationDate(),
                request.getLimit(), request.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "update")
    @PermissionCheck
    public ActionResponse update(@RequestBody @Validated ResourceDTO resource) throws Exception {
        return new ActionResponseWithData<>(resourceService.update(resource), true);
    }

    @PostMapping(value = "save")
    @PermissionCheck
    public ActionResponse save(@RequestBody @Validated ResourceDTO resource) throws Exception {
        return new ActionResponseWithData<>(resourceService.save(resource), true);
    }

    @DeleteMapping(value = "resource/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse deleteCustomer(@PathVariable long id) throws Exception {
        resourceService.delete(id);
        return new ActionResponse(true);
    }

    @GetMapping(value = "resource-types", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse getResourceTypes() throws Exception {
        return new ActionResponseWithData<>(resourceService.getResourceTypes(), true);
    }
}
