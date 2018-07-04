package com.lms.common.dto.cleintapi;

import com.lms.common.dto.atom.resource.ResourceTypeDTO;
import com.lms.common.dto.response.PagingRequest;

import java.util.Date;

public class ClientResourceFilteringRequest extends PagingRequest {

    private Long id;
    private String name;
    private String author;
    private String publisher;
    private Date fromEditionDate;
    private Date toEditionDate;
    private ResourceTypeDTO resourceType;
    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getFromEditionDate() {
        return fromEditionDate;
    }

    public void setFromEditionDate(Date fromEditionDate) {
        this.fromEditionDate = fromEditionDate;
    }

    public Date getToEditionDate() {
        return toEditionDate;
    }

    public void setToEditionDate(Date toEditionDate) {
        this.toEditionDate = toEditionDate;
    }

    public ResourceTypeDTO getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceTypeDTO resourceType) {
        this.resourceType = resourceType;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
