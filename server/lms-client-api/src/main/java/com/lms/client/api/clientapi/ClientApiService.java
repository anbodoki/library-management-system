package com.lms.client.api.clientapi;

import com.lms.common.dto.atom.category.CategoryDTO;
import com.lms.common.dto.atom.materialtype.MaterialTypeDTO;
import com.lms.common.dto.atom.resource.ResourceDTO;
import com.lms.common.dto.atom.resource.ResourceTypeDTO;
import com.lms.common.dto.cleintapi.LightResource;
import com.lms.common.dto.response.ComboObject;
import com.lms.common.dto.response.ListResult;

import java.util.Date;
import java.util.List;

public interface ClientApiService {

    ListResult<LightResource> find(String query, int limit, int offset) throws Exception;

    ListResult<LightResource> find(Long id,
                                   String name,
                                   String author,
                                   String publisher,
                                   Date fromEditionDate, Date toEditionDate,
                                   ResourceTypeDTO resourceType,
                                   Long categoryCode,
                                   int limit, int offset) throws Exception;

    ResourceDTO getResourceById(Long id) throws Exception;

    List<ComboObject> getResourceTypes();

    ListResult<CategoryDTO> getCategories() throws Exception;

    ListResult<CategoryDTO> getSpecialCategories() throws Exception;

    ListResult<MaterialTypeDTO> getMaterialTypes() throws Exception;
}
