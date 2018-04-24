package com.lms.atom.material.storage;

import com.lms.atom.material.storage.model.MaterialType;
import com.lms.common.dto.atom.materialtype.MaterialTypeDTO;

import java.util.ArrayList;
import java.util.List;

public class MaterialTypeHelper {

    public static MaterialTypeDTO fromEntity(MaterialType materialType) {
        if (materialType == null) {
            return null;
        }
        MaterialTypeDTO result = new MaterialTypeDTO();
        result.setId(materialType.getId());
        result.setCode(materialType.getCode());
        result.setName(materialType.getName());
        result.setDescription(materialType.getDescription());
        return result;
    }

    public static MaterialType toEntity(MaterialTypeDTO materialType) {
        if (materialType == null) {
            return null;
        }
        MaterialType result = new MaterialType();
        result.setId(materialType.getId());
        result.setCode(materialType.getCode());
        result.setName(materialType.getName());
        result.setDescription(materialType.getDescription());
        return result;
    }

    public static List<MaterialTypeDTO> fromEntities(List<MaterialType> materialTypes) {
        if (materialTypes == null) {
            return null;
        }
        List<MaterialTypeDTO> result = new ArrayList<>();
        for (MaterialType materialType : materialTypes) {
            result.add(fromEntity(materialType));
        }
        return result;
    }

    public static List<MaterialType> toEntities(List<MaterialTypeDTO> materialTypes) {
        if (materialTypes == null) {
            return null;
        }
        List<MaterialType> result = new ArrayList<>();
        for (MaterialTypeDTO materialType : materialTypes) {
            result.add(toEntity(materialType));
        }
        return result;
    }
}
