package com.lms.application.controller.atom;

import com.lms.application.security.PermissionCheck;
import com.lms.atom.material.service.MaterialTypeService;
import com.lms.common.dto.atom.materialtype.MaterialTypeDTO;
import com.lms.common.dto.request.GeneralFilteringRequest;
import com.lms.common.dto.request.MaterialTypeFilteringRequest;
import com.lms.common.dto.response.ActionResponse;
import com.lms.common.dto.response.ActionResponseWithData;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/atom/material-type-api/")
@PermissionCheck
public class MaterialTypeController {

    private final MaterialTypeService materialTypeService;

    @Autowired
    public MaterialTypeController(MaterialTypeService materialTypeService) {
        this.materialTypeService = materialTypeService;
    }

    @PostMapping(value = "quick-find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody GeneralFilteringRequest generalFilteringRequest) throws Exception {
        ListResult<MaterialTypeDTO> result = materialTypeService.find(generalFilteringRequest.getQuery(), generalFilteringRequest.getLimit(), generalFilteringRequest.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody MaterialTypeFilteringRequest request) throws Exception {
        ListResult<MaterialTypeDTO> result = materialTypeService.find(request.getId(),
                request.getCode(), request.getName(), request.getDescription(), request.getLimit(), request.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "update")
    @PermissionCheck
    public ActionResponse update(@RequestBody @Validated MaterialTypeDTO materialType) throws Exception {
        return new ActionResponseWithData<>(materialTypeService.update(materialType), true);
    }

    @PostMapping(value = "save")
    @PermissionCheck
    public ActionResponse save(@RequestBody @Validated MaterialTypeDTO materialType) throws Exception {
        return new ActionResponseWithData<>(materialTypeService.save(materialType), true);
    }

    @DeleteMapping(value = "materialType/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse deleteCustomer(@PathVariable long id) throws Exception {
        materialTypeService.delete(id);
        return new ActionResponse(true);
    }
}
