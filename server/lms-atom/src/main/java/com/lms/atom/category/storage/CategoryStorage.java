package com.lms.atom.category.storage;

import com.lms.atom.category.storage.model.Category;
import com.lms.common.dto.response.ListResult;
import com.lms.utils.MathUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryStorage {

    @PersistenceContext
    private EntityManager em;

    public ListResult<Category> find(String query, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (query != null && query.length() >= 3) {
            builder.append(" WHERE LOWER(u.code) LIKE :query");
            builder.append(" OR LOWER(u.name) LIKE :query");
            builder.append(" OR LOWER(u.description) LIKE :query");
            params.put("query", "%" + query.toLowerCase() + "%");
        }
        TypedQuery<Category> q = em.createQuery("SELECT u FROM Category u " + builder.toString() + " ORDER BY u.id DESC", Category.class);
        Query cq = em.createQuery("SELECT COUNT(u.id) FROM Category u " + builder.toString());
        if (!(limit == -1 && offset == -1)) {
            q.setFirstResult(offset);
            q.setMaxResults(limit);
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
            cq.setParameter(entry.getKey(), entry.getValue());
        }
        List<Category> resultList = q.getResultList();
        ListResult<Category> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) cq.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }

    public ListResult<Category> find(Long id, String code, String name, String description, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (id != null) {
            builder.append(" AND u.id = :id");
            params.put("id", id);
        }
        if (code != null) {
            builder.append(" AND LOWER(u.code) LIKE LOWER(:code)");
            params.put("code", "%" + code + "%");
        }
        if (name != null) {
            builder.append(" AND LOWER(u.name) LIKE LOWER(:name)");
            params.put("name", "%" + name + "%");
        }
        if (description != null) {
            builder.append(" AND LOWER(u.description) LIKE LOWER(:description)");
            params.put("description", "%" + description + "%");
        }
        TypedQuery<Category> q = em.createQuery("SELECT u FROM Category u WHERE 1=1 " + builder.toString() + " ORDER BY u.id DESC", Category.class);
        Query cq = em.createQuery("SELECT COUNT(u.id) FROM Category u WHERE 1=1 " + builder.toString());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
            cq.setParameter(entry.getKey(), entry.getValue());
        }
        if (!(limit == -1 && offset == -1)) {
            q.setFirstResult(offset);
            q.setMaxResults(limit);
        }
        List<Category> resultList = q.getResultList();
        ListResult<Category> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) cq.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }
}
