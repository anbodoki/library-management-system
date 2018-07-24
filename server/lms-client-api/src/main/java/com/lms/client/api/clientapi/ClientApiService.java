package com.lms.client.api.clientapi;

import com.lms.common.dto.atom.category.CategoryDTO;
import com.lms.common.dto.atom.language.LanguageDTO;
import com.lms.common.dto.atom.materialtype.MaterialTypeDTO;
import com.lms.common.dto.atom.notification.NotificationDTO;
import com.lms.common.dto.atom.notification.NotificationResponse;
import com.lms.common.dto.atom.resource.ResourceBorrowDTO;
import com.lms.common.dto.atom.resource.ResourceDTO;
import com.lms.common.dto.atom.resource.ResourceTypeDTO;
import com.lms.common.dto.cleintapi.LightResource;
import com.lms.common.dto.client.CardDTO;
import com.lms.common.dto.client.ClientDTO;
import com.lms.common.dto.client.SchoolDTO;
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
                                   String editionYear,
                                   ResourceTypeDTO resourceType,
                                   List<Long> categoryIds,
                                   Long languageId,
                                   int limit, int offset) throws Exception;

    ResourceDTO getResourceById(Long id) throws Exception;

    List<ComboObject> getResourceTypes();

    ListResult<CategoryDTO> getCategories() throws Exception;

    ListResult<CategoryDTO> getSpecialCategories() throws Exception;

    ListResult<MaterialTypeDTO> getMaterialTypes() throws Exception;

    ListResult<SchoolDTO> getSchools() throws Exception;

    ClientDTO getClientById(Long id) throws Exception;

    ClientDTO updateClient(Long clientId, String firstName, String lastName, String phone, Long schoolId) throws Exception;

    void addFavorite(Long resourceId) throws Exception;

    void removeFavorite(Long resourceId) throws Exception;

    List<LightResource> getClientFavorite() throws Exception;

    ClientDTO getAuthorizedUser(String token) throws Exception;

    ListResult<ResourceBorrowDTO> getClientResourceBorrow(Long clientId, boolean current, int limit, int offset);

    ListResult<LanguageDTO> getLanguages() throws Exception;

    NotificationResponse getNotificationsForClient(int limit, int offset);

    void markAsSeen();

    void markAsRead(Long notificationId);

    void logout();

    CardDTO activateCard(Long cardId) throws Exception;

    CardDTO deactivateCard(Long cardId) throws Exception;

    void deleteCard(Long cardId) throws Exception;
}
