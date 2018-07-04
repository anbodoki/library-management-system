package com.lms.client.api.clientapi;

import com.lms.atom.book.service.ResourceService;
import com.lms.atom.book.storage.model.ResourceType;
import com.lms.atom.category.service.CategoryService;
import com.lms.atom.material.service.MaterialTypeService;
import com.lms.atom.messages.Messages;
import com.lms.common.dto.atom.category.CategoryDTO;
import com.lms.common.dto.atom.materialtype.MaterialTypeDTO;
import com.lms.common.dto.atom.resource.ResourceDTO;
import com.lms.common.dto.atom.resource.ResourceTypeDTO;
import com.lms.common.dto.cleintapi.LightResource;
import com.lms.common.dto.cleintapi.helper.LightResourceHelper;
import com.lms.common.dto.response.ComboObject;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ClientApiServiceImpl implements ClientApiService {

    private final ResourceService resourceService;
    private final CategoryService categoryService;
    private final MaterialTypeService materialTypeService;

    @Autowired
    public ClientApiServiceImpl(ResourceService resourceService, CategoryService categoryService, MaterialTypeService materialTypeService) {
        this.resourceService = resourceService;
        this.categoryService = categoryService;
        this.materialTypeService = materialTypeService;
    }

    @Override
    public ListResult<LightResource> find(String query, int limit, int offset) throws Exception {
        ListResult<ResourceDTO> resources = resourceService.find(query, limit, offset);
        ListResult<LightResource> result = resources.copy(LightResource.class);
        result.setResultList(LightResourceHelper.toLights(resources.getResultList()));
        return null;
    }

    @Override
    public ListResult<LightResource> find(Long id, String name, String author, String publisher, Date fromEditionDate, Date toEditionDate,
                                          ResourceTypeDTO resourceType, Long categoryCode, int limit, int offset) throws Exception {
        ListResult<ResourceDTO> resources = resourceService.find(id,
                name,
                author,
                null,
                null,
                publisher,
                fromEditionDate, toEditionDate,
                null,
                null,
                null,
                null,
                resourceType,
                null,
                null, null,
                null, null,
                categoryCode,
                limit, offset);
        ListResult<LightResource> result = resources.copy(LightResource.class);
        result.setResultList(LightResourceHelper.toLights(resources.getResultList()));
        return result;
    }

    @Override
    public ResourceDTO getResourceById(Long id) throws Exception {
        return resourceService.getResourceById(id);
    }

    @Override
    public List<ComboObject> getResourceTypes() {
        List<ComboObject> result = new ArrayList<>();
        for (ResourceType userType : ResourceType.values()) {
            result.add(new ComboObject(userType.name(), Messages.get(ResourceType.class.getSimpleName() + "_" + userType.name())));
        }
        return result;
    }

    @Override
    public ListResult<CategoryDTO> getCategories() throws Exception {
        return categoryService.find(null, -1, -1);
    }

    @Override
    public ListResult<CategoryDTO> getSpecialCategories() throws Exception {
        return categoryService.find(null, null, null, null, true, -1, -1);
    }

    @Override
    public ListResult<MaterialTypeDTO> getMaterialTypes() throws Exception {
        return materialTypeService.find(null, -1, -1);
    }

}
