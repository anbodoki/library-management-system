package com.lms.atom.material.storage;

import com.lms.atom.material.storage.model.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialTypeRepository extends JpaRepository<MaterialType, Long> {
}
