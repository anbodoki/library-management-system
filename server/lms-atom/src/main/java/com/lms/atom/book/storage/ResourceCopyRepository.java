package com.lms.atom.book.storage;

import com.lms.atom.book.storage.model.Resource;
import com.lms.atom.book.storage.model.ResourceCopy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceCopyRepository extends JpaRepository<ResourceCopy, Long> {

    ResourceCopy findByIdentifier(String identifier);
}
