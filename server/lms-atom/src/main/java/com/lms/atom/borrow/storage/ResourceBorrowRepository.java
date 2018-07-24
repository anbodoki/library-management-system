package com.lms.atom.borrow.storage;

import com.lms.atom.borrow.storage.model.ResourceBorrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceBorrowRepository extends JpaRepository<ResourceBorrow, Long> {
    long countByReturnTimeIsNull();
    long countByCriticalIsTrue();
}
