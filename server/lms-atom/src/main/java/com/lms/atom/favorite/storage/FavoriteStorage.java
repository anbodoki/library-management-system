package com.lms.atom.favorite.storage;

import com.lms.atom.favorite.storage.model.Favorite;
import com.lms.client.exception.ClientException;
import com.lms.client.messages.Messages;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class FavoriteStorage {

    @PersistenceContext
    private EntityManager em;

    public Favorite getByClientAndResource(Long clientId, Long resourceId) throws ClientException {
        TypedQuery<Favorite> query = em.createQuery(
                "SELECT f FROM Favorite f WHERE f.client.id = :clientId and f.resource.id = :resourceId", Favorite.class);
        query.setParameter("clientId", clientId);
        query.setParameter("resourceId", resourceId);
        List<Favorite> resultList = query.getResultList();
        if (resultList.size() > 1) {
            throw new ClientException(Messages.get("manyFavoritesForUniqueClientAndResource"));
        }
        if (resultList.size() < 1) {
            return null;
        }
        return resultList.get(0);
    }

    public List<Favorite> getFavourites(Long clientId) throws ClientException {
        TypedQuery<Favorite> query = em.createQuery(
                "SELECT f FROM Favorite f WHERE f.client.id = :clientId", Favorite.class);
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }

    public List<Long> getClintFavoriteIds(Long clientId) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT f.resource.id FROM Favorite f WHERE f.client.id = :clientId", Long.class);
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }
}
