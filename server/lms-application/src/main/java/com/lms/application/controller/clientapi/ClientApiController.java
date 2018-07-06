package com.lms.application.controller.clientapi;

import com.lms.client.api.clientapi.ClientApiService;
import com.lms.common.dto.cleintapi.AddRemoveFavouriteRequest;
import com.lms.common.dto.cleintapi.ClientResourceFilteringRequest;
import com.lms.common.dto.cleintapi.ClientUpdateRequest;
import com.lms.common.dto.cleintapi.LightResource;
import com.lms.common.dto.client.ClientDTO;
import com.lms.common.dto.request.GeneralFilteringRequest;
import com.lms.common.dto.response.ActionResponse;
import com.lms.common.dto.response.ActionResponseWithData;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/client/api/")
//TODO add client permission to all method
public class ClientApiController {

    private final ClientApiService service;

    @Autowired
    public ClientApiController(ClientApiService service) {
        this.service = service;
    }

    @PostMapping(value = "quick-find-resources", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse find(@RequestBody GeneralFilteringRequest generalFilteringRequest) throws Exception {
        ListResult<LightResource> result = service.find(generalFilteringRequest.getQuery(), generalFilteringRequest.getLimit(), generalFilteringRequest.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "find-resources", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse find(@RequestBody ClientResourceFilteringRequest request) throws Exception {
        ListResult<LightResource> result = service.find(request.getId(),
                request.getName(),
                request.getAuthor(),
                request.getPublisher(),
                request.getFromEditionDate(), request.getToEditionDate(),
                request.getResourceType(),
                request.getCategoryId(),
                request.getLimit(), request.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @GetMapping(value = "get-resource/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse findByUsername(@PathVariable Long id) throws Exception {
        return new ActionResponseWithData<>(service.getResourceById(id), true);
    }

    @GetMapping(value = "get-resource-types", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse getResourceTypes() throws Exception {
        return new ActionResponseWithData<>(service.getResourceTypes(), true);
    }

    @GetMapping(value = "get-categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse getCategories() throws Exception {
        return new ActionResponseWithData<>(service.getCategories(), true);
    }

    @GetMapping(value = "get-special-categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse getSpecialCategories() throws Exception {
        return new ActionResponseWithData<>(service.getSpecialCategories(), true);
    }

    @GetMapping(value = "get-material-types", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse getMaterialTypes() throws Exception {
        return new ActionResponseWithData<>(service.getMaterialTypes(), true);
    }

    @GetMapping(value = "get-client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse getClientById(@PathVariable Long id) throws Exception {
        return new ActionResponseWithData<>(service.getClientById(id), true);
    }

    @GetMapping(value = "get-schools", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse getSchools() throws Exception {
        return new ActionResponseWithData<>(service.getSchools(), true);
    }

    @PostMapping(value = "update-client", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse find(@RequestBody ClientUpdateRequest clientUpdateRequest) throws Exception {
        ClientDTO result = service.updateClient(clientUpdateRequest.getClientId(),
                clientUpdateRequest.getFirstName(), clientUpdateRequest.getLastName(),
                clientUpdateRequest.getPhone(), clientUpdateRequest.getSchoolId());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "add-favourite", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse addFavourite(@RequestBody AddRemoveFavouriteRequest request) throws Exception {
        service.addFavorite(request.getClientId(), request.getResourceId());
        return new ActionResponse(true);
    }

    @PostMapping(value = "remove-favourite", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse removeFavourite(@RequestBody AddRemoveFavouriteRequest request) throws Exception {
        service.removeFavorite(request.getClientId(), request.getResourceId());
        return new ActionResponse(true);
    }

    @GetMapping(value = "get-client-favourite/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse getClientFavourite(@PathVariable Long clientId) throws Exception {
        return new ActionResponseWithData<>(service.getClientFavorite(clientId), true);
    }
}
