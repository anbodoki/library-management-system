package com.lms.atom.book.storage;

import com.lms.atom.book.storage.model.ResourceCopy;
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
public class ResourceCopyStorage {

    @PersistenceContext
    private EntityManager em;

    public ListResult<ResourceCopy> getResourcesCopies(long resourceId, String query, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (query != null && query.length() >= 3) {
            builder.append(" AND (LOWER(u.name) LIKE :query");
            builder.append(" OR LOWER(u.identifier) LIKE :query)");
            params.put("query", "%" + query.toLowerCase() + "%");
        }
        TypedQuery<ResourceCopy> q = em.createQuery("SELECT u FROM ResourceCopy u WHERE u.resource.id = :rr " + builder.toString() + " ORDER BY u.id DESC", ResourceCopy.class);
        Query cq = em.createQuery("SELECT COUNT(u.id) FROM ResourceCopy u WHERE u.resource.id = :rr " + builder.toString());
        if (!(limit == -1 && offset == -1)) {
            q.setFirstResult(offset);
            q.setMaxResults(limit);
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
            cq.setParameter(entry.getKey(), entry.getValue());
        }
        q.setParameter("rr", resourceId);
        cq.setParameter("rr", resourceId);
        List<ResourceCopy> resultList = q.getResultList();
        ListResult<ResourceCopy> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) cq.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }
}
