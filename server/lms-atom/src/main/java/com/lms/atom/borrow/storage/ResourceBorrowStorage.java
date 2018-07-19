package com.lms.atom.borrow.storage;

import com.lms.atom.borrow.storage.model.ResourceBorrow;
import com.lms.atom.material.storage.model.MaterialType;
import com.lms.common.dto.atom.resource.ResourceBorrowDTO;
import com.lms.common.dto.response.ListResult;
import com.lms.utils.DateUtils;
import com.lms.utils.MathUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.*;

@Repository
public class ResourceBorrowStorage {

    @PersistenceContext
    private EntityManager em;

    public ListResult<ResourceBorrow> getClientResourceBorrow(Long clientId, boolean current, int limit, int offset) {
        TypedQuery<ResourceBorrow> query;
        Query count;
        if (current) {
            query = em.createQuery("SELECT r FROM ResourceBorrow r " +
                    "WHERE r.clientId = :clientId AND r.returnTime IS NULL ORDER BY r.borrowTime DESC", ResourceBorrow.class);
            count = em.createQuery("SELECT COUNT(r.id) FROM ResourceBorrow r " +
                    "WHERE r.clientId = :clientId AND r.returnTime IS NULL");
        } else {
            query = em.createQuery("SELECT r FROM ResourceBorrow r " +
                    "WHERE r.clientId = :clientId AND r.returnTime IS NOT NULL ORDER BY r.borrowTime DESC", ResourceBorrow.class);
            count = em.createQuery("SELECT COUNT(r.id) FROM ResourceBorrow r " +
                    "WHERE r.clientId = :clientId AND r.returnTime IS NOT NULL ");
        }
        if (!(limit == -1 && offset == -1)) {
            query.setFirstResult(offset);
            query.setMaxResults(limit);
        }
        query.setParameter("clientId", clientId);
        count.setParameter("clientId", clientId);
        List<ResourceBorrow> resultList = query.getResultList();
        ListResult<ResourceBorrow> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) count.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }

    public ListResult<ResourceBorrow> getResourceCopyHistory(String identifier, int limit, int offset) {
        TypedQuery<ResourceBorrow> query = em.createQuery("SELECT r FROM ResourceBorrow r " +
                "WHERE r.identifier = :identifier ORDER BY r.borrowTime DESC", ResourceBorrow.class);
        Query count = em.createQuery("SELECT COUNT(r.id) FROM ResourceBorrow r " +
                "WHERE r.identifier = :identifier");
        if (!(limit == -1 && offset == -1)) {
            query.setFirstResult(offset);
            query.setMaxResults(limit);
        }
        query.setParameter("identifier", identifier);
        count.setParameter("identifier", identifier);
        List<ResourceBorrow> resultList = query.getResultList();
        ListResult<ResourceBorrow> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) count.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }

    public ResourceBorrow get(String bookId, Long clientId) throws Exception{
        try {
            return em.createQuery("SELECT r FROM ResourceBorrow r WHERE " +
                    "r.resourceCopy.identifier = :bookId " +
                    "AND r.clientId = :clientId " +
                    "AND r.returnTime IS NULL ORDER BY r.borrowTime DESC", ResourceBorrow.class)
                    .setParameter("bookId", bookId)
                    .setParameter("clientId", clientId).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<ResourceBorrow> getLateResourceBorrows() {
        try {
            List<ResourceBorrow> result = new ArrayList<>();
            List<ResourceBorrow> list = em.createQuery("SELECT r FROM ResourceBorrow r WHERE " +
                    "r.returnTime IS NULL ORDER BY r.borrowTime DESC", ResourceBorrow.class).getResultList();
            for (ResourceBorrow resourceBorrow : list) {
                if (resourceBorrow.getScheduledReturnTime().before(new Date()))
                    result.add(resourceBorrow);
            }
            return result;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<ResourceBorrow> getTwoDayLeftResourceBorrows() {
        try {
            List<ResourceBorrow> result = new ArrayList<>();
            List<ResourceBorrow> list = em.createQuery("SELECT r FROM ResourceBorrow r WHERE " +
                    "r.returnTime IS NULL ORDER BY r.borrowTime DESC", ResourceBorrow.class).getResultList();
            for (ResourceBorrow resourceBorrow : list) {
                if (DateUtils.truncateTime(DateUtils.shiftDay(resourceBorrow.getScheduledReturnTime(), -2))
                        .before(new Date()))
                    result.add(resourceBorrow);
            }
            return result;
        } catch (NoResultException e) {
            return null;
        }
    }

    public ListResult<ResourceBorrow> find(String query, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (query != null && query.length() >= 3) {
            builder.append(" WHERE LOWER(u.resourceCopy.identifier) LIKE :query");
            builder.append(" OR LOWER(u.resourceCopy.resource.name) LIKE :query");
            params.put("query", "%" + query.toLowerCase() + "%");
        }
        TypedQuery<ResourceBorrow> q = em.createQuery("SELECT u FROM ResourceBorrow u " + builder.toString() + " ORDER BY u.id DESC", ResourceBorrow.class);
        Query cq = em.createQuery("SELECT COUNT(u.id) FROM ResourceBorrow u " + builder.toString());
        if (!(limit == -1 && offset == -1)) {
            q.setFirstResult(offset);
            q.setMaxResults(limit);
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
            cq.setParameter(entry.getKey(), entry.getValue());
        }
        List<ResourceBorrow> resultList = q.getResultList();
        ListResult<ResourceBorrow> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) cq.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }

    public ListResult<ResourceBorrow> find(String identifier, String isbn, String clientId,
                                           Date fromBorrowTime, Date toBorrowTime,
                                           Date fromReturnTime, Date toReturnTime,
                                           Date fromScheduledTime, Date toScheduledTime,
                                           Boolean critical, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (fromBorrowTime != null) {
            builder.append(" AND u.borrowTime >= :fromBorrowTime");
            params.put("fromBorrowTime", fromBorrowTime);
        }
        if (toBorrowTime != null) {
            builder.append(" AND u.borrowTime <= :toBorrowTime");
            params.put("toBorrowTime", toBorrowTime);
        }
        if (fromReturnTime != null) {
            builder.append(" AND u.returnTime >= :fromReturnTime");
            params.put("fromReturnTime", fromReturnTime);
        }
        if (toReturnTime != null) {
            builder.append(" AND u.returnTime <= :toReturnTime");
            params.put("toReturnTime", toReturnTime);
        }
        if (fromScheduledTime != null) {
            builder.append(" AND u.scheduledReturnTime >= :fromScheduledTime");
            params.put("fromScheduledTime", fromScheduledTime);
        }
        if (toScheduledTime != null) {
            builder.append(" AND u.scheduledReturnTime <= :toScheduledTime");
            params.put("toScheduledTime", toScheduledTime);
        }
        if (identifier != null) {
            builder.append(" AND u.resourceCopy.identifier = :identifier");
            params.put("identifier", identifier);
        }
        if (isbn != null) {
            builder.append(" AND u.resourceCopy.resource.isbn = :isbn");
            params.put("isbn", isbn);
        }
        if (clientId != null) {
            builder.append(" AND u.clientId = :clientId");
            params.put("clientId", clientId);
        }
        if (critical != null) {
            builder.append(" AND u.critical = :critical");
            params.put("critical", critical);
        }
        TypedQuery<ResourceBorrow> q = em.createQuery("SELECT u FROM ResourceBorrow u WHERE 1=1 " + builder.toString() + " ORDER BY u.id DESC", ResourceBorrow.class);
        Query cq = em.createQuery("SELECT COUNT(u.id) FROM ResourceBorrow u WHERE 1=1 " + builder.toString());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
            cq.setParameter(entry.getKey(), entry.getValue());
        }
        if (!(limit == -1 && offset == -1)) {
            q.setFirstResult(offset);
            q.setMaxResults(limit);
        }
        List<ResourceBorrow> resultList = q.getResultList();
        ListResult<ResourceBorrow> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) cq.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }
}
