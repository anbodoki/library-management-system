package com.lms.application.controller.atom;

import com.lms.application.security.PermissionCheck;
import com.lms.atom.category.service.CategoryService;
import com.lms.common.dto.atom.category.CategoryDTO;
import com.lms.common.dto.request.CategoryFilteringRequest;
import com.lms.common.dto.request.GeneralFilteringRequest;
import com.lms.common.dto.response.ActionResponse;
import com.lms.common.dto.response.ActionResponseWithData;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/atom/category-api/")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "quick-find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody GeneralFilteringRequest generalFilteringRequest) throws Exception {
        ListResult<CategoryDTO> result = categoryService.find(generalFilteringRequest.getQuery(), generalFilteringRequest.getLimit(), generalFilteringRequest.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody CategoryFilteringRequest request) throws Exception {
        ListResult<CategoryDTO> result = categoryService.find(request.getId(),
                request.getCode(), request.getName(), request.getDescription(), request.getLimit(), request.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "update")
    @PermissionCheck
    public ActionResponse update(@RequestBody @Validated CategoryDTO category) throws Exception {
        return new ActionResponseWithData<>(categoryService.update(category), true);
    }

    @PostMapping(value = "save")
    @PermissionCheck
    public ActionResponse save(@RequestBody @Validated CategoryDTO category) throws Exception {
        return new ActionResponseWithData<>(categoryService.save(category), true);
    }

    @DeleteMapping(value = "category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse deleteCustomer(@PathVariable long id) throws Exception {
        categoryService.delete(id);
        return new ActionResponse(true);
    }
}
