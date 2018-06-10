package com.lms.application.controller.security;

import com.lms.application.security.PermissionCheck;
import com.lms.common.dto.request.GeneralFilteringRequest;
import com.lms.common.dto.response.ActionResponse;
import com.lms.common.dto.response.ActionResponseWithData;
import com.lms.common.dto.response.ListResult;

import com.lms.common.dto.security.PrivilegeDTO;
import com.lms.common.dto.security.RoleDTO;
import com.lms.common.dto.request.RoleFilteringRequest;
import com.lms.security.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/security/role-api/")
@PermissionCheck
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(value = "quick-find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody GeneralFilteringRequest generalFilteringRequest) throws Exception {
        ListResult<RoleDTO> result = roleService.find(generalFilteringRequest.getQuery(), generalFilteringRequest.getLimit(), generalFilteringRequest.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody RoleFilteringRequest filteringRequest) throws Exception {
        ListResult<RoleDTO> result = roleService.find(filteringRequest.getId(), filteringRequest.getName(), filteringRequest.getLimit(), filteringRequest.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "update")
    @PermissionCheck
    public ActionResponse update(@RequestBody @Validated RoleDTO roleDTO) throws Exception {
        RoleDTO result = roleService.update(roleDTO);
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "save")
    @PermissionCheck
    public ActionResponse save(@RequestBody @Validated RoleDTO roleDTO) throws Exception {
        RoleDTO result = roleService.save(roleDTO);
        return new ActionResponseWithData<>(result, true);
    }

    @DeleteMapping(value = "delete/{id}")
    @PermissionCheck
    public ActionResponse delete(@PathVariable Long id) throws Exception {
        roleService.delete(id);
        return new ActionResponse(true);
    }

    @GetMapping(value = "privileges", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse findPrivileges() throws Exception {
        Map<String, List<PrivilegeDTO>> result = roleService.getAllPrivileges();
        return new ActionResponseWithData<>(result, true);
    }
}
