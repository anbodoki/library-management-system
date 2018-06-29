package com.lms.client.school.storage;

import com.lms.client.school.storage.model.School;
import com.lms.client.school.storage.model.University;
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
public class SchoolStorage {

    @PersistenceContext
    private EntityManager em;

    public ListResult<School> find(String query, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (query != null && query.length() >= 3) {
            builder.append(" WHERE LOWER(u.name) LIKE :query");
            params.put("query", "%" + query.toLowerCase() + "%");
        }
        TypedQuery<School> q = em.createQuery("SELECT u FROM School u " + builder.toString() + " ORDER BY u.id DESC", School.class);
        Query cq = em.createQuery("SELECT COUNT(u.id) FROM School u " + builder.toString());
        if (!(limit == -1 && offset == -1)) {
            q.setFirstResult(offset);
            q.setMaxResults(limit);
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
            cq.setParameter(entry.getKey(), entry.getValue());
        }
        List<School> resultList = q.getResultList();
        ListResult<School> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) cq.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }

    public ListResult<School> find(Long id, String name, University university, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (id != null) {
            builder.append(" AND u.id = :id");
            params.put("id", id);
        }
        if (university != null) {
            builder.append(" AND university = :university");
            params.put("university", university);
        }
        if (name != null) {
            builder.append(" AND LOWER(u.name) LIKE LOWER(:name)");
            params.put("name", "%" + name + "%");
        }
        TypedQuery<School> q = em.createQuery("SELECT u FROM School u WHERE 1=1 " + builder.toString() + " ORDER BY u.id DESC", School.class);
        Query cq = em.createQuery("SELECT COUNT(u.id) FROM School u WHERE 1=1 " + builder.toString());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
            cq.setParameter(entry.getKey(), entry.getValue());
        }
        if (!(limit == -1 && offset == -1)) {
            q.setFirstResult(offset);
            q.setMaxResults(limit);
        }
        List<School> resultList = q.getResultList();
        ListResult<School> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) cq.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }
}
