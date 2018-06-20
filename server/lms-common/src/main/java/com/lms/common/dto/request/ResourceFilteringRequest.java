package com.lms.common.dto.request;

import com.lms.common.dto.atom.resource.ResourceTypeDTO;
import com.lms.common.dto.response.PagingRequest;

import java.util.Date;

public class ResourceFilteringRequest extends PagingRequest {

    private Long id;
    private String name;
    private String author;
    private String subName;
    private String edition;
    private String publisher;
    private Date fromEditionDate;
    private Date toEditionDate;
    private String language;
    private String isbn;
    private String udc;
    private String identifier;
    private ResourceTypeDTO resourceType;
    private String materialTypeCode;
    private Date fromCreationDate;
    private Date toCreationDate;
    private Date fromModificationDate;
    private Date toModificationDate;
    private String categoryCode;

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

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUdc() {
        return udc;
    }

    public void setUdc(String udc) {
        this.udc = udc;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public ResourceTypeDTO getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceTypeDTO resourceType) {
        this.resourceType = resourceType;
    }

    public String getMaterialTypeCode() {
        return materialTypeCode;
    }

    public void setMaterialTypeCode(String materialTypeCode) {
        this.materialTypeCode = materialTypeCode;
    }

    public Date getFromCreationDate() {
        return fromCreationDate;
    }

    public void setFromCreationDate(Date fromCreationDate) {
        this.fromCreationDate = fromCreationDate;
    }

    public Date getToCreationDate() {
        return toCreationDate;
    }

    public void setToCreationDate(Date toCreationDate) {
        this.toCreationDate = toCreationDate;
    }

    public Date getFromModificationDate() {
        return fromModificationDate;
    }

    public void setFromModificationDate(Date fromModificationDate) {
        this.fromModificationDate = fromModificationDate;
    }

    public Date getToModificationDate() {
        return toModificationDate;
    }

    public void setToModificationDate(Date toModificationDate) {
        this.toModificationDate = toModificationDate;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
