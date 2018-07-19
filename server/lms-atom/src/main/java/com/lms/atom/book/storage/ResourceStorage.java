package com.lms.atom.book.storage;

import com.lms.atom.book.storage.model.Resource;
import com.lms.atom.book.storage.model.ResourceType;
import com.lms.common.dto.response.ListResult;
import com.lms.utils.MathUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ResourceStorage {

    @PersistenceContext
    private EntityManager em;

    public ListResult<Resource> find(String query, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (query != null && query.length() >= 3) {
            builder.append(" WHERE LOWER(u.name) LIKE :query");
            builder.append(" OR LOWER(u.author) LIKE :query");
            builder.append(" OR LOWER(u.subName) LIKE :query");
            builder.append(" OR LOWER(u.isbn) LIKE :query");
            builder.append(" OR LOWER(u.udc) LIKE :query");
            params.put("query", "%" + query.toLowerCase() + "%");
        }
        TypedQuery<Resource> q = em.createQuery("SELECT u FROM Resource u " + builder.toString() + " ORDER BY u.id DESC", Resource.class);
        Query cq = em.createQuery("SELECT COUNT(u.id) FROM Resource u " + builder.toString());
        if (!(limit == -1 && offset == -1)) {
            q.setFirstResult(offset);
            q.setMaxResults(limit);
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
            cq.setParameter(entry.getKey(), entry.getValue());
        }
        List<Resource> resultList = q.getResultList();
        ListResult<Resource> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) cq.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }

    public ListResult<Resource> find(Long id,
                                     String name,
                                     String author,
                                     String subName,
                                     String edition,
                                     String publisher,
                                     Date fromEditionDate, Date toEditionDate,
                                     Long language,
                                     String isbn,
                                     String udc,
                                     ResourceType resourceType,
                                     Long materialTypeCode,
                                     Date fromCreationDate, Date toCreationDate,
                                     Date fromModificationDate, Date toModificationDate,
                                     Long categoryCode,
                                     String issn,
                                     String place,
                                     int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (id != null) {
            builder.append(" AND u.id = :id");
            params.put("id", id);
        }
        if (name != null) {
            builder.append(" AND LOWER(u.name) LIKE LOWER(:name)");
            params.put("name", "%" + name + "%");
        }
        if (author != null) {
            builder.append(" AND LOWER(u.author) LIKE LOWER(:author)");
            params.put("author", "%" + author + "%");
        }
        if (subName != null) {
            builder.append(" AND LOWER(u.subName) LIKE LOWER(:subName)");
            params.put("description", "%" + subName + "%");
        }
        if (edition != null) {
            builder.append(" AND u.edition = :edition");
            params.put("edition", edition);
        }
        if (publisher != null) {
            builder.append(" AND u.publisher = :publisher");
            params.put("publisher", publisher);
        }
        if (fromEditionDate != null) {
            builder.append(" AND u.editionDate >= :fromEditionDate");
            params.put("fromEditionDate", fromEditionDate);
        }
        if (toCreationDate != null) {
            builder.append(" AND u.editionDate <= :toEditionDate");
            params.put("toEditionDate", toEditionDate);
        }
        if (language != null) {
            builder.append(" AND u.language.id = :language");
            params.put("language", language);
        }
        if (isbn != null) {
            builder.append(" AND u.isbn = :isbn");
            params.put("isbn", isbn);
        }
        if (udc != null) {
            builder.append(" AND u.udc = :udc");
            params.put("udc", udc);
        }
        if (resourceType != null) {
            builder.append(" AND u.resourceType = :resourceType");
            params.put("resourceType", resourceType);
        }
        if (materialTypeCode != null) {
            builder.append(" AND u.materialType.id = :materialTypeCode");
            params.put("materialTypeCode", materialTypeCode);
        }
        if (fromCreationDate != null) {
            builder.append(" AND u.creationDate >= :fromCreationDate");
            params.put("fromCreationDate", fromCreationDate);
        }
        if (toCreationDate != null) {
            builder.append(" AND u.creationDate <= :toCreationDate");
            params.put("toCreationDate", toCreationDate);
        }
        if (fromModificationDate != null) {
            builder.append(" AND u.modificationDate >= :fromModificationDate");
            params.put("fromModificationDate", fromModificationDate);
        }
        if (toModificationDate != null) {
            builder.append(" AND u.modificationDate <= :toModificationDate");
            params.put("toModificationDate", toModificationDate);
        }
        if (categoryCode != null) {
            builder.append(" AND u.category.id = :categoryCode");
            params.put("categoryCode", categoryCode);
        }
        if (issn != null) {
            builder.append(" AND u.issn = :issn");
            params.put("issn", issn);
        }
        if (place != null) {
            builder.append(" AND u.place = :place");
            params.put("place", place);
        }
        TypedQuery<Resource> q = em.createQuery("SELECT u FROM Resource u WHERE 1=1 " + builder.toString() + " ORDER BY u.id DESC", Resource.class);
        Query cq = em.createQuery("SELECT COUNT(u.id) FROM Resource u WHERE 1=1 " + builder.toString());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
            cq.setParameter(entry.getKey(), entry.getValue());
        }
        if (!(limit == -1 && offset == -1)) {
            q.setFirstResult(offset);
            q.setMaxResults(limit);
        }
        List<Resource> resultList = q.getResultList();
        ListResult<Resource> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) cq.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }

    public ListResult<Resource> findSpecial(Long id,
                                     String name,
                                     String author,
                                     String subName,
                                     String edition,
                                     String publisher,
                                     Date fromEditionDate, Date toEditionDate,
                                     Long language,
                                     String isbn,
                                     String udc,
                                     ResourceType resourceType,
                                     Long materialTypeCode,
                                     Date fromCreationDate, Date toCreationDate,
                                     Date fromModificationDate, Date toModificationDate,
                                     List<Long> categoryIds,
                                     String issn,
                                     String place,
                                     int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (id != null) {
            builder.append(" AND u.id = :id");
            params.put("id", id);
        }
        if (name != null) {
            builder.append(" AND LOWER(u.name) LIKE LOWER(:name)");
            params.put("name", "%" + name + "%");
        }
        if (author != null) {
            builder.append(" AND LOWER(u.author) LIKE LOWER(:author)");
            params.put("author", "%" + author + "%");
        }
        if (subName != null) {
            builder.append(" AND LOWER(u.subName) LIKE LOWER(:subName)");
            params.put("description", "%" + subName + "%");
        }
        if (edition != null) {
            builder.append(" AND u.edition = :edition");
            params.put("edition", edition);
        }
        if (publisher != null) {
            builder.append(" AND u.publisher = :publisher");
            params.put("publisher", publisher);
        }
        if (fromEditionDate != null) {
            builder.append(" AND u.fromEditionDate >= :fromEditionDate");
            params.put("fromEditionDate", fromEditionDate);
        }
        if (toCreationDate != null) {
            builder.append(" AND u.toEditionDate <= :toEditionDate");
            params.put("toEditionDate", toEditionDate);
        }
        if (language != null) {
            builder.append(" AND u.language.id = :language");
            params.put("language", language);
        }
        if (isbn != null) {
            builder.append(" AND u.isbn = :isbn");
            params.put("isbn", isbn);
        }
        if (udc != null) {
            builder.append(" AND u.udc = :udc");
            params.put("udc", udc);
        }
        if (resourceType != null) {
            builder.append(" AND u.resourceType = :resourceType");
            params.put("resourceType", resourceType);
        }
        if (materialTypeCode != null) {
            builder.append(" AND u.materialType.id = :materialTypeCode");
            params.put("materialTypeCode", materialTypeCode);
        }
        if (fromCreationDate != null) {
            builder.append(" AND u.fromCreationDate >= :fromCreationDate");
            params.put("fromCreationDate", fromCreationDate);
        }
        if (toCreationDate != null) {
            builder.append(" AND u.toCreationDate <= :toCreationDate");
            params.put("toCreationDate", toCreationDate);
        }
        if (fromModificationDate != null) {
            builder.append(" AND u.fromModificationDate >= :fromModificationDate");
            params.put("fromModificationDate", fromModificationDate);
        }
        if (toModificationDate != null) {
            builder.append(" AND u.toModificationDate <= :toModificationDate");
            params.put("toModificationDate", toModificationDate);
        }
        if (categoryIds != null && !categoryIds.isEmpty()) {
            builder.append(" AND u.category.id IN :categoryIds");
            params.put("categoryIds", categoryIds);
        }
        if (issn != null) {
            builder.append(" AND u.issn = :issn");
            params.put("issn", issn);
        }
        if (place != null) {
            builder.append(" AND u.place = :place");
            params.put("place", place);
        }
        TypedQuery<Resource> q = em.createQuery("SELECT u FROM Resource u WHERE 1=1 " + builder.toString() + " ORDER BY u.id DESC", Resource.class);
        Query cq = em.createQuery("SELECT COUNT(u.id) FROM Resource u WHERE 1=1 " + builder.toString());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            q.setParameter(entry.getKey(), entry.getValue());
            cq.setParameter(entry.getKey(), entry.getValue());
        }
        if (!(limit == -1 && offset == -1)) {
            q.setFirstResult(offset);
            q.setMaxResults(limit);
        }
        List<Resource> resultList = q.getResultList();
        ListResult<Resource> result = new ListResult<>();
        result.setResultList(resultList);
        result.setPage(offset / limit);
        result.setOffset(offset);
        result.setLimit(limit);
        result.setCount((Long) cq.getSingleResult());
        result.setPageNum(MathUtils.calculatePageNum(result.getCount(), result.getLimit()));
        return result;
    }
}
