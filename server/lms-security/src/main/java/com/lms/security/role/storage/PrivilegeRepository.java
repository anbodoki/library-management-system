package com.lms.security.role.storage;

import com.lms.security.role.storage.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, String> {

}
