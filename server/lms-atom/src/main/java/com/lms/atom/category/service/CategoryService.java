package com.lms.atom.category.service;

import com.lms.atom.exception.AtomException;
import com.lms.common.dto.atom.category.CategoryDTO;
import com.lms.common.dto.response.ListResult;

public interface CategoryService {

    ListResult<CategoryDTO> find(String query, int limit, int offset) throws Exception;

    ListResult<CategoryDTO> find(Long id, String code, String name, String description, Boolean special, int limit, int offset) throws Exception;

    CategoryDTO update(CategoryDTO category) throws Exception;

    CategoryDTO save(CategoryDTO category) throws AtomException;

    void delete(Long id) throws Exception;
}
