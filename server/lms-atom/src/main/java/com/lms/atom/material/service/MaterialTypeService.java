package com.lms.atom.material.service;

import com.lms.atom.exception.AtomException;
import com.lms.atom.material.storage.model.MaterialType;
import com.lms.common.dto.atom.materialtype.MaterialTypeDTO;
import com.lms.common.dto.response.ListResult;

public interface MaterialTypeService {

    ListResult<MaterialTypeDTO> find(String query, int limit, int offset) throws Exception;

    ListResult<MaterialTypeDTO> find(Long id, String code, String name, String description, int limit, int offset) throws Exception;

    MaterialTypeDTO update(MaterialTypeDTO materialType) throws Exception;

    MaterialTypeDTO save(MaterialTypeDTO materialType) throws AtomException;

    void delete(Long id) throws Exception;
}
