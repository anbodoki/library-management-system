package com.lms.atom.notofication.service;

import com.lms.common.dto.atom.notification.NotificationDTO;
import com.lms.common.dto.response.ListResult;

public interface NotificationService {

    void create(String message, Long clientId, Long resourceId);

    ListResult<NotificationDTO> getNotificationsForClient(int limit, int offset);

    void markAsSeen();

    void markAsRead(Long notificationId);

    NotificationDTO getNotificationForResource(Long resourceId);
}
