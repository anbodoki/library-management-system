package com.lms.client.favorite.service;

import com.lms.atom.exception.AtomException;
import com.lms.client.exception.ClientException;
import com.lms.common.dto.atom.resource.ResourceDTO;
import com.lms.common.dto.response.ListResult;

import java.util.List;

public interface FavoriteService {

    void addFavorite(Long resourceId) throws Exception;

    void removeFavorite(Long resourceId) throws ClientException;

    List<ResourceDTO> getClientFavorite() throws ClientException;

    List<Long> getClintFavoriteIds();
}
