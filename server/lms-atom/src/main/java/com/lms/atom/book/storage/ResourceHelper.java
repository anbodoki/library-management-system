package com.lms.atom.book.storage;

import com.lms.atom.book.storage.model.Resource;
import com.lms.atom.book.storage.model.ResourceType;
import com.lms.atom.language.storage.LanguageHelper;
import com.lms.atom.material.storage.MaterialTypeHelper;
import com.lms.common.dto.atom.resource.ResourceDTO;
import com.lms.common.dto.atom.resource.ResourceTypeDTO;

import java.util.ArrayList;
import java.util.List;

public class ResourceHelper {

    public static ResourceDTO fromEntity(Resource resource) {
        if (resource == null) {
            return null;
        }
        ResourceDTO result = new ResourceDTO();
        result.setId(resource.getId());
        result.setName(resource.getName());
        result.setAuthor(resource.getAuthor());
        result.setSubName(resource.getSubName());
        result.setEdition(resource.getEdition());
        result.setPublisher(resource.getPublisher());
        result.setEditionDate(resource.getEditionDate());
        result.setLanguage(LanguageHelper.fromEntity(resource.getLanguage()));
        result.setPageNum(resource.getPageNum());
        result.setIsbn(resource.getIsbn());
        result.setUdc(resource.getUdc());
        result.setIdentifier(resource.getIdentifier());
        result.setResourceType(ResourceTypeDTO.valueOf(resource.getResourceType().name()));
        result.setMaterialType(MaterialTypeHelper.fromEntity(resource.getMaterialType()));
        result.setReferenceURL(resource.getReferenceURL());
        result.setCreationDate(resource.getCreationDate());
        result.setModificationDate(resource.getModificationDate());
        return result;
    }

    public static Resource toEntity(ResourceDTO resource) {
        if (resource == null) {
            return null;
        }
        Resource result = new Resource();
        result.setId(resource.getId());
        result.setName(resource.getName());
        result.setAuthor(resource.getAuthor());
        result.setSubName(resource.getSubName());
        result.setEdition(resource.getEdition());
        result.setPublisher(resource.getPublisher());
        result.setEditionDate(resource.getEditionDate());
        result.setLanguage(LanguageHelper.toEntity(resource.getLanguage()));
        result.setPageNum(resource.getPageNum());
        result.setIsbn(resource.getIsbn());
        result.setUdc(resource.getUdc());
        result.setIdentifier(resource.getIdentifier());
        result.setResourceType(ResourceType.valueOf(resource.getResourceType().name()));
        result.setMaterialType(MaterialTypeHelper.toEntity(resource.getMaterialType()));
        result.setReferenceURL(resource.getReferenceURL());
        result.setCreationDate(resource.getCreationDate());
        result.setModificationDate(resource.getModificationDate());
        return result;
    }

    public static List<ResourceDTO> fromEntities(List<Resource> resources) {
        if (resources == null) {
            return null;
        }
        List<ResourceDTO> result = new ArrayList<>();
        for (Resource resource : resources) {
            result.add(fromEntity(resource));
        }
        return result;
    }

    public static List<Resource> toEntities(List<ResourceDTO> resources) {
        if (resources == null) {
            return null;
        }
        List<Resource> result = new ArrayList<>();
        for (ResourceDTO resource : resources) {
            result.add(toEntity(resource));
        }
        return result;
    }
}
