package com.lms.common.dto.atom.resource;

import com.lms.common.dto.atom.category.CategoryDTO;
import com.lms.common.dto.atom.language.LanguageDTO;
import com.lms.common.dto.atom.materialtype.MaterialTypeDTO;
import com.lms.common.dto.atom.notification.NotificationDTO;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class ResourceDTO implements Serializable {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String author;
    @NotNull
    private String subName;
    private String edition;
    @NotNull
    private String publisher;
    private String editionYear;
    @NotNull
    private LanguageDTO language;
    private Integer pageNum;
    @NotNull
    private String isbn;
    @NotNull
    private String udc;
    @NotNull
    private ResourceTypeDTO resourceType;
    @NotNull
    private MaterialTypeDTO materialType;
    private String referenceURL;
    private Date creationDate;
    private Date modificationDate;
    private CategoryDTO category;
    private int quantity;
    private int rentedQuantity;
    private String issn;
    private String place;
    private String imageUrl;
    private String resourceUrl;
    private boolean clientFavorite;
    private NotificationDTO notification;

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

    public String getEditionYear() {
        return editionYear;
    }

    public void setEditionYear(String editionYear) {
        this.editionYear = editionYear;
    }

    public LanguageDTO getLanguage() {
        return language;
    }

    public void setLanguage(LanguageDTO language) {
        this.language = language;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
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

    public ResourceTypeDTO getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceTypeDTO resourceType) {
        this.resourceType = resourceType;
    }

    public MaterialTypeDTO getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialTypeDTO materialType) {
        this.materialType = materialType;
    }

    public String getReferenceURL() {
        return referenceURL;
    }

    public void setReferenceURL(String referenceURL) {
        this.referenceURL = referenceURL;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRentedQuantity() {
        return rentedQuantity;
    }

    public void setRentedQuantity(int rentedQuantity) {
        this.rentedQuantity = rentedQuantity;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public boolean getClientFavorite() {
        return clientFavorite;
    }

    public void setClientFavorite(boolean clientFavorite) {
        this.clientFavorite = clientFavorite;
    }

    public NotificationDTO getNotification() {
        return notification;
    }

    public void setNotification(NotificationDTO notification) {
        this.notification = notification;
    }
}
