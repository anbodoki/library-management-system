package com.lms.application.controller.clientapi;

import com.lms.application.security.ClientPermissionCheck;
import com.lms.client.api.clientapi.ClientApiService;
import com.lms.common.dto.atom.notification.NotificationDTO;
import com.lms.common.dto.atom.resource.ResourceBorrowDTO;
import com.lms.common.dto.cleintapi.AddRemoveFavouriteRequest;
import com.lms.common.dto.cleintapi.ClientResourceFilteringRequest;
import com.lms.common.dto.cleintapi.ClientUpdateRequest;
import com.lms.common.dto.cleintapi.LightResource;
import com.lms.common.dto.client.ClientDTO;
import com.lms.common.dto.request.ClientResourceCopyHistoryRequest;
import com.lms.common.dto.request.GeneralFilteringRequest;
import com.lms.common.dto.response.ActionResponse;
import com.lms.common.dto.response.ActionResponseWithData;
import com.lms.common.dto.response.ListResult;
import com.lms.common.dto.response.PagingRequest;
import com.lms.security.auth.GoogleConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/client/api/")
public class ClientApiController {

    private final ClientApiService service;

    @Autowired
    public ClientApiController(ClientApiService service) {
        this.service = service;
    }

    @GetMapping(value = "get-authorized-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ClientPermissionCheck
    public ActionResponse getAuthorizedUser(@RequestHeader HttpHeaders headers) throws Exception {
        if (headers.get(GoogleConstants.HEADER_STRING) == null) {
            return new ActionResponse(false);
        }
        return new ActionResponseWithData<>(service.getAuthorizedUser(headers.get(GoogleConstants.HEADER_STRING).get(0)), true);
    }

    @PostMapping(value = "quick-find-resources", produces = MediaType.APPLICATION_JSON_VALUE)
    @ClientPermissionCheck
    public ActionResponse find(@RequestBody GeneralFilteringRequest generalFilteringRequest) throws Exception {
        ListResult<LightResource> result = service.find(generalFilteringRequest.getQuery(), generalFilteringRequest.getLimit(), generalFilteringRequest.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "find-resources", produces = MediaType.APPLICATION_JSON_VALUE)
    @ClientPermissionCheck
    public ActionResponse find(@RequestBody ClientResourceFilteringRequest request) throws Exception {
        ListResult<LightResource> result = service.find(request.getId(),
                request.getName(),
                request.getAuthor(),
                request.getPublisher(),
                request.getFromEditionDate(), request.getToEditionDate(),
                request.getResourceType(),
                request.getCategoryIds(),
                request.getLanguageId(),
                request.getLimit(), request.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @GetMapping(value = "get-resource/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ClientPermissionCheck
    public ActionResponse findByUsername(@PathVariable Long id) throws Exception {
        return new ActionResponseWithData<>(service.getResourceById(id), true);
    }

    @GetMapping(value = "get-resource-types", produces = MediaType.APPLICATION_JSON_VALUE)
    @ClientPermissionCheck
    public ActionResponse getResourceTypes() throws Exception {
        return new ActionResponseWithData<>(service.getResourceTypes(), true);
    }

    @GetMapping(value = "get-categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ClientPermissionCheck
    public ActionResponse getCategories() throws Exception {
        return new ActionResponseWithData<>(service.getCategories(), true);
    }

    @GetMapping(value = "get-special-categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ClientPermissionCheck
    public ActionResponse getSpecialCategories() throws Exception {
        return new ActionResponseWithData<>(service.getSpecialCategories(), true);
    }

    @GetMapping(value = "get-material-types", produces = MediaType.APPLICATION_JSON_VALUE)
    @ClientPermissionCheck
    public ActionResponse getMaterialTypes() throws Exception {
        return new ActionResponseWithData<>(service.getMaterialTypes(), true);
    }

    @GetMapping(value = "get-client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ClientPermissionCheck
    public ActionResponse getClientById(@PathVariable Long id) throws Exception {
        return new ActionResponseWithData<>(service.getClientById(id), true);
    }

    @GetMapping(value = "get-schools", produces = MediaType.APPLICATION_JSON_VALUE)
    @ClientPermissionCheck
    public ActionResponse getSchools() throws Exception {
        return new ActionResponseWithData<>(service.getSchools(), true);
    }

    @GetMapping(value = "get-languages", produces = MediaType.APPLICATION_JSON_VALUE)
    @ClientPermissionCheck
    public ActionResponse getLanguages() throws Exception {
        return new ActionResponseWithData<>(service.getLanguages(), true);
    }

    @PostMapping(value = "update-client", produces = MediaType.APPLICATION_JSON_VALUE)
    @ClientPermissionCheck
    public ActionResponse find(@RequestBody ClientUpdateRequest clientUpdateRequest) throws Exception {
        ClientDTO result = service.updateClient(clientUpdateRequest.getClientId(),
                clientUpdateRequest.getFirstName(), clientUpdateRequest.getLastName(),
                clientUpdateRequest.getPhone(), clientUpdateRequest.getSchoolId());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "add-favourite", produces = MediaType.APPLICATION_JSON_VALUE)
    @ClientPermissionCheck
    public ActionResponse addFavourite(@RequestBody AddRemoveFavouriteRequest request) throws Exception {
        service.addFavorite(request.getResourceId());
        return new ActionResponse(true);
    }

    @PostMapping(value = "remove-favourite", produces = MediaType.APPLICATION_JSON_VALUE)
    @ClientPermissionCheck
    public ActionResponse removeFavourite(@RequestBody AddRemoveFavouriteRequest request) throws Exception {
        service.removeFavorite(request.getResourceId());
        return new ActionResponse(true);
    }

    @GetMapping(value = "get-client-favourite", produces = MediaType.APPLICATION_JSON_VALUE)
    @ClientPermissionCheck
    public ActionResponse getClientFavourite() throws Exception {
        return new ActionResponseWithData<>(service.getClientFavorite(), true);
    }

    @PostMapping(value = "get-client-resource-copy-borrow-history", produces = MediaType.APPLICATION_JSON_VALUE)
    @ClientPermissionCheck
    public ActionResponse getResourceBorrowHistory(@RequestBody ClientResourceCopyHistoryRequest request) throws Exception {
        ListResult<ResourceBorrowDTO> result = service.getClientResourceBorrow(request.getClientId(), request.getCurrent(), request.getLimit(), request.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "get-notifications", produces = MediaType.APPLICATION_JSON_VALUE)
    @ClientPermissionCheck
    public ActionResponse getNotifications(@RequestBody PagingRequest request) throws Exception {
        ListResult<NotificationDTO> result = service.getNotificationsForClient(request.getLimit(), request.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "mark-as-seen")
    @ClientPermissionCheck
    public ActionResponse markAsSeen() throws Exception {
        service.markAsSeen();
        return new ActionResponse(true);
    }

    @PostMapping(value = "mark-as-read/{notificationId}")
    @ClientPermissionCheck
    public ActionResponse markAsRead(@PathVariable Long notificationId) throws Exception {
        service.markAsRead(notificationId);
        return new ActionResponse(true);
    }
}
