package com.lms.client.api.clientapi;

import com.lms.atom.book.service.ResourceService;
import com.lms.atom.book.storage.model.ResourceType;
import com.lms.atom.category.service.CategoryService;
import com.lms.atom.material.service.MaterialTypeService;
import com.lms.atom.messages.Messages;
import com.lms.client.client.service.ClientService;
import com.lms.client.favorite.service.FavoriteService;
import com.lms.client.school.service.SchoolService;
import com.lms.common.dto.atom.category.CategoryDTO;
import com.lms.common.dto.atom.materialtype.MaterialTypeDTO;
import com.lms.common.dto.atom.resource.ResourceDTO;
import com.lms.common.dto.atom.resource.ResourceTypeDTO;
import com.lms.common.dto.cleintapi.LightResource;
import com.lms.common.dto.cleintapi.helper.LightResourceHelper;
import com.lms.common.dto.client.ClientDTO;
import com.lms.common.dto.client.SchoolDTO;
import com.lms.common.dto.response.ComboObject;
import com.lms.common.dto.response.ListResult;
import com.lms.configuration.cache.ConfigurationPropertyCodes;
import com.lms.configuration.properties.service.ConfigurationPropertyService;
import com.lms.configuration.properties.storage.model.ConfigurationProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ClientApiServiceImpl implements ClientApiService {

    public static String URL = "http://localhost:8080";

    private final ResourceService resourceService;
    private final CategoryService categoryService;
    private final MaterialTypeService materialTypeService;
    private final ClientService clientService;
    private final SchoolService schoolService;
    private final FavoriteService favoriteService;
    private final ConfigurationPropertyService configurationPropertyService;

    @Autowired
    public ClientApiServiceImpl(ResourceService resourceService, CategoryService categoryService, MaterialTypeService materialTypeService, ClientService clientService, SchoolService schoolService, FavoriteService favoriteService, ConfigurationPropertyService configurationPropertyService) {
        this.resourceService = resourceService;
        this.categoryService = categoryService;
        this.materialTypeService = materialTypeService;
        this.clientService = clientService;
        this.schoolService = schoolService;
        this.favoriteService = favoriteService;
        this.configurationPropertyService = configurationPropertyService;
    }

    @Override
    public ListResult<LightResource> find(String query, int limit, int offset) throws Exception {
        ListResult<ResourceDTO> resources = resourceService.find(query, limit, offset);
        ListResult<LightResource> result = resources.copy(LightResource.class);
        result.setResultList(LightResourceHelper.toLights(resources.getResultList()));
        //TODO get client from security context and determine their favourite resources
        ConfigurationProperty configurationProperty = configurationPropertyService.get(ConfigurationPropertyCodes.SERVER_BASE_URL);
        if (configurationProperty != null) {
            URL = configurationProperty.getStringValue();
        }
        for (LightResource lightResource : result.getResultList()) {
            lightResource.setImageUrl(URL + lightResource.getImageUrl());
        }
        return result;
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
                resourceType,
                null,
                null, null,
                null, null,
                categoryCode,
                null,
                null,
                limit, offset);
        ListResult<LightResource> result = resources.copy(LightResource.class);
        result.setResultList(LightResourceHelper.toLights(resources.getResultList()));
        ConfigurationProperty configurationProperty = configurationPropertyService.get(ConfigurationPropertyCodes.SERVER_BASE_URL);
        if (configurationProperty != null) {
            URL = configurationProperty.getStringValue();
        }
        for (LightResource lightResource : result.getResultList()) {
            lightResource.setImageUrl(URL + lightResource.getImageUrl());
        }
        return result;
    }

    @Override
    public ResourceDTO getResourceById(Long id) throws Exception {
        ResourceDTO resourceById = resourceService.getResourceById(id);
        ConfigurationProperty configurationProperty = configurationPropertyService.get(ConfigurationPropertyCodes.SERVER_BASE_URL);
        if (configurationProperty != null) {
            URL = configurationProperty.getStringValue();
        }
        resourceById.setImageUrl(URL + resourceById.getImageUrl());
        //TODO get client from security context and determine their favourite resources
        return resourceById;
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

    @Override
    public ListResult<SchoolDTO> getSchools() throws Exception {
        return schoolService.find(null, -1, -1);
    }

    @Override
    public ClientDTO getClientById(Long id) throws Exception {
        return clientService.getById(id);
    }

    @Override
    public ClientDTO updateClient(Long clientId, String firstName, String lastName, String phone, Long schoolId) throws Exception {
        ClientDTO client = clientService.getById(clientId);
        if (firstName != null) {
            client.setFirstName(firstName);
        }
        if (lastName != null) {
            client.setLastName(lastName);
        }
        if (phone != null) {
            client.setPhone(phone);
        }
        if (schoolId != null) {
            client.setSchool(schoolService.getById(schoolId));
        }
        return clientService.update(client);
    }

    @Override
    public void addFavorite(Long clientId, Long resourceId) throws Exception {
        favoriteService.addFavorite(clientId, resourceId);
    }

    @Override
    public void removeFavorite(Long clientId, Long resourceId) throws Exception {
        favoriteService.removeFavorite(clientId, resourceId);
    }

    @Override
    public List<LightResource> getClientFavorite(Long clientId) throws Exception {
        return LightResourceHelper.toLights(favoriteService.getClientFavorite(clientId));
    }

}
