package com.lms.security.role.storage;

import com.lms.common.dto.response.ListResult;
import com.lms.security.role.storage.model.Role;
import com.lms.utils.MathUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoleStorage {

    @PersistenceContext
    private EntityManager em;

    public ListResult<Role> find(String query, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (query != null && query.length() >= 3) {
            builder.append(" WHERE LOWER(r.name) LIKE :query AND r.hidden = false");
            params.put("query", "%" + query.toLowerCase() + "%");
        } else {
            builder.append(" WHERE r.hidden = false");
        }
        TypedQuery<Role> q = em.createQuery("SELECT r FROM Role r " + builder.toString() + " ORDER BY r.id DESC", Role.class);
        Query cq = em.createQuery("SELECT COUNT(r.id) FROM Role r " + builder.toString());
        if (!(limit == -1 && offset == -1)) {
            q.setFirstResult(offset);
            q.setMaxResults(limit);
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
            cq.setParameter(entry.getKey(), entry.getValue());
        }
        List<Role> resultList = q.getResultList();
        ListResult<Role> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) cq.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }

    public ListResult<Role> findByFilters(Long id, String name, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (id != null) {
            builder.append(" AND r.id = :id");
            params.put("id", id);
        }
        if (name != null && !StringUtils.isEmpty(name)) {
            builder.append(" AND LOWER(r.name) LIKE LOWER(:name)");
            params.put("name", "%" + name + "%");
        }

        TypedQuery<Role> q = em.createQuery("SELECT r FROM Role r WHERE r.hidden = false " + builder.toString() + " ORDER BY r.id DESC", Role.class);
        Query cq = em.createQuery("SELECT COUNT(r.id) FROM Role r WHERE r.hidden = false " + builder.toString());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
            cq.setParameter(entry.getKey(), entry.getValue());
        }
        if (!(limit == -1 && offset == -1)) {
            q.setFirstResult(offset);
            q.setMaxResults(limit);
        }
        List<Role> resultList = q.getResultList();
        ListResult<Role> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) cq.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }

}
