package com.lms.atom.category.storage;

import com.lms.atom.category.storage.model.Category;
import com.lms.common.dto.atom.category.CategoryDTO;

import java.util.ArrayList;
import java.util.List;

public class CategoryHelper {

    public static CategoryDTO fromEntity(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDTO result = new CategoryDTO();
        result.setId(category.getId());
        result.setCode(category.getCode());
        result.setName(category.getName());
        result.setDescription(category.getDescription());
        result.setColor(category.getColor());
        result.setSpecial(category.getSpecial());
        return result;
    }

    public static Category toEntity(CategoryDTO category) {
        if (category == null) {
            return null;
        }
        Category result = new Category();
        result.setId(category.getId());
        result.setCode(category.getCode());
        result.setName(category.getName());
        result.setDescription(category.getDescription());
        result.setColor(category.getColor());
        result.setSpecial(category.getSpecial());
        return result;
    }

    public static List<CategoryDTO> fromEntities(List<Category> categories) {
        if (categories == null) {
            return null;
        }
        List<CategoryDTO> result = new ArrayList<>();
        for (Category category : categories) {
            result.add(fromEntity(category));
        }
        return result;
    }

    public static List<Category> toEntities(List<CategoryDTO> categories) {
        if (categories == null) {
            return null;
        }
        List<Category> result = new ArrayList<>();
        for (CategoryDTO category : categories) {
            result.add(toEntity(category));
        }
        return result;
    }
}
