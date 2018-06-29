package com.lms.application.controller.client;

import com.lms.application.security.PermissionCheck;
import com.lms.client.school.service.SchoolService;
import com.lms.common.dto.client.SchoolDTO;
import com.lms.common.dto.request.GeneralFilteringRequest;
import com.lms.common.dto.request.SchoolFilteringRequest;
import com.lms.common.dto.response.ActionResponse;
import com.lms.common.dto.response.ActionResponseWithData;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/client/school-api/")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping(value = "quick-find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody GeneralFilteringRequest generalFilteringRequest) throws Exception {
        ListResult<SchoolDTO> result = schoolService.find(generalFilteringRequest.getQuery(), generalFilteringRequest.getLimit(), generalFilteringRequest.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody SchoolFilteringRequest request) throws Exception {
        ListResult<SchoolDTO> result = schoolService.find(request.getId(), request.getName(), request.getUniversity(),
                request.getLimit(), request.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "update")
    @PermissionCheck
    public ActionResponse update(@RequestBody @Validated SchoolDTO materialType) throws Exception {
        return new ActionResponseWithData<>(schoolService.update(materialType), true);
    }

    @PostMapping(value = "save")
    @PermissionCheck
    public ActionResponse save(@RequestBody @Validated SchoolDTO materialType) throws Exception {
        return new ActionResponseWithData<>(schoolService.save(materialType), true);
    }

    @DeleteMapping(value = "school/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse deleteCustomer(@PathVariable long id) throws Exception {
        schoolService.delete(id);
        return new ActionResponse(true);
    }

    @GetMapping(value = "universities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse getUniverities() throws Exception {
        return new ActionResponseWithData<>(schoolService.getUniversities(), true);
    }
}
