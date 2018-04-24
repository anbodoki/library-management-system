package com.lms.application.controller.atom;

import com.lms.application.security.PermissionCheck;
import com.lms.atom.material.service.MaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "atom/material-type-api/")
@PermissionCheck
public class MaterialTypeController {

    private final MaterialTypeService materialTypeService;

    @Autowired
    public MaterialTypeController(MaterialTypeService materialTypeService) {
        this.materialTypeService = materialTypeService;
    }
}
