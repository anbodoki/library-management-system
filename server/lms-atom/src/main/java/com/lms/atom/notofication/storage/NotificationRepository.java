package com.lms.atom.notofication.storage;

import com.lms.atom.notofication.storage.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
