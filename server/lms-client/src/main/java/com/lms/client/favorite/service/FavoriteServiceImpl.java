package com.lms.client.favorite.service;

import com.lms.atom.book.service.ResourceService;
import com.lms.atom.book.storage.ResourceHelper;
import com.lms.client.client.service.ClientService;
import com.lms.client.client.storage.ClientHelper;
import com.lms.client.exception.ClientException;
import com.lms.client.favorite.storage.FavoriteRepository;
import com.lms.client.favorite.storage.FavoriteStorage;
import com.lms.client.favorite.storage.model.Favorite;
import com.lms.common.dto.atom.resource.ResourceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    private final ResourceService resourceService;
    private final ClientService clientService;
    private final FavoriteRepository repository;
    private final FavoriteStorage storage;

    @Autowired
    public FavoriteServiceImpl(ResourceService resourceService, ClientService clientService, FavoriteRepository repository, FavoriteStorage storage) {
        this.resourceService = resourceService;
        this.clientService = clientService;
        this.repository = repository;
        this.storage = storage;
    }

    @Override
    public void addFavorite(Long clientId, Long resourceId) throws Exception {
        Favorite byClientAndResource = storage.getByClientAndResource(clientId, resourceId);
        if (byClientAndResource != null) {
            throw new ClientException("resourceIsAlreadyInFavorites");
        }
        Favorite favorite = new Favorite();
        favorite.setActionTime(new Date());
        favorite.setResource(ResourceHelper.toEntity(resourceService.getResourceById(resourceId)));
        favorite.setClient(ClientHelper.toEntity(clientService.getById(clientId)));
        repository.save(favorite);
    }

    @Override
    public void removeFavorite(Long clientId, Long resourceId) throws ClientException {
        Favorite byClientAndResource = storage.getByClientAndResource(clientId, resourceId);
        if (byClientAndResource == null) {
            throw new ClientException("resourceIsNotfavoredForThisClient");
        }
        repository.delete(byClientAndResource.getId());
    }

    @Override
    public List<ResourceDTO> getClientFavorite(Long clientId) throws ClientException {
        List<Favorite> favourites = storage.getFavourites(clientId);
        List<ResourceDTO> result = new ArrayList<>();
        for (Favorite favourite : favourites) {
            result.add(ResourceHelper.fromEntity(favourite.getResource()));
        }
        return result;
    }
}
