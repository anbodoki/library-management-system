package com.lms.atom.category.storage;

import com.lms.atom.category.storage.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getByCode(String code);
}
