package com.lms.atom.notofication.storage;

import com.lms.atom.notofication.storage.model.Notification;
import com.lms.common.dto.response.ListResult;
import com.lms.utils.MathUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class NotificationStorage {

    @PersistenceContext
    private EntityManager em;

    public ListResult<Notification> getNotificationsForClient(Long clientId, int limit, int offset) {
        TypedQuery<Notification> q = em.createQuery("SELECT n FROM Notification n WHERE " +
                "n.clientId = :clientId ORDER BY n.creationDate DESC", Notification.class)
                .setParameter("clientId", clientId);
        Query cq = em.createQuery("SELECT COUNT(n.id) FROM Notification n WHERE " +
                "n.clientId = :clientId ")
                .setParameter("clientId", clientId);
        if (!(limit == -1 && offset == -1)) {
            q.setFirstResult(offset);
            q.setMaxResults(limit);
        }
        List<Notification> resultList = q.getResultList();
        ListResult<Notification> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) cq.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }

    public Long getUnseenNotificationsCount(Long clientId) {
        Query cq = em.createQuery("SELECT COUNT(n.id) FROM Notification n WHERE " +
                "n.clientId = :clientId " +
                "AND n.seen = :seen ")
                .setParameter("clientId", clientId)
                .setParameter("seen", false);
        return (Long) cq.getSingleResult();
    }

    public void markAsSeen(Long clientId) {
        em.createQuery("UPDATE Notification n SET n.seen = :seen " +
                "WHERE n.clientId = :clientId " +
                "AND n.seen = :notSeen ")
                .setParameter("clientId", clientId)
                .setParameter("seen", true)
                .setParameter("notSeen", false).executeUpdate();
    }

    public Notification getNotificationForResource(Long clientId, Long resourceId) {
        try {
            TypedQuery<Notification> q = em.createQuery("SELECT n FROM Notification n " +
                    "WHERE n.resourceId = :resourceId " +
                    "AND n.clientId = :clientId " +
                    "AND n.read = :read ORDER BY n.creationDate DESC", Notification.class)
                    .setParameter("resourceId", resourceId)
                    .setParameter("clientId", clientId)
                    .setParameter("read", false);
            q.setFirstResult(0);
            q.setMaxResults(1);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
