package com.lms.client.api.clientapi;

import com.lms.atom.book.service.ResourceService;
import com.lms.atom.book.storage.model.ResourceType;
import com.lms.atom.borrow.service.ResourceBorrowService;
import com.lms.atom.category.service.CategoryService;
import com.lms.atom.favorite.service.FavoriteService;
import com.lms.atom.language.service.LanguageService;
import com.lms.atom.material.service.MaterialTypeService;
import com.lms.atom.messages.Messages;
import com.lms.atom.notofication.service.NotificationService;
import com.lms.client.client.service.ClientService;
import com.lms.client.exception.ClientException;
import com.lms.client.school.service.SchoolService;
import com.lms.common.dto.atom.category.CategoryDTO;
import com.lms.common.dto.atom.language.LanguageDTO;
import com.lms.common.dto.atom.materialtype.MaterialTypeDTO;
import com.lms.common.dto.atom.notification.NotificationDTO;
import com.lms.common.dto.atom.notification.NotificationResponse;
import com.lms.common.dto.atom.resource.ResourceBorrowDTO;
import com.lms.common.dto.atom.resource.ResourceDTO;
import com.lms.common.dto.atom.resource.ResourceTypeDTO;
import com.lms.common.dto.cleintapi.LightResource;
import com.lms.common.dto.cleintapi.helper.LightResourceHelper;
import com.lms.common.dto.client.CardDTO;
import com.lms.common.dto.client.ClientDTO;
import com.lms.common.dto.client.SchoolDTO;
import com.lms.common.dto.response.ComboObject;
import com.lms.common.dto.response.ListResult;
import com.lms.configuration.cache.ConfigurationPropertyCodes;
import com.lms.configuration.properties.service.ConfigurationPropertyService;
import com.lms.configuration.properties.storage.model.ConfigurationProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ClientApiServiceImpl implements ClientApiService {

    public static String DEFAULT_URL = "http://localhost:8080";

    private final ResourceService resourceService;
    private final CategoryService categoryService;
    private final MaterialTypeService materialTypeService;
    private final ClientService clientService;
    private final SchoolService schoolService;
    private final FavoriteService favoriteService;
    private final ConfigurationPropertyService configurationPropertyService;
    private final ResourceBorrowService resourceBorrowService;
    private final LanguageService languageService;
    private final NotificationService notificationService;

    @Autowired
    public ClientApiServiceImpl(ResourceService resourceService, CategoryService categoryService, MaterialTypeService materialTypeService, ClientService clientService, SchoolService schoolService, FavoriteService favoriteService, ConfigurationPropertyService configurationPropertyService, ResourceBorrowService resourceBorrowService, LanguageService languageService, NotificationService notificationService) {
        this.resourceService = resourceService;
        this.categoryService = categoryService;
        this.materialTypeService = materialTypeService;
        this.clientService = clientService;
        this.schoolService = schoolService;
        this.favoriteService = favoriteService;
        this.configurationPropertyService = configurationPropertyService;
        this.resourceBorrowService = resourceBorrowService;
        this.languageService = languageService;
        this.notificationService = notificationService;
    }

    @Override
    public ListResult<LightResource> find(String query, int limit, int offset) throws Exception {
        ListResult<ResourceDTO> resources = resourceService.find(query, limit, offset);
        ListResult<LightResource> result = resources.copy(LightResource.class);
        result.setResultList(LightResourceHelper.toLights(resources.getResultList()));
        setProperImageURL(result.getResultList());
        setProperResourceURL(result.getResultList());
        setFavourites(result.getResultList());
        return result;
    }

    @Override
    public ListResult<LightResource> find(Long id, String name, String author, String publisher, String editionYear,
                                          ResourceTypeDTO resourceType, List<Long> categoryIds, Long languageId, int limit, int offset) throws Exception {
        ListResult<ResourceDTO> resources = resourceService.findSpecial(id,
                name,
                author,
                null,
                null,
                publisher,
                editionYear,
                languageId,
                null,
                null,
                resourceType,
                null,
                null, null,
                null, null,
                categoryIds,
                null,
                null,
                limit, offset);
        ListResult<LightResource> result = resources.copy(LightResource.class);
        result.setResultList(LightResourceHelper.toLights(resources.getResultList()));
        setProperImageURL(result.getResultList());
        setProperResourceURL(result.getResultList());
        setFavourites(result.getResultList());
        return result;
    }

    @Override
    public ResourceDTO getResourceById(Long id) throws Exception {
        ResourceDTO resourceById = resourceService.getResourceById(id);
        ConfigurationProperty configurationProperty = configurationPropertyService.get(ConfigurationPropertyCodes.SERVER_BASE_URL);
        if (configurationProperty != null) {
            DEFAULT_URL = configurationProperty.getStringValue();
        }
        if (resourceById.getImageUrl() != null) {
            resourceById.setImageUrl(DEFAULT_URL + resourceById.getImageUrl());
        }
        if (resourceById.getResourceUrl() != null) {
            resourceById.setResourceUrl(DEFAULT_URL + resourceById.getResourceUrl());
        }
        List<Long> favoriteIds = favoriteService.getClintFavoriteIds();
        if (favoriteIds.contains(resourceById.getId())) {
            resourceById.setClientFavorite(true);
        }
        NotificationDTO notification = notificationService.getNotificationForResource(id);
        if (notification != null) {
            resourceById.setNotification(notification);
        }
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
    public void addFavorite(Long resourceId) throws Exception {
        favoriteService.addFavorite(resourceId);
    }

    @Override
    public void removeFavorite(Long resourceId) throws Exception {
        favoriteService.removeFavorite(resourceId);
    }

    @Override
    public List<LightResource> getClientFavorite() throws Exception {
        List<LightResource> result = LightResourceHelper.toLights(favoriteService.getClientFavorite());
        setProperImageURL(result);
        setProperResourceURL(result);
        for (LightResource lightResource : result) {
            lightResource.setClientFavorite(true);
        }
        return result;
    }

    @Override
    public ClientDTO getAuthorizedUser(String token) throws ClientException {
        return clientService.getAuthorizedClient();
    }

    @Override
    public ListResult<ResourceBorrowDTO> getClientResourceBorrow(Long clientId, boolean current, int limit, int offset) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ClientDTO client = clientService.getByEmail(authentication.getName());
        ListResult<ResourceBorrowDTO> result = resourceBorrowService.getClientResourceBorrow(client.getId(), current, limit, offset);
        List<ResourceDTO> resources = new ArrayList<>();
        for (ResourceBorrowDTO resourceBorrow : result.getResultList()) {
            resources.add(resourceBorrow.getResourceCopy().getResource());
        }
        setProperImageURLResource(resources);
        setProperResourceURLResource(resources);
        return result;
    }

    @Override
    public ListResult<LanguageDTO> getLanguages() throws Exception {
        return languageService.find(null, -1, -1);
    }

    @Override
    public NotificationResponse getNotificationsForClient(int limit, int offset) {
        NotificationResponse response = new NotificationResponse();
        response.setNotifications(notificationService.getNotificationsForClient(limit, offset));
        response.setCount(notificationService.getUnseenNotificationsCount());
        return response;
    }

    @Override
    public void markAsSeen() {
        notificationService.markAsSeen();
    }

    @Override
    public void markAsRead(Long notificationId) {
        notificationService.markAsRead(notificationId);
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    @Override
    public CardDTO activateCard(Long cardId) throws Exception {
        return clientService.activateCard(cardId);
    }

    @Override
    public CardDTO deactivateCard(Long cardId) throws Exception {
        return clientService.deactivateCard(cardId);
    }

    @Override
    public void deleteCard(Long cardId) throws Exception {
        clientService.deleteCard(cardId);
    }

    private void setProperImageURL(List<LightResource> resources) {
        ConfigurationProperty configurationProperty = configurationPropertyService.get(ConfigurationPropertyCodes.SERVER_BASE_URL);
        if (configurationProperty != null) {
            DEFAULT_URL = configurationProperty.getStringValue();
        }
        for (LightResource lightResource : resources) {
            if (lightResource.getImageUrl() != null) {
                lightResource.setImageUrl(DEFAULT_URL + lightResource.getImageUrl());
            }
        }
    }

    private void setProperImageURLResource(List<ResourceDTO> resources) {
        ConfigurationProperty configurationProperty = configurationPropertyService.get(ConfigurationPropertyCodes.SERVER_BASE_URL);
        if (configurationProperty != null) {
            DEFAULT_URL = configurationProperty.getStringValue();
        }
        for (ResourceDTO lightResource : resources) {
            if (lightResource.getImageUrl() != null) {
                lightResource.setImageUrl(DEFAULT_URL + lightResource.getImageUrl());
            }
        }
    }

    private void setProperResourceURL(List<LightResource> resources) {
        ConfigurationProperty configurationProperty = configurationPropertyService.get(ConfigurationPropertyCodes.SERVER_BASE_URL);
        if (configurationProperty != null) {
            DEFAULT_URL = configurationProperty.getStringValue();
        }
        for (LightResource lightResource : resources) {
            if (lightResource.getResourceUrl() != null) {
                lightResource.setResourceUrl(DEFAULT_URL + lightResource.getResourceUrl());
            }
        }
    }

    private void setProperResourceURLResource(List<ResourceDTO> resources) {
        ConfigurationProperty configurationProperty = configurationPropertyService.get(ConfigurationPropertyCodes.SERVER_BASE_URL);
        if (configurationProperty != null) {
            DEFAULT_URL = configurationProperty.getStringValue();
        }
        for (ResourceDTO lightResource : resources) {
            if (lightResource.getResourceUrl() != null) {
                lightResource.setResourceUrl(DEFAULT_URL + lightResource.getResourceUrl());
            }
        }
    }

    private void setFavourites(List<LightResource> resources) {
        List<Long> favoriteIds = favoriteService.getClintFavoriteIds();
        for (LightResource lightResource : resources) {
            if (favoriteIds.contains(lightResource.getId())) {
                lightResource.setClientFavorite(true);
            }
        }
    }
}