package com.lms.configuration.properties.storage;

import com.lms.common.dto.response.ListResult;
import com.lms.configuration.properties.storage.model.ConfigurationProperty;
import com.lms.configuration.properties.storage.model.ConfigurationPropertyType;
import com.lms.security.utils.MathUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ConfigurationPropertyStorage {

    @PersistenceContext
    private EntityManager em;

    public ListResult<ConfigurationProperty> find(String query, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (query != null && query.length() >= 3) {
            builder.append(" WHERE LOWER(d.code) LIKE LOWER(:query)");
            params.put("query", "%" + query.toLowerCase() + "%");
        }
        TypedQuery<ConfigurationProperty> q = em.createQuery("SELECT d FROM ConfigurationProperty d " + builder.toString() + " ORDER BY d.id DESC", ConfigurationProperty.class);
        Query cq = em.createQuery("SELECT COUNT(d.id) FROM ConfigurationProperty d " + builder.toString());
        if (!(limit == -1 && offset == -1)) {
            q.setFirstResult(offset);
            q.setMaxResults(limit);
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
            cq.setParameter(entry.getKey(), entry.getValue());
        }
        List<ConfigurationProperty> resultList = q.getResultList();
        ListResult<ConfigurationProperty> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) cq.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }

    public ListResult<ConfigurationProperty> find(Long id, String code, ConfigurationPropertyType configurationPropertyType, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (id != null) {
            builder.append(" AND d.id = :id");
            params.put("id", id);
        }
        if (code != null) {
            builder.append(" AND d.code = :code");
            params.put("code", code);
        }
        if (configurationPropertyType != null) {
            builder.append(" AND d.type = :type");
            params.put("type", configurationPropertyType);
        }
        TypedQuery<ConfigurationProperty> q = em.createQuery("SELECT d FROM ConfigurationProperty d WHERE 1=1 " + builder.toString() + " ORDER BY d.id DESC", ConfigurationProperty.class);
        Query cq = em.createQuery("SELECT COUNT(d.id) FROM ConfigurationProperty d WHERE 1=1 " + builder.toString());
        if (!(limit == -1 && offset == -1)) {
            q.setFirstResult(offset);
            q.setMaxResults(limit);
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
            cq.setParameter(entry.getKey(), entry.getValue());
        }
        List<ConfigurationProperty> resultList = q.getResultList();
        ListResult<ConfigurationProperty> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) cq.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }

    public ConfigurationProperty get(String code) {
        TypedQuery<ConfigurationProperty> query = em.createQuery("SELECT c FROM ConfigurationProperty c WHERE c.code = :code", ConfigurationProperty.class);
        query.setParameter("code", code);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
