package com.lms.atom.favorite.service;

import com.lms.atom.book.service.ResourceService;
import com.lms.atom.book.storage.ResourceHelper;
import com.lms.atom.favorite.storage.FavoriteRepository;
import com.lms.atom.favorite.storage.FavoriteStorage;
import com.lms.atom.favorite.storage.model.Favorite;
import com.lms.client.client.service.ClientService;
import com.lms.client.client.storage.ClientHelper;
import com.lms.client.exception.ClientException;
import com.lms.common.dto.atom.resource.ResourceDTO;
import com.lms.common.dto.client.ClientDTO;
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
    public void addFavorite(Long resourceId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ClientDTO client = clientService.getByEmail(authentication.getName());
        Favorite byClientAndResource = storage.getByClientAndResource(client.getId(), resourceId);
        if (byClientAndResource != null) {
            throw new ClientException("resourceIsAlreadyInFavorites");
        }
        Favorite favorite = new Favorite();
        favorite.setActionTime(new Date());
        favorite.setResource(ResourceHelper.toEntity(resourceService.getResourceById(resourceId)));
        favorite.setClient(ClientHelper.toEntity(clientService.getById(client.getId())));
        repository.save(favorite);
    }

    @Override
    public void removeFavorite(Long resourceId) throws ClientException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ClientDTO client = clientService.getByEmail(authentication.getName());
        Favorite byClientAndResource = storage.getByClientAndResource(client.getId(), resourceId);
        if (byClientAndResource == null) {
            throw new ClientException("resourceIsNotfavoredForThisClient");
        }
        repository.delete(byClientAndResource.getId());
    }

    @Override
    public List<ResourceDTO> getClientFavorite() throws ClientException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ClientDTO client = clientService.getByEmail(authentication.getName());
        List<Favorite> favourites = storage.getFavourites(client.getId());
        List<ResourceDTO> result = new ArrayList<>();
        for (Favorite favourite : favourites) {
            result.add(ResourceHelper.fromEntity(favourite.getResource()));
        }
        return result;
    }

    @Override
    public List<Long> getClintFavoriteIds() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ClientDTO client = clientService.getByEmail(authentication.getName());
        return storage.getClintFavoriteIds(client.getId());
    }
}
