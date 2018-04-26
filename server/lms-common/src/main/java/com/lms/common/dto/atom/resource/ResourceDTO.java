package com.lms.common.dto.atom.resource;

import com.lms.common.dto.atom.language.LanguageDTO;
import com.lms.common.dto.atom.materialtype.MaterialTypeDTO;

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
    private Integer edition;
    @NotNull
    private String publisher;
    @NotNull
    private Date editionDate;
    @NotNull
    private LanguageDTO language;
    private Integer pageNum;
    @NotNull
    private String isbn;
    @NotNull
    private String udc;
    @NotNull
    private String identifier; //რესურსის შიფრი ???
    @NotNull
    private ResourceTypeDTO resourceType;
    @NotNull
    private MaterialTypeDTO materialType;
    private String referenceURL;
    private Date creationDate;
    private Date modificationDate;

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

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getEditionDate() {
        return editionDate;
    }

    public void setEditionDate(Date editionDate) {
        this.editionDate = editionDate;
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
}
