package com.lms.application.controller.atom;

import com.lms.application.security.PermissionCheck;
import com.lms.atom.language.service.LanguageService;
import com.lms.common.dto.atom.language.LanguageDTO;
import com.lms.common.dto.request.GeneralFilteringRequest;
import com.lms.common.dto.request.LanguageFilteringRequest;
import com.lms.common.dto.response.ActionResponse;
import com.lms.common.dto.response.ActionResponseWithData;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/atom/language-api/")
public class LanguageController {

    private final LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping(value = "quick-find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody GeneralFilteringRequest generalFilteringRequest) throws Exception {
        ListResult<LanguageDTO> result = languageService.find(generalFilteringRequest.getQuery(), generalFilteringRequest.getLimit(), generalFilteringRequest.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody LanguageFilteringRequest request) throws Exception {
        ListResult<LanguageDTO> result = languageService.find(request.getId(), request.getCode(), request.getName(), request.getLimit(), request.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "update")
    @PermissionCheck
    public ActionResponse update(@RequestBody @Validated LanguageDTO materialType) throws Exception {
        return new ActionResponseWithData<>(languageService.update(materialType), true);
    }

    @PostMapping(value = "save")
    @PermissionCheck
    public ActionResponse save(@RequestBody @Validated LanguageDTO materialType) throws Exception {
        return new ActionResponseWithData<>(languageService.save(materialType), true);
    }

    @DeleteMapping(value = "language/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse deleteCustomer(@PathVariable long id) throws Exception {
        languageService.delete(id);
        return new ActionResponse(true);
    }
}
