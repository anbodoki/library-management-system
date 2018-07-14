package com.lms.atom.favorite.service;

import com.lms.client.exception.ClientException;
import com.lms.common.dto.atom.resource.ResourceDTO;

import java.util.List;

public interface FavoriteService {

    void addFavorite(Long resourceId) throws Exception;

    void removeFavorite(Long resourceId) throws ClientException;

    List<ResourceDTO> getClientFavorite() throws ClientException;

    List<Long> getClintFavoriteIds();
}
