package com.lms.common.dto.cleintapi.helper;

import com.lms.common.dto.atom.resource.ResourceDTO;
import com.lms.common.dto.cleintapi.LightResource;

import java.util.ArrayList;
import java.util.List;

public class LightResourceHelper {

    public static List<LightResource> toLights(List<ResourceDTO> resources) {
        if (resources == null) {
            return null;
        }
        List<LightResource> result = new ArrayList<>();
        for (ResourceDTO resource : resources) {
            result.add(toLight(resource));
        }
        return result;
    }

    public static LightResource toLight(ResourceDTO resource) {
        if (resource == null) {
            return null;
        }
        LightResource result = new LightResource();
        result.setId(resource.getId());
        result.setName(resource.getName());
        result.setAuthor(resource.getAuthor());
        result.setSubName(resource.getSubName());
        result.setEdition(resource.getEdition());
        result.setPublisher(resource.getPublisher());
        result.setImageUrl(resource.getImageUrl());
        result.setResourceUrl(resource.getResourceUrl());
        result.setEditionYear(resource.getEditionYear());
        result.setCategory(resource.getCategory());
        return result;
    }
}
