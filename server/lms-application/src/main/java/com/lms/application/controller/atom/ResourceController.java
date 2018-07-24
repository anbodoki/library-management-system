package com.lms.application.controller.atom;

import com.lms.application.security.PermissionCheck;
import com.lms.atom.book.service.ResourceService;
import com.lms.atom.borrow.service.ResourceBorrowService;
import com.lms.atom.borrow.storage.model.ResourceBorrow;
import com.lms.common.dto.atom.resource.ResourceBorrowDTO;
import com.lms.common.dto.atom.resource.ResourceCopyDTO;
import com.lms.common.dto.atom.resource.ResourceDTO;
import com.lms.common.dto.request.*;
import com.lms.common.dto.response.ActionResponse;
import com.lms.common.dto.response.ActionResponseWithData;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/atom/resource-api/")
public class ResourceController {

    private final ResourceService resourceService;
    private final ResourceBorrowService resourceBorrowService;

    @Autowired
    public ResourceController(ResourceService resourceService, ResourceBorrowService resourceBorrowService) {
        this.resourceService = resourceService;
        this.resourceBorrowService = resourceBorrowService;
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
                request.getEditionYear(),
                request.getLanguageId(),
                request.getIsbn(),
                request.getUdc(),
                request.getResourceType(),
                request.getMaterialTypeId(),
                request.getFromCreationDate(), request.getToCreationDate(),
                request.getFromModificationDate(), request.getToModificationDate(),
                request.getCategoryId(),
                request.getIssn(),
                request.getPlace(),
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

    @GetMapping(value = "find-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse findByUsername(@PathVariable Long id) throws Exception {
        return new ActionResponseWithData<>(resourceService.getResourceById(id), true);
    }

    @PostMapping(value = "add-resource-copy")
    @PermissionCheck
    public ActionResponse addResourceCopy(@RequestBody @Validated ResourceCopyDTO resourceCopy) throws Exception {
        return new ActionResponseWithData<>(resourceService.addResourceCopy(resourceCopy), true);
    }

    @PostMapping(value = "update-resource-copy")
    @PermissionCheck
    public ActionResponse updateResourceCopy(@RequestBody @Validated ResourceCopyDTO resourceCopy) throws Exception {
        return new ActionResponseWithData<>(resourceService.update(resourceCopy), true);
    }

    @DeleteMapping(value = "resource-copy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse removeResourceCopy(@PathVariable long id) throws Exception {
        resourceService.removeResourceCopy(id);
        return new ActionResponse(true);
    }

    @PostMapping(value = "get-resource-copies", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse getResourcesCopies(@RequestBody ResourceCopyForResourceRequest request) throws Exception {
        ListResult<ResourceCopyDTO> result = resourceService.getResourcesCopies(request.getResourceId(), request.getQuery(), request.getLimit(), request.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "get-resource-copy-borrow-history", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse getResourceBorrowHistory(@RequestBody ResourceCopyHistoryRequest request) throws Exception {
        ListResult<ResourceBorrowDTO> result = resourceBorrowService.getResourceCopyHistory(request.getIdentifier(), request.getLimit(), request.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "get-client-resource-copy-borrow-history", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse getResourceBorrowHistory(@RequestBody ClientResourceCopyHistoryRequest request) throws Exception {
        ListResult<ResourceBorrowDTO> result = resourceBorrowService.getClientResourceBorrow(request.getClientId(), request.getCurrent(), request.getLimit(), request.getOffset());
        return new ActionResponseWithData<>(result, true);
    }
}
