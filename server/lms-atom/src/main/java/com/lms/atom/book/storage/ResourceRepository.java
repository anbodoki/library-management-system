package com.lms.atom.book.storage;

import com.lms.atom.book.storage.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    Resource getByIdentifier(String identifier);
    Resource getByIsbn(String isbn);
    Resource getByUdc(String udc);
}
