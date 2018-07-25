package com.lms.common.dto.cleintapi;

import com.lms.common.dto.atom.category.CategoryDTO;

import java.io.Serializable;
import java.util.Date;

public class LightResource implements Serializable {

    private Long id;
    private String name;
    private String author;
    private String subName;
    private String edition;
    private String publisher;
    private String editionYear;
    private String imageUrl;
    private String resourceUrl;
    private CategoryDTO category;
    private boolean clientFavorite;
    private boolean clientCritical;
    private int quantity;

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

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public boolean getClientFavorite() {
        return clientFavorite;
    }

    public void setClientFavorite(boolean clientFavorite) {
        this.clientFavorite = clientFavorite;
    }

    public boolean getClientCritical() {
        return clientCritical;
    }

    public void setClientCritical(boolean clientCritical) {
        this.clientCritical = clientCritical;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
