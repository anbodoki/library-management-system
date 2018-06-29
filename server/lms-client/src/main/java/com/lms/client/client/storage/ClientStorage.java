package com.lms.client.client.storage;

import com.lms.client.client.storage.model.Client;
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
public class ClientStorage {

    @PersistenceContext
    private EntityManager em;

    public ListResult<Client> find(String query, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (query != null && query.length() >= 3) {
            builder.append(" WHERE LOWER(u.firstName) LIKE :query");
            builder.append(" OR LOWER(u.lastName) LIKE :query");
            builder.append(" OR LOWER(u.phone) LIKE :query");
            builder.append(" OR LOWER(u.email) LIKE :query");
            params.put("query", "%" + query.toLowerCase() + "%");
        }
        TypedQuery<Client> q = em.createQuery("SELECT u FROM Client u " + builder.toString() + " ORDER BY u.id DESC", Client.class);
        Query cq = em.createQuery("SELECT COUNT(u.id) FROM Client u " + builder.toString());
        if (!(limit == -1 && offset == -1)) {
            q.setFirstResult(offset);
            q.setMaxResults(limit);
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
            cq.setParameter(entry.getKey(), entry.getValue());
        }
        List<Client> resultList = q.getResultList();
        ListResult<Client> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) cq.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }

    public ListResult<Client> find(Long id, String firstName, String lastName, String email, String phone, Long schoolId, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (id != null) {
            builder.append(" AND u.id = :id");
            params.put("id", id);
        }
        if (firstName != null) {
            builder.append(" AND LOWER(u.firstName) LIKE LOWER(:firstName)");
            params.put("firstName", "%" + firstName + "%");
        }
        if (lastName != null) {
            builder.append(" AND LOWER(u.lastName) LIKE LOWER(:lastName)");
            params.put("lastName", "%" + lastName + "%");
        }
        if (email != null) {
            builder.append(" AND LOWER(u.email) LIKE LOWER(:email)");
            params.put("email", "%" + email + "%");
        }
        if (phone != null) {
            builder.append(" AND LOWER(u.phone) LIKE LOWER(:phone)");
            params.put("phone", "%" + phone + "%");
        }
        if (schoolId != null) {
            builder.append(" AND u.school.id = :schoolId");
            params.put("schoolId", schoolId);
        }
        TypedQuery<Client> q = em.createQuery("SELECT u FROM Client u WHERE 1=1 " + builder.toString() + " ORDER BY u.id DESC", Client.class);
        Query cq = em.createQuery("SELECT COUNT(u.id) FROM Client u WHERE 1=1 " + builder.toString());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
            cq.setParameter(entry.getKey(), entry.getValue());
        }
        if (!(limit == -1 && offset == -1)) {
            q.setFirstResult(offset);
            q.setMaxResults(limit);
        }
        List<Client> resultList = q.getResultList();
        ListResult<Client> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) cq.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }
}
