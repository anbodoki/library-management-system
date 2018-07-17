package com.lms.atom.borrow.storage;

import com.lms.atom.borrow.storage.model.ResourceBorrow;
import com.lms.common.dto.atom.resource.ResourceBorrowDTO;
import com.lms.common.dto.response.ListResult;
import com.lms.utils.DateUtils;
import com.lms.utils.MathUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
