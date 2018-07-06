package com.lms.atom.book.storage;

import com.lms.atom.book.storage.model.ResourceCopy;
import com.lms.common.dto.atom.resource.ResourceCopyDTO;

import java.util.ArrayList;
import java.util.List;

public class ResourceCopyHelper {

    public static ResourceCopy toEntity(ResourceCopyDTO copy) {
        if (copy == null) {
            return null;
        }
        ResourceCopy result = new ResourceCopy();
        result.setId(copy.getId());
        result.setResource(ResourceHelper.toEntity(copy.getResource()));
        result.setIdentifier(copy.getIdentifier());
        result.setName(copy.getName());
        result.setAvailability(copy.getAvailability());
        return result;
    }

    public static ResourceCopyDTO fromEntity(ResourceCopy copy) {
        if (copy == null) {
            return null;
        }
        ResourceCopyDTO result = new ResourceCopyDTO();
        result.setId(copy.getId());
        result.setResource(ResourceHelper.fromEntity(copy.getResource()));
        result.setIdentifier(copy.getIdentifier());
        result.setName(copy.getName());
        result.setAvailability(copy.getAvailability());
        return result;
    }

    public static List<ResourceCopy> toEntities(List<ResourceCopyDTO> copies) {
        if (copies == null) {
            return null;
        }
        List<ResourceCopy> result = new ArrayList<>();
        for (ResourceCopyDTO copy : copies) {
            result.add(toEntity(copy));
        }
        return result;
    }

    public static List<ResourceCopyDTO> fromEntities(List<ResourceCopy> copies) {
        if (copies == null) {
            return null;
        }
        List<ResourceCopyDTO> result = new ArrayList<>();
        for (ResourceCopy copy : copies) {
            result.add(fromEntity(copy));
        }
        return result;
    }
}
