package com.lms.client.school.storage;

import com.lms.client.school.storage.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
